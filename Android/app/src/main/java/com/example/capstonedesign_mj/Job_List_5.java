package com.example.capstonedesign_mj;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Job_List_5 extends Activity {


    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADD = "address";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_view);

        personList = new ArrayList<HashMap<String, String>>();
        getData("http://210.115.229.135/google.php"); //수정 필요

        Intent intent = getIntent();
        int code = intent.getExtras().getInt("code");

        //테스트 코드
        final TextView test = (TextView)findViewById(R.id.test);

        int test_num = intent.getExtras().getInt("test");
        test.setText(""+test_num+" 번 기업");

        //공지사항

        LinearLayout notice_list = (LinearLayout)findViewById(R.id.notice_list);

        //객체생성
        int cnt = 5;
        LinearLayout notice_frame[] = new LinearLayout[cnt];
        TextView notice_num[] = new TextView[cnt];
        TextView notice_name[] = new TextView[cnt];
        TextView notice_date[] = new TextView[cnt];

        //레이아웃

        LinearLayout.LayoutParams fr_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        fr_param.height = DtS(80);
        fr_param.leftMargin =DtS(10);
        fr_param.rightMargin =DtS(10);

        LinearLayout.LayoutParams num_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        num_param.width = DtS(10);
        num_param.weight = 1;

        LinearLayout.LayoutParams name_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        name_param.width = DtS(260);
        name_param.leftMargin =DtS(10);
        name_param.rightMargin =DtS(10);
        name_param.weight = 1;

        LinearLayout.LayoutParams date_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        date_param.width = DtS(80);
        date_param.weight = 1;

        //공지사항 생성
        for(int i=0;i<cnt;i++) {

            final int position = i;

            notice_frame[i] = new LinearLayout(this);
            notice_frame[i].setOrientation(LinearLayout.HORIZONTAL);
            notice_frame[i].setGravity(Gravity.CENTER);
            notice_list.addView(notice_frame[i], fr_param);

            notice_num[i] = new TextView(this);
            notice_num[i].setText(""+(i+1));
            notice_num[i].setTextSize(18);
            notice_num[i].setGravity(Gravity.CENTER);
            notice_frame[i].addView(notice_num[i], num_param);

            notice_name[i] = new TextView(this);
            notice_name[i].setText("[삼성SDS] " + (i+1) +" 번 공지사항");
            notice_name[i].setTextSize(18);
            notice_name[i].setGravity(Gravity.CENTER);
            notice_frame[i].addView(notice_name[i], name_param);

            notice_date[i] = new TextView(this);
            notice_date[i].setText(""+(i+1)+" 일");
            notice_date[i].setTextSize(15);
            notice_date[i].setGravity(Gravity.CENTER);
            notice_frame[i].addView(notice_date[i], date_param);
        }
    }


    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String name = c.getString(TAG_NAME);
                String address = c.getString(TAG_ADD);

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_ID, id);
                persons.put(TAG_NAME, name);
                persons.put(TAG_ADD, address);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    Job_List_5.this, personList, R.layout.list_item,
                    new String[]{TAG_ID, TAG_NAME, TAG_ADD},
                    new int[]{R.id.id, R.id.name, R.id.address}
            );

            list.setAdapter(adapter);

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


    public void onClick(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
        startActivity(intent);
    }

    public void logout(View view){
        Intent intent = new Intent(getApplicationContext(), Login_1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public int DtS(float DP){ //DP -> 픽셀 변환
        float SP = DP*(getResources().getDisplayMetrics().density);
        return (int)SP;
    }

    public void Pop(View view){
        Intent intent = getIntent();
        final int code = intent.getExtras().getInt("code");

        intent = new Intent(getApplicationContext(), Rotation_List.class);
        switch(code)
        {
            case 1:
                intent.putExtra("code",1);
                startActivity(intent);
                break;
            case 2:
                intent.putExtra("code",2);
                startActivity(intent);
                break;
            case 3:
                intent.putExtra("code",3);
                startActivity(intent);
                break;
            case 4:
                intent.putExtra("code",4);
                startActivity(intent);
                break;
            case 5:
                intent.putExtra("code",5);
                startActivity(intent);
                break;
        }
    }

}
