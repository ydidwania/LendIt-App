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
        TextView skipButton = (TextView)findViewById(R.id.skip_button);
        //textView = (TextView)findViewById(R.id.status);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent main_to_home=new Intent(MainActivity.this , HomeActivity.class);
                main_to_home.putExtra("logged_in",true);
                main_to_home.putExtra("user_id",loginResult.getAccessToken().getUserId());
                startActivity(main_to_home);
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
                Intent main_to_home=new Intent(MainActivity.this , HomeActivity.class);
                main_to_home.putExtra("logged_in",false);
                startActivity(main_to_home);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
