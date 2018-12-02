package com.stetter.dhartmuseum.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.interfaces.RecyclerViewOnItemClickListener;
import com.stetter.dhartmuseum.model.Obras;
import com.stetter.dhartmuseum.model.Record;

import java.util.List;

public class
RecyclerViewObrasAdapter extends RecyclerView.Adapter<RecyclerViewObrasAdapter.ViewHolder> {

    private List<Obras> listaObras;
    private List<Record> listaRecord;
    Context mctx;
    private RecyclerViewOnItemClickListener listener;

    public RecyclerViewObrasAdapter(Context context, List<Record> listaRecord, RecyclerViewOnItemClickListener listener) {
        this.listaRecord = listaRecord;
        this.mctx = context;
        this.listener = listener;
    }

   /* public RecyclerViewObrasAdapter(Context context, List<Obras> listObras, RecyclerViewOnItemClickListener listener ) {
        this.mctx = context;
        this.listaObras = listObras;
        this.listener = listener;


    }*/

    @NonNull
    @Override
    public RecyclerViewObrasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_obras, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewObrasAdapter.ViewHolder holder, int position) {
        final Record record = listaRecord.get(position);

        holder.obra.setText(record.getTitle());
        holder.descrição.setText(record.getTitle());

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

    protected class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView obra;
        protected TextView descrição;

        public ViewHolder(View itemView) {
            super(itemView);

            obra = (TextView) itemView.findViewById(R.id.textViewRecyclerObra);
            descrição = (TextView) itemView.findViewById(R.id.textViewRecyclerDescricao);
        }
    }
}