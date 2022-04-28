package mra.com.tastyfoodcafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductActivity extends AppCompatActivity {
    TextView pname,pPrise;
    ImageView pimage;
    Button cart,buy;
    FirebaseUser user;
    Intent intent;
    String uid;
    DatabaseReference reference;
    String task;
    int totalamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        pname=(TextView)findViewById(R.id.text);
        pPrise=(TextView)findViewById(R.id.text1);
        pimage=(ImageView)findViewById(R.id.image);
        cart=(Button)findViewById(R.id.addcart);
        buy=(Button)findViewById(R.id.buynow);


        callingCake();
        callingPizza();
        callingPastery();
        callingDrycake();
        callingcupcake();

        callinAddtocart();
        intent=getIntent();
        uid=intent.getStringExtra("id");


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    Intent i = getIntent();
                    String p=pname.getText().toString();
                    String pi="default";
                    String prise=pPrise.getText().toString();
                    String getid = i.getStringExtra("id");
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AddToCart").child("data");
                    Bakeryitemss b= new Bakeryitemss(getid,p,pi,prise);
                    reference.child(getid).setValue(b);
                    Toast.makeText(ProductActivity.this,"Product Added To Cart :)",Toast.LENGTH_SHORT).show();




                }
                catch (Exception e)
                {
                    Toast.makeText(ProductActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(ProductActivity.this,BuyProductActivity.class);
                i.putExtra("id",uid);
                startActivity(i);
            }
        });



    }

    private void callingcupcake()
    {
        intent=getIntent();
        uid=intent.getStringExtra("id");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Products").child("cupcakes").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Bakeryitemss i=dataSnapshot.getValue(Bakeryitemss.class);
                try {
                    pname.setText(i.getPname());
                    pPrise.setText(i.getPrise());
                    if(i.getPimg().equals("default"))
                    {
                        pimage.setImageResource(R.drawable.cart);

                    }
                    else
                    {
                        Glide.with(getApplicationContext()).load(i.getPimg()).into(pimage);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(ProductActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(ProductActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void callingDrycake()
    {
        intent=getIntent();
        uid=intent.getStringExtra("id");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Products").child("Drycake").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Bakeryitemss i=dataSnapshot.getValue(Bakeryitemss.class);
                try {
                    pname.setText(i.getPname());
                    pPrise.setText(i.getPrise());
                    if(i.getPimg().equals("default"))
                    {
                        pimage.setImageResource(R.mipmap.ic_launcher);

                    }
                    else
                    {
                        Glide.with(getApplicationContext()).load(i.getPimg()).into(pimage);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(ProductActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(ProductActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void callinAddtocart()
    {
        intent=getIntent();
        uid=intent.getStringExtra("id");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("AddToCart").child("data").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Bakeryitemss i=dataSnapshot.getValue(Bakeryitemss.class);
                try {
                    pname.setText(i.getPname());
                    pPrise.setText(i.getPrise());
                    if(i.getPimg().equals("default"))
                    {
                        pimage.setImageResource(R.drawable.cart);

                    }
                    else
                    {
                        Glide.with(getApplicationContext()).load(i.getPimg()).into(pimage);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(ProductActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(ProductActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void callingPastery()
    {
        intent=getIntent();
        uid=intent.getStringExtra("id");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Products").child("pastery").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Bakeryitemss i=dataSnapshot.getValue(Bakeryitemss.class);
                try {
                    pname.setText(i.getPname());
                    pPrise.setText(i.getPrise());
                    if(i.getPimg().equals("default"))
                    {
                        pimage.setImageResource(R.mipmap.ic_launcher);

                    }
                    else
                    {
                        Glide.with(getApplicationContext()).load(i.getPimg()).into(pimage);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(ProductActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(ProductActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void callingCake()
    {
        intent=getIntent();
        uid=intent.getStringExtra("id");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Products").child("cakes").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Bakeryitemss i=dataSnapshot.getValue(Bakeryitemss.class);
                try {
                    pname.setText(i.getPname());
                    pPrise.setText(i.getPrise());
                    if(i.getPimg().equals("default"))
                    {
                        pimage.setImageResource(R.mipmap.ic_launcher);

                    }
                    else
                    {
                        Glide.with(getApplicationContext()).load(i.getPimg()).into(pimage);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(ProductActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(ProductActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void callingPizza()
    {
        intent=getIntent();
        uid=intent.getStringExtra("id");
        reference= FirebaseDatabase.getInstance().getReference("Products").child("cookies").child(uid);
        //reference=FirebaseDatabase.getInstance().getReference("Products").child("cakes").child(pizza);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Bakeryitemss i=dataSnapshot.getValue(Bakeryitemss.class);
                try {
                    pname.setText(i.getPname());
                    pPrise.setText(i.getPrise());
                    if(i.getPimg().equals("default"))
                    {
                        pimage.setImageResource(R.mipmap.ic_launcher);

                    }
                    else
                    {
                        Glide.with(getApplicationContext()).load(i.getPimg()).into(pimage);
                    }


                }
                catch (Exception e)
                {
                    Toast.makeText(ProductActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(ProductActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
