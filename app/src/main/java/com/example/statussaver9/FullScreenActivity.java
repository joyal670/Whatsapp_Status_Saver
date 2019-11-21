package com.example.statussaver9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FullScreenActivity extends AppCompatActivity {
    ImageView fullimage;
    VideoView fullvideo;
    public final String DIR_SAVE = "/WSDownloader/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        fullimage = (ImageView)findViewById(R.id.fullimage);
        fullvideo = (VideoView)findViewById(R.id.fullvideo);
        final String data = getIntent().getExtras().getString("img");
        if(data.endsWith(".mp4"))
        {
            fullvideo.setVisibility(View.VISIBLE);
            fullvideo.setVideoURI(Uri.parse(data));
            fullvideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);
                    fullvideo.start();
                }
            });
        }
        else
        {
            fullimage.setVisibility(View.VISIBLE);
            fullimage.setImageURI(Uri.parse(data));
        }
        final File currentFile= new File(data);
        FloatingActionButton fab1 = findViewById(R.id.floatingAB);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                new Runnable()
                {
                    @Override
                    public  void run()
                    {

                        File destfile = new File(Environment.getExternalStorageDirectory().toString() + DIR_SAVE + currentFile.getName());
                        Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_SHORT).show();
                        try {
                            copyFile(currentFile,destfile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.run();

            }
        });

    }
    private void copyFile(File sourceFile, File destfile) throws IOException {
        if(!destfile.getParentFile().exists())
        {
            destfile.getParentFile().mkdir();
        }

        if(!destfile.exists())
            destfile.createNewFile();

        FileChannel source = null;
        FileChannel destination = null;

        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destfile).getChannel();

        destination.transferFrom(source,0,source.size());

        if(source!=null)
        {
            source.close();
        }
        if(destination!=null)
        {
            destination.close();
        }
    }
}
