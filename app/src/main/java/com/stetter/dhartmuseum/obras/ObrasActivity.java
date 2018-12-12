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

public class ObrasActivity extends AppCompatActivity implements RecyclerViewOnItemClickListener {

    private List<Obras> listObras = new ArrayList<>();
    private List<Record> recordList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerViewListaObrasAdapter adapter;
    private HomeViewModel objectViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obras2);

        setaRecyclerView();

        objectViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        objectViewModel.getObjects("primaryimageurl");

        objectViewModel.objectLiveData.observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(@Nullable List<Record> records) {
                adapter.update(records);
            }
        });
    }

    public void setaRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewObras2);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new RecyclerViewListaObrasAdapter(recordList, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Record record) {
        Intent intent = new Intent(this, com.stetter.dhartmuseum.obras_detalhe.view.ObrasActivity.class);
        intent.putExtra("record", record);
        startActivity(intent);
    }
}
