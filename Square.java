import java.lang.*;
import java.io.*;
import java.util.*;
public class Square implements Comparable<Square>{
  private int row, col, val; // val represents the moves possible for this square.

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

  public int compareTo(Square other) {
    if (other.getValue() == this.getValue()) return 0;
    if (other.getValue() < this.getValue()) return 1;
    if (other.getValue() > this.getValue()) return -1;
    return 0;
  }
  public String toString() {
    return ""+val;
  }

  public static void main(String[] args) {
    Square a = new Square(1, 1, 1);
    Square b = new Square(2,5,0);
    Square c = new Square(2,5,7);
    Square d = new Square(2,5,2);
    Square e = new Square(2,5,4);
    Square f = new Square(2,5,9);

    ArrayList<Square> s = new ArrayList<Square>();
    s.add(a);
    s.add(b);
    s.add(c);
    s.add(d);
    s.add(e);
    s.add(f);
    Collections.sort(s);
    System.out.println(s);

  }

}
