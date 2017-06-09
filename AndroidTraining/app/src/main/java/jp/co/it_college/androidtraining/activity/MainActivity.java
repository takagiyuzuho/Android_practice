package jp.co.it_college.androidtraining.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import jp.co.it_college.androidtraining.R;
import jp.co.it_college.androidtraining.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private Button centerButton;
//    private Button button1;
//    private Button button2;
//    private Button button3;
//    private Button button4;
//    private Button button5;
//    private Button button6;
//    private Button button7;
//    private TextView textView;
//    private TextView aboveButtonTextView;
//    private TextView belowButtonTextView;
//    private TextView leftSideTextView;
//    private TextView rightSideTextView;

    //データバインディングクラス
    protected ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        centerButton = (Button)findViewById(R.id.center_button);
//        button1 = (Button)findViewById(R.id.button1);
//        button2 = (Button)findViewById(R.id.button2);
//        button3 = (Button)findViewById(R.id.button3);
//        button4 = (Button)findViewById(R.id.button4);
//        button5 = (Button)findViewById(R.id.button5);
//        button6 = (Button)findViewById(R.id.button6);
//        button7 = (Button)findViewById(R.id.button7);
//
//        textView =(TextView)findViewById(R.id.textView);
//        aboveButtonTextView = (TextView)findViewById(R.id.above_button_textview);
//        belowButtonTextView = (TextView)findViewById(R.id.below_button_textview);
//        leftSideTextView = (TextView)findViewById(R.id.left_side_textview);
//        rightSideTextView = (TextView)findViewById(R.id.right_side_textview);

        //リスナー追加
        binding.centerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.textView.setText("center button clicked");
                openSecondActivity();
            }
        });

        binding.button1.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);
        binding.button4.setOnClickListener(this);
        binding.button5.setOnClickListener(this);
        binding.button6.setOnClickListener(this);
        binding.button7.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == binding.button1) { binding.aboveButtonTextView.setText("button1 clicked");
        } else if (view == binding.button2) { binding.belowButtonTextView.setText("button2 clicked");
        } else if (view == binding.button3) { binding.leftSideTextView.setText("button3 clicked");
        } else if (view == binding.button4) { binding.rightSideTextView.setText("button4 clicked");
        } else if (view == binding.button5) { binding.textView.setText("button5 clicked");
        } else if (view == binding.button6) { binding.textView.setText("button6 clicked");
            binding.aboveButtonTextView.setText("button6 clicked");
            binding.belowButtonTextView.setText("button6 clicked");
            binding.leftSideTextView.setText("button6 clicked");
            binding.rightSideTextView.setText("button6 clicked");
        } else if (view == binding.button7) {
            // textView.setText(null)はtextView.setText("")と同じ働きをする
            binding.textView.setText(null);
            binding.aboveButtonTextView.setText(null);
            binding.belowButtonTextView.setText(null);
            binding.leftSideTextView.setText(null);
            binding.rightSideTextView.setText(null);
        }
    }

    private void openSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}