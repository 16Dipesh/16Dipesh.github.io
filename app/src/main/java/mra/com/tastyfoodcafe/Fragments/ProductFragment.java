package mra.com.tastyfoodcafe.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mra.com.tastyfoodcafe.Bakeryitemss;
import mra.com.tastyfoodcafe.ProductAdapter;
import mra.com.tastyfoodcafe.R;


public class ProductFragment extends Fragment {
    RecyclerView recyclerView,recyclerView1,recyclerView3,recyclerView4,recyclerView5;
    private List<Bakeryitemss> itemsPastery,itemCake,itemcupcake,itemdrycake,itemcookie;
    private EditText search_user;
    private ProductAdapter checkAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product, container, false);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerView=view.findViewById(R.id.Recycler1);
        recyclerView1=view.findViewById(R.id.Recycler2);
        recyclerView3=view.findViewById(R.id.Recycler3);
        recyclerView4=view.findViewById(R.id.Recycler4);
        recyclerView5=view.findViewById(R.id.Recycler5);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView3.setLayoutManager(linearLayoutManager2);
        recyclerView4.setLayoutManager(linearLayoutManager3);
        recyclerView5.setLayoutManager(linearLayoutManager4);
        recyclerView.setHasFixedSize(true);
        recyclerView1.setHasFixedSize(true);
        recyclerView3.setHasFixedSize(true);
        recyclerView4.setHasFixedSize(true);
        recyclerView5.setHasFixedSize(true);

        itemsPastery=new ArrayList<>();
        itemCake=new ArrayList<>();
        itemcupcake=new ArrayList<>();
        itemcookie=new ArrayList<>();
        itemdrycake=new ArrayList<>();

        readProductsPastery();
        readproductCake();
        readproductcupcake();
        readproductdrycake();
        readproductcookies();

        return view;
    }
    private void readproductdrycake()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Products").child("Drycake");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemdrycake.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Bakeryitemss item=snapshot.getValue(Bakeryitemss.class);

                    itemdrycake.add(item);
                }

                checkAdapter=new ProductAdapter(getContext(),itemdrycake);
                recyclerView4.setAdapter(checkAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void readproductcupcake()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Products").child("cupcakes");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemcupcake.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Bakeryitemss item=snapshot.getValue(Bakeryitemss.class);

                    itemcupcake.add(item);
                }

                checkAdapter=new ProductAdapter(getContext(),itemcupcake);
                recyclerView3.setAdapter(checkAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void readproductcookies()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Products").child("cookies");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemcookie.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Bakeryitemss item=snapshot.getValue(Bakeryitemss.class);

                    itemcookie.add(item);
                }

                checkAdapter=new ProductAdapter(getContext(),itemcookie);
                recyclerView5.setAdapter(checkAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void readproductCake()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Products").child("cakes");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemCake.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Bakeryitemss item=snapshot.getValue(Bakeryitemss.class);

                    itemCake.add(item);
                }

                checkAdapter=new ProductAdapter(getContext(),itemCake);
                recyclerView.setAdapter(checkAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void readProductsPastery()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Products").child("pastery");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemsPastery.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Bakeryitemss item=snapshot.getValue(Bakeryitemss.class);

                    itemsPastery.add(item);
                }

                checkAdapter=new ProductAdapter(getContext(),itemsPastery);
                recyclerView1.setAdapter(checkAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
