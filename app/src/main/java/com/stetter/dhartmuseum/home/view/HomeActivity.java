package com.stetter.dhartmuseum.home.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.adapters.RecyclerViewObrasAdapter;
import com.stetter.dhartmuseum.adapters.ViewPagerAdapter;
import com.stetter.dhartmuseum.home.fragments.ViewPagerFragment;
import com.stetter.dhartmuseum.home.model.GalleryRecord;
import com.stetter.dhartmuseum.home.viewmodel.HomeViewModel;
import com.stetter.dhartmuseum.interfaces.RecyclerViewOnItemClickListener;
import com.stetter.dhartmuseum.login.LoginActivity;
import com.stetter.dhartmuseum.model.Record;
import com.stetter.dhartmuseum.obras_detalhe.view.WorkDetailsActivity;

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
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                mAuth.signOut();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

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
        recyclerView = findViewById(R.id.recyclerview_home);
        progressBar = findViewById(R.id.gallery_progress_bar);
        viewPager = findViewById(R.id.viewpager_home);
        viewPager.setOffscreenPageLimit(fragments.size());
        spinner = findViewById(R.id.spinner_home);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), new ArrayList<>());
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewObrasAdapter(this, recordList, this);
        mAuth = FirebaseAuth.getInstance();
    }

    private void setGallery(int selectedFloor) {
        viewModel.getGalleryRecords(selectedFloor);
        viewModel.galleryLiveData.observe(this, new Observer<List<GalleryRecord>>() {
            @Override
            public void onChanged(@Nullable List<GalleryRecord> galleryRecordList) {
                fragments.clear();
                viewPagerAdapter.update(fragments);
                for (int i = 0; i < galleryRecordList.size(); i++) {
                    fragments.add(ViewPagerFragment.newInstance(galleryRecordList.get(i).getName(), galleryRecordList.get(i).getGalleryid()));
                    //                     fragments.add(ViewPagerFragment.newInstance(galleryRecordList.get(i).getName(), galleryRecordList.get(i).getId()));
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

                // TODO Retirar clear quando colocar paginacao
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
        Intent intent = new Intent(HomeActivity.this, WorkDetailsActivity.class);
        intent.putExtra("record", record);
        startActivity(intent);
    }
}
