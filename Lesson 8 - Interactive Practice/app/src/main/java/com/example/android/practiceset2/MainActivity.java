package com.example.android.practiceset2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declaring some global variable here
    int teamAscore = 0;
    int teamBscore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.displayForTeamA(this.teamAscore);
        this.displayForTeamB(this.teamBscore);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Add score for a +3 for team A!
     * */
    public void add3A(View view) {
        this.addScoreA(3);
        this.displayForTeamA(this.teamAscore);
    }

    /**
     * Add score for a +2 for team A!
     * */
    public void add2A(View view) {
        this.addScoreA(2);
        this.displayForTeamA(this.teamAscore);
    }

    /**
     * Add score for a free throw for team A!
     * */
    public void addFreeA(View view) {
        this.addScoreA(1);
        this.displayForTeamA(this.teamAscore);
    }

    /**
     * Add score method for team A, to be called internally only
     * */
    private void addScoreA(int scoreAdded) {
        this.teamAscore += scoreAdded;
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Add score for a +3 for team A!
     * */
    public void add3B(View view) {
        this.addScoreB(3);
        this.displayForTeamB(this.teamBscore);
    }

    /**
     * Add score for a +2 for team A!
     * */
    public void add2B(View view) {
        this.addScoreB(2);
        this.displayForTeamB(this.teamBscore);
    }

    /**
     * Add score for a free throw for team A!
     * */
    public void addFreeB(View view) {
        this.addScoreB(1);
        this.displayForTeamB(this.teamBscore);
    }

    /**
     * Add score method for team A, to be called internally only
     * */
    private void addScoreB(int scoreAdded) {
        this.teamBscore += scoreAdded;
    }

    /**
     * Method to reset the scores for both teams
     * */
    public void resetScores(View v) {
        this.teamAscore = 0;
        this.teamBscore = 0;
        displayForTeamA(this.teamAscore);
        displayForTeamB(this.teamBscore);
    }
}
