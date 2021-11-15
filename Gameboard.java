/**
 *		Name and ID: Matthew Gee, 263 370 31
 *		Comp 249
 *		Assignment #: 1
 *		Due Date: September 21, 2018	
 */

import java.util.Scanner;
import java.util.Random;

public class Gameboard {
	static int[][] gameboard = new int[8][8];
	static Scanner userInput = new Scanner(System.in);
	static Random rand = new Random();
	static private String coordinate;
	static private int xCoordinate;
	static private int yCoordinate;
	
		/**
		 * Prompts user for coordinate of ship, calls checkValidPlacement() to determine if the coordinate is valid, if valid, will place a ship in the gameboard array.
		 * User is permitted to enter the coordinates as any combination of number or characters as long as they are within A-H and 0-9 (so a1=aa=11=1a or h8=hh=88=8h)
		 */
		public static void placeShips() {
			for (int i =1; i<7; i++) {
				boolean proceed;
				do{
					proceed = true;
					System.out.print("Please Enter the coordinates of Ship #" + i + ": ");
					coordinate = userInput.nextLine();
					yCoordinate = convertCharToInt(coordinate.charAt(0));
					xCoordinate = convertCharToInt(coordinate.charAt(1));
					proceed = checkValidPlacement(xCoordinate, yCoordinate);
				} while (proceed == false);
				gameboard[xCoordinate][yCoordinate] = 1; // a 1 indicates a human ship
			}
		}
	
		/**
		 * Prompts user for coordinate of grenade, calls checkValidPlacement() to determine if the coordinate is valid, if valid, will place a grenade in the gameboard array.
		 * User is permitted to enter the coordinates as any combination of number or characters as long as they are within A-H and 0-9
		 */
		public static void placeGrenades() {
			for (int i =1; i<5; i++) {
				boolean proceed;
				do{
					proceed = true;
					System.out.print("Please Enter the coordinates of Grenade #" + i + ": ");
					coordinate = userInput.nextLine();
					yCoordinate = convertCharToInt(coordinate.charAt(0));
					xCoordinate = convertCharToInt(coordinate.charAt(1));
					proceed = checkValidPlacement(xCoordinate, yCoordinate);
				} while (proceed == false);
				gameboard[xCoordinate][yCoordinate] = 2; // a 2 indicates a human grenade
			}
			Gameboard.printFullGameboard();
		}
		
		/**
		 * Generates random coordinate for ship placement, calls checkValidPlacement() to determine if the coordinate is valid, if valid will place a ship in the gameboard array.
		 */
		public static void placeComputerShips() {
			for (int i = 1; i < 7; i++) { 
				boolean proceed;
				do {
					proceed = true;
					xCoordinate = rand.nextInt(8)-1;
					yCoordinate = rand.nextInt(8)-1;
					proceed = checkValidComputerPlacement(xCoordinate, yCoordinate);
				} while (proceed == false);
				gameboard[xCoordinate][yCoordinate] = 3; // a 3 indicates a computer ship
			}
		}
	
	
		/**
		 * Generates random coordinate for grenade placement, calls checkValidPlacement() to determine if the coordinate is valid, if valid will place a grenade in the gameboard
		 */
		public static void placeComputerGrenades() {
			for (int i = 1; i < 5; i++) {
				boolean proceed;
				do {
					proceed = true;
					xCoordinate = rand.nextInt(8);
					yCoordinate = rand.nextInt(8);
					proceed = checkValidComputerPlacement(xCoordinate, yCoordinate);
				} while (proceed == false);
				gameboard[xCoordinate][yCoordinate] = 4; // a 4 indicates a computer grenade
			}
			Gameboard.printFullGameboard();
		}
		
		/**
		 * This verifies that the coordinates entered by the user are within the bounds of the gameboard.
		 * 
		 * @param row the x-coordinate of the user's strike
		 * @param col the y-coordinate of the user's strike.
		 * @return False if out of bounds or the coordinate is already in use, returns True otherwise.
		 */
		public static boolean checkValidPlacement(int row, int col) {
			if ((row) > 7 || row < 0) {
				System.out.println("Sorry, that coordinate is out of bounds, please pick again.");
				return false;
			}
			else if ((col) > 7 || col < 0) {
				System.out.println("Sorry, that coordinate is out of bounds, please pick again.");
				return false;
			}
			else if (gameboard[row][col] == 1 || gameboard[row][col] == 2 || gameboard[row][col] == 3) {
				System.out.println("Sorry, that coordinate is already in use, please pick again.");
				return false;
			}
			else
				return true;
		}

		/**
		 * This verifies that the random coordinate from the computer's shot is within the bounds of the gameboard. 
		 * Different from checkValidPlacement() because there are no need for error messages when the computer is generating random coordinates.
		 * 
		 * @param row the x-coordinate of the user's strike
		 * @param col the y-coordinate of the user's strike.
		 * @return False if out of bounds or the coordinate is already in use, returns True otherwise.
		 */
		public static boolean checkValidComputerPlacement(int row, int col) {
			if ((row) > 7 || row < 0) {
				return false;
			}
			else if ((col) > 7 || col < 0) {
				return false;
			}
			else if (gameboard[row][col] == 1 || gameboard[row][col] == 2 || gameboard[row][col] == 3) {
				return false;
			}
			else
				return true;
		}
	
		/**
		 * Prints the gameboard. Since the gameboard is an int[] array, it prints different things based on the value of the element.
		 * This method is called after every non-skipped turn.
		 */
		public static void printGameboard() {
			System.out.println("");
			for (int r = 0; r < 8; r ++) {
				System.out.print("\t");
				for (int c = 0; c < 8; c ++) {
					if (gameboard[r][c] == 0)
						System.out.print(" _ "); // location not yet hit.
					if (gameboard[r][c] == 1)
						System.out.print(" _ "); // location not yet hit but contains human ship.
					if (gameboard[r][c] == 2)
						System.out.print(" _ "); // location not yet hit but contains human grenade.
					if (gameboard[r][c] == 3)
						System.out.print(" _ "); // location not yet hit but contains computer ship.
					if (gameboard[r][c] == 4)
						System.out.print(" _ "); // location not yet hit but contains computer grenade
					if (gameboard[r][c] == 5)
						System.out.print(" * "); // location hit but does not contain anything
					if (gameboard[r][c] == 6)
						System.out.print(" s "); // user's ship, was hit during gameplay
					if (gameboard[r][c] == 7)
						System.out.print(" g "); // user's grenade, was hit during gameplay
					if (gameboard[r][c] == 8)
						System.out.print(" S "); // computer's ship, was hit during gameplay
					if (gameboard[r][c] == 9)
						System.out.print(" G "); // computer's grenade, was hit during gameplay
				}
				System.out.println();
			}	
		}
		
		/**
		 * Prints the full gameboard - including ships and grenades that were not hit during the game. 
		 * Since the gameboard is an int[] array, it prints different things based on the value of the element.
		 * This method is only called at the completion of the game.
		 */
		public static void printFullGameboard() {
			System.out.println("");
			for (int r = 0; r < 8; r ++) {
				System.out.print("\t");
				for (int c = 0; c < 8; c ++) {
					if (gameboard[r][c] == 0)
						System.out.print(" _ "); // location not hit during gameplay
					if (gameboard[r][c] == 1)
						System.out.print(" s "); // user's ship, never hit
					if (gameboard[r][c] == 2)
						System.out.print(" g "); // user's grenade, never hit
					if (gameboard[r][c] == 3)
						System.out.print(" S "); // computer's ship, never hit
					if (gameboard[r][c] == 4)
						System.out.print(" G "); // computer's grenade, never hit
					if (gameboard[r][c] == 5)
						System.out.print(" _ "); // location hit but did not contain anything.
					if (gameboard[r][c] == 6)
						System.out.print(" s "); // user's ship, was hit during gameplay
					if (gameboard[r][c] == 7)
						System.out.print(" g "); // user's ship, was hit during gameplay
					if (gameboard[r][c] == 8)
						System.out.print(" S "); // computer's ship, was hit during gameplay
					if (gameboard[r][c] == 9)
						System.out.print(" G "); // computer's grenade, was hit during gameplay
				}
				System.out.println();
			}			
		}
		
		/**
		 * This converts the characters used in the strike into integers so that it can be mapped to the gameboard.
		 * This also permits the user to input '11' instead of 'A1' (and etc) if they wish to target [0][0]
		 * 
		 * @param value this character will be converted into the corresponding int used for mapping to the gameboard array.
		 * @return returns the index of the letter or number corresponding to the map (A becomes 0, B becomes 1, 1 becomes 0, 2 becomes 1, etc)
		 */
		public static int convertCharToInt(char value) {
			switch (value) {
				case('1'):
				case('A'): 
				case('a'): {
					return 0;	
				}	
				case('2'):
				case('B'): 
				case('b'): {
					return 1;	
				}	
				case('3'):
				case('C'): 
				case('c'): {
					return 2;	
				}	
				case('4'):
				case('D'): 
				case('d'): {
					return 3;	
				}	
				case('5'):
				case('E'): 
				case('e'): {
					return 4;	
				}	
				case('6'):
				case('F'): 
				case('f'): {
					return 5;	
				}	
				case('7'):
				case('G'): 
				case('g'): {
					return 6;	
				}	
				case('8'):
				case('H'): 
				case('h'): {
					return 7;	
				}	
				default: //
					return 9;
			}
		}

		/**
		 * Accessor method for coordinate string.
		 * @return returns xy coordinate string
		 */
		public static String getCoordinate() {
			return coordinate;
		}

		/**
		 * Mutator method for coordinate string.
		 * @param coordinate takes xy coordinate as string.
		 */
		public static void setCoordinate(String coordinate) {
			Gameboard.coordinate = coordinate;
		}

		/** 
		 * Accessor method for x-coordinate.
		 * @return x-coordinate index.
		 */
		public static int getxCoordinate() {
			return xCoordinate;
		}

		/** Mutator method for x-coordinate
		 * @param xCoordinate takes x-coordinate index.
		 */
		public static void setxCoordinate(int xCoordinate) {
			Gameboard.xCoordinate = xCoordinate;
		}

		/**
		 * Accessor method for y-coordinate.
		 * @return y-coordinate index.
		 */
		public static int getyCoordinate() {
			return yCoordinate;
		}

		/** Mutator method for y-coordinate
		 * @param yCoordinate takes y-coordinate index.
		 */
		public static void setyCoordinate(int yCoordinate) {
			Gameboard.yCoordinate = yCoordinate;
		}
		

	
}
