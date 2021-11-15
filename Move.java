/**
 *		Name and ID: Matthew Gee, 263 370 31
 *		Comp 249
 *		Assignment #: 1
 *		Due Date: September 21, 2018	
 */

import java.util.Scanner;
import java.util.Random;

public class Move {
	private int row;
	private int column;
	static String coordinate;
	static private int xCoordinate;
	static private int yCoordinate;
	static Scanner userInput = new Scanner(System.in);
	static Random rand = new Random();

	static Player human = new Player();
	static Player computer = new Player();
	
		/**
		 * Default constructor for Move();
		 */
		public Move() {
			setRow(0);
			setColumn(0);
		}
		
		/**
		 * Constructor for Move used in player and computer actions.
		 * @param row the x-coordinate of the strike
		 * @param col the y-coordinate of the strike
		 */
		public Move(int row, int col) {
			setRow(row);
			setColumn(col);
		}
	
		/**
		 * A greeting message
		 */
		public static void introduction() {
			System.out.println("Hello! Let's play Battleship!");
			System.out.println("");
		}
		
		/**
		 * A message used to indicate the beginning of the 'striking' portion of the game.
		 */
		public static void startPlaying() {
			System.out.println("");
			System.out.println("OK, the computer placed its ships and grenades at random. Let’s play!");
		}
		
		/**
		 * This loop will run indefinitely until the game completes.
		 * If a player's turnSkip counter is greater than 0, they will be informed and their turn will be passed over and their turnSkip counter will be reverted to 0.
		 * It calls humanMove or computerMove to allow player actions.
		 * Calls printGameboard after each action to display the current state of the gameboard.
		 * After each move, it checks to see whether the player's or computer's score reaches 6. If the conditions are satisfied, the game will terminate.
		 */
		public static void playRotation() { 
			do {
				if (human.getTurnSkip() == 0) {
					humanMove();
					Gameboard.printGameboard();
					checkWin();
					}
				else {
					human.undoTurnSkip();
					System.out.println("");
					System.out.println("You miss this turn due to hitting a grenade");
				}
				
				if (computer.getTurnSkip() == 0) {
					computerMove();
					Gameboard.printGameboard();
					checkWin();
				}
				else {
					computer.undoTurnSkip();
					System.out.println("");
					System.out.println("The computer misses this turn due to hitting a grenade");
				}
				
			} while (true);
		}
		
		/**
		 * Prompts user for coordinates of their desired strike. Calls checkValidMove to determine if strike is valid. If valid, the strike is applied to the gameboard.
		 * User is permitted to enter the coordinates as any combination of number or characters as long as they are within A-H and 0-9
		 */
		public static void humanMove() {
			System.out.println("");
			Move humanMove;
			printScore();
			do {
				
				System.out.print("Please enter the coordinate of your shot: ");
				coordinate = userInput.nextLine();
				yCoordinate = convertCharToInt(coordinate.charAt(0));
				xCoordinate = convertCharToInt(coordinate.charAt(1));
				humanMove = new Move(convertCharToInt(coordinate.charAt(1)),convertCharToInt(coordinate.charAt(0)));
			} while (checkValidMove(humanMove) == false);
			humanHitSuccess(humanMove);
		}
		
		/**
		 * Generates random coordinates of the computer's strike. Calls checkValidMove to determine if strike is valid. If valid, the strike is applied to the gameboard.
		 */
		public static void computerMove() {
			System.out.println("");
			printScore();
			String xString;
			Move computerMove;
			do {
				xCoordinate = rand.nextInt(8);
				yCoordinate = rand.nextInt(8);
				xString = convertRowToString(xCoordinate);
				computerMove = new Move(yCoordinate, xCoordinate); 
			} while (checkValidMove(computerMove) == false);
			computerHitSuccess(computerMove,xString);
		}
		
		public static void printScore() {
			System.out.println("Human Score: " + human.getScore());
			System.out.println("Computer Score: " + computer.getScore());
		}
	
	
		/**
		 * Verifies that the user's move is within the bounds of the gameboard.
		 * 
		 * @param move obtained from humanMove method, contains the x and y coordinates of the strike.
		 * @return returns False if outside of the gameboard, True if valid. Does not consider a previously struck coordinate as invalid.
		 */
		public static boolean checkValidMove(Move move) {
			if ((move.getRow())> 7 || move.getRow() < 0) {
				System.out.println("This is out of bounds! Select again.");
				return false;
			}
			else if ((move.getColumn()) > 7 || move.getColumn() <0) {
				System.out.println("This is out of bounds! Select again.");
				return false;
			}
			else
				return true;
		}
	
		/**
		 * Informs the user of the success of their strike.
		 * Calls the setScore method if they strike a battleship and will increase the user's or computer's score depending on ownership of the battleship.
		 * Calls the setTurnSkip method if they strike a grenade belonging to the user or computer.
		 * Whichever coordinate is struck will be increased by 5 on the gameboard array - the gameboard is composed of integers and prints various things based on the value of the element.
		 * See the printGameboard method to see which numbers correspond to the various values of each element.
		 * 
		 * @param move contains the XandY coordinates of the strike.
		 */
		public static void humanHitSuccess(Move move) {
			if (Gameboard.gameboard[move.getRow()][move.getColumn()] == 0) {
				System.out.println("You missed!");
				Gameboard.gameboard[move.getRow()][move.getColumn()] += 5;
			}
			else if (Gameboard.gameboard[move.getRow()][move.getColumn()] == 1) {
				System.out.println("You sunk your own battleship!");
				Gameboard.gameboard[move.getRow()][move.getColumn()] += 5;
				computer.setScore();
			}
			else if (Gameboard.gameboard[move.getRow()][move.getColumn()] == 2) {
				System.out.println("You hit your own grenade! You miss your next turn.");
				Gameboard.gameboard[move.getRow()][move.getColumn()] += 5;
				human.setTurnSkip();
			}
			else if (Gameboard.gameboard[move.getRow()][move.getColumn()] == 3) {
				System.out.println("You sunk your opponent's battleship!");
				Gameboard.gameboard[move.getRow()][move.getColumn()] += 5;
				human.setScore();
			}
			else if (Gameboard.gameboard[move.getRow()][move.getColumn()] == 4) {
				System.out.println("You hit your opponent's grendade! You miss your next turn.");
				Gameboard.gameboard[move.getRow()][move.getColumn()] += 5;
				human.setTurnSkip();
			}
			else if (Gameboard.gameboard[move.getRow()][move.getColumn()] > 4) {
				System.out.println("Pay attention! That coordinate has already been hit!");
			}
		}
	
		/**
		 * Informs the user of the computer's actions and the success  of their strike.
		 * Calls the setScore method if they strike a battleship and will increase the user's or computer's score depending on ownership of the battleship.
		 * Calls the setTurnSkip method if they strike a grenade belonging to the user or computer.
		 * Whichever coordinate is struck will be increased by 5 on the gameboard array - the gamebaord is composed of integers and prints various things based on the value of the element.
		 * See the printGameboard method to see which numbers correspond to the various values of each element.
		 * 
		 * @param move the XandY-coordinates of the computer's strike
		 * @param xString the letter corresponding to the x-coordinate of the strike since the computers strike originates as integers.
		 */
		public static void computerHitSuccess(Move move, String xString) {
			if (Gameboard.gameboard[move.getRow()][move.getColumn()] == 0) {
				System.out.println("Computer shot " + xString + "" + (move.getRow()+1) + " and missed!");
				Gameboard.gameboard[move.getRow()][move.getColumn()] += 5;
			}
			else if (Gameboard.gameboard[move.getRow()][move.getColumn()] == 1) {
				System.out.println("Computer shot " + xString + "" + (move.getRow()+1) + " and sunk your battleship!");
				Gameboard.gameboard[move.getRow()][move.getColumn()] += 5;
				computer.setScore();
			}
			else if (Gameboard.gameboard[move.getRow()][move.getColumn()] == 2) {
				System.out.println("Computer shot " + xString + "" + (move.getRow()+1) + " and hit your grenade! They miss their next turn.");
				Gameboard.gameboard[move.getRow()][move.getColumn()] += 5;
				computer.setTurnSkip();
			}
			else if (Gameboard.gameboard[move.getRow()][move.getColumn()] == 3) {
				System.out.println("Computer shot " + xString + "" + (move.getRow()+1) + " and hit his own battleship!");
				Gameboard.gameboard[move.getRow()][move.getColumn()] += 5;
				human.setScore();
			}
			else if (Gameboard.gameboard[move.getRow()][move.getColumn()] == 4) {
				System.out.println("Computer shot " + xString + "" + (move.getRow()+1) + " and hit his own grenade! They miss their next turn.");
				Gameboard.gameboard[move.getRow()][move.getColumn()] += 5;
				computer.setTurnSkip();
			}
			else if (Gameboard.gameboard[move.getRow()][move.getColumn()] > 4) {
				System.out.println("The computer wasn't paying attention and shot at a previously hit coordinate, " + xString + "" + (move.getRow()+1) + "!");
			}
		}
	
		/**
		 * This converts the characters used in the strike into integers so that it can be mapped to the gameboard.
		 * This also permits the user to input '11' instead of 'A1' (and etc) if they wish to target [0][0].
		 * 
		 * @param value the x or y coordinate to be converted to the index for the gameboard.
		 * @return returns the index of the row or column corresponding to the input.
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
		 * This is just used to determine the row corresponding to the x-value of the computer's randomly generated strike. It's used for printing so that the player may more easily
		 * follow which coordinate the computer has attacked.
		 * @param row takes the x-Coordinae of the computer's strike
		 * @return returns the corresponding letter value of the x-coordinate
		 */
		public static String convertRowToString(int row) {
			switch (row) {
				case 0 :{
					return "A";
				}	
				case 1 :{
					return "B";	
				}	
				case 2 :{
					return "C";	
				}	
				case 3 :{
					return "D";	
				}	
				case 4 :{
					return "E";	
				}	
				case 5 :{
					return "F";	
				}	
				case 6:{
					return "G";	
				}	
				case 7:{
					return "H";	
				}
				default:
					return "";
			}
		}
	
		/**
		 * Used in the play rotation after each strike to determine if the user or computer has reached a score of 6. 
		 * If either player reaches 6 points, the full gameboard will be printed and the program will terminate.
		 */
		public static void checkWin() {
			if (human.getScore() >= 6) {
				System.out.println("\nYou sunk all of your opponent's battleships! You won ^_^ \n\nThanks for playing!");
				Gameboard.printFullGameboard();
				System.exit(0);
			}
			if (computer.getScore() >= 6) {
				System.out.println("\nYour opponent sunk all of your battleships! You lost -___- \n\nThanks for playing!");
				Gameboard.printFullGameboard();
				System.exit(0);
			}
		}
		
		/**
		 * Accessor method for y-coordinate
		 * @return returns y-coordinate.
		 */
		public int getRow() {
			return row;
		}
	
		/**
		 * Mutator method for y-coordinate
		 * @param row Changes value of move.row to value indicated.
		 */
		public void setRow(int row) {
			this.row = row;
		}
	
		/**
		 * Accessor method for x-coordinate.
		 * @return returns y-coordinate.
		 */
		public int getColumn() {
			return column;
		}
	
		/**
		 * Mutator method for x-coordinate.
		 * @param column Changes value of move.column to value indicated.
		 */
		public void setColumn(int column) {
			this.column = column;
		}
}
