# Simple-Conways-Game-of-Life - Aman Braich 
Conways Game of Life Displayed in Console Ascii text form

This program is a recreation of Conway's Game of Life where the program displays the game in cosole in text form. 
It starts by reading a grid of a Txt file which contains a grid of 0's and 1's indicating dead and alive and it proceeds 
to cycle through the grid many times once check for the number of neighbors each cell has also keeping in memory what state the cell is at the moment. 
Based on how many neighbors are around the cell and its State. It will either die or stay alive and this new state is copied to another array Called gridDup 
and all the states are duplicated over to that. Once all the cells have been checked the program overwrites the just scanned grid with the new grid 
being saved in gridDup and when the program goes to reiterate it creates a new gridDup and repeats the process until the user's 
entered amount of iterations are met.

Running Steps

1. Enter File Name for grid 
 - Two Example files are provided 
 - small.txt & large.txt 
 
 Feel free to expiement with your own grids and see how it plays out 
 
 2. Enter how many iterations you would like the game to cycle though 
 
 3. Watch as your Game of Life progress though the diffrent states 
