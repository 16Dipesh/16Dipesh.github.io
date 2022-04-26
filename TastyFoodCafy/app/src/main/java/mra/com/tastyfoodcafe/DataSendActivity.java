package mra.com.tastyfoodcafe;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataSendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_send);
    }

    public static class DataSend extends AppCompatActivity {

        ImageView i;
        EditText e,f;
        Button b;
        FirebaseAuth mAuth;
        DatabaseReference reference;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_data_send);
            e=(EditText)findViewById(R.id.sndtext);
            f=(EditText)findViewById(R.id.prise);

            b=(Button)findViewById(R.id.snd);

            mAuth=FirebaseAuth.getInstance();
            reference= FirebaseDatabase.getInstance().getReference("Products");


            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String sndfeed = e.getText().toString();
                    final String productprise=f.getText().toString();
                    final String images="default";

                    mAuth = FirebaseAuth.getInstance();

                    if (TextUtils.isEmpty(sndfeed) ) {
                        //progressDialog.dismiss();

                        Toast.makeText(DataSend.this, "Something is Missing....", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("SpecialOffers").child("week1");
                        String id = reference.push().getKey();
                        specialOffers f=new specialOffers(id, sndfeed,images,productprise);
                        reference.child(id).setValue(f);
                        Toast.makeText(DataSend.this, "Feed Receive..!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}