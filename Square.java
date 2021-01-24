package com.example.minesweeper;

public class Square {
    int mineNumber;
    boolean isRevealed;
    boolean isFlagged;

    public Square(){
        mineNumber = 0;
        isRevealed = false;
        isFlagged = false;
    }

    public boolean getReveal(){
        return isRevealed;
    }

    public void reveal(){
        isRevealed = true;
    }

    public int getMineNumber(){
        return mineNumber;
    }

    public void setMineNumber(int n){
        mineNumber = n;
    }

    public void addMine(){
        mineNumber = 9;
    }

    public boolean hasMine(){
        if (mineNumber == 9){
            return true;
        }
        return false;
    }

    public void flag(){
        isFlagged = true;
    }

    public void deflag(){
        isFlagged = false;
    }

}
