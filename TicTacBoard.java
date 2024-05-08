/* Aung Nanda Oo
   TicTacBoard.java
   Starter code for Assignment 11 in CS 111B

   A class representing a Tic Tac Toe board -
   both its user interface and its logical functionality.

   MY JOB IS TO WRITE THE BODY OF THE checkWinner METHOD BELOW
   SO THAT THE GAME STOPS WHEN SOMEONE WINS, OR WHEN THE BOARD IS FULL.
   LIKE SHOWN IN THE SAMPLE OUTPUT AT BOTTOM. BUT I ALSO IMPLEMENTED THE
   PART WHICH IS TO PREDICT IF THE SITUATION PUSHES BOTH OF THE PLAYERS IN A 
   DRAW UNTIL THE BOARD IS FULL
*/

import java.util.Scanner;

public class TicTacBoard
{
  private char[][] board;  // 2-D array of characters
  private char curPlayer; // the player whose turn it is (X or O)

  // Constructor: board will be size x size
  public TicTacBoard(int size)
  {
    board = new char[size][size];

    // initialize the board with all spaces:
    for(int row=0; row < board.length; row++)
      for(int col=0; col < board[row].length; col++)
        board[row][col] = ' ';

    curPlayer = 'X';  // X gets the first move
  }

  public void playGame()
  {
    display();
    do
    {
      takeTurn();
      display();
    }while(!checkWinner());
  }

  ///////  display  ////////
  // Display the current status of the board on the
  // screen, using hyphens (-) for horizontal lines
  // and pipes (|) for vertical lines.
  public void display()
  {
    System.out.println();
    dispRow(0);
    System.out.println("-----");
    dispRow(1);
    System.out.println("-----");
    dispRow(2);
    System.out.println();
  }

  // Display the current status of row r of the board
  // on the screen, using hyphens (-) for horizontal
  // lines and pipes (|) for vertical lines.
  private void dispRow(int r)
  {
    System.out.println(board[r][0] + "|" + board[r][1]
                       + "|" + board[r][2]);
  }

  ///////  takeTurn  ////////
  // Allow the curPlayer to take a turn.
  // Send output to screen saying whose turn
  // it is and specifying the format for input.
  // Read user's input and verify that it is a
  // valid move.  If it's invalid, make them
  // re-enter it.  When a valid move is entered,
  // put it on the board.
  public void takeTurn()
  {
    Scanner scan = new Scanner(System.in);
    int row, col;
    boolean invalid;

    do{
      invalid = false; // assume correct entry
      System.out.println("It is now " + curPlayer + "'s turn.");
      System.out.println("Please enter your move in the form row column.");
      System.out.println("So 0 0 would be the top left, and 0 2 would be the top right.");
      row = scan.nextInt();
      col = scan.nextInt();

      if(row < 0 || col < 0 || row > 2 || col > 2)
      {
        System.out.println("Invalid entry: row and column must both be between 0 and 2 (inclusive).");
        System.out.println("Please try again.");
        invalid = true;
      }
      else if(board[row][col] != ' ')
      {
        System.out.println("Invalid entry: Row " + row + " at Column " + col
                           + " already contains: " + board[row][col]);
        System.out.println("Please try again.");
        invalid = true;
      }
    }while(invalid);
    // Now that input validation loop is finished, put the move on the board:
    board[row][col] = curPlayer;

    // Switch to the other player (take turns):
    if(curPlayer == 'X')
      curPlayer = 'O';
    else
      curPlayer = 'X';
  }

  // If the game is over, print who won (if anyone),
  // and return true.  If the game is not over, return false.
  public boolean checkWinner() {
	    
	    if((board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X') || // Checking if player X already wins
	       (board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X') ||
	       (board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X') ||
	       (board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X') ||
	       (board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X') ||
	       (board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X') ||
	       (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') ||
	       (board[2][0] == 'X' && board[1][1] == 'X' && board[0][2] == 'X')) {
	        System.out.println("Player X wins!");
	        return true;
         
	    } else if((board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O') || // Checking if player O already wins
	              (board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O') ||
	              (board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O') ||
	              (board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O') ||
	              (board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O') ||
	              (board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O') ||
	              (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') ||
	              (board[2][0] == 'O' && board[1][1] == 'O' && board[0][2] == 'O')) {
	        System.out.println("Player O wins!");
	        return true;
	    }
	    boolean isDraw = false;
	    int count = -1; //using this count to identify which row we have reached out; row 0 if count = 0, row 1 if count = 1, row 2 if count = 2
	    int spaceCount = 0;
	    for (int i = 0; i < 3; i++) {
	    	count += 1; //incrementing the count
	    	spaceCount = 0;
	    	for (int j = 0; j < 3; j++) {
	    		if(board[i][j] == ' ') { //checking spaces in broad 
	    			if(count == 0 || count == 2) { //using OR condition since the pattern I found applies for these two rows I mentioned above 
	    				for(char[] row : board) {
	    					for(char element : row) {
	    						if(element == ' ') {
	    							spaceCount += 1; //incrementing spaces to apply it in below condition
	    						}
	    							
	    					}	
	    				}if (spaceCount == 1) { //only when the spaceCount is 1, will check the condition; if the left side ad right side of the space
                                      //is either X or O, if so, it is unwinnable situation for both of the players
		    				if((board[i][0] == 'X' && board[i][2] == 'O') || (board[i][0] == 'O' && board[i][2] == 'X')) {
		    					isDraw = true;
		    				}
		    				if (isDraw) break;
	    				}
	    					

	    				
	    			}else if(count == 1){ //using the middle row if top and bottom of it either X or O, if so it is unwinnale situation
	    				if(((board[0][0] == 'X' && board[2][0] == 'O') || (board[0][0] == 'O' && board[2][0] == 'X')) &&
	    					((board[0][1] == 'X' && board[2][1] == 'O') || (board[0][1] == 'O' && board[2][1] == 'X')) &&
	    					((board[0][2] == 'X' && board[2][2] == 'O') || (board[0][2] == 'O' && board[2][2] == 'X'))
	    					) {
	    					isDraw = true;
	    				}else {
	    					for(char[] row : board) {
	    						for(char element : row) {
	    							if(element == ' ') {
	    								spaceCount += 1; //incrementing space count
	    							}
	    						}
	    					}if(spaceCount == 1) { //if onlt spaceCount is 1, the below condition will be implemented
                  //checking if the bottom and top of the second row is either X or O, if so it is unwinnable situation, 
                  //breaking the loop which results, nobody wins
	    						if((board[0][0] == 'X' && board[2][0] == 'O') || (board[0][0] == 'O' && board[2][0] == 'X') || 
	    							(board[0][2] == 'X' && board[2][2] == 'O') || (board[0][2] == 'O' && board[2][2] == 'X')) {
	    							isDraw = true;
	    						}
	    					}
	    					
	    					
	    				}
	    				if (isDraw) break;
	    				
	    			}
	    			
	    		}
	    	}
	    }
        if (isDraw) { //if the isDraw is True, then it ends with a draw until the board is full
            System.out.println("Nobody wins. Game ends in a draw.");
            return true;
        }

        //this below condition is checked if the board is already full or not, if full, return True & eliminate the program
        int fullBoardSpaceCount = 0;
        for(char[] row : board) {
          for(char element : row) {
            if(element == ' ') { //checking the spaceCount
              fullBoardSpaceCount += 1;
            }
          }
        }
        if (fullBoardSpaceCount == 0) { //if there is no space, then the board is full, so the Game ends in a draw
          System.out.println("Nobody won. Game ends in a draw.");
          return true;
        }         
    
	    return false;  
	}
}

/* Sample Output
#1
 | | 
-----
 | | 
-----
 | | 

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
-1 1
Invalid entry: row and column must both be between 0 and 2 (inclusive).
Please try again.
It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 3
Invalid entry: row and column must both be between 0 and 2 (inclusive).
Please try again.
It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
1 1

 | | 
-----
 |X| 
-----
 | | 

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
1 1
Invalid entry: Row 1 at Column 1 already contains: X
Please try again.
It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 0

 | | 
-----
 |X| 
-----
O| | 

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 0

X| | 
-----
 |X| 
-----
O| | 

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 2

X| | 
-----
 |X| 
-----
O| |O

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 2

X| |X
-----
 |X| 
-----
O| |O

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 1

X| |X
-----
 |X| 
-----
O|O|O

Player O wins!
--------------------------------------------------------------
#2
 | | 
-----
 | | 
-----
 | | 

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 2

 | | 
-----
 | | 
-----
 | |X

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 0

O| | 
-----
 | | 
-----
 | |X

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 2

O| |X
-----
 | | 
-----
 | |X

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
1 1

O| |X
-----
 |O| 
-----
 | |X

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
1 2

O| |X
-----
 |O|X
-----
 | |X

Player X wins!
--------------------------------------------------------------
#3 (Extra Credit Output)
 | | 
-----
 | | 
-----
 | | 

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 0

X| | 
-----
 | | 
-----
 | | 

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 0

X| | 
-----
 | | 
-----
O| | 

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 2

X| |X
-----
 | | 
-----
O| | 

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 1

X|O|X
-----
 | | 
-----
O| | 

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 2

X|O|X
-----
 | | 
-----
O| |X

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
1 1

X|O|X
-----
 |O| 
-----
O| |X

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 1 

X|O|X
-----
 |O| 
-----
O|X|X

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
1 2

X|O|X
-----
 |O|O
-----
O|X|X

Nobody wins. Game ends in a draw.
--------------------------------------------------------------
#4 (Extra Credit Output)
 | | 
-----
 | | 
-----
 | | 

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 0

X| | 
-----
 | | 
-----
 | | 

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 1

X|O| 
-----
 | | 
-----
 | | 

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 2

X|O|X
-----
 | | 
-----
 | | 

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 0

X|O|X
-----
 | | 
-----
O| | 

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 1

X|O|X
-----
 | | 
-----
O|X| 

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 2

X|O|X
-----
 | | 
-----
O|X|O

Nobody wins. Game ends in a draw.

#5

 | | 
-----
 | | 
-----
 | | 

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 2

 | |X
-----
 | | 
-----
 | | 

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
1 2

 | |X
-----
 | |O
-----
 | | 

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 2

 | |X
-----
 | |O
-----
 | |X

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 0

O| |X
-----
 | |O
-----
 | |X

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
1 1

O| |X
-----
 |X|O
-----
 | |X

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
0 1

O|O|X
-----
 |X|O
-----
 | |X

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 1

O|O|X
-----
 |X|O
-----
 |X|X

It is now O's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
2 0

O|O|X
-----
 |X|O
-----
O|X|X

It is now X's turn.
Please enter your move in the form row column.
So 0 0 would be the top left, and 0 2 would be the top right.
1 0

O|O|X
-----
X|X|O
-----
O|X|X

*/