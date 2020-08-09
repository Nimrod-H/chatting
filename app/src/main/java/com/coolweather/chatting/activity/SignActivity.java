package com.coolweather.chatting.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coolweather.chatting.R;

public class SignActivity extends AppCompatActivity {
    private Button button_sign;
    private Button button_register;
    private EditText signID;
    private EditText signPassword;
    private TextView fogetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_layout);
        initView();

    }
    public void initView(){
        signID = findViewById(R.id.sign_id_content);
        signPassword = findViewById(R.id.sign_password_content);
        button_sign = findViewById(R.id.sign_button);
        button_register = findViewById(R.id.sign_register);
        fogetPassword = findViewById(R.id.sign_forgetpassword);
        fogetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"忘记密码!",Toast.LENGTH_SHORT).show();
            }
        });
        button_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
