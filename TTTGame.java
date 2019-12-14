import java.util.*;
//============================================================================
// Name        : Ultimate Tic-Tac-Toe Game 
// Author      : Utsav Malik
// Section     : 006
//============================================================================
/* Analysis- Create Player versus AI with Ultimate Tic Tac Toe Game where the board is made up of nine mini boards and in order to win the game, the AI or the user must win the three mini boards first and win them in a Tic Tac Toe Pattern collectively.  
 * Design- The TTTGame class is indirectly built off of Board class, Box class, Player class, and AI class. 
 * This class will basically have the content to run the game such as switching turns or starting the game. 
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
public class TTTGame 
{
	//data field members below 
	private Board board; 
	private Board board1; 
	private Board board2;
	private Board board3;
	private Board board4;
	private Board board5;
	private Board board6;
	private Board board7;
	private Board board8; 
	private int row = 3;
	private int col = 3; 
	private Player player1;
	private Player player2; 
	private String gameWinner = "";
	private AI ai; 
	private boolean aiTrack = false;
	private String firstAiOrP1; 
	//below is the default constructor for TTTGame type of object 
	public TTTGame() 
	{
		//calls setBoard, which creates the Ultimate Tic Tac Toe Board 
		setBoard();  
	}

	
	//findBoard returns a board object if it can be found depending on the argument num passed 
	public Board findBoard(int num)
	{
		//below are if statements checking if num matches with any of the existing boards, if so return that board instance 
		if(num == 0)
		{
			return board;
		}
		else if(num == 1)
		{
			return board1;
		}
		else if(num == 2)
		{
			return board2; 
		}
		else if(num == 3)
		{
			return board3;
		}
		else if(num == 4)
		{
			return board4; 
		}
		else if(num == 5)
		{
			return board5; 
		}
		else if(num == 6)
		{
			return board6; 
		}
		else if(num == 7)
		{
			return board7; 
		}
		else
			return board8; 
	}
	//switchPlayers switches into AI's turn if it was player's turn beforehand or Player's turn if it was AI's turn beforehand 
	public void switchPlayers(Player p, int i)
	{
		//find the board if it exists 
		Board b = findBoard(i); 
		//if current player is Player 1, switch into AI 
		if(p.getName() == "Player 1")
		{
			   //if aiTrack is true, go into the condition 
			   if(aiTrack)
			   {
				   //set aiTrack to false
				   aiTrack = false;
				   //AI will make his or her turn on the board now 
				   makeMoveAI(ai, b);
			   }
			   
		}
	    //if current player is AI 
		else if(p.getName().equals("AI"))
		{
			//switch into Player's turn 
			aiTrack = true; 
			makeMoveAI(player1, b); 
		}
	}
    //checkTie returns true if a tie happens, otherwise will return false 
	public boolean checkTie()
	{
	     //check if all the boards are full and no winner has been assigned, return true 	
		if(!checkBoardFull(board) && !checkBoardFull(board1) && !checkBoardFull(board2) && !checkBoardFull(board3) && !checkBoardFull(board4) && !checkBoardFull(board5) && !checkBoardFull(board6) && !checkBoardFull(board7) && !checkBoardFull(board8) && gameWinner.isEmpty())
		{
		  return true;
		}
		//else return false 
		else 
			return false;
	}
	//makeMoveAI makes the move on the board depending on who's turn it is 
	public void makeMoveAI(Player p, Board b)
	{
	//if tie happened, assign Tie to gameWinner 
	if(checkTie())
	{
			gameWinner = "Tie"; 
			 
	}
	//if tie did not happen, then go make a move 
	else
	{
		//take in input for the index in the board selected
		Scanner input = new Scanner(System.in); 
		System.out.println(p.getName() + " Please input a valid square on the selected board: " + b.getName());
		int squareNum = 0;
		//if Player 1 had the first turn, go into the if statement 
	    if(firstAiOrP1.equals("P1"))
		{
	    	//if !aiTrack is true, then let the AI select an option on the board
			if(!aiTrack)
			{
				squareNum  = ai.generateMove();
				System.out.println("AI has selected " + squareNum + " on " + b.getName());
			}
			//otherwise player will select the square on the board 
			else 
				squareNum = input.nextInt();
			
			
		}
	    //if AI had the first turn, assign the square below depending on the cases 
		else if(firstAiOrP1.equals("AI"))
		{
			//if !aiTrack is true, let AI select an option on the board
			if(!aiTrack)
			{
				squareNum  = ai.generateMove();
				System.out.println("AI has selected " + squareNum + " on " + b.getName());
			}
			//else let the player pick 
			else 
				squareNum = input.nextInt();
			
			
		}
	    //validate player's picked square 
		while(squareNum < 0 || squareNum > 8)
		{
			System.out.println("Incorrect index selected, please input a valid square on the selected board.");
			 squareNum = input.nextInt(); 
		}
	    //if the square picked is empty and the board is not full, then go ahead and mark that index and switch players if a winner has not been found 
	   if(b.isAvailable(squareNum) && checkBoardFull(b))
		{
			 b.boxToMark(p.getMark(), squareNum);
			 print(); 
			 while(mainWinner(p, b))
			 {
				 switchPlayers(p, squareNum);
			 }
		}
	   //if the index is not empty and the board is not full and !aiTrack is true, make the player reselect a valid index on the board 
		else if(!b.isAvailable(squareNum) && checkBoardFull(b) && !aiTrack)
		{
			//output message to console 
			System.out.println("Since that index is not available, please input a valid index on " + b.getName());
			{       
				    //generate AI's move on the board selected 
					int num = 0;
					num = ai.generateMove();
					//validate the square selected on the board 
					while(num < 0 || num > 8 || b.getMark(num).equals("X") || b.getMark(num).equals("O"))
					{
						System.out.println("AI selected an invalid option still for " + b.getName());
						System.out.println("Input a valid square index for " + b.getName()); 
						num = ai.generateMove();
					}
				//mark the square on the board 
				 b.boxToMark(p.getMark(), num);
				 //output message that AI selected this square on the board and output the result 
				 System.out.println("AI selected " + num + " on " +  b.getName());
				 print();
				 //while gameWinner has not been assigned a value in mainWinner, switch player 
				 while(mainWinner(p, b))
				 {
					 switchPlayers(p, num);
				 }
			}
		}
	   //if the square on the board is not empty and board is not full and aiTrack is true, let player reselect a valid square on the board 
		else if(!b.isAvailable(squareNum) && checkBoardFull(b) && aiTrack)
		{
			//output message 
			System.out.println("Since that index is not available, please input a valid index on " + b.getName());
			{
				    //take in square input from player 
					int num = 0;
				    num = input.nextInt();
				    //validate the square input 
					while(num < 0 || num > 8 || b.getMark(num).equals("X") || b.getMark(num).equals("O"))
					{
						System.out.println("Since that index is not available, please input a valid index on " + b.getName());
						num = input.nextInt();
					}
				//mark it on the board and output the result 
				 b.boxToMark(p.getMark(), num);
				 System.out.println(p.getName() + " selected " + num + " on " +  b.getName());
				 print();
				 //if gameWinner has not been assigned, then switch players
				 while(mainWinner(p, b))
				 {
					 switchPlayers(p, num);
				 }
			}
		}
	   //if board is full, go into this condition 
		else if(!checkBoardFull(b))
		{
				//force the current player  to select a new board option since the current board is full 
				int boardNum = 0;
				Board newB;
				System.out.println("Since all the indexes at " + b.getName() + " are full, please input a new board");
				//if current player is AI, make the AI pick a new board and square on that board and validate it 
				if(p.getName().equals("AI"))
				{
					boardNum  = ai.generateMove();
					Board secondBoardOption = findBoard(boardNum);
					String boardNumString = "BOARD#" + boardNum; 
					while(boardNumString.equals(b.getName()) || checkBoardFull(secondBoardOption) == false)
					{
							System.out.println("AI selected an unavailable board option");
							System.out.println("BOARD#" + boardNum + " is full. AI please input a valid board option");
							boardNum = ai.generateMove();
							boardNumString = "BOARD#" + boardNum; 
							secondBoardOption = findBoard(boardNum);
					}
					System.out.println("AI has selected board " + boardNum);
					newB = findBoard(boardNum); 
					
					System.out.println(p.getName() + " Please input a valid square on the selected board: " + newB.getName());
					if(p.getName().equals("AI"))
					{
						squareNum  = ai.generateMove();
						while(newB.isAvailable(squareNum) == false)
						{
							squareNum = ai.generateMove();
						}
						System.out.println("AI has selected square " + squareNum + " on " + newB.getName());
					}
				}
		//if current player is user, make the user pick a new board and square on that board and validate it 
				else
				{
					boardNum = input.nextInt();
					String boardTrack = "BOARD#" + boardNum;
					newB = findBoard(boardNum);
					while(boardNum < 0 || boardNum > 8 || boardTrack.equals(b.getName()) || checkBoardFull(newB) == false)
					{
						System.out.println("Input a valid board option"); 
						boardNum = input.nextInt();
						boardTrack = "BOARD#" + boardNum;
						newB = findBoard(boardNum);
					
					}
						System.out.println(p.getName() + " has selected " + newB.getName());
					if (p.getName().equals("Player 1"))
					{
						squareNum = input.nextInt();
					}
					while(squareNum < 0 || squareNum > 8 || newB.getMark(squareNum).equals("O") || newB.getMark(squareNum).equals("X"))
					{
						System.out.println("Incorrect index selected, please input a valid square on the selected board.");
						 squareNum = input.nextInt(); 
					}
		
			   }
			//mark that square on the board and output the results 
			newB.boxToMark(p.getMark(), squareNum);
			print();
			//if gameWinner is not assigned, switch players again 
			 while(mainWinner(p, newB)) 
			 {
				 switchPlayers(p, squareNum);
			 }
	    }
	}
  }

    //returns true if all boards are not full, otherwise false if all boards are full 
	public boolean checkBoardFull(Board b)
	{
		if(b.isAvailable(0) || b.isAvailable(1) || b.isAvailable(2) || b.isAvailable(3) || b.isAvailable(4) || b.isAvailable(5) || b.isAvailable(6) || b.isAvailable(7) || b.isAvailable(8))
		{
			return true;
		}
		else 
			return false;  
	}
	//mainWinner checks if a winner or tie has occurred in the ultimate tic tac toe game 
	public boolean mainWinner(Player p, Board b)
	{
		//check if winner has occurred in one of the 9 boards 
	    checkWinner(p, b);
	    //depending on which option is validated, the winner is assigned for the ultimate tic tac toe, which can be a tie 
	    //if gameWinner is assigned a value then this method will return false, otherwise it will return true 
	    if(board.checkWon() && board.getWinningMark().equals("X") && board1.checkWon() && board1.getWinningMark().equals("X") && board2.checkWon() && board2.getWinningMark().equals("X"))
	    {
	    	if(player1.getMark().equals("X"))
	    	{
	    		gameWinner = player1.getName(); 
	    		return false;
	    	}
	    	else
	    	{
	    		if(!aiTrack)
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		
	    		return false; 
	    	}
	    }
	    else if(board3.checkWon() && board3.getWinningMark().equals("X") && board4.checkWon() && board4.getWinningMark().equals("X") && board5.checkWon() && board5.getWinningMark().equals("X"))
	    {
	    	if(player1.getMark().equals("X"))
	    	{
	    		gameWinner = player1.getName(); 
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		return false; 
	    	}
	    }
	    else if(board6.checkWon() && board6.getWinningMark().equals("X") && board7.checkWon() && board7.getWinningMark().equals("X") && board8.checkWon() && board8.getWinningMark().equals("X"))
	    {
	    	if(player1.getMark().equals("X"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		else
	    		{
	    			gameWinner = player2.getName();
	    		}
	    		return false; 
	    	}
	    }
	    else if(board.checkWon() && board.getWinningMark().equals("O") && board1.checkWon() && board1.getWinningMark().equals("O") && board2.checkWon() && board2.getWinningMark().equals("O"))
	    {
	    	if(player1.getMark().equals("O"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		
	    		return false; 
	    	}
	    }
	    else if(board3.checkWon() && board3.getWinningMark().equals("O") && board4.checkWon() && board4.getWinningMark().equals("O") && board5.checkWon() && board5.getWinningMark().equals("O"))
	    {
	    	if(player1.getMark().equals("O"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		
	    		return false; 
	    	}
	    }
	    else if(board6.checkWon() && board6.getWinningMark().equals("O") && board7.checkWon() && board7.getWinningMark().equals("O") && board8.checkWon() && board8.getWinningMark().equals("O"))
	    {
	    	if(player1.getMark().equals("O"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		
	    		return false; 
	    	}
	    }
	    else if(board.checkWon() && board.getWinningMark().equals("X") && board4.checkWon() && board4.getWinningMark().equals("X") && board8.checkWon() && board8.getWinningMark().equals("X"))
	    {
	    	if(player1.getMark().equals("X"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		
	    		return false; 
	    	}
	    }
	    else if(board.checkWon() && board.getWinningMark().equals("O") && board4.checkWon() && board4.getWinningMark().equals("O") && board8.checkWon() && board8.getWinningMark().equals("O"))
	    {
	    	if(player1.getMark().equals("O"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		
	    		return false; 
	    	}
	    }
	    else if(board2.checkWon() && board2.getWinningMark().equals("O") && board4.checkWon() && board4.getWinningMark().equals("O") && board6.checkWon() && board6.getWinningMark().equals("O"))
	    {
	    	if(player1.getMark().equals("O"))
	    	{
	    		gameWinner = player1.getName(); 
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		 
	    		return false; 
	    	}
	    }
	    else if(board2.checkWon() && board2.getWinningMark().equals("X") && board4.checkWon() && board4.getWinningMark().equals("X") && board6.checkWon() && board6.getWinningMark().equals("X"))
	    {
	    	if(player1.getMark().equals("X"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		
	    		return false; 
	    	}
	    }
	    else if(board.checkWon() && board.getWinningMark().equals("X") && board3.checkWon() && board3.getWinningMark().equals("X") && board6.checkWon() && board6.getWinningMark().equals("X"))
	    {
	    	if(player1.getMark().equals("X"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		
	    		return false;
	    	}
	    }
	    else if(board.checkWon() && board.getWinningMark().equals("O") && board3.checkWon() && board3.getWinningMark().equals("O") && board6.checkWon() && board6.getWinningMark().equals("O"))
	    {
	    	if(player1.getMark().equals("O"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		
	    		return false;
	    	}
	    }
	    else if(board1.checkWon() && board1.getWinningMark().equals("O") && board4.checkWon() && board4.getWinningMark().equals("O") && board7.checkWon() && board7.getWinningMark().equals("O"))
	    {
	    	if(player1.getMark().equals("O"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		return false;
	    	}
	    }
	    else if(board1.checkWon() && board1.getWinningMark().equals("X") && board4.checkWon() && board4.getWinningMark().equals("X") && board7.checkWon() && board7.getWinningMark().equals("X"))
	    {
	    	if(player1.getMark().equals("X"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		
	    		return false; 
	    	}
	    }
	    else if(board2.checkWon() && board2.getWinningMark().equals("O") && board5.checkWon() && board5.getWinningMark().equals("O") && board8.checkWon() && board8.getWinningMark().equals("O"))
	    {
	    	if(player1.getMark().equals("O"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		
	    		return false;
	    	}
	    }
	    else if(board2.checkWon() && board2.getWinningMark().equals("X") && board5.checkWon() && board5.getWinningMark().equals("X") && board8.checkWon() && board8.getWinningMark().equals("X"))
	    {
	    	if(player1.getMark().equals("X"))
	    	{
	    		gameWinner = player1.getName();
	    		return false;
	    	}
	    	else
	    	{
	    		if(ai.getName().equals("AI"))
	    		{
	    			gameWinner = ai.getName();
	    		}
	    		
	    		return false; 
	    	}
	    }
	    if(gameWinner.equals("Tie"))
	    {
	    	return false; 
	    }
	    return true; 
	}
	//checkWinner checks if one of the 9 boards selected has been won or not 
	public void checkWinner(Player p, Board b)
	{ 
		//get the mark of the current player 
		//based on the board passed in it will check if a winner occurs in that specific board, and if so, it will assign
		//a winningMark to that board and make the set Won board option for that baord true 
		String mark = p.getMark();
		if(b.getMark(0).equals(mark) && b.getMark(1).equals(mark) && b.getMark(2).equals(mark) && !b.checkWon())
		{
			b.setWon();
			b.setWinningMark(mark);
		}
		else if(b.getMark(3).equals(mark) && b.getMark(4).equals(mark) && b.getMark(5).equals(mark) && !b.checkWon())
		{
			
			b.setWon();
			b.setWinningMark(mark);
		}
		else if(b.getMark(6).equals(mark) && b.getMark(7).equals(mark) && b.getMark(8).equals(mark) && !b.checkWon())
		{
			b.setWon();
			b.setWinningMark(mark);

		}
		else if(b.getMark(0).equals(mark) && b.getMark(4).equals(mark) && b.getMark(8).equals(mark) && !b.checkWon())
		{
			b.setWon();
			b.setWinningMark(mark);
		}
		else if(b.getMark(2).equals(mark) && b.getMark(4).equals(mark) && b.getMark(6).equals(mark) && !b.checkWon())
		{
			b.setWon();
			b.setWinningMark(mark);
		}
		else if(b.getMark(0).equals(mark) && b.getMark(4).equals(mark) && b.getMark(8).equals(mark) && !b.checkWon())
		{
			b.setWon();
			b.setWinningMark(mark);
		}
		else if(b.getMark(0).equals(mark) && b.getMark(3).equals(mark) && b.getMark(6).equals(mark) && !b.checkWon())
		{
			b.setWon();
			b.setWinningMark(mark);
		}
		else if(b.getMark(1).equals(mark) && b.getMark(4).equals(mark) && b.getMark(7).equals(mark) && !b.checkWon())
		{
			b.setWon();
			b.setWinningMark(mark);
		}
		else if(b.getMark(2).equals(mark) && b.getMark(5).equals(mark) && b.getMark(8).equals(mark) && !b.checkWon())
		{
			b.setWon();
			b.setWinningMark(mark);
		}
		
			
	}
	
	//at the start of the game, this method will set the attributes of the AI and player game 
	public void setAIAndPlayer() 
	{
		//output message asking what type of marks would the current player like 
		System.out.println("Would you like to have X marks or O marks?"); 
		Scanner input = new Scanner(System.in); 
		String mark = input.nextLine();
		//validate the input for the marks 
		while(!mark.equals("X") && !mark.equals("O") && !mark.equals("o") && !mark.equals("x"))
		{
			System.out.println("Would you like to have X marks or O marks?"); 
			mark = input.nextLine();
		}
		//if current player selects X marks then set X marks to user player and O to AI 
			if(mark.equals("X") || mark.equals("x"))
			{
				player1 = new Player(); 
				player1.setMark("X");
				player1.setName("Player 1");
				ai = new AI();
				ai.setMark("O");
				ai.setName("AI");
				System.out.println("Player 1 has X marks and AI has O marks"); 
				
			}
			//otherwise user player has O marks and AI has X marks 
			else if(mark.equals("O") || mark.equals("o"))
			{
				player1 = new Player(); 
				player1.setMark("O");
				player1.setName("Player 1");
				ai = new AI();
				ai.setMark("X");
				ai.setName("AI");
				System.out.println("Player 1 has O marks and AI has X marks"); 
				
			}
			
		}
	
	
	//this method will decide if AI or the user player will have the first turn
	public int decideFirst()
	{
		//assign two random values for each player
		 int player1Num = (int) (Math.random()*10);
		 int player2Num = (int) (Math.random()*10);
		 //if both players have equal values, reassign  the random values
		 while(player1Num == player2Num)
		 {
			 player1Num = (int) (Math.random()*10);
			 player2Num = (int) (Math.random()*10);
		 }
		 //if player1 has greater value, user will go first
		 if(player1Num > player2Num)
		 {
			 System.out.println("Player 1 will go first since he or she had the higher random number");
			 firstAiOrP1 = "P1"; 
			 aiTrack = true;
			 return 1;
		 }
		 //otherwise Ai will go first because AI had a larger random value
		 else if(player2Num > player1Num)
		 {
				 System.out.println("AI will go first since he or she had the higher random number");
				 firstAiOrP1 = "AI";
				 aiTrack = true; 
				 return 2; 
			 
	   }
		 //return 0 because method returns an int 
		  return 0; 
     }
         //startAI does the first turn depending on whose turn is it 
		 public void startAI()
			{
			        //call decideFirst() and store the result into result to see who has first turn 
				    int result = decideFirst(); 
				    int boardNum = 0;
				    Scanner input = new Scanner(System.in); 
					System.out.println("Please select which Board you would like to make a move at?");
					//if result is 2, AI has first turn to select board 
					if(result == 2)
					{
						boardNum = ai.generateMove();
						System.out.println("AI selected board " + boardNum);
					}
					//otherwise user player will pick the board 
					else
					{
						
						boardNum = input.nextInt();
						
					}
					//validate user input 
					while(boardNum < 0 || boardNum > 8)
					{
						System.out.println("Please select which Board you would like to make a move at?");
						boardNum = input.nextInt();
					}
					    //take in the square index for the board depending on who is going first 
						System.out.println("Please input a valid square on the selected board.");
						int squareNum = 0; 
						if(result == 2)
						{
						   
						   squareNum = ai.generateMove();
						   System.out.println("AI selected " + squareNum + " on " + "BOARD#" + boardNum); 
						}
						else
						{
							squareNum = input.nextInt();
						}
						//validate the input
						while(squareNum < 0 || squareNum > 8)
						{
							System.out.println("Please input a valid square on the selected board.");
							 squareNum = input.nextInt(); 
						}
						  //depending on who has the turn and what board was picked for the first turn, go and make that mark on that baord and then switch players
						   //the if statments below check which board was selected for first turn 
						    if(boardNum == 0)
							{
						    	if(result == 1)
						    	{
								 board.boxToMark(player1.getMark(), squareNum); 
								 print();
						    	 switchPlayersAI(1, squareNum);
						    	}
						    	else 
						    	{
						    	 board.boxToMark(ai.getMark(), squareNum);
						    	 print();
						    	 switchPlayersAI(2, squareNum);
						    	}
						    		
							}
							else if(boardNum == 1)
							{
								if(result == 1)
								{
								 board1.boxToMark(player1.getMark(), squareNum);
								 print();
								 switchPlayersAI(1, squareNum);
								}
								else
								{
									board1.boxToMark(ai.getMark(), squareNum);
									print();
									switchPlayersAI(2, squareNum);
								}
							}
							else if(boardNum == 2)
							{
								if(result == 1)
								{
									board2.boxToMark(player1.getMark(), squareNum);
									print();
									switchPlayersAI(1, squareNum);
								}
								else
								{
										board2.boxToMark(ai.getMark(), squareNum);
										print();
										switchPlayersAI(2, squareNum);
								}
							}
							else if(boardNum == 3)
							{
								if(result == 1)
								{
									 board3.boxToMark(player1.getMark(), squareNum);
									 print();
									 switchPlayersAI(1, squareNum); 
								}
								else
								{
								   board3.boxToMark(ai.getMark(), squareNum);
								   print();
								   switchPlayersAI(2, squareNum); 
								}
							}
							else if(boardNum == 4)
							{
								if(result == 1)
								{
									 board4.boxToMark(player1.getMark(), squareNum);
									 print();
									 switchPlayersAI(1, squareNum);
								}
								else
								{
								   board4.boxToMark(ai.getMark(), squareNum);
								   print();
								   switchPlayersAI(2, squareNum);
								}
							}
							else if(boardNum == 5)
							{
								if(result == 1)
								{
									 board5.boxToMark(player1.getMark(), squareNum);
									 print();
									 switchPlayersAI(1, squareNum);
								}
								else
								{
								   board5.boxToMark(ai.getMark(), squareNum);
								   print();
								   switchPlayersAI(2, squareNum);
								}
							}
							else if(boardNum == 6)
							{
								if(result == 1)
								{
									 board6.boxToMark(player1.getMark(), squareNum);
									 print();
									 switchPlayersAI(1, squareNum);
								}
								else
								{
								   board6.boxToMark(ai.getMark(), squareNum);
								   print();
								   switchPlayersAI(2, squareNum);
								}
							}
							else if(boardNum == 7)
							{
								if(result == 1)
								{
									 board7.boxToMark(player1.getMark(), squareNum);
									 print();
									 switchPlayersAI(1, squareNum);
								}
								else
								{
								   board7.boxToMark(ai.getMark(), squareNum);
								   print();
								   switchPlayersAI(2, squareNum);
								}
							}
							else if(boardNum == 8)
							{
								if(result == 1)
								{
									 board8.boxToMark(player1.getMark(), squareNum);
									 print();
									 switchPlayersAI(1, squareNum);
								}
								else
								{
								    board8.boxToMark(ai.getMark(), squareNum);
								    print();
								    switchPlayersAI(2, squareNum);
								}
							}
						}
			//switch PlayersAI switches current player after the first turn 
			public void switchPlayersAI(int i, int squareIndex)
			{ 
				//return the board for the next turn 
				Board b = findBoard(squareIndex);  
				//if user player went first, then let AI make next turn 
				if(i == 1)
				{
					if(aiTrack)
					{
						aiTrack = false; 
						makeMoveAI(ai, b);
					} 
				}
				//if AI went first, let user make next turn 
				else if(i == 2)
				{
					aiTrack = true; 
					makeMoveAI(player1, b);
				}
			}

		
	//setBoard creates a boards of the given size with the name 
	private void setBoard() {
		this.board = new Board(row, col, "BOARD#0");
		this.board1 = new Board(row, col, "BOARD#1");
		this.board2 = new Board(row, col, "BOARD#2");
		this.board3 = new Board(row, col, "BOARD#3");
		this.board4 = new Board(row, col, "BOARD#4");
		this.board5 = new Board(row, col, "BOARD#5");
		this.board6 = new Board(row, col, "BOARD#6");
		this.board7 = new Board(row, col, "BOARD#7");
		this.board8 = new Board(row, col, "BOARD#8");	
	} 
	//pirntBoard() outputs the current board for Ultimate Tic Tac Toe  
	public void printBoard(Board b, int firstIndex, int secondIndex, int thirdIndex)
	{
		//prints out content of the board in correct output bellow using the print() method 
		System.out.print(b.getName());
		System.out.print( " " + "|" + " "); 
		b.print(firstIndex);
		System.out.print( " " + "|" + " "); 
		b.print(secondIndex);
		System.out.print( " " + "|" + " "); 
		b.print(thirdIndex);
		System.out.print( " " + "|" + " "); 
	}
	//print() will print out the content of the board row by row for each board 
	public void print()
	{
		//outputting each row of each board below 
        printBoard(board,0, 1 , 2);
        printBoard(board1, 0, 1 , 2); 
        printBoard(board2, 0, 1 , 2); 
        System.out.println(); 
        
        printBoard(board, 3, 4, 5); 
        printBoard(board1, 3, 4, 5); 
        printBoard(board2, 3, 4, 5); 
        System.out.println();
        
        printBoard(board, 6, 7, 8); 
        printBoard(board1, 6, 7, 8); 
        printBoard(board2, 6, 7, 8); 
        System.out.println();
        
        printBoard(board3, 0, 1, 2); 
        printBoard(board4, 0, 1, 2); 
        printBoard(board5, 0, 1, 2); 
        System.out.println();
        
        printBoard(board3, 3, 4, 5); 
        printBoard(board4, 3, 4, 5); 
        printBoard(board5, 3, 4, 5); 
        System.out.println();
        
        printBoard(board3, 6, 7, 8); 
        printBoard(board4, 6, 7, 8); 
        printBoard(board5, 6, 7, 8); 
        System.out.println();
        
        printBoard(board6,0, 1 , 2);
        printBoard(board7, 0, 1 , 2); 
        printBoard(board8, 0, 1 , 2); 
        System.out.println(); 
        
        printBoard(board6, 3, 4, 5); 
        printBoard(board7, 3, 4, 5); 
        printBoard(board8, 3, 4, 5); 
        System.out.println();
        
        printBoard(board6, 6, 7, 8); 
        printBoard(board7, 6, 7, 8); 
        printBoard(board8, 6, 7, 8); 
        System.out.println();
        
	}
	//announceWinner method will output a winner or tie at the end of the game 
	public void announceWinner()
	{    
		//checks if gameWinner is Player 1, and outputs the user won
		if(gameWinner.equals("Player 1"))
		 {
		      System.out.println("Player 1 has won the game");
		 }
		//checks if AI won and then outputs AI won
		else if(gameWinner.equals("AI"))
		{
    	  System.out.println("AI has won the game"); 
		}
		//if there is a tie, it will output a tie happened 
		else if(gameWinner.equals("Tie"))
		{
    	  System.out.println("Because all the boards are full, and no one has won, it is a tie");
		}
	}
	
}