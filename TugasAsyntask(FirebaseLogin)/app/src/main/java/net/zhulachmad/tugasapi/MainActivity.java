package net.zhulachmad.tugasapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private Button btSignUp;
    private Button btSignIn;
    private Button btSignOut;

    private EditText etEmail;
    private EditText etPassword;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        Button btSignIn = (Button) findViewById(R.id.bt_signin);
        final Button btSignOut = (Button) findViewById(R.id.bt_signout);
        final EditText etEmail = (EditText) findViewById(R.id.et_email);
        final EditText etPassword = (EditText) findViewById(R.id.et_password);
        FirebaseAuth.AuthStateListener fStateListener;


        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataLogin(etEmail.getText().toString(),etPassword.getText().toString());

            }
        });

    }
    private void sendDataLogin(final String email,final String password) {

        final  ProgressDialog loading = new ProgressDialog(MainActivity.this);
        class aTask extends AsyncTask<String, Integer, Integer> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading.setCancelable(true);
                loading.setMessage("Cek Autentikasi....");
                loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                loading.show();

            }

            @Override
            protected Integer doInBackground(String... params) {


                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());


                                if (!task.isSuccessful()) {

                                                                  Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(MainActivity.this, "Proses Login Gagal\n",
                                            Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(MainActivity.this, "Login Berhasil\n" +
                                                    "Email " + email,
                                            Toast.LENGTH_SHORT).show();

                                    if (loading != null && loading.isShowing() && loading.isIndeterminate()) {
                                        loading.dismiss();
                                        Intent menu = new Intent(getApplicationContext(), Main2Activity.class);
                                        startActivity(menu);
                                    }
                                }

                            }
                        });

                return null;

            }


            @Override
            protected void onPostExecute(Integer result) {
                super.onPostExecute(result);

            }


        }

        aTask sendPost = new aTask();
        sendPost.execute();
        // ASYNCTASK END
    }

}

