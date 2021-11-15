/**
 *		Name and ID: Matthew Gee, 263 370 31
 *		Comp 249
 *		Assignment #: 1
 *		Due Date: September 21, 2018	
 */

public class Driver {

	public static void main(String[] args) {
		
		Move.introduction(); // Greeting message
		
		Gameboard.placeShips(); // Prompts & places the user's 6 ships.
		Gameboard.placeGrenades(); // Prompts & places the user's 4 grenades.
		Gameboard.placeComputerShips(); // Places the computer's 6 randomly generated ships.
		Gameboard.placeComputerGrenades(); // Places the computer's 4 randomly generated grenades.
		Move.startPlaying(); // Begin playing message
		Move.playRotation(); // Begins the loop used that governs whose turn it is to fire shots.
		

	}

}
