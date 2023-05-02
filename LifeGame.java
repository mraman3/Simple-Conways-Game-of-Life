import java.util.*;
import java.io.*;

/* COSC 1P03
 * ASSIGNMENT #1
 * Name: Aman Braich
 * Student #: 6511679
 * May 15th, 2019
 * 
 * This program is a recreation of Conway's Game of life 
 * where program starts by reading a grid off a file 
 * which contains a grid of 0's and 1's indicating dead and alive
 * and it proceeds to cycle through the grid many times once check
 * for the amount of neighbour each cell has also keeping in memory
 * what state the cell is at the moment. Based off how many neighbours
 * are around the cell and its State. It will either die, or stay alive 
 * and this new state is copied to another array Called gridDup and all 
 * the states are duplicated over to that. Once all the cells have been
 * checked the program overwrites the just scanned grid with the new grid 
 * being saved in gridDup and when the program go to reiterate again it 
 * creates a new gridDup and repeats the process until the users entered 
 * amount of iterations are meet.
 */
public class LifeGame {
  //Array info starts
  int[][] gridOG;
  int[][] gridDup;
  int colums;
  int rows;
  //Array info ends
  
  public LifeGame() {
    String filename;
    Scanner console;
    console=new Scanner(System.in); //System.in represents the command-line stream
    //You can say things like console.next() to read a String from the keyboard,
    //or console.nextInt() to read an Integer (etc.)
    
    //Gets Filename from user and loads grid 
    print("Enter game filename: ");
    filename=console.next();
    loadState(filename);
    
    //Asks for how many iterations, then closes the console
    print("Enter how many iterations: ");
    int numIterations=console.nextInt();
    console.close();
    
    //Prints out the dimensions of the grid
    System.out.println("Dimensions: "+rows+" rows x "+colums+" columns");
    System.out.println();
    
    //Prints out the Original grid from the file
    System.out.println("Initial Game State:");
    gridOGPrint();
    System.out.println();
    
    //Loop iterating the amount of times inputed by user
    for (int i=0; i<numIterations; i++){
      iterate();
      System.out.println();
    }
  }
  
  /*LoadState is a meathod that will load the orignal grid from the 
   * txt file that the user enters. Once the user enters the filename 
   * it starts y getting the colum and row from the top of the text file 
   * and uses those parameters to set the lenght and size of the arrays 
   * Then it procceds to fill the gridOG with the same cell states as the 
   * one in the txt file. Then lastly cloes the file 
   */
  private void loadState(String filename) {
    Scanner file;
    try {
      file = new Scanner(new File(filename)); //This lets you use Scanner as normal, except from a text file!
      //You'd do reads here. The try/catch lets you handle problems gracefully
      colums = file.nextInt();
      rows = file.nextInt();
      gridOG = new int [colums][rows];
      while (file.hasNextLine()){      
        for (int i=0; i<colums; i++){
          for(int j=0; j<rows; j++){
            gridOG[i][j] = file.nextInt();   
          }
        }
      }
      file.close(); //Don't forget to always close your streams!
    }
    catch (Exception exn) {
    }
  }
  
  /*This is a simple meathod that get you the state of a cell in the corrdinates
   *of the grid entered in the methods parameters*/
  private int getCell(int[][]grid, int i, int j){
    int stateCell = gridOG[i][j];
    return stateCell;
  }
  
  /*This is a meathod that will take the give i and j of the 
   * given grid and check the neighbours around it by adding
   * up the states as that will show a correct value for how many 
   * there are. while it does this it makes sure that is subtracts the 
   * cell in the middle of the 3x3 square so it does no effect the count 
   * and this done using the modulous meathod learned in class.
   * Then finally it return the number of neighbours 
   */
  private int countNeighbours(int[][] grid, int i, int j){
    int totalNeighbours = 0;
    for (int k = -1; k < 2; k++) {
      for (int l = -1; l < 2; l++) {
        int col = (i + k + colums) % colums;
        int row = (j + l + rows) % rows;
        totalNeighbours += grid[col][row];
      }
    }
    totalNeighbours -= grid[i][j];
    return totalNeighbours;
  }
  
  /*This method iterates the proccess of going through the entire
   * grid and check all the neighbours and states to make sure that the 
   * correct changes are made. These are done by checking against the 
   * three iff statments which will deterime if the cell will remain alive, 
   * die or come to life if previously dead. Then toward the end of the meathod
   * it takes the changed cells in the new array gridDup and duplicates them 
   * over to the orginal array gridOG then lastly prints this new iteration of 
   * the grid. 
   */
  private void iterate(){
    gridDup = new int[colums][rows];
    for (int i = 0; i < colums; i++) {
      for (int j = 0; j < rows; j++) {
        int state = getCell(gridOG,i,j);
        // Count live neighbors!
        int neighbours = countNeighbours(gridOG, i, j);
        
        if (neighbours == 3 && state == 0) {
          gridDup[i][j] = 1;
        } else if ((neighbours < 2 || neighbours > 3) && state == 1) {
          gridDup[i][j] = 0;
        } else {
          gridDup[i][j] = state;
        }      
      }
    }
    gridOG = gridDup;
    gridOGPrint();
  }
  
  /*Simple meathod to make the printing of the grids easier
   * It prints the cells in the correct rows and colums and 
   * all the cells are a tab space apart with a empty line 
   * under the entire printed grid to make it more visually
   * appeling.                                           */
  private void gridOGPrint(){
    for (int i=0; i<colums; i++){
      for(int j=0; j<rows; j++){
        System.out.print(gridOG[i][j]+"    ");   
      }
      System.out.println();
    }
  }
  
  //This is entirely unnecessary. I just like to save myself some typing
  private void print(String msg) {
    System.out.print(msg);
  }
  
  //Main meathod to run program 
  public static void main(String args[]) {new LifeGame();}
}