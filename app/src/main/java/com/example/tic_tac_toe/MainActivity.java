package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttons[][] = new Button[3][3];
    private boolean player1turn = true;

    private int roundcounts = 0;
    private int player1counts = 0;
    private int player2counts = 0;
    private TextView textviewp1;
    private TextView textviewp2;
    private Button resetbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textviewp1 = (TextView) findViewById(R.id.player_1);
        textviewp2 = (TextView) findViewById(R.id.player_2);
        resetbtn = (Button) findViewById(R.id.btn_reset);
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetgame();
            }
        });


        buttons[0][0] = (Button) findViewById(R.id.button_00);
        buttons[0][1] = (Button) findViewById(R.id.button_01);
        buttons[0][2] = (Button) findViewById(R.id.button_02);
        buttons[1][0] = (Button) findViewById(R.id.button_10);
        buttons[1][1] = (Button) findViewById(R.id.button_11);
        buttons[1][2] = (Button) findViewById(R.id.button_12);
        buttons[2][0] = (Button) findViewById(R.id.button_20);
        buttons[2][1] = (Button) findViewById(R.id.button_21);
        buttons[2][2] = (Button) findViewById(R.id.button_22);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnClickListener(this);
            }
        }

    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        if (!b.getText().toString().equals("")) {
            return;
        }
        if (player1turn) {
            b.setText("X");
        } else {
            b.setText("0");
        }
        roundcounts++;

        if(checkforwin())
        {
            if(player1turn)
            {
                player1wins();
            }
            else
            {
                player2wins();
            }
        }
        else
        {
            if(roundcounts==9)
            {
                draw();
            }
            else
                player1turn=!player1turn;
        }
    }

    private void player1wins() {
        player1counts++;
        Toast.makeText(this, "player 1 wins", Toast.LENGTH_SHORT).show();
        updatepointText();
        resetBoard();
    }

    private void player2wins() {
        player2counts++;
        Toast.makeText(this, "player 2 wins", Toast.LENGTH_SHORT).show();
        updatepointText();
        resetBoard();
    }
    private void draw()
    {
        Toast.makeText(this, "draw this time", Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updatepointText()
    {
        textviewp1.setText("player 1:"+player1counts);
        textviewp2.setText("player 2:"+player2counts);
    }
    private void resetBoard()
    {
        for( int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                buttons[i][j].setText("");

            roundcounts=0;
            player1turn=true;
    }
    private void resetgame()
    {
        player1counts=0;
        player2counts=0;
        updatepointText();
        resetBoard();
    }

    private boolean checkforwin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++)

            for (int j = 0; j < 3; j++)

                field[i][j]=buttons[i][j].getText().toString();

            for( int i=0;i<3;i++)
            {
                if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))
                {
                    return true;
                }

            }
        for( int i=0;i<3;i++)
        {
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
            {
                return true;
            }

        }
        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals(""))
            return true ;

        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
            return true ;

        return false;


    }

}