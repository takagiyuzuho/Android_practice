package jp.co.it_college.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		TextView resultText = (TextView)findViewById(R.id.resultText);
		Intent intent = getIntent();
		resultText.setText(intent.getStringExtra(MainActivity.EXTRA_MY_SCORE));

	}

	public void goBack(View view) {
		finish();
	}
}
