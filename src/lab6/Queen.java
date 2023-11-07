package lab6;

public class Queen {
	private int row;
	private int column;

	public Queen(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public void move() {
		this.row++;
		if (this.row == Node.N) {
			this.row = 0;
		}
	}

	// check whether this Queen can attack the given Queen (q)
	public boolean isConflict(Queen q) {
		for (int i = 0; i < Node.N; i++) {
			if (this.row == q.row || this.column == q.column
					|| this.row - this.column == q.row - q.column
					|| this.row + this.column == q.row + q.column) {
				return true;
			}
		}
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}

}
