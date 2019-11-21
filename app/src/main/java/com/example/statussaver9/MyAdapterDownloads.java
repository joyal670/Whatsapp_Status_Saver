package com.example.statussaver9;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;

public class MyAdapterDownloads extends RecyclerView.Adapter<MyAdapterDownloads.ViewHolder>
{
    private ArrayList<Cell> galleryList;
    private Context context;

    public MyAdapterDownloads(Context context, ArrayList<Cell> galleryList) {
        this.galleryList = galleryList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapterDownloads.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_downloads, parent,false);
        return new MyAdapterDownloads.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position)
    {
        holder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        setImageFromPath(galleryList.get(position).getPath(), holder.img);
        holder.img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               // Toast.makeText(context,"" + galleryList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,FullScreenActivityDownloads.class);
                intent.putExtra("img",galleryList.get(position).getPath());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

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

            img = (ImageView) view.findViewById(R.id.imgdownloads);


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
