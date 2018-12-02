package com.stetter.dhartmuseum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.stetter.dhartmuseum.adapters.RecyclerViewObrasAdapter;
import com.stetter.dhartmuseum.interfaces.RecyclerViewOnItemClickListener;
import com.stetter.dhartmuseum.model.Obras;
import com.stetter.dhartmuseum.model.Record;

import java.util.ArrayList;
import java.util.List;

public class DetalheMuseuActivity extends AppCompatActivity implements RecyclerViewOnItemClickListener {

    private List<Record> listObras = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView map;
    RecyclerViewObrasAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_museu);

        /*getListObras();*/
        setaRecyclerView();

        map = findViewById(R.id.map);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetalheMuseuActivity.this, MapsActivity.class));
            }
        });


    }

    public void setaRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new RecyclerViewObrasAdapter(this, listObras, this);
        mRecyclerView.setAdapter(adapter);
    }

    //Carrega a lista de  pessoas
    /*private List<Obras> getListObras() {

        // Limpo a lista para adicionar mais pessoas e evitar
        // Duplicar as pessoas
        listObras.clear();
        listObras.add(new Obras("Mona Liza", "Elaborada em XXXX"));
        listObras.add(new Obras("O Beijo", "Elaborada em XXXX"));
        listObras.add(new Obras("Guernica", "Elaborada em XXXX"));
        listObras.add(new Obras("Abaporu", "Elaborada em XXXX"));
        listObras.add(new Obras("Mona Liza", "Elaborada em XXXX"));
        listObras.add(new Obras("O Beijo", "Elaborada em XXXX"));
        listObras.add(new Obras("Guernica", "Elaborada em XXXX"));
        listObras.add(new Obras("Abaporu", "Elaborada em XXXX"));
        listObras.add(new Obras("Mona Liza", "Elaborada em XXXX"));
        listObras.add(new Obras("O Beijo", "Elaborada em XXXX"));
        listObras.add(new Obras("Abaporu", "Elaborada em XXXX"));


        return listObras;
    }*/

    @Override
    public void onItemClick(Record record) {

    }

}
