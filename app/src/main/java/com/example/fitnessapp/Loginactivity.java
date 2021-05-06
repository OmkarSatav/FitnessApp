package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Loginactivity extends AppCompatActivity {
    public TextView signUp;
    public Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        signUp=findViewById(R.id.signUp);
        buttonLogin=findViewById(R.id.buttonLogin);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBmi();
            }
        });
    }
    public void openRegister(){
        Intent intent=new Intent(this, Registeractivity.class);
        startActivity(intent);
    }

    public void openBmi(){
        Intent intent=new Intent(this, userDetailsActivity.class);
        startActivity(intent);
    }

}