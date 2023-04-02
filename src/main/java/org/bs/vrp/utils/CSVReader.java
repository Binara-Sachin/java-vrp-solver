package org.bs.vrp.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static double[][] readCSV(String fileName) {
        double[][] outputArray = {};

        try {
            List<double[]> readArray = new ArrayList<>();

            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL resource = classloader.getResource(fileName);

            assert resource != null;
            File csvFile = new File(resource.toURI());
            CSVParser parser = CSVParser.parse(csvFile, Charset.defaultCharset(), CSVFormat.DEFAULT);

            List<CSVRecord> records = parser.getRecords();

            for (CSVRecord record : records) {
                String column1 = record.get(0);
                String column2 = record.get(1);
                // process the row here
                System.out.println(column1 + "," + column2);

                readArray.add(new double[]{Double.parseDouble(column1), Double.parseDouble(column2)});
            }
            
            parser.close();

            outputArray = new double[readArray.size()][2];

            for (int i = 0; i < readArray.size(); i++) {
                outputArray[i] = readArray.get(i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return outputArray;
    }
}
