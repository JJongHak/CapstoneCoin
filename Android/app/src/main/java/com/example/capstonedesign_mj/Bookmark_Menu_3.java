package com.example.capstonedesign_mj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Bookmark_Menu_3 extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark_menu);
    }

    public void onClick(View view){
        Intent intent = new Intent(getApplicationContext(), Company_List_4.class);
        switch(view.getId())
        {
            case R.id.local_group:
                // 국내 즐겨찾기 정보 전송 12345
                startActivity(intent);
                break;

            case R.id.external_group:
                // 해외 즐겨찾기 정보 전송
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
