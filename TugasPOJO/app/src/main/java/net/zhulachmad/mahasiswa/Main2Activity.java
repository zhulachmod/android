package net.zhulachmad.mahasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Parcelable;
import android.os.Parcel;
public class Main2Activity extends AppCompatActivity {
     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main2);


         EditText etNama = (EditText) findViewById(R.id.etName);
         EditText etNim = (EditText) findViewById(R.id.etNim);


         if (getIntent().getStringExtra("nama") != null) {
             Intent intent = getIntent();
             etNama.setText(intent.getStringExtra("nama"));
             etNim.setText(intent.getStringExtra("nim"));


         } else if (getIntent().getParcelableExtra("object") != null) {

             Intent i = getIntent();
             ParcelableMahasiswa parcelablemahasiswa = (ParcelableMahasiswa) i.getParcelableExtra("object");
             Mahasiswa mahasiswa = parcelablemahasiswa.getNamaMhs();
             String nama = mahasiswa.getNamaMhs();
             String nim = mahasiswa.getNim();
             etNama.setText(nama);
             etNim.setText(nim);


        }else {
                 Intent nilaibalik=getIntent();
                 etNama.setText(getIntent().getStringExtra("nama"));
                 etNim.setText(getIntent().getStringExtra("nim"));
                 nilaibalik.putExtra("nama3","Activity 2");
                 setResult(RESULT_OK,nilaibalik);
                 finish();
         }


     }
        
}
