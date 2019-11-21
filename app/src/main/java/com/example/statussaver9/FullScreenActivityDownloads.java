package com.example.statussaver9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

public class FullScreenActivityDownloads extends AppCompatActivity {
    ImageView fullimagedownload;
    VideoView fullvideodownload;
    public final String DIR_SAVE = "/WSDownloader/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_full_screen_downloads);
        fullimagedownload = (ImageView)findViewById(R.id.fullimagedownload);
        fullvideodownload = (VideoView)findViewById(R.id.fullvideodownload);
        final String data = getIntent().getExtras().getString("img");
        if(data.endsWith(".mp4"))
        {
            fullvideodownload.setVisibility(View.VISIBLE);
            fullvideodownload.setVideoURI(Uri.parse(data));
            fullvideodownload.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);
                    fullvideodownload.start();
                }
            });
        }
        else
        {
            fullimagedownload.setVisibility(View.VISIBLE);
            fullimagedownload.setImageURI(Uri.parse(data));
        }
        final File currentFile= new File(data);
        FloatingActionButton fab2 = findViewById(R.id.floatingAB1);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(),"Share",Toast.LENGTH_SHORT).show();
                Intent whatsappIntent = new Intent();
                whatsappIntent.setAction(Intent.ACTION_SEND);
                whatsappIntent.setType("*/*");
                whatsappIntent.setPackage("com.whatsapp");
                File destfile = new File(Environment.getExternalStorageDirectory().toString()+ DIR_SAVE  + currentFile.getName());

               // Toast.makeText(getApplicationContext(),"Share"+ destfile, Toast.LENGTH_SHORT).show();

                Uri pic = Uri.parse(String.valueOf(destfile));
                whatsappIntent.putExtra(Intent.EXTRA_STREAM,pic);
                whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(whatsappIntent,"Share via"));
            }
        });
    }
}
