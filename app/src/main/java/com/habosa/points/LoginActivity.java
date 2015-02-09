package com.habosa.points;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String KEY_PHONE_NUMBER = "phone_number";

    private EditText mUsernameField;
    private EditText mPasswordField;
    private EditText mPhoneNumberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // If logged in, skip to Economies Activity
        ParseUser parseUser = ParseUser.getCurrentUser();
        if (parseUser != null) {
            Log.i(TAG, "Logged in as :" + parseUser.getUsername());
            goToEconomies();
            return;
        }

        // Fields
        mUsernameField = (EditText) findViewById(R.id.field_username);
        mPasswordField = (EditText) findViewById(R.id.field_password);
        mPhoneNumberField = (EditText) findViewById(R.id.field_phonenumber);

        // Button listeners
        findViewById(R.id.button_login).setOnClickListener(this);
        findViewById(R.id.button_signup).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getSystemPhoneNumber() == null) {
            // Show phone number field if Android System does not know the number
            mPhoneNumberField.setVisibility(View.VISIBLE);
        }
    }

    private void goToEconomies() {
        startActivity(new Intent(this, EconomiesActivity.class));
    }

    private String getPhoneNumber() {
        // Get the user's phone number
        String systemNumber = getSystemPhoneNumber();
        if (systemNumber == null) {
            // Android System does not know the number, get it from the field
            // TODO(samstern): Format the phone number
            return mPhoneNumberField.getText().toString();
        } else {
            // Return the phone number as reported by Android System
            return systemNumber;
        }
    }

    private String getSystemPhoneNumber() {
        // Get the phone number as reported by the Android OS
        TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        return manager.getLine1Number();
    }

    private void onSignupClicked() {
        // Create user
        // TODO(samstern): Form validations
        ParseUser user = new ParseUser();
        user.setUsername(mUsernameField.getText().toString());
        user.setPassword(mPasswordField.getText().toString());
        user.put(KEY_PHONE_NUMBER, getPhoneNumber());

        // Upload to parse
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i(TAG, "SignUp Success");
                    // TODO(samstern)
                } else {
                    Log.e(TAG, "SignUp Exception", e);
                    // TODO(samstern)
                }
            }
        });
    }

    private void onLoginClicked() {
        String username = mUsernameField.getText().toString();
        String password = mPasswordField.getText().toString();
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Log.i(TAG, "LogIn Success");
                    goToEconomies();
                } else {
                    Log.e(TAG, "LogIn Exception", e);
                    // TODO(samstern)
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                onLoginClicked();
                break;
            case R.id.button_signup:
                onSignupClicked();
                break;
        }
    }
}
