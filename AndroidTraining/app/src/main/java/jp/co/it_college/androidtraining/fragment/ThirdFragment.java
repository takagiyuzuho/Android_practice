package jp.co.it_college.androidtraining.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import jp.co.it_college.androidtraining.Prefecture;
import jp.co.it_college.androidtraining.R;
import jp.co.it_college.androidtraining.databinding.FragmentThirdBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;


    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false); binding = FragmentThirdBinding.bind(view);
        Prefecture prefecture = (Prefecture) getArguments().getSerializable("prefecture");
        Log.d(getClass().getSimpleName(), "prefecture: " + prefecture);
        getCityList(prefecture);
        return view;
    }


    private void getCityList(Prefecture prefecture) {
        // AsyncTask<Params, Progress, Result>
        // Params: 引数に使う型
        // Progress: 進捗状況を更新するのに使う型
        // Result: マルチスレッド処理の結果として返す型
        new AsyncTask<Prefecture, Void, List<String>>() {
            @Override
            protected List<String> doInBackground(Prefecture... prefectures) {
            // 別スレッド内で行う処理(大体は通信処理)をこのメソッド内に書く
                try {
                    Prefecture prefecture = prefectures[0];
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                URL url = new URL("http://geoapi.heartrails.com/api/json?method=getCities&prefecture=" + prefecture.getName());
                                URLConnection con = url.openConnection();
                                con.connect();

                                //レスポンスを受信
                                InputStream in = con.getInputStream();
                                StringBuffer sb = new StringBuffer();
                                String st = "";
                                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                                while ((st = br.readLine()) != null) {
                                    sb.append(st);
                                }
                                String jsonString = sb.toString();
                                Log.d(getClass().getSimpleName(), jsonString);
                                JSONObject response = new JSONObject(jsonString).getJSONObject("response");
                                JSONArray locationArray = response.getJSONArray("location");

                                //ArrayListにcityをadd
                                List<String> cityList = new ArrayList<>();
                                for (int i = 0; i < locationArray.length(); i++) {
                                    JSONObject location = locationArray.getJSONObject(i);
                                    String city = location.getString("city");
                                    cityList.add(city);
                                }

                                //adapter接続
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, cityList);
                                binding.cityListView.setAdapter(adapter);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return null;

                        }

                    }).start();
                }
            }
        }
    }

}
