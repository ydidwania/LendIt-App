package com.iitb.ferozepurwale.lendit_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView nameView = (TextView)findViewById(R.id.name);
        Bundle from_main = getIntent().getExtras();
        boolean logged_in = from_main.getBoolean("logged_in");;
        if (!logged_in)
            nameView.setText("Guest");
        else {
            String user_id = from_main.getString("user_id");
            nameView.setText(user_id);
        }
    }
}
