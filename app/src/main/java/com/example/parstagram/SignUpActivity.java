package com.example.parstagram;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends LoginActivity {

    public static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnSignUp.setVisibility(View.GONE);
        btnLogin.setText("Sign up");
    }

    @Override
    public void handleUser(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    goMainActivity();
                } else {
                    Log.e(TAG, "Issue with signup", e);
                }
            }
        });
    }

}
