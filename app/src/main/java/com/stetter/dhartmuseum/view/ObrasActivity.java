package com.stetter.dhartmuseum.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.model.Record;

public class ObrasActivity extends AppCompatActivity {

    private ImageView imageViewObra;
    private TextView textViewDescricao;
    private TextView textViewCulture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obras);

        imageViewObra = findViewById(R.id.imageView2);
        textViewDescricao = findViewById(R.id.textView2);
        textViewCulture = findViewById(R.id.textViewCulture);

        Record record = getIntent().getParcelableExtra("record");

        textViewDescricao.setText(record.getDescription());
        textViewCulture.setText("Cultura: "+ record.getCulture());
        Picasso.get().load(record.getPrimaryimageurl()).into(imageViewObra);


    }

}
