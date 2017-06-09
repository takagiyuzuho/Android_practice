package jp.co.it_college.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MY_SCORE = "jp.co.it_college.quizapp.MYSCORE";

    ArrayList<String[]> quizSet = new ArrayList<String[]>();

    private TextView scoreText;
    private TextView qText;
    private Button a0Button;
    private Button a1Button;
    private Button a2Button;
    private Button nextButton;

    private int currentQuiz = 0;
    private int score = 0;

    public void showScore() {
        scoreText.setText("score" + score + "/" + quizSet.size());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadQuizSet();
        getViews();
        setQuiz();
    }

    @Override
    public void onResume() {
        super.onResume();
        nextButton.setText("Next");
        currentQuiz = 0;
        setQuiz();
    }

    public void checkAnswer(View view) {
        //answer
        Button clickedButton = (Button) view;
        String clickedAnswer = clickedButton.getText().toString();

        //judge
        if(clickedAnswer.equals(quizSet.get(currentQuiz)[1])) {
            clickedButton.setText("○" + clickedAnswer);
            score++;
        } else {
            clickedButton.setText("×");
        }
        showScore();

        //button
        a0Button.setEnabled(false);
        a1Button.setEnabled(false);
        a2Button.setEnabled(false);
        nextButton.setEnabled(true);

        //next
        currentQuiz++;

        if(currentQuiz == quizSet.size()) {
            nextButton.setText("show result");
        }
    }

    private void setQuiz() {
        qText.setText(quizSet.get(currentQuiz)[0]);

        ArrayList<String> answers = new ArrayList<String>();
        for(int i = 1; i <= 3; i++) {
            answers.add(quizSet.get(currentQuiz)[i]);
        }

        Collections.shuffle(answers);

        a0Button.setText(answers.get(0));
        a1Button.setText(answers.get(1));
        a2Button.setText(answers.get(2));

        a0Button.setEnabled(true);
        a1Button.setEnabled(true);
        a2Button.setEnabled(true);
        nextButton.setEnabled(false);
    }

    private void getViews() {
        scoreText = (TextView)findViewById(R.id.scoreText);
        qText = (TextView)findViewById(R.id.qText);
        a0Button = (Button)findViewById(R.id.a0Button);
        a1Button = (Button)findViewById(R.id.a1Button);
        a2Button = (Button)findViewById(R.id.a2Button);
        nextButton = (Button)findViewById(R.id.nextButton);
    }

    public void goNext(View view) {
        if(currentQuiz == quizSet.size()) {
            Intent intent = new Intent(this,ResultActivity.class);
            intent.putExtra(EXTRA_MY_SCORE, score + " / " + quizSet.size());
            startActivity(intent);
        } else {
            setQuiz();
        }
    }

    private void loadQuizSet() {
        InputStream inputStream = null;
        BufferedReader bf = null;
        try {
            inputStream = getAssets().open("quiz.text");
            bf = new BufferedReader(new InputStreamReader(inputStream));
            String s;
            while ((s = bf.readLine()) != null) {
                quizSet.add(s.split("\t"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream != null) {
                    inputStream.close();
                }
                if(bf != null) {
                    bf.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
