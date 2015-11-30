import java.util.ArrayList;
import java.util.Scanner;

public class Board implements Cloneable {
	
	private Piece [][] board = new Piece [8][8];
	private double score;
	private String hash;
	private boolean wrmoved, wlmoved, wkmoved, brmoved, blmoved, bkmoved;
	
	public static void main(String [] args){
		Board iboard = new Board(false, false, false, false, false, false);
		iboard.initBoard();
		ArrayList<Board> results = iboard.generateMoves(iboard, 'W');
		int i=0;
		for (Board b: results){
			System.out.println(i++);
			printboard(b.getBoard());
			System.out.println("\n=====================================\n");
		}
	}

	public Board(boolean wrmoved, boolean wlmoved, boolean wkmoved, boolean brmoved, boolean blmoved, boolean bkmoved){
		this.wrmoved = wrmoved;
		this.wlmoved = wlmoved;
		this.wkmoved = wkmoved;
		this.brmoved = brmoved;
		this.blmoved = blmoved;
		this.bkmoved = bkmoved;
	}
	
	@SuppressWarnings("unused")
	private static Piece[][] getBoardInput(){
		Piece[][] board = new Piece[8][8];
		Scanner in = new Scanner(System.in);
		String [] input = new String [8];
		for (int i=0; i<8; i++){
			input[i] = in.nextLine();
		}
		char [][] reps = new char[8][8];
		for (int i=0; i<8; i++){
			int j=0;
			int k=0;
			while (j<input[i].length()){
				reps[i][k++] = input[i].charAt(j);
				j += 2;
			}
		}
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				char rep = reps[i][j];
				switch (rep){
				case 'P':
					board[i][j] = new Piece("Pawn", 'W', 'P');
					break;
				case 'R':
					board[i][j] = new Piece("Rook", 'W', 'R');
					break;
				case 'N':
					board[i][j] = new Piece("Knight", 'W', 'N');
					break;
				case 'B':
					board[i][j] = new Piece("Bishop", 'W', 'B');
					break;
				case 'Q':
					board[i][j] = new Piece("Queen", 'W', 'Q');
					break;
				case 'K':
					board[i][j] = new Piece("King", 'W', 'K');
					break;
				case '-':
					board[i][j] = new Piece("Blank", 'N', '-');
					break;
				case 'p':
					board[i][j] = new Piece("Pawn", 'B', 'p');
					break;
				case 'r':
					board[i][j] = new Piece("Rook", 'B', 'r');
					break;
				case 'n':
					board[i][j] = new Piece("Knight", 'B', 'n');
					break;
				case 'b':
					board[i][j] = new Piece("Bishop", 'B', 'b');
					break;
				case 'q':
					board[i][j] = new Piece("Queen", 'B', 'q');
					break;
				case 'k':
					board[i][j] = new Piece("King", 'B', 'k');
					break;
				}	
			}
		}
		in.close();
		return board;
	}
	
	
	private void initBoard(){
		int i=1, j=0;
		for (j=0; j<8; j++){
			board[i][j] = new Piece("Pawn", 'W', 'P');
		}
		i=6;
		for (j=0; j<8; j++){
			board[i][j] = new Piece("Pawn", 'B', 'p');
		}
		i=0;
		char s = 'W';
		board[i][0] = new Piece("Rook", s, 'R');
		board[i][1] = new Piece("Knight", s, 'N');
		board[i][2] = new Piece("Bishop", s, 'B');
		board[i][3] = new Piece("King", s, 'K');
		board[i][4] = new Piece("Queen", s, 'Q');
		board[i][5] = new Piece("Bishop", s, 'B');
		board[i][6] = new Piece("Knight", s, 'N');
		board[i][7] = new Piece("Rook", s, 'R');
		i = 7;
		s = 'B';
		board[i][0] = new Piece("Rook", s, 'r');
		board[i][1] = new Piece("Knight", s, 'n');
		board[i][2] = new Piece("Bishop", s, 'b');
		board[i][3] = new Piece("King", s, 'k');
		board[i][4] = new Piece("Queen", s, 'q');
		board[i][5] = new Piece("Bishop", s, 'b');
		board[i][6] = new Piece("Knight", s, 'n');
		board[i][7] = new Piece("Rook", s, 'r');
		
		for (int x=2; x<6; x++){
			for (int y=0; y<8; y++){
				board[x][y] = new Piece("Blank", 'N', '-');
			}
		}
	}

	
	private Board movePiece(Board iboard, int[] init, int[] targ){
		Piece[][] board = iboard.getBoard();
		Piece orig = board[init[0]][init[1]];
		Piece[][] newboard = deepCopyBoard(board);
		boolean wr=iboard.getWrmoved(), wl=iboard.getWlmoved(), wk=iboard.getWkmoved();
		boolean br=iboard.getBrmoved(), bl=iboard.getBlmoved(), bk=iboard.getBkmoved();
		boolean moved=false;
		if (orig.getType()=="King"){
			if (orig.getSide()=='W'){
				if (wk==false){
					if (targ[1]==1 && targ[0]==0){
						if (wr==false){
							newboard[0][1] = new Piece("King", 'W', 'K');
							newboard[0][2] = new Piece("Rook", 'W', 'R');
							newboard[0][0] = new Piece("Blank", 'N', '-');
							newboard[0][3] = new Piece("Blank", 'N', '-');
							moved = true;
						}
					}
					else if (targ[1]==5 && targ[0]==0){
						if (wl==false){
							newboard[0][5] = new Piece("King", 'W', 'K');
							newboard[0][4] = new Piece("Rook", 'W', 'R');
							newboard[0][3] = new Piece("Blank", 'N', '-');
							newboard[0][6] = new Piece("Blank", 'N', '-');
							newboard[0][7] = new Piece("Blank", 'N', '-');
							moved = true;
						}
					}
				}
			}
			else if (orig.getSide()=='B'){
				if (bk==false){
					if (targ[1]==1 && targ[0]==7){
						if (br==false){
							newboard[7][1] = new Piece("King", 'W', 'K');
							newboard[7][2] = new Piece("Rook", 'W', 'R');
							newboard[7][0] = new Piece("Blank", 'N', '-');
							newboard[7][3] = new Piece("Blank", 'N', '-');
							moved = true;
						}
					}
					else if (targ[1]==5 && targ[0]==7){
						if (bl==false){
							newboard[7][5] = new Piece("King", 'W', 'K');
							newboard[7][4] = new Piece("Rook", 'W', 'R');
							newboard[7][3] = new Piece("Blank", 'N', '-');
							newboard[7][6] = new Piece("Blank", 'N', '-');
							newboard[7][7] = new Piece("Blank", 'N', '-');
							moved = true;
						}
					}
				}
			}
		}
		if (!moved){
			newboard[targ[0]][targ[1]] = new Piece(orig.getType(), orig.getSide(), orig.getRepresentation());
			newboard[init[0]][init[1]] = new Piece("Blank", 'N', '-');
		}
		if (orig.getType()=="Castle"){
			if (init[1]==0){
				if (orig.getSide()=='W')
					wr=true;
				else if (orig.getSide()=='B')
					br=true;
			}
			else if (init[1]==7){
				if (orig.getSide()=='W')
					wl=true;
				else if (orig.getSide()=='B')
					bl=true;
			}
		}
		else if (orig.getType()=="King"){
			if (orig.getSide()=='W')
				wk=true;
			else if (orig.getSide()=='B')
				bk=true;
		}
		Board newiboard = new Board(wr, wl, wk, br, bl, bk);
		newiboard.setBoard(newboard);
		return newiboard;
	}
	
	private ArrayList<Board> generateMoves(Board iBoard, char side){
		ArrayList<Board> results = new ArrayList<>();
		Piece[][] board = iBoard.getBoard();
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				Piece piece = board[i][j];
				int [] cood = {i, j};
				if (piece.getSide() == side){
					switch (piece.getType()){
					case "Pawn":
						ArrayList<Board> pmoves = movePawn(iBoard, cood);
						results.addAll(pmoves);
						break;
					case "Rook":
						ArrayList<Board> rmoves = moveRook(iBoard, cood);
						results.addAll(rmoves);
						break;
					case "Knight":
						ArrayList<Board> nmoves = moveKnight(iBoard, cood);
						results.addAll(nmoves);
						break;
					case "Bishop":
						ArrayList<Board> bmoves = moveBishop(iBoard, cood);
						results.addAll(bmoves);
						break;
					case "Queen":
						ArrayList<Board> qmoves = moveQueen(iBoard, cood);
						results.addAll(qmoves);
						break;
					case "King":
						ArrayList<Board> kmoves = moveKing(iBoard, cood);
						results.addAll(kmoves);
						break;
					default:
						break;
					}
				}
			}
		}
		return results;
	}

	private ArrayList<Board> movePawn(Board iBoard, int[] cood){
		ArrayList<Board> results = new ArrayList<>();
		int r = cood[0]; //Rank
		int f = cood[1]; //File
		Piece[][] board = iBoard.getBoard();
		Piece piece = board[r][f];
		char side = piece.getSide();
		if (side == 'W'){
			int [][] moves = {{1, 0}, {2, 0}, {1, -1}, {1, 1}};
			for (int i=0; i<4; i++){
				int [] move = moves[i];
				int tx = r+move[0];
				int ty = f+move[1];
				int [] target = {tx, ty};
				if (tx>-1 && tx<8 && ty>-1 && ty<8){
					if (pawnValid(board, cood, target)){
						if (tx==7){
							boolean wr=iBoard.getWrmoved(), wl=iBoard.getWlmoved(), wk=iBoard.getWkmoved();
							boolean br=iBoard.getBrmoved(), bl=iBoard.getBlmoved(), bk=iBoard.getBkmoved();
							//Promote to Queen
							Piece[][] newboard = deepCopyBoard(board);
							Board newiboard = new Board(wr, wl, wk, br, bl, bk);
							newboard[tx][ty] = new Piece("Queen", side, 'Q');
							newboard[r][f] = new Piece("Blank", 'N', '-');
							newiboard.setBoard(newboard);
							results.add(newiboard);
							//Promote to Rook
							Piece[][] newboardr = deepCopyBoard(board);
							Board newiboardr = new Board(wr, wl, wk, br, bl, bk);
							newboardr[tx][ty] = new Piece("Rook", side, 'R');
							newboardr[r][f] = new Piece("Blank", 'N', '-');
							newiboardr.setBoard(newboardr);
							results.add(newiboardr);
							//Promote to Knight
							Piece[][] newboardn = deepCopyBoard(board);
							Board newiboardn = new Board(wr, wl, wk, br, bl, bk);
							newboardn[tx][ty] = new Piece("Knight", side, 'N');
							newboardn[r][f] = new Piece("Blank", 'N', '-');
							newiboardn.setBoard(newboardn);
							results.add(newiboardn);							
							//Promote to Bishop
							Piece[][] newboardb = deepCopyBoard(board);
							Board newiboardb = new Board(wr, wl, wk, br, bl, bk);
							newboardb[tx][ty] = new Piece("Bishop", side, 'B');
							newboardb[r][f] = new Piece("Blank", 'N', '-');
							newiboardb.setBoard(newboardb);
							results.add(newiboardb);							
						}
						else{
							Piece[][] newboard = deepCopyBoard(board);
							Board newiboard = new Board(iBoard.getWrmoved(), iBoard.getWlmoved(), iBoard.getWkmoved(), iBoard.getBrmoved(), iBoard.getBlmoved(), iBoard.getBkmoved());
							newboard[tx][ty] = new Piece(piece.getType(), piece.getSide(), piece.getRepresentation());
							newboard[r][f] = new Piece("Blank", 'N', '-');
							newiboard.setBoard(newboard);
							results.add(newiboard);
						}
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
						if (tx==0){
							boolean wr=iBoard.getWrmoved(), wl=iBoard.getWlmoved(), wk=iBoard.getWkmoved();
							boolean br=iBoard.getBrmoved(), bl=iBoard.getBlmoved(), bk=iBoard.getBkmoved();
							//Promote to Queen
							Piece[][] newboard = deepCopyBoard(board);
							Board newiboard = new Board(wr, wl, wk, br, bl, bk);
							newboard[tx][ty] = new Piece("Queen", side, 'q');
							newboard[r][f] = new Piece("Blank", 'N', '-');
							newiboard.setBoard(newboard);
							results.add(newiboard);
							//Promote to Rook
							Piece[][] newboardr = deepCopyBoard(board);
							Board newiboardr = new Board(wr, wl, wk, br, bl, bk);
							newboardr[tx][ty] = new Piece("Rook", side, 'r');
							newboardr[r][f] = new Piece("Blank", 'N', '-');
							newiboardr.setBoard(newboardr);
							results.add(newiboardr);
							//Promote to Knight
							Piece[][] newboardn = deepCopyBoard(board);
							Board newiboardn = new Board(wr, wl, wk, br, bl, bk);
							newboardn[tx][ty] = new Piece("Knight", side, 'n');
							newboardn[r][f] = new Piece("Blank", 'N', '-');
							newiboardn.setBoard(newboardn);
							results.add(newiboardn);							
							//Promote to Bishop
							Piece[][] newboardb = deepCopyBoard(board);
							Board newiboardb = new Board(wr, wl, wk, br, bl, bk);
							newboardb[tx][ty] = new Piece("Bishop", side, 'b');
							newboardb[r][f] = new Piece("Blank", 'N', '-');
							newiboardb.setBoard(newboardb);
							results.add(newiboardb);
						}
						else{
							Piece[][] newboard = deepCopyBoard(board);
							Board newiboard = new Board(iBoard.getWrmoved(), iBoard.getWlmoved(), iBoard.getWkmoved(), iBoard.getBrmoved(), iBoard.getBlmoved(), iBoard.getBkmoved());
							newboard[tx][ty] = new Piece(piece.getType(), piece.getSide(), piece.getRepresentation());
							newboard[r][f] = new Piece("Blank", 'N', '-');
							newiboard.setBoard(newboard);
							//Set new board hash
							//Set new board score
							results.add(newiboard);
						}
					}
				}
			}
		}
		return results;
	}
	
	private boolean pawnValid(Piece[][] board, int [] init, int [] target){
		int ix = init[0];
		int iy = init[1];
		int tx = target[0];
		int ty = target[1];
		if (tx<0 || ty<0 || tx>7 || ty>7) return false; //Check if target is on board
		Piece ipiece = board[ix][iy];
		Piece tpiece = board[tx][ty];
		if (iy==ty){
			if (tpiece.getType() != "Blank") return false; //Check if target is blank
			int vdiff = Math.abs(tx-ix);
			if (vdiff > 1){
				if (ipiece.getSide() == 'W'){
					if (ix != 1) return false; //In case starting rank isn't 1
					if (board[tx+1][ty].getType() != "Blank") return false; //In case there is another square in between initial and target
				}
				else{
					if (ix != 6) return false; //In case starting rank isn't 6
					if (board[tx-1][ty].getType() != "Blank") return false;
				}
			}
			
		}
		else{
			if (tpiece.getSide() == ipiece.getSide()) return false;
			if (tpiece.getSide() == 'N') return false;
		}
		Piece blp = new Piece("Blank", 'N', '-');
		Piece orig = board[tx][ty];
		board[tx][ty] = board[ix][iy];
		board[ix][iy] = blp;
		boolean check = testCheck(board, ipiece.getSide());
		board[ix][iy] = board[tx][ty];
		board[tx][ty] = orig;
		if (check) return false;
		return true;
	}
	
	
	private ArrayList<Board> moveBishop(Board iBoard, int[] cood){
		ArrayList<Board> results = new ArrayList<>();
		int r = cood[0]; //rank
		int f = cood[1]; //file
		Piece[][] board = iBoard.getBoard();
		Piece piece = board[r][f];
		char side = piece.getSide();
		char enemy;
		if (side == 'W') enemy = 'B';
		else enemy = 'W';
		//Check north east
		for (int i=0; i<8; i++){
			int x = r+i;
			int y = f+i;
			if (x>-1 && x<8 && y>-1 && y<8){
				Piece target = board[x][y];
				if (target.getSide() == side){
					break;
				}
				else{
					Piece blp = new Piece("Blank", 'N', '-');
					Piece orig = board[x][y];
					board[x][y] = board[r][f];
					board[r][f] = blp;
					boolean check = testCheck(board, side);
					board[x][y] = orig;
					board[r][f] = piece;
					if (!check){
						Piece [][] newboard = deepCopyBoard(board);
						newboard[x][y] = new Piece(piece.getType(), piece.getSide(), piece.getRepresentation());
						newboard[r][f] = new Piece("Blank", 'N', '-');
						Board newiboard = new Board(iBoard.getWrmoved(), iBoard.getWlmoved(), iBoard.getWkmoved(), iBoard.getBrmoved(), iBoard.getBlmoved(), iBoard.getBkmoved());
						newiboard.setBoard(newboard);
						results.add(newiboard);
					}
					if (target.getSide() == enemy)
						break;
				}
			}
		}
		//Check north west
		for (int i=0; i<8; i++){
			int x = r-i;
			int y = f+i;
			if (x>-1 && x<8 && y>-1 && y<8){
				Piece target = board[x][y];
				if (target.getSide() == side){
					break;
				}
				else{
					Piece blp = new Piece("Blank", 'N', '-');
					Piece orig = board[x][y];
					board[x][y] = board[r][f];
					board[r][f] = blp;
					boolean check = testCheck(board, side);
					board[x][y] = orig;
					board[r][f] = piece;
					if (!check){
						Piece [][] newboard = deepCopyBoard(board);
						newboard[x][y] = new Piece(piece.getType(), piece.getSide(), piece.getRepresentation());
						newboard[r][f] = new Piece("Blank", 'N', '-');
						Board newiboard = new Board(iBoard.getWrmoved(), iBoard.getWlmoved(), iBoard.getWkmoved(), iBoard.getBrmoved(), iBoard.getBlmoved(), iBoard.getBkmoved());
						newiboard.setBoard(newboard);
						results.add(newiboard);
					}
					if (target.getSide() == enemy)
						break;
				}
			}
		}
		//Check south west
		for (int i=0; i<8; i++){
			int x = r-i;
			int y = f-i;
			if (x>-1 && x<8 && y>-1 && y<8){
				Piece target = board[x][y];
				if (target.getSide() == side){
					break;
				}
				else{
					Piece blp = new Piece("Blank", 'N', '-');
					Piece orig = board[x][y];
					board[x][y] = board[r][f];
					board[r][f] = blp;
					boolean check = testCheck(board, side);
					board[x][y] = orig;
					board[r][f] = piece;
					if (!check){
						Piece [][] newboard = deepCopyBoard(board);
						newboard[x][y] = new Piece(piece.getType(), piece.getSide(), piece.getRepresentation());
						newboard[r][f] = new Piece("Blank", 'N', '-');
						Board newiboard = new Board(iBoard.getWrmoved(), iBoard.getWlmoved(), iBoard.getWkmoved(), iBoard.getBrmoved(), iBoard.getBlmoved(), iBoard.getBkmoved());
						newiboard.setBoard(newboard);
						results.add(newiboard);
					}
					if (target.getSide() == enemy)
						break;
				}
			}
		}
		//Check south east
		for (int i=0; i<8; i++){
			int x = r+i;
			int y = f-i;
			if (x>-1 && x<8 && y>-1 && y<8){
				Piece target = board[x][y];
				if (target.getSide() == side){
					break;
				}
				else{
					Piece blp = new Piece("Blank", 'N', '-');
					Piece orig = board[x][y];
					board[x][y] = board[r][f];
					board[r][f] = blp;
					boolean check = testCheck(board, side);
					board[x][y] = orig;
					board[r][f] = piece;
					if (!check){
						Piece [][] newboard = deepCopyBoard(board);
						newboard[x][y] = new Piece(piece.getType(), piece.getSide(), piece.getRepresentation());
						newboard[r][f] = new Piece("Blank", 'N', '-');
						Board newiboard = new Board(iBoard.getWrmoved(), iBoard.getWlmoved(), iBoard.getWkmoved(), iBoard.getBrmoved(), iBoard.getBlmoved(), iBoard.getBkmoved());
						newiboard.setBoard(newboard);
						results.add(newiboard);
					}
					if (target.getSide() == enemy)
						break;
				}
			}
		}
		return results;
	}
	

	private ArrayList<Board> moveKnight(Board iBoard, int[] cood){
		ArrayList<Board> results = new ArrayList<>();
		int r = cood[0]; //rank
		int f = cood[1]; //file
		Piece[][] board = iBoard.getBoard();
		Piece piece = board[r][f];
		char side = piece.getSide();
		int [][] moves = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
		for (int i=0; i<8; i++){
			int [] move = moves[i];
			int x = r+move[0]; //target rank
			int y = f+move[1]; //target file
			if (x>-1 && x<8 && y>-1 && y<8){
				Piece target = board[x][y]; //target piece
				if (target.getSide() != side){ //check if target piece isn't own piece
					Piece blp = new Piece("Blank", 'N', '-');
					Piece orig = board[x][y];
					board[x][y] = board[r][f];
					board[r][f] = blp;
					boolean check = testCheck(board, piece.getSide());
					board[r][f] = board[x][y];
					board[x][y] = orig;
					if (!check){
						Piece[][] newboard = deepCopyBoard(board);
						Board newiboard = new Board(iBoard.getWrmoved(), iBoard.getWlmoved(), iBoard.getWkmoved(), iBoard.getBrmoved(), iBoard.getBlmoved(), iBoard.getBkmoved());
						newboard[x][y] = new Piece(piece.getType(), side, piece.getRepresentation());
						newboard[r][f] = new Piece("Blank", 'N', '-');
						newiboard.setBoard(newboard);
						results.add(newiboard);
					}
				}
			}
		}
		return results;
	}
	

	private ArrayList<Board> moveRook(Board iBoard, int[] cood){
		ArrayList<Board> results = new ArrayList<>();
		int r = cood[0]; //rank
		int f = cood[1]; //file
		Piece[][] board = iBoard.getBoard();
		Piece piece = board[r][f];
		char side = piece.getSide();
		char enemy;
		if (side == 'W') enemy = 'B';
		else enemy = 'W';
		boolean wr = iBoard.getWrmoved(), wl = iBoard.getWlmoved(), wk =iBoard.getWkmoved();
		boolean br = iBoard.getBrmoved(), bl = iBoard.getBlmoved(), bk = iBoard.getBkmoved();
		//Check north
		for (int i=0; i<8; i++){
			int x = r+i;
			int y = f;
			if (x>-1 && x<8 && y>-1 && y<8){
				Piece target = board[x][y];
				if (target.getSide() == side){
					break;
				}
				else{
					Piece blp = new Piece("Blank", 'N', '-');
					Piece orig = board[x][y];
					board[x][y] = board[r][f];
					board[r][f] = blp;
					boolean check = testCheck(board, side);
					board[x][y] = orig;
					board[r][f] = piece;
					if (!check){
						if (f==0){
							if (side=='W') wr = true;
							else br = true;
						}
						if (f==7){
							if (side=='W') wl = true;
							else bl = true;
						}
						Piece [][] newboard = deepCopyBoard(board);
						newboard[x][y] = new Piece(piece.getType(), piece.getSide(), piece.getRepresentation());
						newboard[r][f] = new Piece("Blank", 'N', '-');
						Board newiboard = new Board(wr, wl, wk, br, bl, bk);
						newiboard.setBoard(newboard);
						results.add(newiboard);
					}
					if (target.getSide() == enemy)
						break;
				}
			}
		}
		//Check south
		for (int i=0; i<8; i++){
			int x = r-i;
			int y = f;
			if (x>-1 && x<8 && y>-1 && y<8){
				Piece target = board[x][y];
				if (target.getSide() == side){
					break;
				}
				else{
					Piece blp = new Piece("Blank", 'N', '-');
					Piece orig = board[x][y];
					board[x][y] = board[r][f];
					board[r][f] = blp;
					boolean check = testCheck(board, side);
					board[x][y] = orig;
					board[r][f] = piece;
					if (!check){
						if (f==0){
							if (side=='W') wr = true;
							else br = true;
						}
						if (f==7){
							if (side=='W') wl = true;
							else bl = true;
						}
						Piece [][] newboard = deepCopyBoard(board);
						newboard[x][y] = new Piece(piece.getType(), piece.getSide(), piece.getRepresentation());
						newboard[r][f] = new Piece("Blank", 'N', '-');
						Board newiboard = new Board(wr, wl, wk, br, bl, bk);
						newiboard.setBoard(newboard);
						results.add(newiboard);
					}
					if (target.getSide() == enemy)
						break;
				}
			}
		}
		//Check east
		for (int i=0; i<8; i++){
			int x = r;
			int y = f+i;
			if (x>-1 && x<8 && y>-1 && y<8){
				Piece target = board[x][y];
				if (target.getSide() == side){
					break;
				}
				else{
					Piece blp = new Piece("Blank", 'N', '-');
					Piece orig = board[x][y];
					board[x][y] = board[r][f];
					board[r][f] = blp;
					boolean check = testCheck(board, side);
					board[x][y] = orig;
					board[r][f] = piece;
					if (!check){
						if (f==0){
							if (side=='W') wr = true;
							else br = true;
						}
						if (f==7){
							if (side=='W') wl = true;
							else bl = true;
						}
						Piece [][] newboard = deepCopyBoard(board);
						newboard[x][y] = new Piece(piece.getType(), piece.getSide(), piece.getRepresentation());
						newboard[r][f] = new Piece("Blank", 'N', '-');
						Board newiboard = new Board(wr, wl, wk, br, bl, bk);
						newiboard.setBoard(newboard);
						results.add(newiboard);
					}
					if (target.getSide() == enemy)
						break;
				}
			}
		}
		//Check west
		for (int i=0; i<8; i++){
			int x = r;
			int y = f-i;
			if (x>-1 && x<8 && y>-1 && y<8){
				Piece target = board[x][y];
				if (target.getSide() == side){
					break;
				}
				else{
					Piece blp = new Piece("Blank", 'N', '-');
					Piece orig = board[x][y];
					board[x][y] = board[r][f];
					board[r][f] = blp;
					boolean check = testCheck(board, side);
					board[x][y] = orig;
					board[r][f] = piece;
					if (!check){
						if (f==0){
							if (side=='W') wr = true;
							else br = true;
						}
						if (f==7){
							if (side=='W') wl = true;
							else bl = true;
						}
						Piece [][] newboard = deepCopyBoard(board);
						newboard[x][y] = new Piece(piece.getType(), piece.getSide(), piece.getRepresentation());
						newboard[r][f] = new Piece("Blank", 'N', '-');
						Board newiboard = new Board(wr, wl, wk, br, bl, bk);
						newiboard.setBoard(newboard);
						results.add(newiboard);
					}
					if (target.getSide() == enemy)
						break;
				}
			}
		}
		return results;
	}
	
	
	private ArrayList<Board> moveQueen(Board iBoard, int[] cood){
		ArrayList<Board> results = new ArrayList<>();
		ArrayList<Board> rmoves = moveRook(iBoard, cood);
		ArrayList<Board> bmoves = moveBishop(iBoard, cood);
		results.addAll(rmoves);
		results.addAll(bmoves);
		return results;
	}
	
	
	private ArrayList<Board> moveKing(Board iBoard, int[] cood){
		ArrayList<Board> results = new ArrayList<>();
		int r = cood[0]; //rank
		int f = cood[1]; //file
		Piece[][] board = iBoard.getBoard();
		Piece piece = board[r][f];
		char side = piece.getSide();
		int [][] moves = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
		for (int i=0; i<8; i++){
			boolean wr = iBoard.getWrmoved(), wl = iBoard.getWlmoved(), wk =iBoard.getWkmoved();
			boolean br = iBoard.getBrmoved(), bl = iBoard.getBlmoved(), bk = iBoard.getBkmoved();
			int [] move = moves[i];
			int x = r+move[0]; //target rank
			int y = f+move[1]; //target file
			if (x>-1 && x<8 && y>-1 && y<8){
				Piece target = board[x][y]; //target piece
				if (target.getSide() != side){ //check if target piece isn't own piece
					Piece blp = new Piece("Blank", 'N', '-');
					Piece orig = board[x][y];
					board[x][y] = board[r][f];
					board[r][f] = blp;
					boolean check = testCheck(board, piece.getSide());
					board[r][f] = board[x][y];
					board[x][y] = orig;
					if (!check){
						if (side=='W') wk = true;
						else bk = true;
						Piece[][] newboard = deepCopyBoard(board);
						Board newiboard = new Board(wr, wl, wk, br, bl, bk);
						newboard[x][y] = new Piece(piece.getType(), side, piece.getRepresentation());
						newboard[r][f] = new Piece("Blank", 'N', '-');
						newiboard.setBoard(newboard);
						results.add(newiboard);
					}
				}
			}
		}
		boolean wr = iBoard.getWrmoved(), wl = iBoard.getWlmoved(), wk =iBoard.getWkmoved();
		boolean br = iBoard.getBrmoved(), bl = iBoard.getBlmoved(), bk = iBoard.getBkmoved();
		if (side == 'W'){
			if (!wk){
				if (!wr){
					boolean possible = true;
					Piece king = board[0][3];
					Piece one = board[0][1];
					Piece two = board[0][2];
					if (!(one.getType().equals("Blank"))) possible = false;
					if (!(two.getType().equals("Blank"))) possible = false;
					if (possible){
						Piece blp = new Piece("Blank", 'N', '-');
						board[0][1] = king;
						board[0][3] = blp;
						boolean checkone = testCheck(board, king.getSide());
						board[0][2] = king;
						board[0][1] = blp;
						boolean checktwo = testCheck(board, king.getSide());
						board[0][3] = king;
						board[0][2] = two;
						board[0][1] = one;
						if (checkone==false && checktwo==false){
							Piece[][] newboard = deepCopyBoard(board);
							newboard[0][1] = new Piece("King", 'W', 'K');
							newboard[0][2] = new Piece("Rook", 'W', 'R');
							newboard[0][0] = new Piece("Blank", 'N', '-');
							newboard[0][3] = new Piece("Blank", 'N', '-');
							Board newiboard = new Board(true, wl, true, br, bl, bk);
							newiboard.setBoard(newboard);
							results.add(newiboard);
						}
					}
				}
				if (!wl){
					boolean possible = true;
					Piece king = board[0][3];
					Piece four = board[0][4];
					Piece five = board[0][5];
					Piece six = board[0][6];
					if (!(four.getType().equals("Blank"))) possible = false;
					if (!(five.getType().equals("Blank"))) possible = false;
					if (!(six.getType().equals("Blank"))) possible = false;
					if (possible){
						board[0][4] = king;
						board[0][3] = four;
						boolean checkfour = testCheck(board, king.getSide());
						board[0][5] = king;
						board[0][4] = four;
						board[0][3] = five;
						boolean checkfive = testCheck(board, king.getSide());
						board[0][6] = king;
						board[0][5] = five;
						board[0][3] = six;
						boolean checksix = testCheck(board, king.getSide());
						board[0][3] = king;
						board[0][4] = four;
						board[0][5] = five;
						board[0][6] = six;
						if (checkfour==false && checkfive==false && checksix==false){
							Piece[][] newboard = deepCopyBoard(board);
							newboard[0][5] = new Piece("King", 'W', 'K');
							newboard[0][4] = new Piece("Rook", 'W', 'R');
							newboard[0][3] = new Piece("Blank", 'N', '-');
							newboard[0][6] = new Piece("Blank", 'N', '-');
							newboard[0][7] = new Piece("Blank", 'N', '-');
							Board newiboard = new Board(wr, true, true, br, bl, bk);
							newiboard.setBoard(newboard);
							results.add(newiboard);
						}
					}
				}
			}
		}
		else{
			if (!bk){
				if (!br){
					boolean possible = true;
					Piece king = board[7][3];
					Piece one = board[7][1];
					Piece two = board[7][2];
					if (!(one.getType().equals("Blank"))) possible = false;
					if (!(two.getType().equals("Blank"))) possible = false;
					if (possible){
						Piece blp = new Piece("Blank", 'N', '-');
						board[7][1] = king;
						board[7][3] = blp;
						boolean checkone = testCheck(board, king.getSide());
						board[7][2] = king;
						board[7][1] = blp;
						boolean checktwo = testCheck(board, king.getSide());
						board[7][3] = king;
						board[7][2] = two;
						board[7][1] = one;
						if (checkone==false && checktwo==false){
							Piece[][] newboard = deepCopyBoard(board);
							newboard[7][1] = new Piece("King", 'B', 'K');
							newboard[7][2] = new Piece("Rook", 'B', 'R');
							newboard[7][0] = new Piece("Blank", 'N', '-');
							newboard[7][3] = new Piece("Blank", 'N', '-');
							Board newiboard = new Board(wr, wl, wk, true, bl, true);
							newiboard.setBoard(newboard);
							results.add(newiboard);
						}
					}
				}
				if (!bl){
					boolean possible = true;
					Piece king = board[7][3];
					Piece four = board[7][4];
					Piece five = board[7][5];
					Piece six = board[7][6];
					if (!(four.getType().equals("Blank"))) possible = false;
					if (!(five.getType().equals("Blank"))) possible = false;
					if (!(six.getType().equals("Blank"))) possible = false;
					if (possible){
						board[7][4] = king;
						board[7][3] = four;
						boolean checkfour = testCheck(board, king.getSide());
						board[7][5] = king;
						board[7][4] = four;
						board[7][3] = five;
						boolean checkfive = testCheck(board, king.getSide());
						board[7][6] = king;
						board[7][5] = five;
						board[7][3] = six;
						boolean checksix = testCheck(board, king.getSide());
						board[7][3] = king;
						board[7][4] = four;
						board[7][5] = five;
						board[7][6] = six;
						if (checkfour==false && checkfive==false && checksix==false){
							Piece[][] newboard = deepCopyBoard(board);
							newboard[7][5] = new Piece("King", 'B', 'K');
							newboard[7][4] = new Piece("Rook", 'B', 'R');
							newboard[7][3] = new Piece("Blank", 'N', '-');
							newboard[7][6] = new Piece("Blank", 'N', '-');
							newboard[7][7] = new Piece("Blank", 'N', '-');
							Board newiboard = new Board(wr, wl, wk, br, true, true);
							newiboard.setBoard(newboard);
							results.add(newiboard);
						}
					}
				}
			}
		}
		return results;
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
					if (piece.getType().equals("King")) return true; //Check if king is in surrounding 8 squares
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
	
	
	private int evaluate(Board iboard){
		int wk=0, wq=0, wr=0, wb=0, wn=0, wp=0;
		int bk=0, bq=0, br=0, bb=0, bn=0, bp=0;
		Piece[][] board = iboard.getBoard();
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				Piece piece = board[i][j];
				switch (piece.getRepresentation()){
				case 'K':
					wk++;
					break;
				case 'Q':
					wq++;
					break;
				case 'R':
					wr++;
					break;
				case 'B':
					wb++;
					break;
				case 'N':
					wn++;
					break;
				case 'P':
					wp++;
					break;
				case 'k':
					bk++;
					break;
				case 'q':
					bq++;
					break;
				case 'r':
					br++;
					break;
				case 'b':
					bb++;
					break;
				case 'n':
					bn++;
					break;
				case 'p':
					bp++;
					break;
				default:
					break;
				}	
			}
		}
		int score = 0;
		score += 200 * (wk-bk);
		score += 9 * (wq-bq);
		score += 5 * (wr-br);
		score += 3 * (wn-bn);
		score += 3 * (wb-bb);
		score += (wp-bp);
		return score;
	}
	
	public static Piece [][] deepCopyBoard(Piece [][] orig){
		Piece [][] board = new Piece[8][8];
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				Piece piece = orig[i][j];
				board[i][j] = new Piece(piece.getType(), piece.getSide(), piece.getRepresentation());
			}
		}
		return board;
	}
	
	public static void printboard(Piece [][] board){
		for (int i=7; i>=0; i--){
			for (int j=7; j>=0; j--){
				System.out.print(board[i][j].getRepresentation() + " ");
			}
			System.out.println();
		}
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

	
	public boolean getWrmoved() {
		return wrmoved;
	}

	
	public void setWrmoved(boolean wrmoved) {
		this.wrmoved = wrmoved;
	}

	
	public boolean getWlmoved() {
		return wlmoved;
	}

	
	public void setWlmoved(boolean wlmoved) {
		this.wlmoved = wlmoved;
	}

	
	public boolean getWkmoved() {
		return wkmoved;
	}

	
	public void setWkmoved(boolean wkmoved) {
		this.wkmoved = wkmoved;
	}

	
	public boolean getBrmoved() {
		return brmoved;
	}

	
	public void setBrmoved(boolean brmoved) {
		this.brmoved = brmoved;
	}

	
	public boolean getBlmoved() {
		return blmoved;
	}

	
	public void setBlmoved(boolean blmoved) {
		this.blmoved = blmoved;
	}

	
	public boolean getBkmoved() {
		return bkmoved;
	}

	
	public void setBkmoved(boolean bkmoved) {
		this.bkmoved = bkmoved;
	}

	
}
