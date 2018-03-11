package com.muktadir.firebaseapp;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {
    EditText myEditText,myEditText2;
    Button myApplyBt;
    String myStringData,myKeyValue;
    Firebase myFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myEditText = findViewById(R.id.editText);
        myEditText2 = findViewById(R.id.editText2);
        myApplyBt = findViewById(R.id.button);

        Firebase.setAndroidContext(this);

        String DeviceID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        myFirebase = new Firebase("https://fir-app-ee692.firebaseio.com/Users"+DeviceID);

        myApplyBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myStringData = myEditText.getText().toString();
                myKeyValue = myEditText2.getText().toString();
                Firebase myNewChild = myFirebase.child(myStringData);
                myNewChild.setValue(myKeyValue);
                Toast.makeText(MainActivity.this, myStringData + " is Updated With "+myKeyValue,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
