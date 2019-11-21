package com.example.statussaver9;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

public class about_mine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_mine);
    }

    @Override
    public void onBackPressed()
    {
        //On Back pressed in MainActivity
        //Call exiting application Dialog method
        super.onBackPressed();
    }


}
