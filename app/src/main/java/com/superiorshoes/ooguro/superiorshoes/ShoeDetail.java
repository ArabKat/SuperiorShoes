package com.superiorshoes.ooguro.superiorshoes;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.superiorshoes.ooguro.superiorshoes.Model.Cloth;

public class ShoeDetail extends AppCompatActivity {

    TextView shoe_name,shoe_price,shoe_desciption;
    ImageView clothing_image;

    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String shoeId="";

    FirebaseDatabase database;
    DatabaseReference shoe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_detail);

        // Firebase
        database = FirebaseDatabase.getInstance();
        shoe = database.getReference("Shoes");

        // Init view
        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);

        shoe_desciption = (TextView)findViewById(R.id.shoe_description);
        shoe_name = (TextView) findViewById(R.id.shoe_name);
        shoe_price = (TextView)findViewById(R.id.shoe_price);
        clothing_image = (ImageView)findViewById(R.id.img_shoes);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        //Get Shoe Id from intent

        if(getIntent() != null)
            shoeId = getIntent().getStringExtra("ShoeId");
        if(!shoeId.isEmpty())
        {
            getDetailShoe(shoeId);
        }


    }

    private void getDetailShoe(String shoeId) {

        shoe.child(shoeId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Cloth cloth = dataSnapshot.getValue(Cloth.class);

                //Sets Image
                Picasso.with(getBaseContext()).load(cloth.getImage())
                        .into(clothing_image);

                collapsingToolbarLayout.setTitle(cloth.getName());

                shoe_price.setText(cloth.getPrice());

                shoe_name.setText(cloth.getName());

                shoe_desciption.setText(cloth.getDescription());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
