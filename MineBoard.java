package com.example.minesweeper;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MineBoard {
    ArrayList<ArrayList<Square>> mineBoard = new ArrayList<ArrayList<Square>>();
    int mines;
    int noOfClicks;

    public MineBoard(int row, int column, int mines){

        for (int i = 0; i < row; i++){
            mineBoard.add(new ArrayList<Square>());
            for (int j = 0; j < column; j++){
                mineBoard.get(i).add(new Square());
            }
        }

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
}
