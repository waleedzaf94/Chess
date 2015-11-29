
public class Piece implements Cloneable{
	
	private String type; // Pawn, Rook, Knight, Bishop, Queen, King, Blank
	private char side; // W, B
	private char representation; // P, R, N, B, Q, K, -
	
	public Piece(String type, char side, char representation){
		this.type = type;
		this.side = side;
		this.representation = representation;
	}

	public char getRepresentation() {
		return representation;
	}

	public void setRepresentation(char representation) {
		this.representation = representation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public char getSide() {
		return side;
	}

	public void setSide(char side) {
		this.side = side;
	}
	
	

}
