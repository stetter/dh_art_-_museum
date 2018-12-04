package com.stetter.dhartmuseum.home.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.adapters.RecyclerViewObrasAdapter;
import com.stetter.dhartmuseum.adapters.ViewPagerAdapter;
import com.stetter.dhartmuseum.home.fragments.ViewPagerFragment;
import com.stetter.dhartmuseum.home.model.GalleryRecord;
import com.stetter.dhartmuseum.home.viewmodel.GalleryViewModel;
import com.stetter.dhartmuseum.interfaces.RecyclerViewOnItemClickListener;
import com.stetter.dhartmuseum.model.Obras;
import com.stetter.dhartmuseum.model.Record;
import com.stetter.dhartmuseum.view.ObrasActivity;
import com.stetter.dhartmuseum.home.viewmodel.ObjectViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements RecyclerViewOnItemClickListener {

    private ViewPager viewPager;
    private List<Obras> listObras = new ArrayList<>();
    private List<Record> recordList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerViewObrasAdapter adapter;
    private ObjectViewModel objectViewModel;
    private GalleryViewModel galleryViewModel;
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerAdapter);

        setObjectViewModel();

        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        galleryViewModel.getGalleryRecords();
        galleryViewModel.galleryLiveData.observe(this, new Observer<List<GalleryRecord>>() {
            @Override
            public void onChanged(@Nullable List<GalleryRecord> galleryRecordList) {
                for (int i = 0; i < galleryRecordList.size(); i++) {
                    fragments.add(ViewPagerFragment.newInstance(galleryRecordList.get(i).getName(), galleryRecordList.get(i).getTheme()));
                }
            }
        });
    }

    private void setObjectViewModel() {
        objectViewModel = ViewModelProviders.of(this).get(ObjectViewModel.class);

        objectViewModel.getObjects("primaryimageurl");

        objectViewModel.objectLiveData.observe(this, new Observer<List<Record>>() {
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
        recyclerView = findViewById(R.id.recyclerviewHome);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewObrasAdapter(this, recordList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Record record) {
        Intent intent = new Intent(HomeActivity.this, ObrasActivity.class);
        intent.putExtra("record", record);
        startActivity(intent);
        //startActivity(new Intent(HomeActivity.this, ObrasActivity.class));
    }
}
