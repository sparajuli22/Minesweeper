package com.example.minesweeper;

import java.util.Random;


// creates a mine board class that has initial number of rows, columns and mines
public class MineBoard
{
    Square[][] mineBoard;
    int row;
    int column;
    int mines;
    int noOfClicks;
    // constructor function
    public MineBoard(int row, int column, int mines){

        this.row = row;
        this.column = column;
        this.mines = mines;
        mineBoard = new Square[row][column];
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                mineBoard[i][j] = new Square(i,j);
            }
        }

    }

    // randomly places the cells with mines or bombs
    public void placeMines(int frow, int fcol){
        for(int i =0; i < mines; i++) {
            Random rand = new Random();
            int rm = rand.nextInt(row);
            int cm = rand.nextInt(column);
            //checks if the squares has a mine and is not around the clicked button (square)
            if (!(mineBoard[rm][cm].hasMine() || ((rm >= frow - 1 && rm <= frow + 1 ) && (cm >= fcol - 1 && cm <= fcol + 1)))) {
                mineBoard[rm][cm].addMine();
            }
            else{
                i--;
            }
        }
        checkAllSquares();

    }
    // boolean that checks if the cell
    public boolean isValidSquare(int row, int column){
        if (row < 0 || column < 0){
            return false;
        }
        if (row >= this.row || column >= this.column ){
            return false;
        }
        return true;
    }

    // checks the cells have mines or bombs
    public void checkMines(int row, int column){
        //sets the number in all mines, i.e the number of neighbouring mines around the square.
        if (mineBoard[row][column].hasMine()){
            mineBoard[row][column].addMine();
        }
        else{
            int count = 0;
            for (int i = row - 1; i <= row + 1; i++){
                for (int j = column - 1; j <= column + 1; j++){
                    if (isValidSquare(i, j)){
                        if (mineBoard[i][j].hasMine()){
                            count++;
                        }
                    }
                }
            }
            mineBoard[row][column].setMineNumber(count);
        }
    }

    // reveals the cells after the click
    public void revealMine(int row, int column){
        mineBoard[row][column].reveal();
    }
    // checks all the square if they have a mine or a bomb
    public void checkAllSquares() {
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                checkMines(i,j);

            }
        }
    }

    // flags a mine based on the long click
    public void flagMine(int row, int column){
        mineBoard[row][column].flag();
    }

    // removes the flag
    public void deFlagMine(int row, int column){
        mineBoard[row][column].deFlag();
    }

    // getter function
    public Square[][] getBoard(){
        return mineBoard;
    }


    public void print() {
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                checkMines(i,j);
                System.out.printf("%d " , mineBoard[i][j].getMineNumber());
            }
            System.out.printf("\n");
        }
    }

    // hides all the cells if the game is over and if new game is selected
    public void hideAll() {
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++) {
                mineBoard[i][j].hide();
            }
        }
    }
}

