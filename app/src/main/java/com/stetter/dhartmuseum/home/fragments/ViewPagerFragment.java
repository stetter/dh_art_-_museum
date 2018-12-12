package com.stetter.dhartmuseum.home.fragments;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.obras.ObrasActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {

    public ViewPagerFragment() {
    }

    public static ViewPagerFragment newInstance(String title) {
        ViewPagerFragment fragment = new ViewPagerFragment();

        Bundle args = new Bundle();

        //args.putString("URL", url);
        args.putString("TITLE", title);

        fragment.setArguments(args);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        Bundle bundle = getArguments();

        TextView title = view.findViewById(R.id.gallery_title);
        ImageView image = view.findViewById(R.id.cardview_image);

        //String imageResource = bundle.getString("URL");
        String titleValue = bundle.getString("TITLE");

        title.setText(titleValue);
        image.setImageResource(R.drawable.img_brickwall);
        //Picasso.get().load(imageResource).into(image);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ObrasActivity.class);
                intent.putExtra("Obra", titleValue);
                startActivity(intent);
            }
        });

        return view;
    }


}
