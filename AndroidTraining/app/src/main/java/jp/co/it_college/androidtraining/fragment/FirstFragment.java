package jp.co.it_college.androidtraining.fragment;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.co.it_college.androidtraining.R;
import jp.co.it_college.androidtraining.activity.ThirdActivity;
import jp.co.it_college.androidtraining.databinding.FragmentFirstBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first,container,false);
        binding= FragmentFirstBinding.bind(view);
        binding.thirdActivityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openThirdActivity();
            }
        });
        binding.secondFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecondFragment();
            }
        });
        // Inflate the layout for this fragment
        return view;

    }

    private void openThirdActivity() {
        startActivity(new Intent(getActivity(),ThirdActivity.class));
    }

    private void openSecondFragment() {
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.container, new SecondFragment());
//        ft.commitAllowingStateLoss();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.container, new SecondFragment());
        //バックキーで前の画面に戻る
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        //ウィンドウ幅を取得
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        float width = point.x;

        switch (transit) {
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN :
                if (enter) {
                    return ObjectAnimator.ofFloat(getView(),"x", width, 0.0f);
                } else {
                    return ObjectAnimator.ofFloat(getView(),"x", 0.0f, -width);
                }
            case FragmentTransaction.TRANSIT_FRAGMENT_CLOSE :
                if (enter) {
                    return ObjectAnimator.ofFloat(getView(),"x", -width, 0.0f);
                } else {
                    return ObjectAnimator.ofFloat(getView(),"x", 0.0f, width);
                }
            default:
                return super.onCreateAnimator(transit, enter, nextAnim);
        }
    }
}


