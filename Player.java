/**
 *		Name and ID: Matthew Gee, 263 370 31
 *		Comp 249
 *		Assignment #: 1
 *		Due Date: September 21, 2018	
 */


public class Player {
	
	
	private int score = 0;
	private int turnSkip = 0;
	
		/**
		 * Default constructor.
		 */
		public Player() {
			score = 0;
			turnSkip = 0;
		}
		/**
		 * Allows calling of player's score
		 * @return player's current score
		 */
		public int getScore() {
			return score;
		}
	
		/** 
		 * Increases player's score by one after they strike an opponent's battleship. The opponent's counter increases if they strike their own battleship.
		 */
		public void setScore() {
			score += 1;
		}
	
		/** Used to determine whether a player is permitted to make a move or if their turn will be skipped due to striking a grenade.
		 * @return Returns a 0 or 1, 1 indicates the player will skip a turn, 0 indicates that the player is okay to proceed with a normal turn
		 */
		public int getTurnSkip() {
			return turnSkip;
		}
	
		/** 
		 * Adds 1 to the turnSkip counter. If a person strikes a grenade, their opponents OR their own, this method will be called.
		 */
		public void setTurnSkip() {
			turnSkip +=1;
		}
		
		/** 
		 * Resets turnSkip counter to 0 after a the skipped turn passes.
		 */
		public void undoTurnSkip() {
			turnSkip = 0;
		}
	
}
