package net.zhulachmad.tugasapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.io.IOException;
import java.util.List;
import android.support.v7.widget.LinearLayoutManager;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;
import okhttp3.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v7.widget.DefaultItemAnimator;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerViewJadwal;
    private JadwalAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private List<JadwalModel> mListData;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    JSONObject postdata = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Login(View v)  {
        Intent login = new Intent(v.getContext(),LoginActivity.class);
        startActivity(login);

    }


    public void APIRegist(View v)  {
        Intent regist = new Intent(Intent.ACTION_VIEW, Uri.parse("http://wahidganteng.ga/process/api/846fe6bc1aa338d3bdaf0d941340682f/jadwal-sholat/get-kota"));
        startActivity(regist);

    }


    public void makeGetRequest(View v) throws IOException {
        GetTask task = new GetTask();
        task.execute();
    }

    public class GetTask extends AsyncTask<String, String, Void> {
        private static final String TAG ="" ;
        private Exception exception;
        private  Response response;
        ProgressDialog loading;

        EditText idk = (EditText) findViewById(R.id.et_idk);
        EditText bln = (EditText) findViewById(R.id.et_bln);
        EditText thn = (EditText) findViewById(R.id.et_thn);

        protected void onPreExecute(){
            super.onPreExecute();
            recyclerViewJadwal = (RecyclerView) findViewById(R.id.recyclerview);
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setMessage("Loading ...");
            mProgressDialog.show();

            mListData = new ArrayList<>();
        }


        protected Void doInBackground(String... urls) {


            final StringRequest request = new StringRequest(Request.Method.GET, "http://wahidganteng.ga/process/api/846fe6bc1aa338d3bdaf0d941340682f/jadwal-sholat?idk="+idk.getText().toString()+"&bln="+bln.getText().toString()+"&thn="+thn.getText().toString(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            mProgressDialog.dismiss();
                            iniData(response);
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);
            return null;
        }
    }


    protected void onPostExecute(JSONObject getResponse) {

        if( mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }

        iniData(getResponse.toString());


    }

    public  void iniData(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);

            //ini toast untuk menampilkan pesan sukses dari json
            Toast.makeText(this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();

            // ini utk mengambil attribute array yg ada di json (yaitu attribute data)
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            //looping utk array
            for(int i=0; i<jsonArray.length(); i++){
                //get json berdasarkan banyaknya data (index i)
                JSONObject objectJadwal = jsonArray.getJSONObject(i);

                //get data berdasarkan attribte yang ada dijsonnya (harus sama)
                String tanggal = objectJadwal.getString("tanggal");
                String imsyak = objectJadwal.getString("imsyak");
                String shubuh = objectJadwal.getString("shubuh");
                String terbit = objectJadwal.getString("terbit");
                String dhuha = objectJadwal.getString("dhuha");
                String dzuhur = objectJadwal.getString("dzuhur");
                String ashr = objectJadwal.getString("ashr");
                String maghrib = objectJadwal.getString("maghrib");
                String isya = objectJadwal.getString("isya");

                //add data ke modelnya
                JadwalModel jadwalModel = new JadwalModel();
                jadwalModel.setTanggal(tanggal);
                jadwalModel.setImsyak(imsyak);
                jadwalModel.setShubuh(shubuh);
                jadwalModel.setTerbit(terbit);
                jadwalModel.setDhuha(dhuha);
                jadwalModel.setDzuhur(dzuhur);
                jadwalModel.setAshr(ashr);
                jadwalModel.setMaghrib(maghrib);
                jadwalModel.setIsya(isya);


                //add model ke list
                mListData.add(jadwalModel);

                //passing data list ke adapter
                mAdapter = new JadwalAdapter(mListData, MainActivity.this);
                mAdapter.notifyDataSetChanged();
                recyclerViewJadwal.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerViewJadwal.setItemAnimator(new DefaultItemAnimator());
                recyclerViewJadwal.setAdapter(mAdapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}


