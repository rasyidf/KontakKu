package com.rasyidf.kontakku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    CheckBox ckRemember;
    TextInputLayout edEmail, edPass;
    Boolean remembered;
    String email, password;
    String success, wrongEmail, wrongPass, wrongCreds, tmpmsg,emptyEmail, emptyPass;
    String userEmail = "admin@mail.com";
    String userPass = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        success = getResources().getString(R.string.loginSuccess);
        wrongEmail = getResources().getString(R.string.loginInvalidEmail);
        wrongPass = getResources().getString(R.string.loginInvalidPass);
        wrongCreds = getResources().getString(R.string.loginInvalidCreds);
        emptyEmail = getResources().getString(R.string.loginEmptyEmail);
        emptyPass = getResources().getString(R.string.loginEmptyPass);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btLogin);

        initEditor();

        btnRegister.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(i);
        });

        btnLogin.setOnClickListener(view -> {
            email = edEmail.getEditText().getText().toString();
            password = edPass.getEditText().getText().toString();

            Boolean userRight = (email.equals(userEmail));
            Boolean passRight = (password.equals(userPass));

            if (userRight && passRight) {
                tmpmsg = success;
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                bundle.putString("pass", password);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {

                if (!userRight){
                    edEmail.setError(wrongEmail);
                }
                if (!passRight){
                    edPass.setError(wrongPass);
                }
                if (email.isEmpty()){
                    edEmail.setError(emptyEmail);
                }
                if (password.isEmpty()){
                    edPass.setError(emptyPass);
                }
            }


        });
    }

    private void initEditor() {
        edEmail = findViewById(R.id.edEmail);
        edPass = findViewById(R.id.edPass);

        edEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edEmail.setError(null);
            }
        });
        edPass.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edPass.setError(null);
            }
        });
    }
}