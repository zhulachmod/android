package net.zhulachmad.mahasiswa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button kirim =(Button) findViewById(R.id.btnPassObject);
        Button kirim2 =(Button) findViewById(R.id.btnPassData);
        Button dial =(Button) findViewById(R.id.btnDial);
        Button returnback = (Button) findViewById(R.id.btnreturn);
        final EditText etNama = (EditText) findViewById(R.id.etName);
        final EditText etNim = (EditText) findViewById(R.id.etNim);

        kirim.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Mahasiswa mahasiswa =new Mahasiswa();
                mahasiswa.setNamaMhs(etNama.getText().toString());
                mahasiswa.setNim(etNim.getText().toString());
                Intent intent= new Intent(getApplicationContext(),Main2Activity.class);
                ParcelableMahasiswa parcelablemahasiswa= new ParcelableMahasiswa(mahasiswa);
                intent.putExtra("object",parcelablemahasiswa);
                startActivity(intent);


            }
        });

        kirim2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(MainActivity.this, Main2Activity.class);
                intent2.putExtra("nama",etNama.getText().toString());
                intent2.putExtra("nim",etNim.getText().toString());
                startActivity(intent2);
            }

        });


        dial.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



        returnback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentback = new Intent(MainActivity.this, Main2Activity.class);
                intentback.putExtra("nama3",etNama.getText().toString());
                intentback.putExtra("nim3",etNim.getText().toString());
                startActivityForResult(intentback,1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            if (data.hasExtra("nama3")) {
                Toast.makeText(
                        this,
                        "Data berhasil di terima,pesan ini dari  :  "+
                                data.getExtras().getString("nama3"),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    }




