package com.example.capstonedesign_mj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login_1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
    }

    public void Login(View view){
        /* 로그인 정보 확인
        * true, false
        * */

        Intent intent = new Intent(getApplicationContext(), test.class);

        startActivity(intent);

    }


}
