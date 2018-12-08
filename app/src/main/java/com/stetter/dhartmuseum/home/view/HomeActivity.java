package com.stetter.dhartmuseum.home.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.adapters.RecyclerViewObrasAdapter;
import com.stetter.dhartmuseum.adapters.ViewPagerAdapter;
import com.stetter.dhartmuseum.home.fragments.ViewPagerFragment;
import com.stetter.dhartmuseum.home.model.GalleryRecord;
import com.stetter.dhartmuseum.home.viewmodel.HomeViewModel;
import com.stetter.dhartmuseum.interfaces.RecyclerViewOnItemClickListener;
import com.stetter.dhartmuseum.model.Record;
import com.stetter.dhartmuseum.obras.view.ObrasActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements RecyclerViewOnItemClickListener {

    private ViewPager viewPager;
    private List<Record> recordList = new ArrayList<>();
    private RecyclerView recyclerView;
    RecyclerViewObrasAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();
    private Spinner spinner;
    private ArrayList<String> floors = new ArrayList<>();
    private int selectedFloor;
    private HomeViewModel viewModel;
    private ProgressBar progressBar;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initAssets();
        viewPager.setAdapter(viewPagerAdapter);
        recyclerView.setAdapter(adapter);
        setObjects();

        floors.add("Térreo");
        floors.add("1º andar");
        floors.add("2º andar");
        floors.add("3º andar");
        floors.add("4º andar");
        floors.add("5º andar");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, floors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setGallery(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initAssets() {
        recyclerView = findViewById(R.id.recyclerviewHome);
        progressBar = findViewById(R.id.progressBar);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(fragments.size());
        spinner = findViewById(R.id.current_level_spinner);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewObrasAdapter(this, recordList, this);
    }

    private void setGallery(int selected_floor) {
        viewModel.getGalleryRecords(selected_floor);
        viewModel.galleryLiveData.observe(this, new Observer<List<GalleryRecord>>() {
            @Override
            public void onChanged(@Nullable List<GalleryRecord> galleryRecordList) {
                fragments.clear();
                for (int i = 0; i < galleryRecordList.size(); i++) {
                    fragments.add(ViewPagerFragment.newInstance(galleryRecordList.get(i).getName()));
                }
                viewPagerAdapter.update(fragments);
            }
        });

        viewModel.isLoadingGallery.observe(this, isLoading -> {
            progressBar.setVisibility((isLoading) ? View.VISIBLE : View.GONE);
        });
    }

    private void setObjects() {

        viewModel.getObjects("primaryimageurl");

        viewModel.objectLiveData.observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(@Nullable List<Record> records) {

                // Retirar clear quando colocar paginacao
                recordList.clear();
                for (int i = 0; i < records.size(); i++) {
                    recordList.add(records.get(i));
                }
                adapter.update(recordList);
            }
        });
    }


    @Override
    public void onItemClick(Record record) {
        Intent intent = new Intent(HomeActivity.this, ObrasActivity.class);
        intent.putExtra("record", record);
        startActivity(intent);
    }
}
