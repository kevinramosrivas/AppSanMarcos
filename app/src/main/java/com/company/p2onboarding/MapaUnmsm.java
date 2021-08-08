package com.company.p2onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaUnmsm extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_unmsm);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng unmsm = new LatLng(-12.0561578, -77.0867083);
        //sitios importantes UNMSM

        LatLng estadio = new LatLng(-12.05721750092754, -77.08323214217533);
        LatLng comedor = new LatLng(-12.059089473750896, -77.08309114489595);
        LatLng huaca  = new LatLng(-12.060381747929767, -77.0870153456026);
        LatLng clinicaUniversitaria = new LatLng(-12.055742661588853, -77.0822901890791);
        LatLng plazaFrayTomas = new LatLng(-12.05618140268176, -77.08611978975932);
        LatLng rectorado = new LatLng(-12.056514165872677, -77.0864076117406);
        LatLng bibliotecaCentral= new LatLng(-12.05562581534605, -77.0855783246752);

        //lugares de comida
        LatLng kioskoGeografica = new LatLng(-12.055547649108197, -77.08599167271947);
        LatLng cafeteriaOdontologia = new LatLng(-12.054587515710628, -77.08522552204023);



        mMap.addMarker(new MarkerOptions().position(estadio).title("Estadio Olímpico de San Marcos"));
        mMap.addMarker(new MarkerOptions().position(comedor).title("Comedor Universitario"));
        mMap.addMarker(new MarkerOptions().position(huaca).title("Huaca San Marcos"));
        mMap.addMarker(new MarkerOptions().position(clinicaUniversitaria).title("Clínica Universitaria UNMSM"));
        mMap.addMarker(new MarkerOptions().position(plazaFrayTomas).title("Plaza Fray Tomás de San Martin"));
        mMap.addMarker(new MarkerOptions().position(rectorado).title("Rectorado UNMSM"));
        mMap.addMarker(new MarkerOptions().position(bibliotecaCentral).title("Biblioteca Central Pedro Zulen "));
        mMap.addMarker(new MarkerOptions().position(kioskoGeografica).title("Kiosco Eap Ing. Geográfica"));
        mMap.addMarker(new MarkerOptions().position(cafeteriaOdontologia).title("Cafetería Eap Odontologia"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(unmsm,16));
    }
}