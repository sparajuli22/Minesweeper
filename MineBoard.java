package com.example.minesweeper;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MineBoard {
    ArrayList<ArrayList<Square>> mineBoard = new ArrayList<ArrayList<Square>>();
    int row;
    int column;
    int mines;
    int noOfClicks;

    public MineBoard(int row, int column, int mines){

        this.row = row;
        this.column = column;
        this.mines = mines;
        for (int i = 0; i < row; i++){
            mineBoard.add(new ArrayList<Square>());
            for (int j = 0; j < column; j++){
                mineBoard.get(i).add(new Square(i,j));
            }
        }

        placeMines();
    }

    public void placeMines(){
        for(int i =0; i < mines; i++) {
            Random rand = new Random();
            int rm = rand.nextInt(row);
            int cm = rand.nextInt(column);
            if (!mineBoard.get(rm).get(cm).hasMine()) {
                mineBoard.get(rm).get(cm).addMine();
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
        if (mineBoard.get(row).get(column).hasMine()){
            mineBoard.get(row).get(column).addMine();
        }
        else{
            int count = 0;
            for (int i = row - 1; i <= row + 1; i++){
                for (int j = column - 1; j <= column; j++){
                    if (isValidSquare(i, j)){
                        if (mineBoard.get(i).get(j).hasMine()){
                            count++;
                        }
                    }
                }
            }
            mineBoard.get(row).get(column).setMineNumber(count);
        }
    }

    public void flagMine(int row, int column){
        mineBoard.get(row).get(column).flag();
    }

    public void deFlagMine(int row, int column){
        mineBoard.get(row).get(column).deFlag();
    }

}
