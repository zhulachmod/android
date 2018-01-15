package net.zhulachmad.tugasapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.io.IOException;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {
          OkHttpClient client;

       public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
       JSONObject postdata = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nophone = (EditText) findViewById(R.id.et_phone);
        EditText pesan = (EditText) findViewById(R.id.et_pesan);


        client = new OkHttpClient();
        try {
            postdata.put("msisdn", nophone.getText().toString());
            postdata.put("content", pesan.getText().toString());


        } catch (JSONException e) {

          e.printStackTrace();
         }
    }
    public void APIRegist(View v)  {
        Intent regist = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mainapi.net/store/client/#/signup"));
        startActivity(regist);

    }

    public void makeGetRequest(View v) throws IOException {
        GetTask task = new GetTask();
        task.execute();
    }

    public class GetTask extends AsyncTask<String, String, JSONObject> {
        private static final String TAG ="" ;
        private Exception exception;
        private  Response response;
        ProgressDialog loading;
        EditText produk = (EditText) findViewById(R.id.et_produk);
        protected void onPreExecute(){
            super.onPreExecute();
            loading= ProgressDialog.show(MainActivity.this,"Mengambil Data","Loading Data...",false,false);

        }


        protected JSONObject doInBackground(String... urls) {

            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://sites.google.com/macros/exec?service=AKfycbx_-gZbLP7Z2gGxehXhWMWDAAQsTp3e3bmpTBiaLuzSDQSbIFWD&menu=nama_produk&query="+produk.getText().toString())
                        .build();
                response = client.newCall(request).execute();
                return new JSONObject(response.body().string());
            } catch (@NonNull IOException | JSONException e) {
                Log.e(TAG, "" + e.getLocalizedMessage());
            }
            return null;
        }


        protected void onPostExecute(JSONObject getResponse) {

            if(loading.isShowing()){
                loading.dismiss();
            }

            Toast.makeText(MainActivity.this,getResponse.toString(),Toast.LENGTH_LONG).show();
            System.out.println(getResponse);


        }


    }


    public void makePostRequest(View v) throws IOException {
        PostTask task = new PostTask();
        task.execute();
    }

    public class PostTask extends AsyncTask<String,String,String> {
        private Exception exception;
        EditText token = (EditText) findViewById(R.id.et_token);
        protected String doInBackground(String... urls) {
            try {
                String getResponse = post("https://api.mainapi.net/smsnotification/1.0.0/messages",postdata);

                return getResponse;
            } catch (Exception e) {
                this.exception = e;

                return null;
            }
        }

        protected void onPostExecute(String getResponse) {
            System.out.println(getResponse);
        }

        private String post(String url, JSONObject postdata) throws IOException {
            RequestBody body = RequestBody.create(JSON, postdata.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Content-Type","application/x-www-form-urlencoded")
                    .addHeader("Accept","application/json")
                    .addHeader("Authorization",token.getText().toString())
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();

            return response.body().string();

        }
    }



}


