package com.example.statussaver9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SavedImages extends AppCompatActivity {
    RecyclerView rv;
    List<Cell> allFilesPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_saved_images);
        rv=(RecyclerView)findViewById(R.id.savedimages);
        showImages();


    }

    private void showImages()
    {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WSDownloader/";
        allFilesPath = new ArrayList<>();
        allFilesPath = listAllFiles(path);

        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rv.setLayoutManager(layoutManager);

        rv.setItemViewCacheSize(20);
        rv.setDrawingCacheEnabled(true);
        rv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        ArrayList<Cell> cells = prepareData();
        MyAdapterDownloads adapterDownloads = new MyAdapterDownloads(getApplicationContext(), cells);
        rv.setAdapter(adapterDownloads);
    }

    private ArrayList<Cell> prepareData()
    {
        ArrayList<Cell> allImages = new ArrayList<>();
        for (Cell c : allFilesPath)
        {
            Cell cell = new Cell();
            cell.setTitle(c.getTitle());
            cell.setPath(c.getPath());
            allImages.add(cell);

        }
        return allImages;
    }

    private List<Cell> listAllFiles(String pathName)
    {
        List<Cell> allFiles = new ArrayList<>();
        File file = new File(pathName);
        File[] files = file.listFiles();
        if (files != null)
        {
            for (File f : files)
            {
                Cell cell = new Cell();
                cell.setTitle(f.getName());
                cell.setPath(f.getAbsolutePath());
                allFiles.add(cell);
            }
        }
        return allFiles;
    }
}
