package com.superiorshoes.ooguro.superiorshoes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.superiorshoes.ooguro.superiorshoes.Interface.ItemClickListener;
import com.superiorshoes.ooguro.superiorshoes.Model.Cloth;
import com.superiorshoes.ooguro.superiorshoes.ViewHolder.ClothViewHolder;

public class ClothingList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference clothList;

    String categoryId="";

    FirebaseRecyclerAdapter<Cloth,ClothViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        clothList = database.getReference("Cloths");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_clothing);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Gets intent here
        if(getIntent() !=null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId != null){

            loadListCloth(categoryId);
        }
    }

    private void loadListCloth(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Cloth, ClothViewHolder>(Cloth.class,
                R.layout.cloth_item,ClothViewHolder.class,clothList.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(ClothViewHolder viewHolder, Cloth model, int position) {

                viewHolder.cloth_name.setText((model.getName()));
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.cloth_image);

                final Cloth local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int positition, boolean isLongClick) {
                        Toast.makeText(ClothingList.this, "" + local.getName(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };

        //Set Adapter

        recyclerView.setAdapter(adapter);
    }
}
