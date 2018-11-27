package com.stetter.dhartmuseum.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.model.Exhibition;

import java.util.List;

public class RecyclerviewExhibition extends RecyclerView.Adapter<RecyclerviewExhibition.ViewHolder> {

    private List<Exhibition> exhibitionList;

    public RecyclerviewExhibition(List<Exhibition> exhibitionList) {
        this.exhibitionList = exhibitionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_obras, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Exhibition exhibition = exhibitionList.get(position);
        holder.bind(exhibition);
    }

    @Override
    public int getItemCount() {
        return exhibitionList.size();
    }

    public void setExhibitionList(List<Exhibition> exhibitionList) {

        if (exhibitionList.size() == 0){
            this.exhibitionList = exhibitionList;
        }else {
            this.exhibitionList.addAll(exhibitionList);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewRecyclerObra);
        }

        public void bind (Exhibition exhibition){
            textView.setText(exhibition.getTitle().toString());


        }
    }
}
