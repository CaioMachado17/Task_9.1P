package com.example.task91p;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.AdvertViewHolder> {

    private Activity activity;
    private List<Advert> advertList;

    public AdvertAdapter(Activity activity, List<Advert> advertList) {
        this.activity = activity;
        this.advertList = advertList;
    }

    @NonNull
    @Override
    public AdvertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_advert, parent, false);
        return new AdvertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertViewHolder holder, int position) {
        Advert advert = advertList.get(position);
        holder.textViewType.setText(advert.type);
        holder.textViewDesc.setText(advert.description);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailActivity.class);
            intent.putExtra("id", advert.id);
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return advertList.size();
    }

    static class AdvertViewHolder extends RecyclerView.ViewHolder {
        TextView textViewType, textViewDesc;

        public AdvertViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewType = itemView.findViewById(R.id.textViewType);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
        }
    }
}
