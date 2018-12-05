package com.stetter.dhartmuseum.obras.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.model.Record;

public class ObrasActivity extends AppCompatActivity {

    private ImageView image;
    private TextView title;
    private TextView author;
    private TextView description;
    private TextView date;
    private TextView places;
    private TextView period;
    private TextView culture;
    private TextView whereToFind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obras);

        initAssets();
        setInformation();
    }

    private void setInformation() {
        Record record = getIntent().getParcelableExtra("record");

        Picasso.get().load(record.getPrimaryimageurl()).into(image);

        if(record.getTitle() != ""){
            title.setText(record.getTitle());
        } else {
            title.setText("Informação não disponível.");
        }

        if(record.getContact() != ""){
            author.setText(record.getContact());
        } else {
            author.setText("Informação não disponível.");
        }

        if(record.getDescription() != ""){
            description.setText(record.getDescription());
        } else {
            description.setText("Informação não disponível.");
        }

        if(record.getDated() != ""){
            date.setText(record.getDated());
        } else {
            date.setText("Informação não disponível.");
        }

        if(record.getProvenance() != ""){
            places.setText(record.getProvenance());
        } else {
            places.setText("Informação não disponível.");
        }

        if(record.getCentury() != ""){
            period.setText(record.getCentury());
        } else {
            period.setText("Informação não disponível.");
        }

        if(record.getCulture() != ""){
            culture.setText(record.getCulture());
        } else {
            culture.setText("Informação não disponível.");
        }

        if(record.getDepartment() != ""){
            whereToFind.setText(record.getDepartment());
        } else {
            whereToFind.setText("Informação não disponível.");
        }
    }

    private void initAssets() {
        image = findViewById(R.id.item_image);
        title = findViewById(R.id.item_title);
        author = findViewById(R.id.item_author);
        description = findViewById(R.id.item_description);
        date = findViewById(R.id.item_date_text);
        places = findViewById(R.id.item_places_text);
        period = findViewById(R.id.item_period_text);
        culture = findViewById(R.id.item_culture_text);
        whereToFind = findViewById(R.id.item_where_to_find_text);
    }

}
