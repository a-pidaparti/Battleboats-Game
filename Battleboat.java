// Written by pidap008

public class Battleboat {

    private int size = 3;
    private boolean orientation; // false <-> horizontal, true <-> vertical
    private Cell[] spaces;

    // TODO: randomly set the orientation of the boat DONE
    // TODO: set size of the boat (default to 3-cells long)
    // TODO: declare the Cell objects associated with each boat

    public Battleboat(int size, boolean orientation, Cell[] spaces){
        this.size = size;
        this.orientation = orientation;
        this.spaces = spaces;
        int cellCount = 0;
        while (cellCount < spaces.length){
            spaces[cellCount].set_status('B');
            cellCount ++;
        }
    } // constructor

    public Battleboat(boolean orientation, Cell[] spaces){
        this(3, orientation, spaces);
    }   //default to 3 cells long

    public boolean get_orientation(){
        return orientation;
    } // get_orientation

    public void set_orientation(boolean newOrientation){
        this.orientation = newOrientation;
    }

    public int get_size(){
        return size;
    } // get_size

    public Cell[] get_spaces(){
        return spaces;
    } // get_spaces

    public void set_spaces(Cell[] newSpaces){
        spaces = newSpaces;
    }   // setter method for array of occupied spaces


} // class
