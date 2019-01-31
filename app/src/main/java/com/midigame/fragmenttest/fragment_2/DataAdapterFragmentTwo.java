package com.midigame.fragmenttest.fragment_2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.midigame.fragmenttest.R;
import com.midigame.fragmenttest.fragment_1.FullSсreenItemFragmentOne;
import com.midigame.fragmenttest.retrofit.DataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DataAdapterFragmentTwo extends RecyclerView.Adapter<DataAdapterFragmentTwo.ViewHolder> {
    private List<DataModel> models;
    private LayoutInflater inflater;
    private Context context;

    DataAdapterFragmentTwo(Context context, List<DataModel> dataModels) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        models = new ArrayList<>();
        models = dataModels;
    }

    @Override
    public DataAdapterFragmentTwo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new DataAdapterFragmentTwo.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapterFragmentTwo.ViewHolder holder, int position) {
        Picasso.with(context).load(models.get(position).getUrl())
                .placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
        holder.numberText.setText("" + (position + 1));
        holder.titleView.setText(models.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView imageView;
        final TextView titleView;
        final TextView numberText;

        ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            imageView = (ImageView) view.findViewById(R.id.image);
            titleView = (TextView) view.findViewById(R.id.title);
            numberText = (TextView) view.findViewById(R.id.textViewNumber);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, FullSсreenItemFragmentOne.class);
            intent.putExtra("url", models.get(getAdapterPosition()).getUrl());
            intent.putExtra("title", models.get(getAdapterPosition()).getTitle());
            intent.putExtra("number", "" + (getAdapterPosition() + 1));
            context.startActivity(intent);
        }
    }
}
