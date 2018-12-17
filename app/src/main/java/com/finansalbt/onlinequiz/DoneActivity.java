package com.finansalbt.onlinequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.finansalbt.onlinequiz.Common.Common;
import com.finansalbt.onlinequiz.Model.QuestionScore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.BufferUnderflowException;

public class DoneActivity extends AppCompatActivity {

    Button btnTryAgain;
    TextView txtResultScore,getTxtResultQuestion;
    ProgressBar progressBar;

    FirebaseDatabase database;
    DatabaseReference question_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        database =FirebaseDatabase.getInstance();
        question_score=database.getReference("Question_Score");

        txtResultScore = findViewById(R.id.txtTotalScore);
        getTxtResultQuestion = findViewById(R.id.txtTotalQuestion);
        progressBar= findViewById(R.id.doneProgressBar);
        btnTryAgain = findViewById(R.id.btnTryAgain);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoneActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle extra = getIntent().getExtras();
        if (extra != null)
        {
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");

            txtResultScore.setText(String.format("SCORE : %d",score));
            getTxtResultQuestion.setText(String.format("PASSED : %d / %d",correctAnswer,totalQuestion));

            progressBar.setMax(totalQuestion);
            progressBar.setProgress(correctAnswer);

            question_score.child(String.format("%s_%s", Common.currentUser.getUserName(),
                    Common.categoryId))
                    .setValue(new QuestionScore(String.format("%s_%s",Common.currentUser.getUserName(),
                            Common.categoryId),
                            Common.currentUser.getUserName(),
                            String.valueOf(score)));
        }

    }
}
