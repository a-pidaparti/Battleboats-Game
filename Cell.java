// Written by pidap008

public class Cell {
    private int row;
    private int col;
    private char status; // ' ': Empty, 'B': Boat, 'H': Hit; 'M': Miss
    
    public char get_status(){
        if (status == 'B'){
          return('B');
        }
        if (status == 'H'){
          return('H');
        }
        if (status == 'M'){
          return('M');
        }
        else{
            return(' ');
        }
    } // get_status

    public void set_status(char c){
        status = c;
    } // set_status method

    public Cell(int row, int col, char status){ //initializes rows columns and status
        this.row = row;
        this.col = col;
        if (status == ' ' || status == 'B' || status == 'H' || status == 'M'){
            this.status = status;
            }
        else{
          System.out.println("invalid status, try again.");
        }
    } // constructor

    public Cell(){
        this(0,0,' ');
    }
    public int get_row(){
        return(row);
    }

    public int get_col(){
        return(col);
    }

    public String toString(){
        return row + ", " + col + ", " + status;
    }
} // class
