import java.util.Arrays;

//this class takes a word and prints all possible permutations of the word and the number of permutations
public class Permutator {
	int[] cities;
	int[] bestPath;
	int minCost = Integer.MAX_VALUE;
	int[][] matrix;
	
	public Permutator (int[] cities, int[][] matrix) {
		this.cities = cities;	//creates an array of chars for the cities from input String
		this.matrix = matrix;
	}//end of constructor
	
	//prints permutations and count
	public void perm(int p) {
		if(p == cities.length) {		//base case, if pointer is at the end of the array
			int cost = getCost();
			if(cost < minCost) {
				minCost = cost;
				System.out.println(Arrays.toString(cities));
				System.out.println(minCost);
				bestPath = cities.clone();
			}
		} else {	//recursive case
			for(int i = p; i < cities.length; i++) {		//iterates through the array to swap characters
				swap(i, p);
				perm(p +1);		//recursive call
				swap(i, p);
			}
		}
	}//end of perm method
	
	//swaps two characters to form a permutation
	public void swap(int i, int p) {
		int temp = cities[i];
		cities[i] = cities[p];
		cities[p] = temp;
	}//end of swap method
	
	//returns total cost of travel between cities
	public int getCost() {
		int total = 0;
		int i = 0;
		while(i < cities.length - 1) {
			total += matrix[cities[i]][cities[i+1]];
			i++;
		}
		if(i != 0) {	//if we have moved away from first city
			total += matrix[cities[i]][cities[0]];		//add cost of going back to start
		}
		return total;
	}
	
	public int getMinCost() {
		return minCost;
	}
	
	public String getBestPath() {
		String route = "";
		for(int city : bestPath) {
			route += city + ", ";
		}
		route += cities[0];
		return route;
	}
}//end of class
