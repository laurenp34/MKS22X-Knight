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
          result += "__";
        } else {
          if (i<10) result += "0"+i;
          else result += i;
        }
        result += " ";
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

  private boolean canAddKnight(int row, int col) {
    if (row >= board.length || col >= board[0].length || row < 0 || col < 0 || board[row][col] != 0) {
      return false;
    }
    return true;
  }

  private void clearBoard() {
    for (int i=0;i<board.length;i++) {
      for (int i2=0;i2<board[0].length;i2++) {
      board[i][i2] = 0;
      }
    }
  }

  private int removeKnight(int row, int col) {
    int i = board[row][col];
    board[row][col] = 0;
    return i;
  }

  public boolean solve(int startingRow,int startingCol) {

    for (int[] row: board) {
      for (int i: row) {
        if (i!=0) throw new IllegalStateException("board must be cleared.");
      }
    }

    if (addKnight(startingRow,startingCol,1)) return solve(startingRow,startingCol,1);
    throw new IllegalArgumentException("startingRow and startingCol must be in bounds");
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
      //System.out.println("testing: "+newR+","+newC);
      if (addKnight(newR,newC,num+1)) {
        //System.out.println("placed: "+newR+","+newC);
        //System.out.println(this);
        num++;
        if (solve(newR,newC,num)) {
          return true;
        }
        removeKnight(newR,newC);
        num--;
      }
    }
    return false;
  }

  public int countSolutions(int startingRow, int startingCol) {
        for (int[] row: board) {
          for (int i: row) {
            if (i!=0) throw new IllegalStateException("board must be cleared.");
          }
        }

        if (startingRow < board.length && startingRow >= 0 && startingCol < board[0].length && startingCol >= 0) {
          addKnight(startingRow,startingCol,1);
           return countSolutions(startingRow,startingCol,1);
         }
        throw new IllegalArgumentException("startingRow and startingCol must be in bounds");
  }

  public int countSolutions(int r, int c, int num) {
    int count = 0;
    //System.out.println("count: "+count);
    if (num == board.length * board[0].length) {
      //System.out.println(count+1);
      //System.out.println(this);
      return 1;
    }
    int[] rMov = {2,2,-2,-2,1,1,-1,-1};
    int[] cMov = {1,-1,1,-1,2,-2,2,-2};
    for (int i=0;i<8;i++) {
      int newR = r + rMov[i];
      int newC = c + cMov[i];
      //System.out.println("testing: "+newR+","+newC);
      if (addKnight(newR,newC,num+1)) {
        //System.out.println("placed: "+newR+","+newC);
        //System.out.println(this);
        num++;
        //System.out.println("COUNT: "+count);
        count += countSolutions(newR,newC,num);
        removeKnight(newR,newC);
        num--;
      }
    }
    return count;
  }

  private int countMoves(int r, int c) {
    int count = 0;
    int[] rMov = {2,2,-2,-2,1,1,-1,-1};
    int[] cMov = {1,-1,1,-1,2,-2,2,-2};
    for (int i=0;i<8;i++) {
      int newR = r+rMov[i];
      int newC = c+cMov[i];

      if (addKnight(newR, newC, 0)){
        count ++;
        removeKnight(newR,newC);
      }
    }
    return count;
  }


  public int countFast(int startingRow, int startingCol) {
        for (int[] row: board) {
          for (int i: row) {
            if (i!=0) throw new IllegalStateException("board must be cleared.");
          }
        }

        if (startingRow < board.length && startingRow >= 0 && startingCol < board[0].length && startingCol >= 0) {
          addKnight(startingRow,startingCol,1);
           return countFast(startingRow,startingCol,2);
         }
        throw new IllegalArgumentException("startingRow and startingCol must be in bounds");
  }

  public int countFast(int r, int c, int num) {
    int count = 0;
    //System.out.println("count: "+count);
    if (num == board.length * board[0].length) {
      System.out.println(count+1);
      System.out.println(this);
      return 1;
    }
    int[] rMov = {2,2,-2,-2,1,1,-1,-1};
    int[] cMov = {1,-1,1,-1,2,-2,2,-2};
    int lowestRow=-1;
    int lowestCol=-1;
    int lowestVal=0;
    boolean first = true;
    for (int i=0;i<8;i++) {
      int newR = r + rMov[i];
      int newC = c + cMov[i];
      System.out.println("testing: "+newR+","+newC);
      if (canAddKnight(newR,newC)) {

        int val = countMoves(newR,newC);
        System.out.println("\tcountMoves: "+val);

        if (val < lowestVal || first) {
          lowestVal = val;
          lowestRow = newR;
          lowestCol = newC;
          first = false;
        }
      }
    }
    if (lowestRow >=0 && lowestCol >=0) {
      addKnight(lowestRow,lowestCol,num);
      System.out.println("placed: "+lowestRow+","+lowestCol);
      System.out.println(this);
      num++;
      //System.out.println("COUNT: "+count);
      count += countFast(lowestRow,lowestCol,num);

      removeKnight(lowestRow,lowestCol);
      num--;
    }

    return count;
  }


  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(4,4);
    //System.out.println(k.countMoves(0,0));
    System.out.println(k);
    k.countFast(0,0);
    //k.addKnight(3,4,14);
    //k.addKnight(1,2,3);
    //k.board[2][4] = 4;
    //k.addKnight(1,3,1);
    //k.removeKnight(2,4);
    //System.out.println(k);
    //System.out.println(k.countSolutions(0,0));
    System.out.println(k);
  }
}
