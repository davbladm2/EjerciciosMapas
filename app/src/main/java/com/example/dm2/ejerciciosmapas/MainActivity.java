package com.example.dm2.ejerciciosmapas;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mapa;
    private GoogleApiClient apiClient;
    private LocationRequest locRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

       //apiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this).addConnectionCallbacks(this).addApi(LocationServices.API).build()
    }

    //MAPAS
    public void onMapReady(GoogleMap googleMap){
        mapa=googleMap;
    }

    public void pulsado(View v) {
        if (v.getId() == R.id.buttonOpciones) {
            cambiarOpciones();
        }
        if(v.getId()==R.id.buttonMover){
            CameraUpdate camUpd1= CameraUpdateFactory.newLatLngZoom(new LatLng(40.41,-3.69),10);
            mapa.moveCamera(camUpd1);
        }
        if(v.getId()==R.id.buttonPosicion){
            CameraPosition camPos=mapa.getCameraPosition();
            LatLng coordenadas=camPos.target;
            Toast.makeText(this,"Lat: "+coordenadas.latitude+" | Log: "+coordenadas.longitude,Toast.LENGTH_SHORT).show();
            CameraUpdate camUpd1= CameraUpdateFactory.newLatLngZoom(new LatLng(coordenadas.latitude,coordenadas.longitude),10);
            mapa.moveCamera(camUpd1);
        }
    }

    private void updateUI(Location loc){
        if(loc!=null){


        }
    }

    //CAMBIAR DE MAPAS
    private void cambiarOpciones(){
        if(mapa.getMapType()==GoogleMap.MAP_TYPE_TERRAIN){
            mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            mapa.getUiSettings().setZoomControlsEnabled(true);
        }else if(mapa.getMapType()==GoogleMap.MAP_TYPE_SATELLITE){
            mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            mapa.getUiSettings().setZoomControlsEnabled(true);
        }else if(mapa.getMapType()==GoogleMap.MAP_TYPE_HYBRID){
            mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            mapa.getUiSettings().setZoomControlsEnabled(true);
        }else{
            mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            mapa.getUiSettings().setZoomControlsEnabled(true);
        }
    }

    //Actualizar posicion

}
