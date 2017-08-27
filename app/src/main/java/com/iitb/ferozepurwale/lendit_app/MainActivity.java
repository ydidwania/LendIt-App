package com.iitb.ferozepurwale.lendit_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        loginButton = (LoginButton)findViewById(R.id.fb_login_id);
        final TextView skipButton = (TextView)findViewById(R.id.skip_button);
        //textView = (TextView)findViewById(R.id.status);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent mainToHome=new Intent(MainActivity.this , HomeActivity.class);
                mainToHome.putExtra("logged_in",true);
                mainToHome.putExtra("user_id",loginResult.getAccessToken().getUserId());
                startActivity(mainToHome);
                //textView.setText(loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                textView.setText("Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                textView.setText("Error");
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainToHome=new Intent(MainActivity.this , HomeActivity.class);
                mainToHome.putExtra("logged_in",false);
                startActivity(mainToHome);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
