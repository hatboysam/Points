package com.habosa.points;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParseUser;


public class EconomiesActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = EconomiesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economies);

        // Buttons
        findViewById(R.id.button_plus).setOnClickListener(this);
    }

    private void signOut() {
        Log.d(TAG, "Signing Out");
        ParseUser.logOut();
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void onPlusClicked() {
        startActivity(new Intent(this, TransactionActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_economies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_signout:
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_plus:
                onPlusClicked();
                break;
        }
    }
}
