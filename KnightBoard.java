public class KnightBoard {
  int[][] board;

  public KnightBoard(int startingRows, int startingCols) {
    if (startingRows < 0 || startingCols < 0) throw new IllegalArgumentException("rows and columns must be positive.");
    board = new int[startingRows][startingCols];
  }

  public String toString() {
    String result = "";
    for (int[] row: board) {
      for (int i: row) {
        if (i==0) {
          result += "_";
        } else {
          result += i;
        }
      }
      result += "\n";
    }
    return result;
  }

  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(4,5);
    k.board[2][4] = 4;
    System.out.println(k);
  }
}
