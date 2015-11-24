import java.util.ArrayList;

public class Board {
	
	private Piece [][] board = new Piece [8][8];
	private double score;
	private String hash;
	
	public static void main(String [] args){
		Board board = new Board();
		board.initBoard();
		board.printBoard();
	}
	
	private void initBoard(){
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

	private void printBoard(){
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				System.out.print(board[i][j].getRepresentation() + " ");
			}
			System.out.println();
		}
	}
	
	private ArrayList<Board> generateMoves(Board iBoard){
		
		
		
		return null;
	}

	private ArrayList<Board> movePawn(Board iBoard, int[] cood){
		ArrayList<Board> results = new ArrayList<>();
		int r = cood[0]; //Rank
		int f = cood[1]; //File
		Piece[][] board = iBoard.getBoard();
		Piece piece = board[r][f];
		char side = piece.getSide();
		if (side == 'W'){
			int [][] moves = {{0, 1}, {0, 2}, {-1, 1}, {1, 1}};
			for (int i=0; i<4; i++){
				int [] move = moves[i];
				int tx = r+move[0];
				int ty = f+move[1];
				int [] target = {tx, ty};
				if (tx>-1 && tx<8 && ty>-1 && ty<8){
					if (pawnValid(board, cood, target)){
						Piece[][] newboard = board.clone();
						Board newiboard = new Board();
						newboard[tx][ty] = new Piece(piece.getType(), target, piece.getSide(), piece.getRepresentation());
						newboard[r][f] = new Piece("Blank", cood, 'N', '-');
						newiboard.setBoard(newboard);
						//Set new board hash
						//Set new board score
						results.add(newiboard);
					}
				}
			}
				
		}
		else if (side == 'B'){
			int [][] moves = {{0, -1}, {0, -2}, {1, -1}, {-1, -1}};
			for (int i=0; i<4; i++){
				int [] move = moves[i];
				int tx = r+move[0];
				int ty = f+move[1];
				int [] target = {tx, ty};
				if (tx>-1 && tx<8 && ty>-1 && ty<8){
					if (pawnValid(board, cood, target)){
						Piece[][] newboard = board.clone();
						Board newiboard = new Board();
						newboard[tx][ty] = new Piece(piece.getType(), target, piece.getSide(), piece.getRepresentation());
						newboard[r][f] = new Piece("Blank", cood, 'N', '-');
						newiboard.setBoard(newboard);
						//Set new board hash
						//Set new board score
						results.add(newiboard);
					}
				}
			}
		}
		return results;
	}
	
	private boolean pawnValid(Piece[][] Board, int [] init, int [] target){
		int ix = init[0];
		int iy = init[1];
		int tx = target[0];
		int ty = target[1];
		if (tx<0 || ty<0 || tx>7 || ty>7) return false; //Check if target is on board
		Piece ipiece = board[ix][iy];
		Piece tpiece = board[tx][ty];
		if (ix==tx){
			if (tpiece.getType() != "Blank") return false; //Check if target is blank
			int vdiff = Math.abs(ty-iy);
			if (vdiff > 1){
				if (ipiece.getSide() == 'W'){
					if (iy != 1) return false; //In case starting position isn't 1
					if (board[tx][ty+1].getType() != "Blank") return false; //In case there is another square in between initial and target
				}
				else{
					if (iy != 6) return false;
					if (board[tx][ty-1].getType() != "Blank") return false;
				}
			}
			
		}
		else{
			if (tpiece.getSide() == ipiece.getSide()) return false;
		}
		board[tx][ty] = board[ix][iy];
		boolean check = testCheck(board, ipiece.getSide());
		if (check) return false;
		return true;
	}
	
	private ArrayList<Board> moveBishop(Board iBoard, int[] cood){
	
		return null;
	}
	
	private ArrayList<Board> moveKnight(Board iBoard, int[] cood){
		
		return null;
	}
	
	private ArrayList<Board> moveRook(Board iBoard, int[] cood){
		
		return null;
	}
	
	private ArrayList<Board> moveQueen(Board iBoard, int[] cood){
		
		return null;
	}
	
	private ArrayList<Board> moveKing(Board iBoard, int[] cood){
		
		return null;
	}
	
	private boolean testCheck(Piece[][] board, char side){
		char enemy='B';
		if (side == 'W') enemy = 'B';
		else enemy = 'W';
		int x=0, y=0;
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				if (board[i][j].getType().equals("King")){
					if (board[i][j].getSide() == side){
						x = i;
						y = j;
						break;
					}
				}
			}
		}
		//Check immediate 8 squares
		int [][] moves = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
		for (int i=0; i<8; i++){
			int [] move = moves[i];
			int tx = x+move[0];
			int ty = y+move[1];
			if (tx>-1 && tx<8 && ty>-1 && ty<8){
				Piece piece = board[tx][ty];
				if (piece.getSide() == enemy){ //No possible check from a knight in the surrounding 8 squares
//					if (piece.getType().equals("Queen")) return true; //Check if queen is in surrounding 8 squares
					if (piece.getType().equals("King")) return true; //Check if king is in surrounding 8 squares
//					if (piece.getType().equals("Rook")){
//						if (move[0]==0 || move[1]==0) return true; //Check if rook is in surrounding 4 squares either on same rank or file
//					}
//					if (piece.getType().equals("Bishop")){
//						if (move[0]!=0 && move[1]!=0) return true; //Check if bishop is in surrounding 4 diagonal squares
//					}
					if (piece.getType().equals("Pawn")){
						if (side == 'W'){
							if (move[0]!=0 && move[1]==1) return true; //Check if pawn is diagonally behind the king
						}
						else{
							if (move[0]!=0 && move[1]==-1) return true;
						}
					}
				}
			}
		}
		//Check for attacking knights
		int [][] kmoves = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
		for (int i=0; i<8; i++){
			int [] move = kmoves[i];
			int tx = x+move[0];
			int ty = y+move[1];
			if (tx>-1 && tx<8 && ty>-1 && ty<8){
				Piece piece = board[tx][ty];
				if (piece.getType().equals("Knight") && piece.getSide() == enemy) return true;
			}
		}
		//Check file upwards
		for (int i=0; i<7; i++){
			int ty=y+i;
			if (ty>-1 && ty<8){
				Piece piece = board[x][ty];
				if (piece.getType().equals("Rook") && piece.getSide() == enemy) return true;
				else if (piece.getType().equals("Queen") && piece.getSide() == enemy) return true;
				else if (!(piece.getType().equals("Blank"))) break;
			}
		}
		//Check file downwards
		for (int i=0; i<7; i++){
			int ty=y-i;
			if (ty>-1 && ty<8){
				Piece piece = board[x][ty];
				if (piece.getType().equals("Rook") && piece.getSide() == enemy) return true;
				else if (piece.getType().equals("Queen") && piece.getSide() == enemy) return true;
				else if (!(piece.getType().equals("Blank"))) break;
			}
		}
		//Check rank right
		for (int i=0; i<7; i++){
			int tx=x+i;
			if (tx>-1 && tx<8){
				Piece piece = board[tx][y];
				if (piece.getType().equals("Rook") && piece.getSide() == enemy) return true;
				else if (piece.getType().equals("Queen") && piece.getSide() == enemy) return true;
				else if (!(piece.getType().equals("Blank"))) break;
			}
		}
		//Check rank left
		for (int i=0; i<7; i++){
			int tx=x-i;
			if (tx>-1 && tx<8){
				Piece piece = board[tx][y];
				if (piece.getType().equals("Rook") && piece.getSide() == enemy) return true;
				else if (piece.getType().equals("Queen") && piece.getSide() == enemy) return true;
				else if (!(piece.getType().equals("Blank"))) break;
			}
		}
		//Check north east diagonal
		for (int i=0; i<7; i++){
			int tx=x+i;
			int ty=y+i;
			if (tx>-1 && tx<8 && ty>-1 && ty<8){
				Piece piece = board[tx][ty];
				if (piece.getType().equals("Bishop") && piece.getSide() == enemy) return true;
				else if (piece.getType().equals("Queen") && piece.getSide() == enemy) return true;
				else if (!(piece.getType().equals("Blank"))) break;
			}
		}
		//Check north west diagonal
		for (int i=0; i<7; i++){
			int tx=x-i;
			int ty=y+i;
			if (tx>-1 && tx<8 && ty>-1 && ty<8){
				Piece piece = board[tx][ty];
				if (piece.getType().equals("Bishop") && piece.getSide() == enemy) return true;
				else if (piece.getType().equals("Queen") && piece.getSide() == enemy) return true;
				else if (!(piece.getType().equals("Blank"))) break;
			}
		}
		//Check south west diagonal
		for (int i=0; i<7; i++){
			int tx=x-i;
			int ty=y-i;
			if (tx>-1 && tx<8 && ty>-1 && ty<8){
				Piece piece = board[tx][ty];
				if (piece.getType().equals("Bishop") && piece.getSide() == enemy) return true;
				else if (piece.getType().equals("Queen") && piece.getSide() == enemy) return true;
				else if (!(piece.getType().equals("Blank"))) break;
			}
		}
		//Check south east diagonal
		for (int i=0; i<7; i++){
			int tx=x+i;
			int ty=y-i;
			if (tx>-1 && tx<8 && ty>-1 && ty<8){
				Piece piece = board[tx][ty];
				if (piece.getType().equals("Bishop") && piece.getSide() == enemy) return true;
				else if (piece.getType().equals("Queen") && piece.getSide() == enemy) return true;
				else if (!(piece.getType().equals("Blank"))) break;
			}
		}
		return false;
	}
	
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Piece[][] getBoard() {
		return board;
	}

	public void setBoard(Piece[][] board) {
		this.board = board;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
}
