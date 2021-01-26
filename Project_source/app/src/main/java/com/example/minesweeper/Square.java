package com.example.minesweeper;

// a class with initial mine number and boolean values
public class Square {
    int mineNumber;
    boolean isRevealed;
    boolean isFlagged;
    int[] location = {0 , 0};

    // initial Constructor
    public Square(){
        mineNumber = 0;
        isRevealed = false;
        isFlagged = false;
    }
    // final constructor
    public Square(int row, int column){
        super();
        location[0] = row;
        location[1] = column;
    }

    // get the initial status of the cell
    public boolean getReveal(){
        return isRevealed;
    }
    // getter function that gets the status of the flag
    public boolean getFlag()
    {
        return isFlagged;
    }



    // reveals a cells if the cell is clicked
    public void reveal(){
        isRevealed = true;
    }

    // getter function for mine nUmber
    public int getMineNumber(){
        return mineNumber;
    }
    // setter function for mine number
    public void setMineNumber(int n){
        mineNumber = n;
    }
    // adds the mine number to each cell
    public void addMine(){
        mineNumber = 9;
    }

    // checks to see if the mine has a number
    public boolean hasMine(){
        if (mineNumber == 9){
            return true;
        }
        return false;
    }
    // boolean function to flag a cell
     public void flag(){
        isFlagged = true;
    }
    // boolean function to deflag a cell
    public void deFlag(){
        isFlagged = false;
    }
    //getter function for location
    public int[] getLocation(){
        return location;
    }

    public void hide() { isRevealed = false;    }
}
