# Java VRP Problem Solving Code using Google OR-Tools

This is a Java implementation of solving Vehicle Routing Problems (VRP) using Google OR-Tools.

# Introduction
Vehicle Routing Problem (VRP) is a well-known optimization problem in operations research. It involves a fleet of vehicles that must visit a set of customers and deliver goods or services. The objective is to minimize the total distance traveled by the vehicles while meeting the demand of all customers.

Google OR-Tools is an open-source software suite for optimization developed by Google. It includes a variety of optimization tools, including solvers for linear programming, mixed-integer programming, and constraint programming, as well as algorithms for graph and network optimization.

# Tested Using
- OpenJDK 11
- Maven 3.8.7

# Usage

1. Clone the repository:

```bash
git clone https://github.com/Binara-Sachin/java-vrp-solver.git
```
2. Build the project:

```bash
cd java-vrp-solver
mvn clean package
```

3. Run the project:
   java -jar target/java-vrp-solver-1.0.jar
```bash
java -cp target/java-vrp-solver-1.0.jar org.bs.vrp.Solver
```

---
# References
[1] https://developers.google.com/optimization/routing/vrp#java