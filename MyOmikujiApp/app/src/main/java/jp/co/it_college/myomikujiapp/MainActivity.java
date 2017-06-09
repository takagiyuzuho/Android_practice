package jp.co.it_college.myomikujiapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getOmikuji(View view) {
        //TextViewの取得
        TextView tv = (TextView)findViewById(R.id.myTextView);
        String[] results = {"大吉", "中吉", "凶"};
        //乱数の生成
        Random randomgenerator = new Random();
        int num = randomgenerator.nextInt(results.length);

        /*
        Color.rgb(255,0,0)
        Color.rgb()
        Color.rgb()
        Color.parseColor()
         */

        if(num == 0) {
            tv.setTextColor(Color.RED);
        } else if(num == 1) {
            tv.setTextColor(Color.BLUE);
        }
        //結果の表示
        tv.setText(results[num]);
    }
}
