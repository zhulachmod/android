package net.zhulachmad.tugasapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.io.IOException;
import android.os.AsyncTask;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
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

        client = new OkHttpClient();
        try {
            postdata.put("msisdn", "082296295744");
            postdata.put("content", "testing");
        } catch (JSONException e) {

            e.printStackTrace();
        }

    }

    public void makeGetRequest(View v) throws IOException {
        GetTask task = new GetTask();
        task.execute();
    }

    public class GetTask extends AsyncTask<String,String,String> {
        private Exception exception;

        protected String doInBackground(String... urls) {
            try {
                String getResponse = get("https://publicobject.com/helloworld.txt");
                return getResponse;
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(String getResponse) {
            System.out.println(getResponse);
        }

        public String get(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

    public void makePostRequest(View v) throws IOException {
        PostTask task = new PostTask();
        task.execute();
    }

    public class PostTask extends AsyncTask<String,String,String> {
        private Exception exception;

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
                    .addHeader("Authorization", "Bearer 1f7363694f1b0ea6a167f3fc531672da")
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }
    public String bowlingJson(String msisdn,String content) {
        content ="hallo";
        msisdn="082296295744";

        return null;
    }
}


