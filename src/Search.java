import java.util.ArrayList;
import java.util.Scanner;

public class Search {

	public static void main(String[] args) {
		Board initial = new Board(false, false, false, false, false, false);
		initial.initBoard();
		Board.printboard(initial.getBoard());
		Node root = new Node();
		root.setBoard(initial);
		root.setValue(Board.evaluate(initial));
		Search search = new Search();
		while (true){
			int [] usermove = search.getUserMove();
			int [] init = {usermove[0], usermove[1]};
			int [] targ = {usermove[2], usermove[3]};
			Board one = Board.movePiece(root.getBoard(), init, targ);
			Board.printboard(one.getBoard());
			root.setBoard(one);
			root.setValue(Board.evaluate(one));
			if (Board.isMate(one)) break;
			Node alpha = new Node();
			Node beta = new Node();
			alpha.setValue(Double.NEGATIVE_INFINITY);
			beta.setValue(Double.POSITIVE_INFINITY);
			root = search.alphabeta(root, 1, alpha, beta, false);
			Board.printboard(root.getBoard().getBoard());
			if (Board.isMate(root.getBoard())) break;
		}

	}
	
	private Node alphabeta(Node node, int depth, Node alpha, Node beta, boolean maxPlayer){
		Board board = node.getBoard();
		char side;
		if (maxPlayer) side = 'W';
		else side = 'B';
		ArrayList<Board> moves = board.generateMoves(board, side);
		if (depth==0 || moves.size()==0){
			return node;
		}
		if (maxPlayer){
			Node v = new Node();
			v.setValue(Double.NEGATIVE_INFINITY);
			for (Board b: moves){
				Node child = new Node();
				child.setBoard(b);
				child.setValue(Board.evaluate(b));
				v = max(v, alphabeta(child, depth-1, alpha, beta, false));
				alpha = max(alpha, v);
				if (beta.getValue() <= alpha.getValue())
					break;
			}
			return v;
		}
		else{
			Node v = new Node();
			v.setValue(Double.POSITIVE_INFINITY);
			for (Board b: moves){
				Node child = new Node();
				child.setBoard(b);
				child.setValue(Board.evaluate(b));
				v = min(v, alphabeta(child, depth-1, alpha, beta, true));
				beta = min(beta, v);
				if (beta.getValue() <= alpha.getValue())
					break;
			}
			return v;
		}
	}
	
	private Node max(Node a, Node b){
		if (a.getValue() > b.getValue())
			return a;
		else
			return b;
	}
	
	private Node min(Node a, Node b){
		if (a.getValue() < b.getValue())
			return a;
		else
			return b;
	}
	
	private int [] getUserMove(){
		int [] move = new int [4];
		System.out.print("Input move: ");
		Scanner in = new Scanner(System.in);
		String m = in.nextLine();
		char r1 = m.charAt(0);
		char f1 = m.charAt(1);
		char r2 = m.charAt(2);
		char f2 = m.charAt(3);
		move[1] = rankToInt(r1);
		move[0] = Integer.parseInt(f1+"");
		move[3] = rankToInt(r2);
		move[2] = Integer.parseInt(f2+"");
//		in.close();
		return move;
	}
	
	private int rankToInt(char c){
		switch (c){
		case 'a':
			return 7;
		case 'b':
			return 6;
		case 'c':
			return 5;
		case 'd':
			return 4;
		case 'e':
			return 3;
		case 'f':
			return 2;
		case 'g':
			return 1;
		case 'h':
			return 0;
		case 'A':
			return 7;
		case 'B':
			return 6;
		case 'C':
			return 5;
		case 'D':
			return 4;
		case 'E':
			return 3;
		case 'F':
			return 2;
		case 'G':
			return 1;
		case 'H':
			return 0;
		default:
			return -1;
		}
	}

}
