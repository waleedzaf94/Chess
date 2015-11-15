
public class Piece {
	
	private String type;
	private int [] position;
	private char side;
	private char representation;
	
	public Piece(String type, int [] position, char side, char representation){
		this.type = type;
		this.position = position;
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

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public char getSide() {
		return side;
	}

	public void setSide(char side) {
		this.side = side;
	}
	
	

}