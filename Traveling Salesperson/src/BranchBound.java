import java.util.Arrays;

//computes the fastest route through cities, using the branch and bound technique
public class BranchBound {
	int[] cities;
	int[] bestPath;
	int minCost = Integer.MAX_VALUE;
	int[][] matrix;
	
	public BranchBound (int[] cities, int[][] matrix) {
		this.cities = cities;	//creates an array of chars for the cities from input String
		this.matrix = matrix;
		this.bestPath = new int[cities.length];
	}//end of constructor

	public void perm(int p) {	//p is the pointer of where to begin permuting on array of cities
		if(p == cities.length) {		//base case, if pointer is at the end of the cities array, has finished a permutation
			int cost = getCost(p);		//cost of completed path
			if(cost < minCost) {		//if cost is less than minCost
				minCost = cost;			//update minCost
				for(int i = 0; i < cities.length; i++) {
					int j = cities[i];
					bestPath[i] = j;
				}
			}
		} else {	//recursive case, if permutation is not complete
			int costEstimate = getCostEstimate(p);
			if(costEstimate < minCost) {	//if branch may contain best path
				for(int i = p; i < cities.length; i++) {		//iterates through the array to swap characters
					swap(i, p);		//swap
					perm(p+1);		//recursive call
					swap(i, p);		//swap back
				}
			}
		}
	}//end of perm method
	
	//estimates cost of path
	public int getCostEstimate(int p) {
		int total = getCost(p);		//cost of path already taken
		for(int i = p - 1; i < cities.length; i++) {		//for each city that can still be subject to permutation (for each not decided on path yet)
			if(total >= minCost) {	//if total is already greater than minCost
				i = (cities.length + 1);	//exit loop
			} else {
				total += findMinBend(cities[i], p);
			}
		}
		return total;
	}
	
	//find minimum path from a given city
	public int findMinBend(int cityFrom, int p) {	//city is city from which to find min path, p is index on list of possible remaining destinations
		int min = Integer.MAX_VALUE;		//set min first to max value, to ensure that we will find a min
		for(int cityTo = p; cityTo < cities.length; cityTo++) {		//iterates through possible destinations, starting at p
			int cost = matrix[cityFrom][cities[cityTo]];
			if((cost != 0) && (cost < min)) {		//if we are going somewhere new and the cost is less than min
				min = cost;							//then update min
			}
		}
		int toStart = matrix[cityFrom][0];	//cost to start
		if(cityFrom != 0 && toStart < min) {	//if cost from city to start is less than current min and we are not already at start
			min = toStart;
		}
		return min;
	}
	
	//swaps two characters to form a permutation
	public void swap(int i, int p) {
		int temp = cities[i];
		cities[i] = cities[p];
		cities[p] = temp;
	}//end of swap method
	
	//returns total cost of travel between cities
	public int getCost(int p) {
		int total = 0;
		int i = 0;
		//while((i < p) && i < cities.length - 1) {		//while not all cities have been reached
		while(i < p-1) {
			total += matrix[cities[i]][cities[i+1]];	//add cost of trip from current city to next city to total
			i++;	//move to next city
		}
		if(i != 0 && (p == (cities.length))) {	//if we have moved away from first city, and have reached the last city
			total += matrix[cities[i]][cities[0]];		//add cost of going back to start
		}
		return total;
	}
	
	//returns the stored minimum cost value
	public int getMinCost() {
		return minCost;
	}
	
	//returns the array of cities that had the lowest cost
	public String getBestPath() {
		String route = "";
		for(int city : bestPath) {
			route += city + ", ";
		}
		route += bestPath	[0];
		return route;
	}
}//end of class
