// Name: Dunxuan Li (Annie)
// USC NetID: 6625999096
// CS 455 PA1
// Spring 2019

import java.util.Scanner;


// Test the CoinTossSimulator class
public class Tester2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter number of trials (>= 1): ");
		int input = in.nextInt();
		
		if(input > 0 ) {
			int numTrials = input;
			// Call the CoinTossSimulator to simulate the process 
			CoinTossSimulator coinToss= new CoinTossSimulator();
			
			//Test case 1
			System.out.println("After constructor: ");
			System.out.println("Number of trials [exp: 0]: " + coinToss.getNumTrials() );
			System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
			System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
			System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
			System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails() == coinToss.getNumTrials()));
			
			//Test case 2
			coinToss.run(1);
			System.out.println();
			System.out.println("After run(10) ");
			System.out.println("Number of trials [exp: 1]: " + coinToss.getNumTrials() );
			System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
			System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
			System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
			System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails() == coinToss.getNumTrials()));
			
			//Test case 3
			coinToss.run(10);
			System.out.println();
			System.out.println("After run(10) ");
			System.out.println("Number of trials [exp: 11]: " + coinToss.getNumTrials() );
			System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
			System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
			System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
			System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails() == coinToss.getNumTrials()));
			
			//Test case 4
			coinToss.run(100);
			System.out.println();
			System.out.println("After run(111) ");
			System.out.println("Number of trials [exp: 1]: " + coinToss.getNumTrials() );
			System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
			System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
			System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
			System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails() == coinToss.getNumTrials()));
			
			//Test case 5 -- After Reset
			coinToss.reset();
			System.out.println();
			System.out.println("After reset: ");
			System.out.println("Number of trials [exp: 1]: " + coinToss.getNumTrials() );
			System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
			System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
			System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
			System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails() == coinToss.getNumTrials()));
			
			//Test case 6
			coinToss.run(1000);
			System.out.println();
			System.out.println("After run(1000): ");
			System.out.println("Number of trials [exp: 1]: " + coinToss.getNumTrials() );
			System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
			System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
			System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
			System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails() == coinToss.getNumTrials()));
			
			//Test case 7
			coinToss.run(1);
			System.out.println();
			System.out.println("After run(1) ");
			System.out.println("Number of trials [exp: 1001]: " + coinToss.getNumTrials() );
			System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
			System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
			System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());
			System.out.println("Tosses add up correctly? " + (coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails() == coinToss.getNumTrials()));
		}
		else {
			System.out.println("ERROR: Number entered must be greater than 0.");
		}
		
		
		
	
		

	}

}
