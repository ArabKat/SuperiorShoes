package com.superiorshoes.ooguro.superiorshoes;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.superiorshoes.ooguro.superiorshoes.Model.User;

public class SignUp extends AppCompatActivity {

    MaterialEditText ssPhone,ssName, ssPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ssName     = (MaterialEditText) findViewById(R.id.ssName);
        ssPhone    = (MaterialEditText) findViewById(R.id.ssPhone);
        ssPassword = (MaterialEditText) findViewById(R.id.ssPassword);

        btnSignUp  = (Button)           findViewById(R.id.btnSignUp);

        // Initializing Fire Base
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait....");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(ssPhone.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Phone Number already registered!", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            mDialog.dismiss();
                            User user = new User(ssName.getText().toString(),ssPassword.getText().toString());
                            table_user.child(ssPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                            finish();
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
