package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //the rows columns and mines in the basic board design
    int _rows = 9;
    int _columns = 9;
    int _mines =  12;
    //the number of flags revealed
    int _flags = 0;
    //all the buttons that are displayed on the screen.
    Button[][] buttons = new Button[_rows][_columns];
    //the mine board object that is taken as reference for the numbers to show and where the mines are placed
    MineBoard board = new MineBoard(_rows, _columns, _mines);
    //the button that redraws the main screen.
    Button restart;
    //checks if the game ends
    boolean gameOver = false;
    //the squares left to reveal are the total squares - mines
    int squaresLeft = _rows * _columns - _mines;

    TableLayout boardViewer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //show the basic board
        setupBoard();

        //adds function click to the restart button
        restart = (Button)findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clears the screen if there is anything from before
                boardViewer.removeAllViews();
                //sets up a new mineBoard every time.
                board = new MineBoard(_rows,_columns, _mines);
                //sets up the board again
                setupBoard();
                //sets everything to initial numbers
                squaresLeft = _rows * _columns - _mines;
                _flags = 0;
                //un reveals the values of all buttons
                board.hideAll();
            }
        });


    }

    private void setupBoard(){
        boardViewer = (TableLayout)findViewById(R.id.MineField);
        gameOver = false;
        // Initially prompts the user to click a button
        TextView t = (TextView) findViewById(R.id.square);
        t.setText(String.format("Click a button"));
        TextView f = (TextView) findViewById(R.id.flag);
        f.setText(String.format("to start"));
        for (int i = 0; i < _rows; i++){
            //the number of rows created is the number of rows in the board
            TableRow newRow = new TableRow(this);
            //this wraps all the rows equally in the given space
            TableLayout.LayoutParams y = new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0);
            y.weight = 1;
            newRow.setLayoutParams(y);
            for (int j = 0; j < _columns; j++){
                //Adds the  buttons into a given tablerow
                buttons[i][j] = new Button(this);
                //this layout wraps all the buttons in a row equally
                TableRow.LayoutParams x = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
                x.weight = 1;
                buttons[i][j].setLayoutParams(x);
                //there is no inital text
                buttons[i][j].setText(" ");
                buttons[i][j].setTypeface(Typeface.DEFAULT_BOLD);
                //changed the looks of the button
                buttons[i][j].setBackgroundResource(R.drawable.squarebtn);
                //buttons[i][j].setPadding(2,2,2,2);
                //All buttens are enabled at start.
                buttons[i][j].setEnabled(true);
                //this had to be done as onClick function does not accept changable values
                int finalRow = i;
                int finalColumn = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    //Adds the click function to all the buttons on the board
                    public void onClick(View view) {
                        //if the button is trigged for first time, it sets the mines in the board
                        //the given set of mines is not around the clicked button
                        if (squaresLeft == _rows * _columns - _mines){
                            board.placeMines(finalRow, finalColumn);
                            TextView f = (TextView) findViewById(R.id.flag);
                            f.setText(String.format("%d Flags left", _mines - _flags));
                        }
                        //this reveals the button's content and checks if the game is won after the reveal
                        reveal(finalRow, finalColumn);
                        endIfMine(finalRow, finalColumn);
                        checkIfWon();
                    }
                });

                buttons[i][j].setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    //flag is place if a long click is triggered
                    public boolean onLongClick(View view) {
                        //flags or deflags a button
                        checkFlag(finalRow, finalColumn);
                        return true;
                    }

                });
                newRow.addView(buttons[i][j]);
            }
            boardViewer.addView(newRow);
        }
    }

    //adds or removes flag from the button
    private void checkFlag(int row, int column) {
        if (!board.getBoard()[row][column].getFlag() && !board.getBoard()[row][column].getReveal()) {
            if (_flags < _mines) {
                //does not allow flags if max number of flags is reached
                board.flagMine(row, column);
                buttons[row][column].setBackgroundResource(R.drawable.flag);
                _flags++;
                TextView f = (TextView) findViewById(R.id.flag);
                f.setText(String.format("%d Flags left", _mines - _flags));

            }
        } else {
            board.deFlagMine(row, column);
            buttons[row][column].setBackgroundResource(R.drawable.squarebtn);
            _flags--;
            TextView f = (TextView) findViewById(R.id.flag);
            f.setText(String.format("%d Flags left", _mines - _flags));
        }
    }


    //reveals the button and the number and mines underneath it.
    private void reveal(int row, int column) {
        //checks if the given button is inside the range
        if ((row < 0 || column < 0 || row >= 9|| column >= 9)) {
            return;
        }

        //deflags a button if it is revealed
        if (!board.getBoard()[row][column].getReveal()) {
            if (board.getBoard()[row][column].getFlag()){
                board.deFlagMine(row, column);
                _flags--;
                TextView f = (TextView) findViewById(R.id.flag);
                f.setText(String.format("%d Flags left", _mines - _flags));
            }

            //get the number to reveal inside the button
            int squareValue = board.getBoard()[row][column].getMineNumber();
            board.revealMine(row, column);
            String str;
            switch (squareValue) {
                case 0:
                    str = " ";
                    break;
                default:
                    //displays the number in the button
                    str = String.valueOf(squareValue);
                    if (squareValue == 1) {
                        buttons[row][column].setTextColor(Color.BLACK);
                    } else if (squareValue == 2) {
                        buttons[row][column].setTextColor(Color.GREEN);
                    } else {
                        buttons[row][column].setTextColor(Color.MAGENTA);
                    }
            }
            buttons[row][column].setBackgroundResource(R.drawable.pressedbtn);

            //change the button to a mine if a mine is clicked
            if (board.getBoard()[row][column].hasMine()) {
                str = " ";
                buttons[row][column].setBackgroundResource(R.drawable.minebtn);
            }

            //decreases the number of squares to be clicked
            buttons[row][column].setText(str);
            squaresLeft--;
            TextView t = (TextView) findViewById(R.id.square);
            t.setText(String.format("%d Squares left", squaresLeft));

            buttons[row][column].setEnabled(false);

            //recursion to reveal all the neighbouring mines of the number in the square is zero
            if (squareValue == 0) {
                reveal(row + 1, column);
                reveal(row + 1, column - 1);
                reveal(row + 1, column + 1);
                reveal(row, column + 1);
                reveal(row , column - 1);
                reveal(row - 1, column + 1);
                reveal(row - 1, column - 1);
                reveal(row -1, column);


            } else {
                return;
            }
        }
    }

    //checks if the game is won
    private void checkIfWon(){
        //if all the squares is clicked ecept the mines, the game is won
        if (squaresLeft == 0 && gameOver == false){

            //flags all the mined buttons
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(board.getBoard()[i][j].hasMine()){
                        buttons[i][j].setBackgroundResource(R.drawable.flag);
                        buttons[i][j].setEnabled(false);
                    }
                }
            }

            //displays the game is won
            TextView t = (TextView) findViewById(R.id.square);
            t.setText(String.format("You won"));

            TextView f = (TextView) findViewById(R.id.flag);
            f.setText(String.format("!!!!!!"));
        }
    }

    //ends the game when a mine is clicked
    private void endIfMine(int row, int column){
        if(board.getBoard()[row][column].hasMine()){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    //reveals all the mine positions
                    if(board.getBoard()[i][j].hasMine()){
                        reveal(i , j);
                    }
                }
            }

            //disables all the buttons
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    buttons[i][j].setEnabled(false);
                }
            }
            gameOver = true;
            TextView t = (TextView) findViewById(R.id.square);
            t.setText(String.format("Game"));

            TextView f = (TextView) findViewById(R.id.flag);
            f.setText(String.format("Over"));
        }



    }


}