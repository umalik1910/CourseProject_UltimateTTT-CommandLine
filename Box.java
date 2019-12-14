//============================================================================
// Name        : Ultimate Tic-Tac-Toe Game 
// Author      : Utsav Malik
// Section     : 006
//============================================================================
/*  Analysis- Create Player versus AI with Ultimate Tic Tac Toe Game where the board is made up of nine mini boards and in order to win the game, the AI or the user must win the three mini boards first and win them in a Tic Tac Toe Pattern collectively.  
 *  Design- The Box class represents each single square in each of the boards. The Board object is made up Box objects. Box class is needed in order put content in the board.
 * Test- When the game starts, the user will be asked to pick X or O marks, input wise for that anything other than X,x, o, or O will not be expected and user will be asked to input a valid input. 
 * Then depending on whose's turn it is, the current player will be asked which board to play on. Only a an integer value between 0 and 8 will be taken, otherwise the current player will be told to retry.
 * For this input, only int type inputs are accepted. If an invalid board is given such as 9 or a board number that is full, then the current player will be asked again and again till he or she is correct thru a loop. 
 * Then the current player will be asked to input a free square on the board selected, the current player must input a free square number between 0 and 8 (inclusive). For this also input must be an int type, no other type of input will work for the program. 
 * If the current player picks an invalid square on the board, he or she will be asked again till he or she is correct. 
 * 
 * In order to win the game, I tried valid options to win the ultimate tic tac toe game against the AI. Whenever I tried invalid values as explained above the program would force me to reenter correct values. 
 * In order to lose against the AI, I tried my best to make the worst decisions for myself when it came to my turn. 
 * For a tie to happen, I tried to make all the boards full with no winner in the game.
 * 
 * Whenever it asked to select a board or square index on the board selected, I always inputted an int type value because that is what is expected by the program. 
 * 
 */
public class Box{
	//data fields below 
	private  int row;
	private  int col;
	private final static String DASH = "-";
	private String placeholder = DASH; 
	
	//overloaded constructor below 
	public Box(int row, int col, String placeholder){
		this.row = row;
		this.col = col;
		this.placeholder = placeholder; 
	}
	
	//accessor method for placeholder 
	public String getPlaceholder() {
		return placeholder;
	}
	//setter method for placeholder 
	public void setPlaceholder(String placeholder) {
			this.placeholder = placeholder;
	}	
	//output the placeholder in the shape of the board
	public void print() {
		System.out.print(placeholder);
	}
	

}

