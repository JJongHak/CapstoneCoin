package com.example.capstonedesign_mj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class External_Search_Menu_3 extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world_info_menu);
    }

    public void onClick(View view){
        Intent intent = new Intent(getApplicationContext(), Company_List_4.class);
        switch(view.getId())
        {
            case R.id.search_us:
                // 미국 100대 기업 정보 intent에 담기
                intent.putExtra("code",3);
                startActivity(intent);
                break;

            case R.id.search_jp:
                // 일본 100대 기업 정보 intent에 담기
                intent.putExtra("code",4);
                startActivity(intent);
                break;

            case R.id.search_ch:
                // 중국 100대 기업 정보 intent에 담기
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
