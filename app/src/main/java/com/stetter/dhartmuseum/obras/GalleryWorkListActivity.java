package com.stetter.dhartmuseum.obras;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
    private RecyclerViewOnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_work_list);

        HomeViewModel viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        viewModel.getObjectsByGalleryId(getIntent().getExtras().getLong("GALLERY_ID"));

        viewModel.objectByGalleryIdLiveData.observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(@Nullable List<Record> records) {
                for (int i = 0; i < records.size(); i++) {
                    recordList.add(records.get(i));
                }
                setRecyclerView();
            }
        });
    }

    public void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_works);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewListaObrasAdapter(this, recordList, listener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Record record) {
        Intent intent = new Intent(GalleryWorkListActivity.this, GalleryWorkListActivity.class);
        intent.putExtra("record", record);
        startActivity(intent);
    }

}
