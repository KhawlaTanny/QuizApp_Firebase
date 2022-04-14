package com.khawlat.quizapp_c;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    //Step 1: Declaration
    EditText etLogin, etPassword;
    Button bLogin;
    TextView tvRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 2: Recuperation des ids

        etLogin = (EditText) findViewById(R.id.etMail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

        mAuth = FirebaseAuth.getInstance();

        //Step 3: Association de listeners

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Step 4: Traitement
                if(etLogin.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"email required",Toast.LENGTH_LONG).show();
                }
                if(etPassword.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"password required",Toast.LENGTH_LONG).show();
                }

                mAuth.signInWithEmailAndPassword(etLogin.getText().toString(),etPassword.getText().toString()).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(MainActivity.this, Quiz1.class);
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    intent.putExtra("Uid",user.getUid());
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"Login failed !",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                );
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Step 4: Traitement
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
    }
}
