
public class Board {
	
	private Piece [][] board = new Piece [8][8];
	
	public void initBoard(){
		int i=1, j=0;
		for (j=0; j<8; j++){
			int a [] = {i, j};
			board[i][j] = new Piece("Pawn", a , 'W', 'P');
		}
		i=6;
		for (j=0; j<8; j++){
			int a [] = {i, j};
			board[i][j] = new Piece("Pawn", a, 'B', 'P');
		}
		i=0;
		char s = 'W';
		int a [] = new int [2];
		a[0] = 0;
		for (int m=0; m<2; m++){
			if (m>0){
				i=7;
				s='B';
			}
			a[1] = 0;
			board[i][0] = new Piece("Rook", a, s, 'R');
			a[1] = 7;
			board[i][7] = new Piece("Rook", a, s, 'R');
			a[1] = 1;
			board[i][1] = new Piece("Knight", a, s, 'N');
			a[1] = 6;
			board[i][6] = new Piece("Knight", a, s, 'N');
			a[1] = 2;
			board[i][2] = new Piece("Bishop", a, s, 'B');
			a[1] = 5;
			board[i][5] = new Piece("Bishop", a, s, 'B');
			a[1] = 3;
			board[i][3] = new Piece("Queen", a, s, 'Q');
			a[1] = 4;
			board[i][4] = new Piece("King", a, s, 'K');
		}
		for (int x=2; x<6; x++){
			for (int y=0; y<8; y++){
				int z[] = {x,y};
				board[x][y] = new Piece("Blank", z, 'N', '-');
			}
		}
	}

	public void printBoard(){
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				System.out.print(board[i][j].getRepresentation() + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String [] args){
		Board board = new Board();
		board.initBoard();
		board.printBoard();
	}
	
}
