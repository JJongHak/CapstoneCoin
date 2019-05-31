package com.example.capstonedesign_mj;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Job_List_5 extends Activity {


    String myJSON;

    private static String TAG_RESULTS = "result";
    private static String TAG_1 = "title";
    private static String TAG_2 = "classify";
    private static String TAG_3 = "type";
    private static String TAG_4 = "date";
    private static String TAG_5 = null;
    private static String TAG_6 = "six";
    private static String url = "210.115.229.135";

    private static String Tag_array[];
    private static int layer_view[];
    private static boolean check = false;


    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_view);


        Intent intent = getIntent();

        final int code = intent.getExtras().getInt("code");
        final int code2 = intent.getExtras().getInt("code2");
        final int comp_image = intent.getExtras().getInt("image");
        final String comp_name = intent.getExtras().getString("name");
        final String comp_field= intent.getExtras().getString("field");
        final String comp_area = intent.getExtras().getString("area");
        final String comp_salary = intent.getExtras().getString("salary");


        final TextView name = (TextView)findViewById(R.id.comp_name);
        final TextView field = (TextView)findViewById(R.id.comp_field);
        final TextView area = (TextView)findViewById(R.id.comp_area);
        final TextView salary = (TextView)findViewById(R.id.comp_salary);
        final ImageView image = (ImageView)findViewById((R.id.comp_image));

        image.setImageResource(comp_image);
        name.setText(comp_name);
        field.setText(comp_field);
        area.setText(comp_area);
        salary.setText(comp_salary);

        String url = comp_url(comp_name);

        list = (ListView) findViewById(R.id.listview);
        personList = new ArrayList<HashMap<String, String>>();
        getData(url);
    }


    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            final String link[] = new String[peoples.length()];

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String view[] = new String[Tag_array.length];

                for (int j = 0; j< Tag_array.length; j++) {
                    view[j] = c.getString(Tag_array[j]);
                }

                if (check == true) {
                    link[i] = c.getString(TAG_6);
                }
                HashMap<String, String> persons = new HashMap<String, String>();

                for (int j = 0; j< Tag_array.length; j++) {
                    persons.put(Tag_array[j],view[j]);
                }
                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    Job_List_5.this, personList, R.layout.style_notice2, Tag_array, layer_view);

            list.setAdapter(adapter);
            if(check == true) {
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link[i]));
                        startActivity(intent);
                    }
                });
            }
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

    public String comp_url(String name){
        switch (name)
        {
            case "삼성":
                TAG_1 = "classify";
                TAG_2 = "c_name";
                TAG_3 = "title";
                TAG_4 = "s_date";
                TAG_5 = "e_date";
                url = "http://210.115.229.135/samsung.php";
                Tag_array = new String[]{TAG_1,TAG_2,TAG_3,TAG_4,TAG_5};
                layer_view = new int[]{R.id.view1,R.id.view2,R.id.view3,R.id.view4,R.id.view5};
                check = false;
                break;
            case "원더풀 플랫폼":
                TAG_1 = "classify";
                TAG_2 = "c_name";
                TAG_3 = "title";
                TAG_4 = "s_date";
                TAG_6 = "e_date";
                url = "http://210.115.229.135/venture.php";
                Tag_array = new String[]{TAG_1,TAG_2,TAG_3,TAG_4};
                layer_view = new int[]{R.id.view1,R.id.view2,R.id.view3,R.id.view4};
                check = true;
                break;
            case "Google":
                TAG_1 = "title";
                TAG_2 = "classify";
                TAG_3 = "address";
                TAG_6 = "url";
                url = "http://210.115.229.135/google.php";
                Tag_array = new String[]{TAG_1,TAG_2,TAG_3};
                layer_view = new int[]{R.id.view1,R.id.view2,R.id.view3};
                check = true;
                break;
            case "小米":
                TAG_1 = "title";
                TAG_2 = "classify";
                TAG_3 = "type";
                TAG_4 = "date";
                TAG_6 = "link";
                url = "http://210.115.229.135/xiaomi.php";
                Tag_array = new String[]{TAG_1,TAG_2,TAG_3,TAG_4};
                layer_view = new int[]{R.id.view1,R.id.view2,R.id.view3,R.id.view4};
                check = true;
                break;
            case "Facebook":
                TAG_1 = "title";
                TAG_2 = "classify";
                TAG_3 = "address";
                TAG_6 = "link";
                url = "http://210.115.229.135/facebook.php";
                Tag_array = new String[]{TAG_1,TAG_2,TAG_3};
                layer_view = new int[]{R.id.view1,R.id.view2,R.id.view3};
                check = true;
                break;
            case "滴滴":
                TAG_1 = "title";
                TAG_2 = "classify";
                TAG_3 = "type";
                TAG_4 = "address";
                TAG_5 = "date";
                TAG_6 = "link";
                url = "http://210.115.229.135/didi.php";
                Tag_array = new String[]{TAG_1,TAG_2,TAG_3,TAG_4,TAG_5};
                layer_view = new int[]{R.id.view1,R.id.view2,R.id.view3,R.id.view4,R.id.view5};
                check = true;
            case "Vivo":
                TAG_1 = "title";
                TAG_2 = "classify";
                TAG_3 = "num";
                TAG_4 = "date";
                TAG_6 = "link";
                url = "http://210.115.229.135/vivo.php";
                Tag_array = new String[]{TAG_1,TAG_2,TAG_3,TAG_4};
                layer_view = new int[]{R.id.view1,R.id.view2,R.id.view3,R.id.view4};
                check = true;
                break;
            case "Cisco":
                TAG_1 = "title";
                TAG_2 = "classify";
                TAG_3 = "type";
                TAG_4 = "address";
                TAG_6 = "link";
                url = "http://210.115.229.135/cisco.php";
                Tag_array = new String[]{TAG_1,TAG_2,TAG_3,TAG_4};
                layer_view = new int[]{R.id.view1,R.id.view2,R.id.view3,R.id.view4};
                check = true;
                break;
            default:

        }
        return url;
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

    public void logout(View view){
        Intent intent = new Intent(getApplicationContext(), Login_1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}