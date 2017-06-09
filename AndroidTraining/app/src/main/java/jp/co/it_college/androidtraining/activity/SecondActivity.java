package jp.co.it_college.androidtraining.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import jp.co.it_college.androidtraining.fragment.FirstFragment;
import jp.co.it_college.androidtraining.R;
import jp.co.it_college.androidtraining.databinding.ActivitySecondBinding;

/**
 * Created by takagi-yuzuho on 2017/06/08.
 */

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);
//        binding.textView.setText("second Activity");
//        binding.thirdActivityButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                openThirdActivity();
//            }
//        });
        getFragmentManager().beginTransaction().replace(R.id.container, new FirstFragment()).commit();
    }

//    private void openThirdActivity() {
//        startActivity(new Intent(this, ThirdActivity.class));
//    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName());
    }

}