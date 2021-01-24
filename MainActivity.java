package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
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

    Button[][] buttons = new Button[9][9];
    MineBoard board = new MineBoard(9,9,10);
    TableRow row;
    TableRow row2;
    TableLayout boardViewer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boardViewer = (TableLayout)findViewById(R.id.MineField);
        row = (TableRow)findViewById(R.id.row1);
        row2 = (TableRow)findViewById(R.id.row2);
    for (int i = 0; i < 9; i++){
            Button btnTag = new Button(this);
            btnTag.setLayoutParams(new TableRow.LayoutParams(150, 150));
            btnTag.setText("2");
            btnTag.setBackgroundResource(R.drawable.squarebtn);
            btnTag.setPadding(20,20,20,20);
            btnTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView t = (TextView)findViewById(R.id.Header);
                    t.setText("Button clicked");
                }
             });

        row.addView(btnTag);
        }

        for (int i = 0; i < 9; i++){
            Button btnTag = new Button(this);
            btnTag.setLayoutParams(new TableRow.LayoutParams(150, 150));
            btnTag.setText("M");
            btnTag.setBackgroundResource(R.drawable.squarebtn);
            btnTag.setPadding(2,2,2,2);
            btnTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView t = (TextView)findViewById(R.id.Header);
                    t.setText("2nd Row clicked");
                }
            });
            row2.addView(btnTag);
        }

        TableRow newOne = new TableRow(this);
        for (int i = 0; i < 9; i++){
            Button btnTag = new Button(this);
            btnTag.setLayoutParams(new TableRow.LayoutParams(150, 150));
            btnTag.setText("M");
            btnTag.setBackgroundResource(R.drawable.squarebtn);
            btnTag.setPadding(2,2,2,2);
            btnTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView t = (TextView)findViewById(R.id.Header);
                    t.setText("3rd Row clicked");
                }
            });
            newOne.addView(btnTag);
        }

        boardViewer.addView(newOne);



        //add button to the layout



    }
}