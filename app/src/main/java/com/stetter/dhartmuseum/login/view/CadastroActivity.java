package com.stetter.dhartmuseum.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.stetter.dhartmuseum.R;

public class CadastroActivity extends AppCompatActivity {

    private Button registerButton;

    private TextInputEditText email;
    private TextInputEditText password;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        initViews();
        initObjects();
        register();

    }

    private void register() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_value = email.getText().toString();
                String password_value = password.getText().toString();

                if (TextUtils.isEmpty(email_value)) {
                    Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password_value)) {
                    Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password_value.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(email_value, password_value)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void initObjects() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void initViews() {
        registerButton = findViewById(R.id.btn_sign_up);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }
}
