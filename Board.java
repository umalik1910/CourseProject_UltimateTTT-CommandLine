//============================================================================
// Name        : Ultimate Tic-Tac-Toe Game 
// Author      : Utsav Malik
// Section     : 006
//============================================================================
/*  Analysis- Create Player versus AI with Ultimate Tic Tac Toe Game where the board is made up of nine mini boards and in order to win the game, the AI or the user must win the three mini boards first and win them in a Tic Tac Toe Pattern collectively.  
 * Design- The Board class represents the Board object needed to make the 9 boards in order to use TTTGame class. The Board class is made up of the Box class objects. 
 * The Board is class is necessary not only to make the main board but also to put marks and check if there is a winner. 
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
 */
//Board class definition below
public class Board{
	//data fields below 
	private final int BOARD_ROW;
	private final int BOARD_COL;
	private String name;
	private Box[] boxes;
	private boolean won = false; 
	private String winningMark; 
	//default constructor for a 3 by 3 board 
	public Board() {
		this(3, 3,"3*3 board");
	}
	
	//overloaded constructor 
	public Board(int rowSize, int colSize, String name) {
		this.BOARD_COL = colSize;
		this.BOARD_ROW = rowSize;
		this.name = name;
		boxes = new Box[BOARD_ROW * BOARD_COL];
		init();
	}
	
	//sets the board with the appropriate boxes 
	private void init() {
		for(int i = 0; i < boxes.length; i++) {
			Box b = new Box(i/BOARD_COL, i%BOARD_COL,Integer.toString(i));
			boxes[i] = b;
		}
	}
	
	//calls from box class to print out data from each individual index square selected 
	public void print(int i)
	{
		boxes[i].print();
	}
	//gets the name of the Board and returns it 
	public String getName() {
		return this.name;
	}
	//marks a sqaureIndex on the board based on the arguments passed in 
	public void boxToMark(String s, int n) 
	{
		//set the proper placeholder String s value on the proper square n 
		boxes[n].setPlaceholder(s);
	}
	//accessor method to return the mark on a specific square 
	public String getMark(int i)
	{
		return boxes[i].getPlaceholder();
	}
	//checks if there is square index i passed in is empty or not and will return true or false depending 
	public boolean isAvailable(int i)
	{
		if(boxes[i].getPlaceholder().equals("X") || boxes[i].getPlaceholder().equals("O"))
		{
			return false; 
		}
		else
		{
			return true; 
		}
	}
	//sets the won attribute of the board to true 
	public void setWon()
	{
		this.won = true; 
	}
	//checks if the board has been won or not 
	public boolean checkWon()
	{
		//if won attribute is true, return true 
		if(this.won)
		{
			return true;
		}
		//else return false 
		else
			return false; 
	}
	//sets the winningMark attribute of the board 
	public void setWinningMark(String mark)
	{
		winningMark = mark; 
	}
	//returns the winningMark attribute of the board 
	public String getWinningMark()
	{
		return winningMark; 
	}
}
	
