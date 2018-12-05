// written by pidap008

public class Board {
    private int num_rows;
    private int num_columns;
    private int num_boats;
    private Battleboat[] boats;
    private Cell[][] board = new Cell [12][12];
    private boolean debugMode;

    // TODO: Assign appropriate number of boats to num_boats variable DONE
    // TODO: Initialize the board as a 2-D Cell array DONE
    // TODO: Initialize boats as a Battleboat array
    // TODO: Place Battleboats appropriately on board and add them to the board's boats

  public Board(int m , int n, boolean debugMode){
        int upper;
        if (n > m){
            upper = n;
        }
        else{
            upper = m;
        }
        if (3 <= m && m <= 12 && 3 <= n && n <= 12) {
            this.num_rows = m;
            this.num_columns = n;
            if (m == 3 || n == 3) {
                this.num_boats = 1;
            }
            else if (3 < upper && upper <= 5) {
                this.num_boats = 2;
            }
            else if (5 < upper && upper <= 7) {
                this.num_boats = 3;
            }
            else if (7 < upper && upper <= 9) {
                this.num_boats = 4;
            }
            else if (9 < upper && upper <= 12) {
                this.num_boats = 6;
            }
        }

        else {
            System.out.println("Board size is outside of range 3 to 12. Try again.");
        }
        this.debugMode = debugMode;
        Cell[][] board = new Cell[num_rows][num_columns];
        this.board = board;
        for(int col = 0; col < num_columns;col++) { //instantiates Cell matrix
            for (int row = 0; row < num_rows; row++) {
                this.board[row][col] = new Cell(row, col, ' ');
            }
        }
        int boatCount = 0;
        boats = new Battleboat[num_boats];
        this.boats = boats;
        Cell[] spaces = new Cell[3]; // only placeholder until real space array is plugged in after boats are placed
        for (int i = 0; i < 3; i++){
            spaces[i] = new Cell(i,i,' ');
        }

       while (boatCount != num_boats) {
            boats[boatCount] = new Battleboat(5,randomOrientation(), spaces); //to change size from 3, enter int before orientation
            int[] moveCor = new int[2];
            moveCor = randomMove(this.boats[boatCount]);
            if (moveCor[0]== 13){
                break;
            }
            Cell[] newSpaces = spaceFilled(this.boats[boatCount], moveCor[0],moveCor[1]);
            boats[boatCount].set_spaces(newSpaces);
            boatCount++;
        }
  } // constructor

    //Determines if move is valid
    public boolean isValidMove(Battleboat boat, int r, int c){
        boolean validSwitch = true;         // operates off assumption that move is valid, but if space is taken switches to false
        if (boat.get_orientation()){  //if boat is vertical
            for(int rows = r; rows < r + boat.get_size(); rows ++){
                if (this.board.length - r < boat.get_size()){
                    validSwitch = false;
                }
                else if (this.board[rows][c].get_status() != ' '){
                    validSwitch = false;
                }

            }
        }
        else{       // if boat is horizontal
            for (int col = c; col < c + boat.get_size(); col ++){
                if (this.board[0].length - c < boat.get_size()){
                    validSwitch = false;
                }
                else if (this.board[r][col].get_status() != ' '){
                    validSwitch = false;
                }
            }
        }

        return (validSwitch);
  } // isValidMove

    //returns list of valid moves
    public Cell[] validMoves(Battleboat tempBoat) {
        int validMovesCount = 0;
        for (int row = 0; row < num_rows; row++) {      //finds number of valid moves
            for (int col = 0; col < num_columns; col++) {
                if (isValidMove(tempBoat, row, col)) {
                    validMovesCount++;
                }
            }
        }
        Cell[] validMoves = new Cell[validMovesCount]; //instantiates array with number of valid moves
        int i = 0;
        for (int row = 0; row < num_rows; row++) {      //populates array
            for (int col = 0; col < num_columns; col++) {
                if (isValidMove(tempBoat, row, col)) {
                    validMoves[i] = this.board[row][col];
                    i++;
                }
            }
        }
        if (validMoves.length < num_boats){
            System.out.println("There is not enough space for boats at the board size you have chosen. Please try running again.");
        }
        return (validMoves);
    } // validMoves

    // places boat and returns array of filled spaces
    public Cell[] spaceFilled(Battleboat tempBoat, int r, int c){
        Cell[] spaceFilled = new Cell[tempBoat.get_size()];
        if(tempBoat.get_orientation()){     //if boat is vertical
            for(int length = 0; length < tempBoat.get_size(); length++){
                this.board[r][c].set_status('B');
                spaceFilled[length] = this.board[r][c];
                r++;
            }
        }
        else{           // if boat is horizontal
            for(int length = 0; length < tempBoat.get_size(); length++){
                this.board[r][c].set_status('B');
                spaceFilled[length] = this.board[r][c];
                c++;
            }
        }
        return (spaceFilled);
    } // spaceFilled

    // finds a random valid move from the list of valid moves
    public int[] randomMove (Battleboat tempBoat){
        int[] moveCor = new int [2];
        Cell[] validMoveArray = validMoves(tempBoat);
        if (validMoveArray.length >= num_boats){
             int index = (int) Math.floor(Math.random() * validMoveArray.length);
             for(int col = 0; col < num_columns; col ++){
                for(int row = 0; row < num_rows; row ++){
                   if (this.board[row][col] == validMoveArray[index]) {
                       moveCor[0] = row;
                       moveCor[1] = col;
                       row = num_rows;
                       col = num_columns;
                   }
                }
             }
        }
        else{
            moveCor[0] = 13;
            moveCor[1] = 13;
        }
        return(moveCor); //rows are [0], and columns are [1]
    } // randomMove

    public boolean randomOrientation(){
        int num = (int)Math.round(Math.random());
        if (num == 1){
            return true;
        }
        else{
            return false;
        }
    }
    //Obscures a character if the game is not being played in debug mode
    private char debug(boolean debugMode, char c){
        if(debugMode){
            return c;
        }
        else{
            switch(c){
                case 'H':
                    c = 'H';
                    break;
                case 'M':
                    c = 'M';
                    break;
                default:
                    c = ' ';
                    break;
            }
            return c;
        }
    } // debug

    //Prints a Board object in a way that makes sense to the player
    public String toString(){

        String boardString = "\t";
        for (int j = 0; j < num_columns-1; j++){
            boardString += j + " |" + "\t";
        }

        boardString += num_columns-1;

        for(int i = 0; i < num_rows; i++){
            boardString+= "\n" + i + "\t";
            for (int j = 0; j < num_columns; j++){
                boardString += debug(debugMode, board[i][j].get_status()) + "\t";
            }
        }

        boardString += "\n";
        return boardString;
    } // toString

    // TODO: Return a int based on the guess for the cell/its status DONE
    // TODO: Change the statuses of the cell if applicable DONE
    public int guess(int r, int c){
      if (r < num_rows && c < num_columns && r >= 0 && c >= 0) {
          Cell guessCell = board[r][c];
              //"Penalty: Out of Bounds";
            if (guessCell.get_status() == ' ') {
              guessCell.set_status('M');
              return 1;
              //"Miss";
          } else if (guessCell.get_status() == 'B') {
              guessCell.set_status('H');
              return 2;
              //"Hit";
          } else {
              return 3;
              //"Penalty: Redundant Guess";
          }
      }
      else{
          return 0;
      }
    } // guess

    //TODO: write a function that calculates the number of unsunk boats DONE
    public int unsunkBoats(){
      int remainingBoats = 0;
      Cell[] spaces;
      boolean unsunkSwitch = false;     //assumes no unsunk boats but if there are, switch is flipped
        int iteration = 0;
        for (int i = 0; i < num_boats; i++){
            spaces = this.boats[i].get_spaces();
            for (int space = 0; space < spaces.length; space++){
                if (spaces[space].get_status() == 'B'){
                    unsunkSwitch = true;
                }
                iteration++;
            }
            if (unsunkSwitch == true){
                remainingBoats ++;
                unsunkSwitch = false;
            }
        }
      return (remainingBoats);
    } // unsunkboats
}
