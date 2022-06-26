package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAddToCart extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String user,dist;
    TextView medicine,type,desc,price;
    EditText qty;

    medicineDAO ob;
    String v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_to_cart);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        dist = sharedPref.getString("dist","");
        medicine = findViewById(R.id.medicine);
        type = findViewById(R.id.type);
        desc = findViewById(R.id.desc);
        price = findViewById(R.id.price);
        qty = findViewById(R.id.qty);

        Intent data = getIntent();
        v = data.getStringExtra("myJson");
        Gson gson = new Gson();
        ob = gson.fromJson(v,medicineDAO.class);

        medicine.setText(ob.getMedicinename());
        type.setText("Type : "+ob.getMedtype());
        desc.setText("Description : "+ob.getMeddec());
        price.setText("Price : "+ob.getMedprice()+"Rs");
    }

    public void addtocart(View view) {
        if(qty.getText().toString().equals("")){
            qty.requestFocus();
            Varii.p(getApplicationContext(), "Enter quantity");
        }else if(Integer.parseInt(qty.getText().toString()) <= 0 ){
            qty.requestFocus();
            Varii.p(getApplicationContext(), "Enter a valid quantity");
        }else if(Integer.parseInt(qty.getText().toString()) > Integer.parseInt(ob.getMedqty())){
            qty.requestFocus();
            Varii.p(getApplicationContext(), "Out of stock. Only "+ob.getMedqty()+" stocks are available now");
        } else {

            progressDoalog = new ProgressDialog(this);
            progressDoalog.setMessage("Loading..");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            try {
                MainApi service = Connection.getcon().create(MainApi.class);
                Call<List<Result>> call = service.addOrder(
                        user,
                        qty.getText().toString(),
                        ob.getStock_id(),
                        ob.getMedshop_id()
                );
                call.enqueue(new Callback<List<Result>>() {
                    @Override
                    public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                        Log.i("onResponse", response.message());
                        try {
                            List<Result> userList = response.body();
                            for (Result n : userList) {
                                SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                if (n.getResult().contains("ok")) {
                                    Varii.p(getApplicationContext(), "Added to cart");
                                    onBackPressed();
                                } else if (n.getResult().contains("no")) {
                                    Varii.p(getApplicationContext(), "Something went wrong. Try again later.");
                                    onBackPressed();
                                } else {
                                    Varii.p(getApplicationContext(), n.getResult() + "");
                                    onBackPressed();
                                }
                                progressDoalog.dismiss();
                            }
                        } catch (Exception e) {
                            progressDoalog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Result>> call, Throwable t) {
                        Varii.p(getApplicationContext(), "Bad network.. Please try again later.");
                        progressDoalog.dismiss();
                    }
                });
            } catch (Exception e) {
                progressDoalog.dismiss();
            }

        }
    }
}