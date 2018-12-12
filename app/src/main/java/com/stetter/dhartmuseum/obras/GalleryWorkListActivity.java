package com.stetter.dhartmuseum.obras;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.home.viewmodel.HomeViewModel;
import com.stetter.dhartmuseum.interfaces.RecyclerViewOnItemClickListener;
import com.stetter.dhartmuseum.model.Obras;
import com.stetter.dhartmuseum.model.Record;
import com.stetter.dhartmuseum.obras.adapter.RecyclerViewListaObrasAdapter;

import java.util.ArrayList;
import java.util.List;

public class GalleryWorkListActivity extends AppCompatActivity implements RecyclerViewOnItemClickListener {

    private List<Obras> listObras = new ArrayList<>();
    private List<Record> recordList = new ArrayList<>();
    private RecyclerView recyclerView;
    RecyclerViewListaObrasAdapter adapter;
    private HomeViewModel objectViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_work_list);

        HomeViewModel viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        setRecyclerView();

        objectViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        objectViewModel.getObjects("primaryimageurl");

        objectViewModel.objectLiveData.observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(@Nullable List<Record> records) {
                adapter.update(records);
            }
        });
    }

    public void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_works);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewListaObrasAdapter(recordList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Record record) {
        Intent intent = new Intent(GalleryWorkListActivity.this, GalleryWorkListActivity.class);
        intent.putExtra("record", record);
        startActivity(intent);
    }
}
