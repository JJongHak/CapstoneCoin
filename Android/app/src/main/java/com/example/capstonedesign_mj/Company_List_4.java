package com.example.capstonedesign_mj;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.util.zip.Inflater;

public class Company_List_4 extends Activity {

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_search);

        int cnt = 5;
        int image = R.drawable.noimage;
        String Tag_Name = "Name";
        String Tag_Num = "Num";

        //어느나라 기업인지 보여주는 코드
        final TextView domain = (TextView)findViewById(R.id.domain);

        Intent intent = getIntent();

        int code = intent.getExtras().getInt("code");

        switch(code)
        {
            case 1:
                domain.setText("<국내 100대 대기업 목록>");
                break;
            case 2:
                domain.setText("<국내 400대 벤처기업 목록>");
                break;
            case 3:
                domain.setText("<미국 100대 기업 목록>");
                break;
            case 4:
                domain.setText("<일본 100대 기업 목록>");
                break;
            case 5:
                domain.setText("<중국 100대 기업 목록>");
                break;
        }

        //기업 목록 생성
        LinearLayout company_list = (LinearLayout)findViewById(R.id.company_list);

        //객체생성
        LinearLayout comp_frame[] = new LinearLayout[cnt];
        ImageView comp_image[] = new ImageView[cnt];
        LinearLayout text_frame[] = new LinearLayout[cnt];
        LinearLayout sub_frame1[] = new LinearLayout[cnt];
        LinearLayout sub_frame2[] = new LinearLayout[cnt];
        TextView comp_name[] = new TextView[cnt];
        TextView slash[] = new TextView[cnt];
        TextView comp_field[] = new TextView[cnt];
        TextView comp_area[] = new TextView[cnt];
        TextView comp_salary[] = new TextView[cnt];
        Button comp_bookmark[] = new Button[cnt];

        //레이아웃 생성

        LinearLayout.LayoutParams big_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        big_param.height = DtS(100);

        LinearLayout.LayoutParams img_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        img_param.width = DtS(100);
        img_param.weight = 1;

        LinearLayout.LayoutParams small_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        small_param.width = DtS(237);
        small_param.weight = 1;

        LinearLayout.LayoutParams sub1_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        sub1_param.height = DtS(55);

        LinearLayout.LayoutParams sub2_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams name_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        name_param.width = DtS(100);
        name_param.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams slash_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        slash_param.width = DtS(0.25);
        slash_param.weight = 1;
        slash_param.topMargin = DtS(5);
        slash_param.bottomMargin = DtS(5);
        slash_param.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams field_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        field_param.width = DtS(100);
        field_param.gravity = Gravity.CENTER;
        field_param.weight = 1;

        LinearLayout.LayoutParams area_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        area_param.width = DtS(100);
        area_param.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams salary_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        salary_param.width = DtS(100);
        salary_param.gravity = Gravity.CENTER;
        salary_param.weight = 1;

        LinearLayout.LayoutParams but_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        but_param.setMargins(DtS(10),DtS(10),DtS(10),DtS(10));
        but_param.weight = 1;

        final int test[] = {1,2,3,4,5};

        //기업 생성
       for(int i=0;i<cnt;i++) {

           final int position = i;

            comp_frame[i] = new LinearLayout(this);
            comp_frame[i].setOrientation(LinearLayout.HORIZONTAL);
            comp_frame[i].setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Job_List_5.class);
                    intent.putExtra("test", test[position] );
                    startActivity(intent);
                }

            });
            company_list.addView(comp_frame[i], big_param);

            comp_image[i] = new ImageView(this);
            comp_image[i].setImageResource(image);
            comp_frame[i].addView(comp_image[i], img_param);

            text_frame[i] = new LinearLayout(this);
            text_frame[i].setOrientation(LinearLayout.VERTICAL);
            comp_frame[i].addView(text_frame[i],small_param);

            sub_frame1[i] = new LinearLayout(this);
            sub_frame1[i].setOrientation(LinearLayout.HORIZONTAL);
            text_frame[i].addView(sub_frame1[i],sub1_param);


            comp_name[i] = new TextView(this);
            comp_name[i].setText("기업명 "+""+i);
            comp_name[i].setTextSize(21);
            comp_name[i].setGravity(Gravity.CENTER);
            sub_frame1[i].addView(comp_name[i], name_param);

            slash[i] = new TextView(this);
            slash[i].setText("/");
            slash[i].setGravity(Gravity.CENTER);
            slash[i].setTextSize(25);
            sub_frame1[i].addView(slash[i],slash_param);

            comp_field[i] = new TextView(this);
            comp_field[i].setText("분야 "+""+i);
            comp_field[i].setTextSize(21);
            comp_field[i].setGravity(Gravity.CENTER);
            sub_frame1[i].addView(comp_field[i], field_param);

            sub_frame2[i] = new LinearLayout(this);
            sub_frame2[i].setOrientation(LinearLayout.HORIZONTAL);
            text_frame[i].addView(sub_frame2[i],sub2_param);

            comp_area[i] = new TextView(this);
            comp_area[i].setText("지역 "+""+i);
            comp_area[i].setTextSize(20);
            comp_area[i].setGravity(Gravity.CENTER);
            sub_frame2[i].addView(comp_area[i], area_param);

            comp_salary[i] = new TextView(this);
            comp_salary[i].setText("평균연봉 "+""+i);
            comp_salary[i].setTextSize(20);
            comp_salary[i].setGravity(Gravity.CENTER);
            sub_frame2[i].addView(comp_salary[i], salary_param);

            comp_bookmark[i] = new Button(this);
            comp_bookmark[i].setText("☆");
            comp_bookmark[i].setTextSize(30);
            comp_frame[i].addView(comp_bookmark[i], but_param);
        }

    }

    public void logout(View view){
        Intent intent = new Intent(getApplicationContext(), Login_1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public int DtS(double DP){ //DP -> 픽셀 변환
        double SP = DP*(getResources().getDisplayMetrics().density);
        return (int)SP;
    }

}
