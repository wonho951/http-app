package com.javaex.http;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListAsyncTask extends AsyncTask< String, String, Void>{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... strings) {

        //서버에 연결 한다.
        //요청을 한다.
        try {
            URL url = new URL("http://221.146.110.204:8088/mysite5/api/guestbook/list");  //url 생성 -> 없는주소 치면 resCode는 에러코드랑 숫자 똑같이 뜸.

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  //url 연결
            conn.setConnectTimeout(10000); // 10초 동안 기다린 후 응답이 없으면 종료
            conn.setRequestMethod("POST"); // 요청방식 POST
            conn.setRequestProperty("Content-Type", "application/json"); //요청시 데이터 형식 json
            conn.setRequestProperty("Accept", "application/json"); //응답시 데이터 형식 json
            conn.setDoOutput(true); //OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
            conn.setDoInput(true); //InputStream으로 서버로 부터 응답을 받겠다는 옵션.

            int resCode = conn.getResponseCode(); // 응답코드 200이 정상
            Log.d("javaStudy", "resCode = " + resCode);

            if(resCode == 200){ //정상이면


                //Stream 을 통해 통신한다
                //데이타 형식은 json으로 한다.

            }
        } catch (IOException e) {
            e.printStackTrace();
        }




        //응답을 받는다.(데이터를 받는다(문자열)) -> guestbooklist 받아야함 -> json형태로 온다 -> java객체로 변환(List<GuestbookVo> guestbookVo)

        return null;
    }

    //진행상태 알려주는 메소드(몇%됐는지)
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    //끝나고
    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
    }



}
