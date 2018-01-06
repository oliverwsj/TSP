# Traveling Salesperson Problem (TSP)

Solves instances of the TSP.  My solution, written in Java, implements an optimized branch and bound technique for faster selection of the shortest route.

Wikipedia describes the TSP as follows:
"The travelling salesman problem (TSP) asks the following question: "Given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city exactly once and returns to the origin city?" It is an NP-hard problem in combinatorial optimization, important in operations research and theoretical computer science."

My solution implements a branch and bound technique, which shortens the time spent investigating costly routes. Additionally, I built on the branch and bound technique to increase the speed by which the program finds the fastest route by implementing a prediction of the cost of the route, eliminating investigation of costly routes in advance. If the cheapest remaining route at any stage of the route is more expensive than the currently cheapest route, investigation into the route is terminated. 

To test this program, a cost matrix from a text file is needed. Example files are inclduded in the project. The file contains an integer indicating how many cities (N). Fore example, the file name tsp_26 would lead to a 26x26 matrix representing 26 cities. There are then N^2 integers indicating the matrix row by row.  There are zeroes on the diagonal.  Program finds the least cost path and prints it out along with how long the calculation took. The required input is the file path of the desired matrix without quotation marks(!), and output is handled in a GUI.
  
