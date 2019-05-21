package com.example.capstonedesign_mj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Main_Menu_2 extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void main_button(View view){

        switch(view.getId())
        {
            case R.id.local_search: // 국내기업
                Intent lo_intent = new Intent(getApplicationContext(), Local_Search_Menu_3.class);
                startActivity(lo_intent);
                break;

            case R.id.external_search: // 해외기업
                Intent ex_intent = new Intent(getApplicationContext(), External_Search_Menu_3.class);
                startActivity(ex_intent);
                break;

            case R.id.bookmark_list://즐겨찾기
                Intent bm_intent = new Intent(getApplicationContext(), Bookmark_Menu_3.class);
                startActivity(bm_intent);
                break;

            case R.id.exit: // 종료
                finishAffinity();
                    System.runFinalization();
                    System.exit(0);
        }
    }

    public void logout(View view){
        Intent intent = new Intent(getApplicationContext(), Login_1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
