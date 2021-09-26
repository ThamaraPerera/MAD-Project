package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText eUsername;
    private EditText ePassword;
    private CheckBox eRemember;
    private Button eLogin;
    private Button eRegister;

    private Boolean isValid=false;

    private String Username="Admin";
    private String Password="Admin@123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eUsername = findViewById(R.id.editUsername);
        ePassword = findViewById(R.id.editPassword);
        eRemember = findViewById(R.id.boxRemember);
        eLogin = findViewById(R.id.buttonLogin);
        eRegister = findViewById(R.id.buttonRegister2);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputuname = eUsername.getText().toString();
                String inputpwd = ePassword.getText().toString();

                if(inputuname.isEmpty() || inputpwd.isEmpty())
                {
                    Toast.makeText(Login.this, "Please enter both Username and Password!",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        isValid = validateLog(inputuname,inputpwd);

                        if(!isValid){
                            Toast.makeText(Login.this, "Username or Password is not matching!",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Login.this,Register.class);
                            startActivity(intent);
                        }
                    }

            }
        });

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

    }

    private Boolean validateLog (String name,String password){

        if(name.equals(Username) && password.equals(Password)){
            return true;
        }
        return false;
    }
}