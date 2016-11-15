package com.example.jongkook.firebase_geofire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("path/to/geofire");
        GeoFire geoFire = new GeoFire(ref);

        String room1 = ref.push().getKey();
        geoFire.setLocation(room1, new GeoLocation(37.7853889, -122.4056973));
        String room2 = ref.push().getKey();
        geoFire.setLocation(room2, new GeoLocation(37.7853889, -122.4056973));
        String room3 = ref.push().getKey();
        geoFire.setLocation(room3, new GeoLocation(37.7853889, -122.4056973), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                if (error != null) {
                    System.err.println("There was an error saving the location to GeoFire: " + error);
                } else {
                    System.out.println(">>>> key : " + key);
                    System.out.println("Location saved on server successfully!");
                }
            }
        });

        // 특정 범위내의 firebase 키값을 가져오기
        GeoLocation center = new GeoLocation(37.7853889, -122.4056973);
        float range = 10.0f;
        GeoQuery geoQuery = geoFire.queryAtLocation(center, range);

        // 좌표를 담을 그릇을 세팅
        final Set<String> nearby = new HashSet<>();

        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                nearby.add(key);
            }

            @Override
            public void onKeyExited(String key) {
                nearby.remove(key);
            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady() {
                System.out.println(">>>> room count : " + nearby.size());
                for (String key : nearby){
                    System.out.println(">>>> key : " + key);
                }
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });

    }
}
