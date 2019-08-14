package com.codehouse.travelmantics;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealVIewHolder> {


    // Firebase variables
    ArrayList<TravelDeal> deals;
    private FirebaseDatabase mFirebasedB;
    private DatabaseReference mdBRef;
    private ChildEventListener mChildListener;

    public DealAdapter() {
        FirebaseUtil.openFbReference("traveldeals");
        mFirebasedB = FirebaseUtil.mFirebasedB;
        mdBRef = FirebaseUtil.mDbRef;
        deals = FirebaseUtil.mDeals;

        // ChildEventListener Codebase
        mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TravelDeal tv = dataSnapshot.getValue(TravelDeal.class);
                Log.d("Deal: ", tv.getTitle());
                tv.setId(dataSnapshot.getKey());
                deals.add(tv);
                notifyItemChanged(deals.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mdBRef.addChildEventListener(mChildListener);

    }
        @NonNull
        @Override
        public DealVIewHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
            Context context = parent.getContext();
            View itemView = LayoutInflater.from(context).inflate(R.layout.rv_row,parent,false);

            return new DealVIewHolder(itemView);
        }

        @Override
        public void onBindViewHolder (@NonNull DealVIewHolder holder,int position){
                    TravelDeal deal = deals.get(position);
                    holder.bind(deal);
        }

        @Override
        public int getItemCount () {
            return deals.size();
        }

// DealViewHolder Class declaration starts here

        public class DealVIewHolder extends RecyclerView.ViewHolder {

            // DealHolder global variables
            TextView tvTitle;
            TextView tvDescription;
            TextView tvPrice;

            public DealVIewHolder(View itemView) {
                super(itemView);

                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvDescription = itemView.findViewById(R.id.tvDescription);
                tvPrice = itemView.findViewById(R.id.tvPrice);

            }

            public void bind(TravelDeal deal) {
                tvTitle.setText(deal.getTitle());
                tvDescription.setText(deal.getDescription());
                tvPrice.setText(deal.getPrice());
            }
        }
    }
