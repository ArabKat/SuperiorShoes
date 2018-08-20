package com.superiorshoes.ooguro.superiorshoes.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.superiorshoes.ooguro.superiorshoes.Interface.ItemClickListener;
import com.superiorshoes.ooguro.superiorshoes.R;

public class ClothViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView cloth_name;
    public ImageView cloth_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ClothViewHolder(View itemView) {
        super(itemView);

        cloth_name = (TextView)itemView.findViewById(R.id.cloth_name);
        cloth_image = (ImageView)itemView.findViewById(R.id.cloth_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
