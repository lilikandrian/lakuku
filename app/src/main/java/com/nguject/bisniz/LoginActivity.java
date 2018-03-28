package com.nguject.bisniz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    sharedPrefManager msharedPrefManager;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;

        msharedPrefManager = new sharedPrefManager(this);

        System.out.println(" Username :" +msharedPrefManager.getSPNama());

        System.out.println(" Password :" +msharedPrefManager.getSPPASSWORD());
        final EditText un=(EditText) findViewById(R.id.etUsername1);
        final EditText pw=(EditText) findViewById(R.id.etPassword1);
        Button b=(Button) findViewById(R.id.btnLogin);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!un.getText().toString().equalsIgnoreCase(msharedPrefManager.getSPNama())||!pw.getText().toString().equalsIgnoreCase(msharedPrefManager.getSPPASSWORD())){
                    Toast.makeText(mContext, "Username atau Password Salah", Toast.LENGTH_SHORT).show();

                }else {
                    msharedPrefManager.saveSPBoolean(msharedPrefManager.SP_HAS_LOGIN, true);
                    startActivity(new Intent(getBaseContext(), InputActivity.class));
                }
            }
        });

        if (msharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(LoginActivity.this, InputActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }



}