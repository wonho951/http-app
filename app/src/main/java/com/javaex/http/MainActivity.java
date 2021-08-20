package com.javaex.http;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //메모리에 올린다.
        ListAsyncTask listAsyncTask = new ListAsyncTask();

        //실행(우리가 만든애를 쓰는게 아님. excute사용)
        listAsyncTask.execute();


        /* 만약 저장하는 메소드를 만든다면 파라미터를 넣어야 한다.
        //메모리에 올린다.
        ListAsyncTask writelistAsyncTask = new ListAsyncTask();
        //실행(우리가 만든애를 쓰는게 아님. excute사용)
        listAsyncTask.execute(파라미터 넣어야함.);
        */







    }
}