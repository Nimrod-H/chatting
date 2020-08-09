package com.coolweather.chatting.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coolweather.chatting.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText registerID;
    private EditText registerPassword;
    private EditText registerName;
    private EditText registerRePassword;
    private EditText registerPhoneNumber;
    private EditText registerAddress;
    private Button comfimRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        initView();
    }
    public void initView(){
          registerID = findViewById(R.id.register_ID);
          registerPassword = findViewById(R.id.register_password);
          registerName = findViewById(R.id.register_name);
          registerRePassword = findViewById(R.id.register_repassword);
          registerPhoneNumber = findViewById(R.id.register_phonenumber);
          registerAddress = findViewById(R.id.register_address);
          comfimRegister = findViewById(R.id.register_register_button);
          comfimRegister.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
              }
          });
    }
}
