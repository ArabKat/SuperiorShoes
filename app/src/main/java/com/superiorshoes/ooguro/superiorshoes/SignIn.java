package com.superiorshoes.ooguro.superiorshoes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jgabrielfreitas.core.BlurImageView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.superiorshoes.ooguro.superiorshoes.Common.Common;
import com.superiorshoes.ooguro.superiorshoes.Model.User;

public class SignIn extends AppCompatActivity {

    EditText ssPhone,ssPassword;
    Button btnSignIn;
    BlurImageView background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ssPassword = (MaterialEditText) findViewById(R.id.ssPassword);
        ssPhone    = (MaterialEditText) findViewById(R.id.ssPhone);
        btnSignIn  = (Button) findViewById(R.id.btnSignIn);
        background = (BlurImageView) findViewById(R.id.Background_Image);
        background.setBlur(2);


        // Initializing Fire Base
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please wait....");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Check to see if user exists in database already
                        if(dataSnapshot.child(ssPhone.getText().toString()).exists()) {
                        // grabbing user information
                        mDialog.dismiss();
                        User user = dataSnapshot.child(ssPhone.getText().toString()).getValue(User.class);
                        user.setPhone(ssPhone.getText().toString());    // set Phone

                        if(user.getPassword().equals(ssPassword.getText().toString())) {
                                {
                                    Intent homeIntent = new Intent(SignIn.this,Home.class);
                                    Common.currentUser = user;
                                    startActivity(homeIntent);
                                    finish();
                                }
                            } else {
                                Toast.makeText(SignIn.this, "Wrong Password!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    else
                        {
                            mDialog.dismiss();
                           Toast.makeText(SignIn.this,"User doesn't exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
