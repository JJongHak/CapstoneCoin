package com.example.capstonedesign_mj;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class Rotation_List extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.style_company);


        int cnt = 5;
        int k[] = new int[cnt];
        //랜덤
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int date = cal.get ( cal.DATE ) ;
        Random rand = new Random();
        rand.setSeed(date);

        //

        final TextView rotation = (TextView)findViewById(R.id.rotation);
        Intent intent = getIntent();
        final int code = intent.getExtras().getInt("code");

        int image[] = new int[cnt];
        image[0] = R.drawable.noimage;
        String name[] = new String[cnt];
        String field[] = new String[cnt];
        String area[] = new String[cnt];
        String salary[] = new String[cnt];

        switch(code)
        {
            case 1: case 2:
                rotation.setText("금일의 국내 기업 로테이션");
                image[0] = R.drawable.samsung;
                name[0] = "삼성";
                field[0] = "통합";
                area[0] = "서울";
                salary[0] = "1억원";

                image[1] = R.drawable.wonderful;
                name[1] = "원더풀 플랫폼";
                field[1] = "머신러닝";
                area[1] = "서울 서초구";
                salary[1] = "5000만원";
                break;
            case 3: case 4: case 5:
                rotation.setText("금일의 해외 기업 로테이션");
                image[0] = R.drawable.google_icon;
                name[0] = "Google";
                field[0] = "integrated";
                area[0] = "California";
                salary[0] = "1억 6500만원";

                image[1] = R.drawable.xiaomi;
                name[1] = "小米";
                field[1] = "积分";
                area[1] = "北京（Bei Jing）";
                salary[1] = "5000만원";
                break;
        }

        for (int i=2 ; i<cnt; i++){
            image[i] = R.drawable.noimage;
            name[i] = "기업 " + i;
            field[i] = "분야 " + i;
            area[i] = "지역 " + i;
            salary[i] = "평균연봉 " + i;
        }




        LinearLayout company_list = (LinearLayout)findViewById(R.id.list);

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
        for(int j=0;j<cnt;j++) {
            //랜덤 번호 생성 및 중복 제거
            int i = rand.nextInt(cnt);

            for(int a = 0 ; a<j;a++){
                if(k[a] == i){
                    a=-1;
                    i = rand.nextInt(cnt);
                }
            }
            final int position = i;

            comp_frame[i] = new LinearLayout(this);
            comp_frame[i].setOrientation(LinearLayout.HORIZONTAL);
            comp_frame[i].setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Job_List_5.class);
                    intent.putExtra("code",code);
                    intent.putExtra("test", test[position] );
                    startActivity(intent);
                }

            });
            company_list.addView(comp_frame[i], big_param);

            comp_image[i] = new ImageView(this);
            comp_image[i].setImageResource(image[i]);
            comp_frame[i].addView(comp_image[i], img_param);

            text_frame[i] = new LinearLayout(this);
            text_frame[i].setOrientation(LinearLayout.VERTICAL);
            comp_frame[i].addView(text_frame[i],small_param);

            sub_frame1[i] = new LinearLayout(this);
            sub_frame1[i].setOrientation(LinearLayout.HORIZONTAL);
            text_frame[i].addView(sub_frame1[i],sub1_param);


            comp_name[i] = new TextView(this);
            comp_name[i].setText(name[i]);
            comp_name[i].setTextSize(21);
            comp_name[i].setGravity(Gravity.CENTER);
            sub_frame1[i].addView(comp_name[i], name_param);

            slash[i] = new TextView(this);
            slash[i].setText("/");
            slash[i].setGravity(Gravity.CENTER);
            slash[i].setTextSize(25);
            sub_frame1[i].addView(slash[i],slash_param);

            comp_field[i] = new TextView(this);
            comp_field[i].setText(field[i]);
            comp_field[i].setTextSize(21);
            comp_field[i].setGravity(Gravity.CENTER);
            sub_frame1[i].addView(comp_field[i], field_param);

            sub_frame2[i] = new LinearLayout(this);
            sub_frame2[i].setOrientation(LinearLayout.HORIZONTAL);
            text_frame[i].addView(sub_frame2[i],sub2_param);

            comp_area[i] = new TextView(this);
            comp_area[i].setText(area[i]);
            comp_area[i].setTextSize(20);
            comp_area[i].setGravity(Gravity.CENTER);
            sub_frame2[i].addView(comp_area[i], area_param);

            comp_salary[i] = new TextView(this);
            comp_salary[i].setText(salary[i]);
            comp_salary[i].setTextSize(20);
            comp_salary[i].setGravity(Gravity.CENTER);
            sub_frame2[i].addView(comp_salary[i], salary_param);

            comp_bookmark[i] = new Button(this);
            comp_bookmark[i].setText("☆");
            comp_bookmark[i].setTextSize(30);
            comp_frame[i].addView(comp_bookmark[i], but_param);

            k[j] = i;
        }
    }
    public int DtS(double DP){ //DP -> 픽셀 변환
        double SP = DP*(getResources().getDisplayMetrics().density);
        return (int)SP;
    }
}
