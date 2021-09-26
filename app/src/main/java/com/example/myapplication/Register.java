package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {
    private Button eLogin;
    private Button eRegister;
    private FirebaseAuth mAuth;
    private EditText eUsername,ePassword,eEmail,eContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        eLogin = findViewById(R.id.buttonLogin2);
        eRegister = findViewById(R.id.buttonRegister);
        eUsername = findViewById(R.id.editUsername);
        ePassword = findViewById(R.id.editPassword);
        eEmail = findViewById(R.id.editTextEmail);
        eContact = findViewById(R.id.editContact);

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

                registerUser();
                
                //Intent intent = new Intent(Register.this,Login.class);
                //startActivity(intent);
            }

            private void registerUser() {
                String uname= eUsername.getText().toString().trim();
                String email= eEmail.getText().toString().trim();
                String pwd= ePassword.getText().toString().trim();
                String contact= eContact.getText().toString().trim();

                if (uname.isEmpty()){
                    eUsername.setError("Username is Required");
                    eUsername.requestFocus();
                    return;
                }
                if (pwd.isEmpty()){
                    ePassword.setError("Password is Required");
                    ePassword.requestFocus();
                    return;
                }
                if (pwd.length() <6){
                    ePassword.setError("Password is must include at least 6 characters");
                    ePassword.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    eEmail.setError("Email is Required");
                    eEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    eEmail.setError("Please enter valid Email address");
                    eEmail.requestFocus();
                    return;
                }
                if (contact.isEmpty()){
                    eContact.setError("Contact number is Required");
                    eContact.requestFocus();
                    return;
                }
                if (contact.length() <10){
                    eContact.setError("Please enter valid contact number");
                    eContact.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                              if(task.isSuccessful()){
                                  User User = new User(uname,email,contact);

                                  FirebaseDatabase.getInstance().getReference("Users")
                                          .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                          .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                                      @Override
                                      public void onComplete(@NonNull Task<Void> task) {
                                          if (task.isSuccessful()){
                                              Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                              //Intent intent = new Intent(Register.this,Login.class);
                                              //startActivity(intent);
                                          }
                                          else {
                                              Toast.makeText(Register.this, "Failed to register!Please try again!", Toast.LENGTH_LONG).show();
                                          }
                                      }
                                  });
                              }
                              else {
                                  Toast.makeText(Register.this, "Failed to register!Please try again!", Toast.LENGTH_LONG).show();
                              }
                            }
                        });
        };
    })
;}}