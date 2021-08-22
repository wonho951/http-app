package com.javaex.http;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ListAsyncTask extends AsyncTask< Void, Integer, List<GuestbookVo>>{
    //제일 처음
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected List<GuestbookVo> doInBackground(Void... voids) {

        //리턴값으로 보내기 때문에 null로 만든다.
        List<GuestbookVo> guestbookList = null;

        //서버에 연결 한다.
        //요청을 한다.
        //http://221.146.110.204:8088
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
                InputStream is = conn.getInputStream();
                //문자열로 변환
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                //읽어야 됨
                BufferedReader br = new BufferedReader(isr);


                //읽어오셈
                //한줄씩 읽어옴 -> 반복해서 읽어와야되니까 반복문 ㄱ
                //라인이 null이면 끝내셈
                String jsonData = "";
                while (true){

                    String line = br.readLine();

                    if(line == null){
                        break;
                    }

                    jsonData = jsonData + line;
                    //jsonData += line;
                }

                //json --> 자바객체로 만든다.
                //Log.d("javaStudy", "jsonData = " + jsonData);
                Gson gson = new Gson();
                guestbookList = gson.fromJson(jsonData, new TypeToken<List<GuestbookVo>>(){}.getType());


                //Log.d("javaStudy", "size = " + guestbookList.size());
                //Log.d("javaStudy", "index(0).name = " + guestbookList.get(0).getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




        //응답을 받는다.(데이터를 받는다(문자열)) -> guestbooklist 받아야함 -> json형태로 온다 -> java객체로 변환(List<GuestbookVo> guestbookVo)
        return guestbookList;

    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPostExecute(List<GuestbookVo> guestbookList) {
        Log.d("javaStudy", "onPostExecute");
        Log.d("javaStudy", "size = " + guestbookList.size());
        Log.d("javaStudy", "index(0).name = " + guestbookList.get(0).getName());

        super.onPostExecute(guestbookList);
    }
}
