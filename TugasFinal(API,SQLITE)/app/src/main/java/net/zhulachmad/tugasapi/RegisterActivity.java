package net.zhulachmad.tugasapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Button btnStore;
    private EditText etnama, etemail,etpass;
    private DataHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        databaseHelper = new DataHelper(this);

        btnStore = (Button) findViewById(R.id.btnstore);

        etnama = (EditText) findViewById(R.id.etnama);
        etemail = (EditText) findViewById(R.id.etemail);
        etpass = (EditText) findViewById(R.id.etpassword);


        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addUserDetail(etnama.getText().toString(), etemail.getText().toString(), etpass.getText().toString());
                etnama.setText("");
                etemail.setText("");
                etpass.setText("");
                Toast.makeText(RegisterActivity.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
