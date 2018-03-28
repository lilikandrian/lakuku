package com.nguject.bisniz;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    ProgressDialog loading;
    sharedPrefManager msharedPrefManager;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mContext = this;

        msharedPrefManager = new sharedPrefManager(this);


        final EditText username=(EditText) findViewById(R.id.etUsername);
        final EditText pw=(EditText) findViewById(R.id.etPassword);
        final EditText pwc=(EditText) findViewById(R.id.etConfirm);
        final Button b=(Button) findViewById(R.id.btnSignUp);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((username.getText().toString().length()==0)&&(pw.getText().toString().length()==0)){
                    username.setError(" Isi Username !!");
                    pw.setError(" Isi Password !!");
                    }
                else if(username.getText().toString().length()==0){
                    username.setError(" Isi Username !!");
                }
                else if(pw.getText().toString().length()==0){
                    pw.setError(" Isi Password !!");
                }
                else if(!(pw.getText().toString().equalsIgnoreCase(pwc.getText().toString()))){
                    pwc.setError(" Konfirmasi Password Salah!!");
                }
                else {
                    loading = ProgressDialog.show(mContext, null, "Sedang Proses...", true, false);
                    requestSignUp(username.getText().toString(),pw.getText().toString());
                }
            }
        });


    }

    private void requestSignUp(String username,String pw){

        msharedPrefManager.saveSPString(msharedPrefManager.SP_NAMA,username);
        msharedPrefManager.saveSPString(msharedPrefManager.SP_PASSWORD,pw);
        startActivity(new Intent(mContext, LoginActivity.class));


    }
}