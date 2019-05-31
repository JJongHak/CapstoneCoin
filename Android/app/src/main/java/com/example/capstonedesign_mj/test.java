package com.example.capstonedesign_mj;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class test extends Activity {

    String myJSON;
// 삼성 기업코드 스위치문
    private static final String TAG_RESULTS = "result";
    private static final String TAG_1 = "title";
    private static final String TAG_2 = "classify";
    private static final String TAG_3 = "type";
    private static final String TAG_4 = "date";
    private static final String TAG_5 = "link";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_view);
        list = (ListView) findViewById(R.id.listview);
        personList = new ArrayList<HashMap<String, String>>();
        getData("http://210.115.229.135/xiaomi.php");
    }


    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            final String link[] = new String[peoples.length()];

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String view1 = c.getString(TAG_1);
                String view2 = c.getString(TAG_2);
                String view3 = c.getString(TAG_3);
                String view4 = c.getString(TAG_4);
                String view5 = c.getString(TAG_5);

                link[i] = view5;

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_1, view1);
                persons.put(TAG_2, view2);
                persons.put(TAG_3, view3);
                persons.put(TAG_4, view4);
                persons.put(TAG_5, view5);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    test.this, personList, R.layout.style_notice2,
                    new String[]{TAG_1, TAG_2, TAG_3, TAG_4},
                    new int[]{R.id.view1, R.id.view2, R.id.view3, R.id.view4}
            );

            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link[i]));
                    startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }


            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}