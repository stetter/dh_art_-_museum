package com.stetter.dhartmuseum.view;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.model.Obras;

public class ObrasActivity extends AppCompatActivity {

    private TextView textViewLerMais;
    private TextView textViewLerDetalhes;
    private TextView textViewNoticia;
    private View linha4;
    private View linha5;
    private ImageView imageViewMenu;
    private Toolbar toolbar;

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

        imageViewMenu = findViewById(R.id.image_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar( toolbar );



        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindow popup = new PopupWindow( ObrasActivity.this);
                View layout = getLayoutInflater().inflate(R.layout.popup_menu, null);
                popup.setContentView(layout);
                // Set content width and height
                popup.setHeight( WindowManager.LayoutParams.WRAP_CONTENT);
                popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
                // Closes the popup window when touch outside of it - when looses focus
                popup.setOutsideTouchable(true);
                popup.setFocusable(true);
                // Show anchored to button
                popup.setBackgroundDrawable(new BitmapDrawable());
                popup.showAsDropDown(imageViewMenu);

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

}
