package com.hospitalscoveradministration.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hospitalscoveradministration.R;

public class activity_login extends AppCompatActivity {

    private Button login_btn;
    private EditText email_etxt;
    private EditText password_etxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login_btn=(Button) findViewById(R.id.login_btn);
        email_etxt=(EditText) findViewById(R.id.email_etxt);
        password_etxt=(EditText)findViewById(R.id.password_etxt);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String email= email_etxt.getText().toString();
               String password=password_etxt.getText().toString();
               Intent intent= new Intent(activity_login.this,HomeScreenAdmin.class);
               finish();
               startActivity(intent);
            }
        });
    }

}
