import java.util.Scanner;

//takes number typed by user to determine length of randomly generated tsp matrix
public class Random {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int size = console.nextInt();
		for(int i = 0; i < size; i++) {
			System.out.println();
			for(int j = 0; j < size; j++) {
				if(i == j) {
					System.out.print("0 ");
				} else {
					System.out.print((int) ((Math.random() * 100) + 1) + " ");
				}
			}
		}
	}
}
