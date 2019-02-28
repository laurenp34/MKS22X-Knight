import java.io.*;
import java.util.*;
public class KnightBoard {
  int[][] board;
  //Square[][] sBoard;

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
    if (row >= board.length || col >= board[0].length || row < 0 || col < 0 || board[row][col] != 0)   {
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
  private void wait(int millis){
     try {
         Thread.sleep(millis);
     }
     catch (InterruptedException e) {
     }
 }


public void clearTerminal(){

    //erase terminal, go to top left of screen.

    System.out.println("\033[2J\033[1;1H");

}

  public boolean solveFast(int startingRow,int startingCol) {

    for (int[] row: board) {
      for (int i: row) {
        if (i!=0) throw new IllegalStateException("board must be cleared.");
      }
    }

    if (addKnight(startingRow,startingCol,1)) return solveFast(startingRow,startingCol,1);
    throw new IllegalArgumentException("startingRow and startingCol must be in bounds");
  }

  public boolean solveFast(int r, int c, int num) {
    //System.out.println(this);
    if (num == board.length * board[0].length) {
      return true;
    }
    int[] rMov = {2,2,-2,-2,1,1,-1,-1};
    int[] cMov = {1,-1,1,-1,2,-2,2,-2};

    ArrayList<Square> moves = new ArrayList<Square>();

    for (int i=0;i<8;i++) {
      int newR = r + rMov[i];
      int newC = c + cMov[i];
      //System.out.println("testing: "+newR+","+newC);
      if (canAddKnight(newR,newC) && countMoves(newR,newC) != 0) {



        Square temp = new Square(newR,newC,countMoves(newR,newC));
        moves.add(temp);
        //System.out.println("\tcountMoves: "+val);

      }
    }
    //System.out.println("rows: "+Arrays.toString(rows));
    //System.out.println("cols: "+Arrays.toString(cols));
    //sort3(vals,rows,cols);
    //System.out.println("rows: "+Arrays.toString(rows));
    //System.out.println("cols: "+Arrays.toString(cols));
    /*
    int moves = 0;
    for (int i=0;i<vals.length;i++) {
      if (vals[i] > 0) moves ++;
    }
    */
    //Collections.sort(moves);

    for (int i=0;i<moves.size();i++) {
      Square cur = moves.get(i);

      int nextR = cur.getRow();
      int nextC = cur.getCol();

    //  System.out.println("Placing @: "+nextR+","+nextC);
      //System.out.println(this);
      addKnight(nextR,nextC,num+1);

                clearTerminal();
                System.out.println(this);

                wait(20);

      if (solveFast(nextR,nextC,num+1)) {
        return true;
      }
    //  System.out.println("removing @" +nextR+","+nextC);
      removeKnight(nextR,nextC);

  }
        //System.out.println("placed: "+newR+","+newC);
        //System.out.println(this);

    return false;

  }

  private int countMoves(int r, int c) {
    int count = 0;
    int[] rMov = {2,2,-2,-2,1,1,-1,-1};
    int[] cMov = {1,-1,1,-1,2,-2,2,-2};
    for (int i=0;i<8;i++) {
      int newR = r+rMov[i];
      int newC = c+cMov[i];

      if (canAddKnight(newR,newC)){
        count ++;

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
      //System.out.println(count+1);
      //System.out.println(this);
      return 1;
    }
    int[] rMov = {2,2,-2,-2,1,1,-1,-1};
    int[] cMov = {1,-1,1,-1,2,-2,2,-2};

    int[] rows = new int[8];
    int[] cols = new int[8];
    int[] vals = new int[8];
    boolean first = true;
    for (int i=0;i<8;i++) {
      int newR = r + rMov[i];
      int newC = c + cMov[i];
      //System.out.println("testing: "+newR+","+newC);
      if (canAddKnight(newR,newC)) {

        int val = countMoves(newR,newC);
        //System.out.println("\tcountMoves: "+val);

          vals[i] = val;
          rows[i] = newR;
          cols[i] = newC;
          first = false;

      }
    }
    //sort
    sort3(vals,rows,cols);

    for (int i=0;i<vals.length;i++) {
      if (vals[i] != 0) {
        addKnight(rows[i],cols[i],num);
        //.out.println("placed: "+rows[i]+","+cols[i]);
        //System.out.println(this);
        num++;
        //System.out.println("COUNT: "+count);
        count += countFast(rows[i],cols[i],num);

        removeKnight(rows[i],cols[i]);
        num--;
      }
    }

    return count;
  }

  private static void sort3(int[] vals, int[] a2, int[] a3) {
    for (int i1=0;i1<8;i1++) {
      int smallest=0;
      int smIndex=0;
      boolean first = true;
      boolean found = false;
      int temp;
      for (int i2=i1;i2<8;i2++) {
        if ((vals[i2] > 0 && first) || (vals[i2] > 0 && vals[i2] < smallest)) {
          //System.out.println("smallest sofar: "+vals[i2]);
          smallest = vals[i2];
          smIndex = i2;
          first = false;
          found = true;
        }
      }
      if (found) {
        //System.out.println("overall smallest: "+vals[smIndex]);
      temp = vals[smIndex];
      vals[smIndex]=vals[i1];
      vals[i1] = temp;

      temp = a2[smIndex];
      a2[smIndex] = a2[i1];
      a2[i1] = temp;

      temp = a3[smIndex];
      a3[smIndex] = a3[i1];
      a3[i1] = temp;
    }
    }
  }


  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(4,5);
    //System.out.println(k.countMoves(0,0));
    System.out.println(k);
    System.out.println(k.solveFast(0,0));
    //k.countFast(0,0);
    //k.addKnight(3,4,14);
    //k.addKnight(1,2,3);
    //k.board[2][4] = 4;
    //k.addKnight(1,3,1);
    //k.removeKnight(2,4);
    //System.out.println(k);countFast(0,0)
    //System.out.println(k.countSolutions(0,0));
    System.out.println(k);
    /*
    int[] hi = {0, 0, 0, 0, 0, 3, 0, 1};
    int[] hii = {3, 2, 5, 3, 4, 7, 8, 3};
    int[] hiii = {4, 1, -1, 4, -5, 2, 8, 1};
    sort3(hi,hii,hiii);
    System.out.println(Arrays.toString(hi));
    System.out.println(Arrays.toString(hii));
    System.out.println(Arrays.toString(hiii));
    */

  }
}
