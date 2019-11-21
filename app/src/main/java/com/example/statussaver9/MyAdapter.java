package com.example.statussaver9;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private ArrayList<Cell> galleryList;
    private Context context;
    public  final String DIR_SAVE = "/WSDownloader/";

    public MyAdapter(Context context, ArrayList<Cell> galleryList) {
        this.galleryList = galleryList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent,false);
        return new MyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position)
    {
        holder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        setImageFromPath(galleryList.get(position).getPath(), holder.img);
        holder.img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,"" + galleryList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,FullScreenActivity.class);
                intent.putExtra("img",galleryList.get(position).getPath());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        final File currentFile= new File(galleryList.get(position).getPath());




    }





    @Override
    public int getItemCount()
    {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView img;


        public ViewHolder(View view)
        {
            super(view);

            img = (ImageView) view.findViewById(R.id.img);

        }
    }

    private void setImageFromPath(String path, ImageView image)
    {
        File imgFile = new File(path);
        if (imgFile.exists())
        {
            Bitmap myBitmap = ImageHelper.decodeSampleBitmapFromPath(imgFile.getAbsolutePath(),200,200);
            image.setImageBitmap(myBitmap);
        }
    }

}
