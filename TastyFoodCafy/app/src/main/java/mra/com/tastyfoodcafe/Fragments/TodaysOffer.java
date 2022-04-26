package mra.com.tastyfoodcafe.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mra.com.tastyfoodcafe.Adapters.OfferAdapter;
import mra.com.tastyfoodcafe.R;
import mra.com.tastyfoodcafe.specialOffers;


public class TodaysOffer extends Fragment {
    RecyclerView recyclerView,recyclerView1,recyclerView3;
    private List<specialOffers.SpecialOffers> itemsPastery,itemCake,itemPizza;
    private EditText search_user;
    private OfferAdapter checkAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_todays_offer, container, false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView=view.findViewById(R.id.offersRecyclers);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        //recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        itemsPastery=new ArrayList<>();

        readoffer();



        return view;
    }

    private void readoffer()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("SpecialOffers").child("week1");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemsPastery.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    specialOffers.SpecialOffers item=snapshot.getValue(specialOffers.SpecialOffers.class);

                    itemsPastery.add(item);
                }

                checkAdapter=new OfferAdapter(getContext(),itemsPastery);
                recyclerView.setAdapter(checkAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
