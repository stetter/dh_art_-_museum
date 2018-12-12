package com.stetter.dhartmuseum.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.interfaces.RecyclerViewOnItemClickListener;
import com.stetter.dhartmuseum.model.Record;

import java.util.List;

public class
RecyclerViewObrasAdapter extends RecyclerView.Adapter<RecyclerViewObrasAdapter.ViewHolder> {

    private List<Record> listaRecord;
    Context context;
    private RecyclerViewOnItemClickListener listener;

    public RecyclerViewObrasAdapter(Context context, List<Record> listaRecord, RecyclerViewOnItemClickListener listener) {
        this.listaRecord = listaRecord;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewObrasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_work_tiles, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewObrasAdapter.ViewHolder holder, int position) {
        final Record record = listaRecord.get(position);
        holder.obra.setText(record.getTitle());
        holder.artista.setText(record.getCreditline()); // TODO trocar para algo mais útil
        Picasso.get().load(record.getPrimaryimageurl()).into(holder.imagem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(record);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaRecord.size();
    }

    public void update(List<Record> recordList) {
        this.listaRecord = recordList;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView obra;
        protected TextView artista;
        protected ImageView imagem;

        public ViewHolder(View itemView) {
            super(itemView);
            obra = itemView.findViewById(R.id.record_title);
            artista = itemView.findViewById(R.id.record_artist);
            imagem = itemView.findViewById(R.id.record_image);
        }
    }
}