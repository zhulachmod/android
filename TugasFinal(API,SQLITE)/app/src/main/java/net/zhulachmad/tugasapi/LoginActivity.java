package net.zhulachmad.tugasapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btnDaftar, btnGetall,btnLogin;
    private EditText etnama, etemail,etpass;
    private DataHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DataHelper(this);
        btnDaftar=(Button)findViewById(R.id.btndaftar);
        btnLogin =(Button) findViewById(R.id.btnlogin);
        etnama = (EditText) findViewById(R.id.etnama);
        etemail = (EditText) findViewById(R.id.etemail);
        etpass = (EditText) findViewById(R.id.etpassword);


        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inte = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(inte);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String pass = etemail.getText().toString();
                String pass1 = etpass.getText().toString();
                String password=databaseHelper.caripass(etemail.getText().toString().trim(),etpass.getText().toString().trim());

                if(pass.equals(password) && (pass1.equals(password))){

                    Toast.makeText(LoginActivity.this,"Berhasil Login ",Toast.LENGTH_LONG).show();
                    Intent menu = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(menu);


                }else{
                    Toast.makeText(LoginActivity.this,"Gagal login  ",Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
