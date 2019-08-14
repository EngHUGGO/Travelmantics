package com.codehouse.travelmantics;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebasedB;
    public static DatabaseReference mDbRef;
    private static FirebaseUtil firebaseUtil;
    public static ArrayList<TravelDeal> mDeals;

    private FirebaseUtil (){}

    public static void openFbReference(String ref){
        if(firebaseUtil == null){
            firebaseUtil = new FirebaseUtil();
            mFirebasedB = FirebaseDatabase.getInstance();
            mDeals = new ArrayList<TravelDeal>();
        }
        mDbRef = mFirebasedB.getReference().child(ref);
    }
}
