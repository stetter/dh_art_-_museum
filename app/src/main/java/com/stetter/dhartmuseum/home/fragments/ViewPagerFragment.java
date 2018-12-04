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

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment implements View.OnClickListener {

    public ViewPagerFragment() {
    }

    public static ViewPagerFragment newInstance(String title, String theme) {
        ViewPagerFragment fragment = new ViewPagerFragment();

        Bundle args = new Bundle();

        //args.putInt("IMAGE", image);
        args.putString("TITLE", title);
        args.putString("THEME", theme);

        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        Bundle bundle = getArguments();

        ImageView image = view.findViewById(R.id.cardview_image);
        TextView title = view.findViewById(R.id.gallery_title);
        TextView theme = view.findViewById(R.id.gallery_theme);

        image.setImageResource(R.drawable.android_image_1);
        String titleValue = bundle.getString("TEXT");
        String themeValue = bundle.getString("THEME");

        return view;
    }

    @Override
    public void onClick(View v) {
        //TODO implementar troca do recyclerview com as obras da galeria
    }
}