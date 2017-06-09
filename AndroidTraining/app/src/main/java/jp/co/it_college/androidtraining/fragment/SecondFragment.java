package jp.co.it_college.androidtraining.fragment;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import jp.co.it_college.androidtraining.Prefecture;
import jp.co.it_college.androidtraining.R;
import jp.co.it_college.androidtraining.databinding.FragmentSecondBinding;
import jp.co.it_college.androidtraining.databinding.PrefectureRowBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        binding = FragmentSecondBinding.bind(view);

        // Adapterの設定(ListとViewの橋渡し役)
//        ArrayAdapter<String> prefectureAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, PREFECTURES);
        // カスタムAdapterの設定
        PrefectureAdapter prefectureAdapter = new PrefectureAdapter();
        binding.prefectureListView.setAdapter(prefectureAdapter);
        binding.prefectureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PrefectureAdapter adapter = (PrefectureAdapter) adapterView.getAdapter();
                Prefecture prefecture = (Prefecture) adapter.getItem(i);
                Log.d(SecondFragment.this.getClass().getSimpleName(), "item(" + i + "): " + prefecture);
                openThirdFragment(prefecture);
            }
        });
        return view;
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        // ウィンドウ幅を取得
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        float width = point.x;

        switch (transit) {
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN:
                if (enter) {
                    return ObjectAnimator.ofFloat(getView(), "x", width, 0.0f);
                } else {
                    return ObjectAnimator.ofFloat(getView(), "x", 0.0f, -width);
                }
            case FragmentTransaction.TRANSIT_FRAGMENT_CLOSE:
                if (enter) {
                    return ObjectAnimator.ofFloat(getView(), "x", -width, 0.0f);
                } else {
                    return ObjectAnimator.ofFloat(getView(), "x", 0.0f, width);
                }
            default:
                return super.onCreateAnimator(transit, enter, nextAnim);
        }
    }

//    private void openThirdFragment(Prefecture prefecture) {
//        // Fragmentに値を受け渡す
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("prefecture", prefecture);
//        ThirdFragment fragment = new ThirdFragment();
//        fragment.setArguments(bundle);
//
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        ft.replace(R.id.container, fragment);
//        ft.addToBackStack(null);
//        ft.commitAllowingStateLoss();
//    }


    private void openThirdFragment(Prefecture prefecture) { // Fragmentに値を受け渡す
        Bundle bundle = new Bundle();
        bundle.putSerializable("prefecture", prefecture);
        ThirdFragment fragment = new ThirdFragment();
        fragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

    // カスタムAdapter
    private class PrefectureAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return Prefecture.values().length;
        }

        @Override
        public Object getItem(int i) {
            return Prefecture.values()[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            PrefectureRowBinding binding;
            if (view == null) {
                // インナークラスにすることでFragmentのメソッドを呼ぶ事ができる
                binding = DataBindingUtil.inflate((LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE), R.layout.prefecture_row, viewGroup, false);
                view = binding.getRoot();
                view.setTag(binding);
            } else {
                binding = (PrefectureRowBinding) view.getTag();
            }
            Prefecture prefecture = (Prefecture) getItem(i);
            binding.prefectureName.setText(prefecture.getName());
            binding.prefectureIcon.setImageResource(prefecture.getImageId());
            return view;
        }

    }
}