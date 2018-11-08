package com.stetter.dhartmuseum.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.adapters.RecyclerViewObrasAdapter;
import com.stetter.dhartmuseum.interfaces.RecyclerViewOnItemClickListener;
import com.stetter.dhartmuseum.model.Obras;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements RecyclerViewOnItemClickListener {

    private List<Obras> listObras = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerViewObrasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getListObras();
        setaRecyclerView();

    }

    public void setaRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewHome);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new RecyclerViewObrasAdapter(this, listObras, this);
        mRecyclerView.setAdapter(adapter);
    }

    //Carrega a lista de  pessoas
    private List<Obras> getListObras() {

        // Limpo a lista para adicionar mais pessoas e evitar
        // Duplicar as pessoas
        listObras.clear();
        listObras.add(new Obras("Museu Louvre", "Inaugurado em 1900"));
        listObras.add(new Obras("Museu do Ipiranga", "Inaugurado em 1910"));
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
    }


    @Override
    public void onItemClick(Obras obras) {
        startActivity(new Intent(HomeActivity.this, DetalheMuseuActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate( R.menu.menu_favorito, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.adc_favorito) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }


}
