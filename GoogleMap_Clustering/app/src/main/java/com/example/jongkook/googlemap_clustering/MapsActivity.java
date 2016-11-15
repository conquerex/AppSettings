package com.example.jongkook.googlemap_clustering;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        setCluster();
    }

    private void setCluster(){
        LatLng center = new LatLng(51.503186, -0.126446);
        float zoomLevel = 10f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, zoomLevel));

        clusterManager = new ClusterManager<Item>(this, mMap);


        // 클러스터(그룹)를 클릭했을 때 발생
        clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<Item>() {
            @Override
            public boolean onClusterClick(Cluster<Item> cluster) {
                Toast.makeText(MapsActivity.this,"아이템개수="+cluster.getSize()
                        ,Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // 마커단위를 클릭했을 때 발생
        clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<Item>() {
            @Override
            public boolean onClusterItemClick(Item item) {
                Toast.makeText(MapsActivity.this,"마커좌표="+item.getPosition()
                        , Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);

        try{
            readitems();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private ClusterManager<Item> clusterManager;

    private void readitems() throws JSONException{
        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
        List<Item> items = new ItemReader().read(inputStream);
        clusterManager.addItems(items);
    }


}

class Item implements ClusterItem{
    private LatLng coord;
    public Item(double lat, double lng){
        coord = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return coord;
    }
}
