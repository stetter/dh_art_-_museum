package com.stetter.dhartmuseum.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private TextInputEditText confirmPassword;

    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout confirmPasswordLayout;

    private CheckBox checkBoxAgree;

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
                String confirmPassword_value = confirmPassword.getText().toString();


                if (TextUtils.isEmpty(email_value)) {
                    emailLayout.setError("Please fill in the required fields");
                    return;
                } else if (TextUtils.isEmpty(password_value)) {
                    passwordLayout.setError("Please fill in the required fields");
                    return;
                } else if (password_value.length() < 6) {
                    passwordLayout.setError("Password must be at least 6 characters");
                    return;
                } else if (!password_value.equals(confirmPassword_value)) {
                    confirmPasswordLayout.setError("Password not equals");
                    return;
                } else if (!checkBoxAgree.isChecked()) {
                    Toast.makeText(getApplicationContext(), "You must agree the Terms", Toast.LENGTH_SHORT).show();
                    return;
                } else {

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
        confirmPassword = findViewById(R.id.confirm_password);
        checkBoxAgree = findViewById(R.id.image_check_box);
        emailLayout = findViewById(R.id.text_email);
        passwordLayout = findViewById(R.id.text_password);
        confirmPasswordLayout = findViewById(R.id.text_confirm_password);
    }
}
