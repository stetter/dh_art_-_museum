package com.stetter.dhartmuseum.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.stetter.dhartmuseum.R;

public class ObrasActivity extends AppCompatActivity {

    private TextView textViewLerMais;
    private TextView textViewLerDetalhes;
    private TextView textViewNoticia;
    private View linha4;
    private View linha5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obras);

        textViewLerDetalhes = findViewById(R.id.text_view_detalhes);
        textViewLerMais = findViewById(R.id.ler_mais);
        textViewNoticia = findViewById(R.id.noticias);
        linha4 = findViewById(R.id.linha4);
        linha5 = findViewById(R.id.linha5);

        textViewLerMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collapseExpandCardView();
            }
        });

    }
    public void collapseExpandCardView(){
        if(textViewLerDetalhes.getVisibility() == View.GONE){
            textViewLerDetalhes.setVisibility(View.VISIBLE);
            textViewNoticia.setVisibility(View.GONE);
            linha4.setVisibility(View.GONE);
            linha5.setVisibility(View.GONE);
        }else{

            textViewLerDetalhes.setVisibility(View.GONE);
            textViewNoticia.setVisibility(View.VISIBLE);
            linha4.setVisibility(View.VISIBLE);
            linha5.setVisibility(View.VISIBLE);
        }

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
