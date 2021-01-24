package com.example.minesweeper;

import java.util.Random;

public class MineBoard {
    Square[][] mineBoard;
    int row;
    int column;
    int mines;
    int noOfClicks;

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

        placeMines();
        checkAllSquares();

    }

    public void placeMines(){
        for(int i =0; i < mines; i++) {
            Random rand = new Random();
            int rm = rand.nextInt(row);
            int cm = rand.nextInt(column);
            if (!mineBoard[rm][cm].hasMine()) {
                mineBoard[rm][cm].addMine();
            }
            else{
                i--;
            }
        }


    }

    public boolean isValidSquare(int row, int column){
        if (row < 0 || column < 0){
            return false;
        }
        if (row >= this.row || column >= this.column ){
            return false;
        }
        return true;
    }

    public void checkMines(int row, int column){
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

    public void checkAllSquares() {
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                checkMines(i,j);

            }
        }
    }

    public void flagMine(int row, int column){
        mineBoard[row][column].flag();
    }

    public void deFlagMine(int row, int column){
        mineBoard[row][column].deFlag();
    }

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



}
