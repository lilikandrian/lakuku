package com.nguject.bisniz;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nguject.bisniz.Topsis.MatrixAdapter;
import com.nguject.bisniz.Topsis.Topsis;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class BusinessListActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    ArrayList<HashMap<String,String>> listItem;
    Topsis obj1=new Topsis();
    MatrixAdapter mtx1=new MatrixAdapter();
    ProgressDialog loading;
    TextView score,score2,vproductName;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);
        setUpBackButton();mContext=this;
        Bundle b = getIntent().getExtras();
        final double productPrice = Double.parseDouble(b.getString("Price"));
        final String productName = b.getString("Name");
        final String productCategory = b.getString("Category");
        final String productConditions = b.getString("Conditions");
        final String productDesc = b.getString("Desc");
        final double sellerFeedback = Double.parseDouble(b.getString("Feedback"));
        String url="";
        if (productConditions.equalsIgnoreCase("Baru")) {
            if (productCategory.equalsIgnoreCase("Smartphone")) {
                url = "https://api.bukalapak.com/v2/products.json?keywords=" + productName + "&category_id=8&conditions=new";
            } else {
                url = "https://api.bukalapak.com/v2/products.json?keywords=" + productName + "&category_id=3&conditions=new";
            }
        }
        else {
            if (productCategory.equalsIgnoreCase("Smartphone")) {
                url = "https://api.bukalapak.com/v2/products.json?keywords=" + productName + "&category_id=8&conditions=used";
            } else {
                url = "https://api.bukalapak.com/v2/products.json?keywords=" + productName + "&category_id=3&conditions=used";
            }
        }

        score=(TextView)findViewById(R.id.score);
        score2=(TextView)findViewById(R.id.score2);
        vproductName=(TextView)findViewById(R.id.productName);
        vproductName.setText(productName);
        requestQueue = Volley.newRequestQueue(BusinessListActivity.this);
        listItem = new ArrayList<HashMap<String, String>>();
        loading=ProgressDialog.show(mContext,null,"Sedang Proses",true,false);
        stringRequest = new StringRequest(Request.Method.GET, url.replace(" ","%20"), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("response : ", response);
                    double totalInterest = 0, totalView = 0, normalValue = 100;
                    int maxFeedback = 100000, maxViews = 1000, maxInterest = 1000, maxDesc = 1000;
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("products");
                        System.out.println(" Panjang "+jsonArray.length());
                        if(jsonArray.length()==0){
                            score.setText("100%");
                            loading.dismiss();
                        }else {
                            int l;

                            if(jsonArray.length()>4){
                                l=4;
                            }else{
                                l=jsonArray.length();
                            }
                            double[] tmpPrice=new double[l+1];
                            double[] tmpFeedback=new double[l+1];
                            double[] tmpDesc=new double[l+1];
                            for (int i = 0; i < l; i++) {
                                JSONObject json = jsonArray.getJSONObject(i);
                                Integer ob = new Integer(json.getString("desc").split("\\w+").length);
                                Double n = ob.doubleValue() / maxDesc * normalValue;
                                System.out.println("Panjang " + n);
                                obj1.dataInput(json.getString("name"), json.getDouble("price") / 100000000 * normalValue, n, json.getDouble("interest_count") / maxInterest * normalValue, json.getDouble("view_count") / maxViews * normalValue, (json.getDouble("seller_positive_feedback") - json.getDouble("seller_negative_feedback")) / maxFeedback * normalValue);
                                totalInterest = totalInterest + json.getDouble("interest_count");
                                totalView = totalView + json.getDouble("view_count");
                                tmpPrice[i]=json.getDouble("price") ;
                                tmpFeedback[i]=json.getDouble("seller_positive_feedback") - json.getDouble("seller_negative_feedback") ;
                                tmpDesc[i]=json.getString("desc").split("\\w+").length;
                            }
                            Integer ob2 = new Integer(productDesc.split("\\w+").length);
                            obj1.firstData(productName, productPrice / 100000000 * normalValue, ob2.doubleValue() / maxDesc * normalValue, ((totalInterest / jsonArray.length()) / maxInterest * normalValue), ((totalView / jsonArray.length()) / maxViews * normalValue), sellerFeedback / maxFeedback * normalValue);
                            tmpPrice[l]=productPrice ;
                            tmpFeedback[l]=sellerFeedback ;
                            tmpDesc[l]=ob2.doubleValue();
                            int size = obj1.dataOutput();
                            System.out.print("Total Column = " + obj1.mx.countColumn(obj1.productPrice, size) + " " + obj1.mx.countColumn(obj1.productDesc, size) + " " + " " + obj1.mx.countColumn(obj1.ppInterest, size) + " " + obj1.mx.countColumn(obj1.ppView, size) + " " + obj1.mx.countColumn(obj1.feedback, size));
                            obj1.getdataOutput2(obj1.mx.countColumn(obj1.productPrice, size), obj1.mx.countColumn(obj1.productDesc, size), obj1.mx.countColumn(obj1.ppInterest, size), obj1.mx.countColumn(obj1.ppView, size), obj1.mx.countColumn(obj1.feedback, size));
                            obj1.getdataOutput3();
                            obj1.getdataOutput4();
                            obj1.getdataOutput5();

                            String s1="",s2="",s3="",s="",s12="",s22="",s32="";

                            if(obj1.getdataOutput5()>=79.99){
                                s="tinggi";
                            }else if(obj1.getdataOutput5()>=69.99){
                                s="sedang";
                            }else if(obj1.getdataOutput5()>=49.99){
                                s="rendah";
                            }else {
                                s="sangat rendah";
                            }
                            boolean sP=false,tF=false,tD=false;
                            if(tmpPrice[l]!=mtx1.countColumnSolution(tmpPrice,"Min")) {
                                s1="Harga Barang Mahal, ";
                                s12="turunkan harga barang, ";
                            }else{
                                s1="Harga Barang Murah, ";
                                s12="turunkan harga barang, ";
                                sP=true;
                            }
                            if(tmpFeedback[l]!=mtx1.countColumnSolution(tmpFeedback,"Max")) {
                                s2="Feedback Pedagang Rendah, ";
                                s22="Naikkan Feedback Pedagang, ";
                            }else{
                                s2="Feedback Pedagang Tinggi, ";
                                s22="Naikkan Feedback Pedagang, ";
                                tF=true;
                            }
                            if(tmpDesc[l]!=mtx1.countColumnSolution(tmpDesc,"Max")) {
                                s32="Perbanyak Deskripsi Barang ";
                                s3="Deskripsi Barang Kurang Maksimum.";
                            }else{
                                s3="Deskripsi Barang Maksimum.";
                                s32="Perbanyak Deskripsi Barang ";
                                tD=true;
                            }

                            if(ob2.doubleValue()==1000){
                                s32="";
                            }
                            if(sellerFeedback==maxFeedback){
                                s22="";
                            }
                            if(productPrice==0){
                                s12="";
                            }
                            if(sP==true&&tF==true&&tD==true&&ob2.doubleValue()==1000&&sellerFeedback==maxFeedback&&productPrice==0){
                                score2.setText("Hasil prediksi diatas dipengaruhi oleh nilai kriteria penjualan barang yaitu "+s1+s2+s3+"\n\n");
                            }else{
                                score2.setText("Hasil prediksi diatas dipengaruhi oleh nilai kriteria penjualan barang yaitu "+s1+s2+s3+
                                        "\n\nUntuk meningkatkan nilai persentase, coba anda "+s12+s22+s32+".\n\n");
                            }
                            score.setText(Double.toString(obj1.getdataOutput5()) + "%");
                            loading.dismiss();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(BusinessListActivity.this, "Maaf, Ada Gangguan Jaringan  ", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequest);
        }

    public void setUpBackButton(){
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int actionId=item.getItemId();
        switch (actionId){
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
        }
        return false;
    }
}