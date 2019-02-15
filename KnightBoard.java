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

  private boolean addKnight(int row, int col, int num) {
    if (board[row][col] == 0) {
      board[row][col] = num;
      return true;
    }
    return false;
  }

  private int removeKnight(int row, int col) {
    int i = board[row][col];
    board[row][col] = 0;
    return i;
  }

  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(4,5);
    k.board[2][4] = 4;
    k.addKnight(1,3,1);
    k.removeKnight(2,4);
    System.out.println(k);
  }
}
