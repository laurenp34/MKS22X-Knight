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
    if (row >= board.length || col >= board[0].length || row < 0 || col < 0) {
      return false;
    }
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

  public boolean solve() {
    addKnight(0,0,1);
    return solve(0,0,1);
  }

  public boolean solve(int r, int c, int num) {
    if (num == board.length * board[0].length) {
      return true;
    }
    int[] rMov = {2,2,-2,-2,1,1,-1,-1};
    int[] cMov = {1,-1,1,-1,2,-2,2,-2};
    for (int i=0;i<8;i++) {
      int newR = r + rMov[i];
      int newC = c + cMov[i];
      System.out.println("testing: "+newR+","+newC);
      if (addKnight(newR,newC,num+1)) {
        System.out.println("placed: "+newR+","+newC);
        System.out.println(this);
        num++;
        if (solve(newR,newC,num)) {
          return true;
        }
        removeKnight(newR,newC);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(4,5);
    //k.board[2][4] = 4;
    //k.addKnight(1,3,1);
    //k.removeKnight(2,4);
    //System.out.println(k);
    k.solve();
    System.out.println(k);
  }
}
