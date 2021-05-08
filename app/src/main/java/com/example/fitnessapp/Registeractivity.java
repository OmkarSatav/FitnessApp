package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registeractivity extends AppCompatActivity {
    public TextView alreadyHaveAccount;
    private EditText inputEmail;
    private EditText password;
    private EditText confirmPassword;

    private FirebaseAuth auth;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);
        alreadyHaveAccount=findViewById(R.id.alreadyHaveAccount);
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });

        confirmPassword=findViewById(R.id.confirmPassword);
        inputEmail=findViewById(R.id.inputEmail);
        password=findViewById(R.id.password);
        buttonRegister=findViewById(R.id.buttonRegister);
        auth = FirebaseAuth.getInstance();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_Email=inputEmail.getText().toString();
                String txt_Password=password.getText().toString();
                String txt_confirmPassword=confirmPassword.getText().toString();
                if(txt_Password.equals(txt_confirmPassword)) {
                    if (TextUtils.isEmpty(txt_Email) || TextUtils.isEmpty(txt_Password)) {
                        Toast.makeText(Registeractivity.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                    } else if (!isValidPassword(txt_Password)) {
                        Toast.makeText(Registeractivity.this, "Password must contain mix of upper and lower case letters as well as digits and one special charecter(4-20)", Toast.LENGTH_LONG).show();
                    } else {
                        RegisterUser(txt_Email, txt_Password);
                    }
                }
                else{
                    Toast.makeText(Registeractivity.this, "Confirm password is not correct", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void RegisterUser(String email, String password) {


        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Registeractivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Registeractivity.this, "REgistration successfull", Toast.LENGTH_LONG).show();
                    openLogin();
                    finish();
                }else{
                    Toast.makeText(Registeractivity.this, "Registraaton failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void openLogin() {
        Intent intent = new Intent(this, Loginactivity.class);
        startActivity(intent);
    }
    public static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,20})").matcher(password);
        return matcher.matches();
    }
}