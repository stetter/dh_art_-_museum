package com.stetter.dhartmuseum.home.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.obras.GalleryWorkListActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {

    public ViewPagerFragment() {
    }

    public static ViewPagerFragment newInstance(String title, Long galleryId) {
        ViewPagerFragment fragment = new ViewPagerFragment();

        Bundle args = new Bundle();

        args.putString("TITLE", title);
        args.putLong("GALLERY_ID", galleryId);
        //args.putLong("GALLERY_ID", galleryId);

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

        String titleValue = bundle.getString("TITLE");
        Long galleryID = bundle.getLong("GALLERY_ID");

        title.setText(titleValue);
        image.setImageResource(R.drawable.img_brickwall);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GalleryWorkListActivity.class);
                intent.putExtra("GALLERY_ID", galleryID);
                startActivity(intent);
            }
        });
        return view;
    }
}
