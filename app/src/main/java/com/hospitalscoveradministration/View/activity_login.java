package com.hospitalscoveradministration.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hospitalscoveradministration.FireBase.MyFirebaseMessagingService;
import com.hospitalscoveradministration.ModelView.UserModelView;
import com.hospitalscoveradministration.R;

public class activity_login extends AppCompatActivity {

    private Button login_btn;
    private EditText email_etxt;
    private EditText password_etxt;

    private FirebaseAuth mAuth;

    void subscribeToTopic(String hospitalID)
    {
        MyFirebaseMessagingService myFirebaseMessagingService=new MyFirebaseMessagingService();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login_btn = (Button) findViewById(R.id.login_btn);
        email_etxt = (EditText) findViewById(R.id.email_etxt);
        password_etxt = (EditText) findViewById(R.id.password_etxt);

// tt@tt.com
        //12345678
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_etxt.getText().toString();
                String password = password_etxt.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(activity_login.this, "Please fill your email and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(activity_login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(activity_login.this, user.getUid(),
                                            Toast.LENGTH_SHORT).show();



                                    UserModelView userModelView= new UserModelView();
                                    userModelView.getAllUser(user.getUid());
                                    userModelView.getUser();

                                    subscribeToTopic("dasdas");


                                    finish();
                                    Intent intent = new Intent(activity_login.this, HomeScreenAdmin.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(activity_login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }

        });
    }
}
