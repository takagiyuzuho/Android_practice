package jp.co.it_college.androidtraining.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jp.co.it_college.androidtraining.R;
import jp.co.it_college.androidtraining.databinding.ActivityThirdBinding;

public class ThirdActivity extends AppCompatActivity {

    private ActivityThirdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_third);
        binding.textView.setText("third Activity");
    }
}
