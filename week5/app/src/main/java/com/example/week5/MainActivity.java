package com.example.week5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String PLAYER_1 = "X";
    static final String PLAYER_2 = "O";
    boolean player1Turn = true;
    byte board[][] = new byte[3][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Traversing through buttons
        TableLayout table = findViewById(R.id.board);
        for (int i=0; i<3; i++){
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j=0; j<3; j++){
                Button button = (Button) row.getChildAt(j);
                button.setOnClickListener(new CellListener(i,j));  // Inline class
            }
        }

    }

    class CellListener implements View.OnClickListener{
        int row, col;

        public CellListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View view) {
            if (!isValidMove(row, col)){
                Toast.makeText(MainActivity.this, "Cell is already occupied", Toast.LENGTH_LONG).show();
                return;
            }

            if (player1Turn){
                ((Button)view).setText(PLAYER_1);
                board[row][col] = 1;
            }
            else {
                ((Button)view).setText(PLAYER_2);
                board[row][col] = 2;
            }

            if (isGameEnded(row, col) == -1){
                player1Turn = !player1Turn;
            }
            else if (isGameEnded(row,col) == 0) {
                Toast.makeText(MainActivity.this, "Draw", Toast.LENGTH_LONG).show();
            }
            else if (isGameEnded(row,col) == 1){
                Toast.makeText(MainActivity.this, "Player 1 wins!", Toast.LENGTH_LONG).show();
            }
            else if (isGameEnded(row,col) == 2){
                Toast.makeText(MainActivity.this, "Player 2 wins!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean isValidMove(int row, int col){
        return board[row][col] == 0;
    }

    public int isGameEnded(int row, int col){
        int symbol = board[row][col];
        boolean win = true;

        // check cols
        for (int i=0; i<3; i++){
            if (board[i][col] != symbol){
                win = false;
                break;
            }
        }
        if (win){
            return symbol;
        }

        // check rows
        win = true;
        for (int i=0; i<3; i++){
            if (board[row][i] != symbol){
                win = false;
                break;
            }
        }
        if (win){
            return symbol;
        }

        // check diagonals (0,0),(1,1),(2,2)
        win = true;
        for (int i=0; i<3; i++){
            if (board[i][i] != symbol){
                win = false;
                break;
            }
        }
        if (win){
            return symbol;
        }

        // check diagonals (0,2), (1,1), (2,0)
        win = true;
        int colChecker = 2;
        for (int i=0; i<3; i++){
            if (board[i][colChecker] != symbol){
                win = false;
                break;
            }
            colChecker--;
        }
        if (win){
            return symbol;
        }

        return -1;  // game is continuing, so return -1
    }


    // Ekranı yan çevirince state'lerin gitmemesi için 2 fonksiyon
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean("player1Turn", player1Turn);
        byte singleSizeBoard[] = new byte[9];
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                singleSizeBoard[3*i+j] = board[i][j];
            }
        }
        outState.putByteArray("board", singleSizeBoard);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        player1Turn = savedInstanceState.getBoolean("player1Turn");
        byte[] singleSizeBoard = savedInstanceState.getByteArray("board");
        for(int i=0; i<9; i++){
            board[i/3][i%3] = singleSizeBoard[i];
        }

        TableLayout table = findViewById(R.id.board);
        for(int i=0; i<3; i++){
            TableRow row = (TableRow) table.getChildAt(i);
            for(int j=0; j<3; j++){
                Button button = (Button) row.getChildAt(j);
                if (board[i][j] == 1){
                    button.setText(PLAYER_1);
                }
                else if(board[i][j] == 2){
                    button.setText(PLAYER_2);
                }
            }
        }
    }
}