public class Square {
  private int row, col, val;

  //row that has been designated a value.
  public Square(int r, int c, int v) {
    row = r;
    col = c;
    val = v;
  }

  //un-numbered square (default state)
  public Square(int r, int c) {
    row = r;
    col = c;
  }

  public boolean isFree() {
    return val == 0;
  }

  public int getValue() {
    return val;
  }
  public int getRow() {
    return row;
  }
  public int getCol() {
    return col;
  }

  public void setValue(int v) {
    val = v;
  }

}
