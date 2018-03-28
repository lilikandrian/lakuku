package com.nguject.bisniz;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity implements View.OnClickListener{
    RadioGroup rg;
    RadioButton radio;
    EditText name,desc;
    EditText price,feedback;
    Button button;
    Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        showAll();
        button.setOnClickListener(this);
    }

    public void showAll(){
        feedback=(EditText) findViewById(R.id.feed);
        rg=(RadioGroup) findViewById(R.id.rg);
        price=(EditText) findViewById(R.id.price);
        name=(EditText) findViewById(R.id.name);
        desc=(EditText) findViewById(R.id.desc);
        spinner2 = (Spinner) findViewById(R.id.category);
        button=(Button) findViewById(R.id.process);
    }

    public void onClick(View v) {
        int id = rg.getCheckedRadioButtonId();
        radio = (RadioButton) findViewById(id);
        double rp,fb;
        if(price.getText().toString().isEmpty()){
            rp=0;
        }else{
            rp=Integer.parseInt(getJoinData(price.getText().toString().split("\\.")));
        }
        if(feedback.getText().toString().isEmpty()){
            fb=0;
        }else{
            fb=Double.parseDouble(feedback.getText().toString());
        }
        if((name.getText().toString().length()==0)&&(price.getText().toString().length()==0)&&(feedback.getText().toString().length()==0)
                &&(desc.getText().toString().length()==0)){
            name.setError(" Isi Nama Barang !!");
            price.setError(" Isi Harga Barang !!");
            feedback.setError(" Isi Feedback !!");
            desc.setError(" Isi Deskripsi Barang !!");
        }
        else if(name.getText().toString().length()==0){
            name.setError("Isi Nama Barang !!");
        }
        else if(price.getText().toString().length()==0){
            price.setError("Isi Harga Barang !!");
        }
        else if(feedback.getText().toString().length()==0){
            feedback.setError("Isi Feedback !!");
        }
        else if(desc.getText().toString().split("\\w+").length>1000){
            desc.setError("Jumlah Kata Diluar Batasan !!");
        }
        else if(desc.getText().toString().length()==0){
            desc.setError("Isi Deskripsi Barang!!");
        }
        else if(rp>100000000||rp<0&&fb>100000||fb<0){
            price.setError("Harga Diluar Batasan !!");
            feedback.setError("Feedback Diluar Batasan !!");
        }
        else if(rp>100000000||rp<0){
            price.setError("Harga Diluar Batasan !!");
        }
        else if(fb>100000||fb<0){
            feedback.setError("Feedback Diluar Batasan !!");
        }else if(id==-1){
            Toast.makeText(InputActivity.this, "Pilih Kondisi Barang Anda  ", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent in = new Intent(getApplicationContext(), BusinessListActivity.class);
            Bundle b = new Bundle();
            b.putString("Conditions", radio.getText().toString());
            b.putString("Price", Double.toString(rp));
            b.putString("Feedback", Double.toString(fb));
            b.putString("Name", name.getText().toString());
            b.putString("Category", spinner2.getSelectedItem().toString());
            b.putString("Desc", desc.getText().toString());
            in.putExtras(b);
            startActivity(in);
        }

    }

    public String getJoinData(String[] rp){
        String price="";
        for(String data:rp){
            price=price+data;
        }
        return price;
    }

}