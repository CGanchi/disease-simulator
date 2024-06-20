import java.util.Scanner;
		public class Proj12 { //Challenge #2
			public static void main(String[] args) {
				// asking user for inputs
				Scanner in = new Scanner(System.in);
				System.out.println("What percentage of the population should be vaccinated? ");
				double vax = in.nextDouble()/100.0;
				System.out.println("What size grid? ");
				int size = in.nextInt();
				System.out.println("How many days should be simulated? ");
				int days = in.nextInt();
				System.out.println("How many times would you like to run the simulation? ");
				int times = in.nextInt();
		
				double sum = 0.0; // variable that will hold the sum of all percentages from each attempt (used in average calculation)
	for(int p = 0; p < times; p++) { // repeats the simulation the amount of times the user wants
		int num = 0;  // these next lines make sure to reset all variables before starting new simulation
		boolean[][] vaccinated = new boolean[size][size];
		boolean[][] infected = new boolean[size][size];
		
		for(int i = 0; i < vaccinated.length; i++) {
			for(int j = 0; j < vaccinated[0].length; j++) {
				if(Math.random() < vax) {
					vaccinated[i][j] = true;
				}
			}
		}
		
		int rand = (int) Math.random()*size;
		int rand2 = (int) Math.random()*size;
		
		infected[rand][rand2] = true;
		 for(int g = 0; g < days; g++) {	// runs the simulation for the amount of days the user input
				for(int i = 0; i < size; i++){
					for(int j = 0; j < size; j++) {
					boolean[][] arr = copyArr(infected); // makes sure to look through the original 2D array through another variable 
					// and alter the present 2D array based on the original
				if(arr[i][j] != true) {
					if(vaccinated[i][j] == true) { // if it is looking at a vaccinated person, then the probabilities change
						if((neighbors(arr, i, j) == 1 && Math.random() < 0.025)
							|| (neighbors(arr, i, j) == 2 && Math.random() < 0.075)
							|| (neighbors(arr, i, j) >= 3 && Math.random() < 0.1) ) {
						infected[i][j] = true;
					
				}
					}else { // if not vaccinated then the probabilities are as follows:
						if((neighbors(arr, i, j) == 1 && Math.random() < 0.75)
								|| (neighbors(arr, i, j) == 2 && Math.random() < 0.85)
								|| (neighbors(arr, i, j) >= 3 && Math.random() < 0.95) ) {
							infected[i][j] = true;
					}
					}
				}
					}
						
				}
				
			}
	
				
				for(int i = 0; i < size; i++){  // counts the amount of infected people in the 2D array (infected)
					for(int j = 0; j < size; j++) {
						if(infected[i][j] == true) {
							num += 1;
						}
					}
				
			}
				double avg = 100 * ((1.0*num)/(size*size)); //calculates the percent of infected people related to all people
				sum += (1.0 * avg); //adds the percentage to this variable so I can later calculate 
				// the average of the percentages calculated
				
				}
			double totavg = Double.parseDouble(String.format("%.2f", (1.0 * sum)/times)); // rounds the percentage to 
			// 2 decimal points (where I found how to do this is in my reflection)
			System.out.println("After " + days + " days, "+ totavg + "% of the population was infected on average"); //prints info to user
				
			}
			public static boolean[][] copyArr(boolean[][] toCopy){
				boolean[][] newArr = new boolean[toCopy.length][toCopy[0].length];
				for (int i = 0; i < toCopy.length; i++) {
					for (int j = 0; j < toCopy.length; j++) {
						newArr[i][j] = toCopy[i][j];
					}
				}
				return newArr;
			}
			public static void printArr(boolean[][] toPrint) {
				for (boolean[] row: toPrint) {
					for (boolean val: row) {
						System.out.print( val ? 1 : 0);
						System.out.print(" ");
						
					}
					System.out.println();
				}
			}
			public static void printArr(int[][] toPrint) {
				for (int[] row: toPrint) {
					for (int val: row) {
						System.out.print(val);
						System.out.print(" ");
						
					}
					System.out.println();
				}
			}
			public static int neighbors(boolean[][] arr, int row, int col){ // function I wrote that calculates the amount of 
				// infected neighbors a person has in a 2D array and returns that value.
					int counts = 0;
					int rowlength = arr.length;
					int collength = arr[0].length;
					
					if((row+1 < rowlength && arr[row+1][col] == true)
							|| (col + 1 < collength && arr[row][col + 1] == true)
							|| (row-1 >= 0 && arr[row-1][col] == true)
							|| (col-1 >= 0 && arr[row][col-1] == true)
							|| (row + 1 < rowlength && col - 1 >= 0 && arr[row+1][col-1] == true)
							|| (row + 1 < rowlength && col + 1 < collength && arr[row+1][col+1] == true)
							|| (row - 1 >= 0 && col - 1 >= 0 && arr[row-1][col-1] == true)
							|| (row - 1 >= 0 && col + 1 < collength && arr[row-1][col+1] == true)) {
						counts += 1;
					}
					return counts;
				}
	
			}
			
		

