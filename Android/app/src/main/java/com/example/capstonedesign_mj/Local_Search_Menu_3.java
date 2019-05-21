package com.example.capstonedesign_mj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Local_Search_Menu_3 extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_info_menu);
    }

    public void onClick(View view){
        Intent intent = new Intent(getApplicationContext(), Company_List_4.class);
        switch(view.getId())
        {
            case R.id.big_company:
                intent.putExtra("code",1);
                startActivity(intent);
                break;

            case R.id.venture_company:
                intent.putExtra("code",2);
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
