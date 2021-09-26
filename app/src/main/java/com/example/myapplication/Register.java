package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Register extends AppCompatActivity {
    private Button eLogin;
    private Button eRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eLogin = findViewById(R.id.buttonLogin2);
        eRegister = findViewById(R.id.buttonRegister);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
    }
}