package appmoviles.com.parcial1estebanmolano;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE = 1 ;

    private final static int PREGUNTAS_DIFICILES = 2;
    private final static int PREGUNTAS_FACILES = 0;
    private final static int TIENDA = 1;

    private GoogleMap mMap;
    private LocationManager manager;
    private Marker me;
    private Button btn_preguntas;
    private Button getBtn_preguntasDificiles;
    private Button btn_tiendas;
    private Double[] der_inferior_pregunta;

    private Double[] izq_superior_pregunta;

    private Double[] der_inferior_pregunta_dificil;

    private Double[] izq_superior_pregunta_dificil;

    private Double[] der_inferior_tienda;

    private Double[] izq_superior_tienda;

    private int valor_total;

    private TextView txt_valorTotal;

    public MapsActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        valor_total=0;

        txt_valorTotal=findViewById(R.id.txt_valorTotal);

        txt_valorTotal.setText(valor_total+"");

        btn_tiendas = findViewById(R.id.btn_tienda);

        btn_tiendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MapsActivity.this, Canje.class);
                i.putExtra("valor", valor_total);
                startActivityForResult(i,TIENDA);
                }
                });

        btn_preguntas = findViewById(R.id.btn_preguntas);

        btn_preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MapsActivity.this, PreguntasFaciles.class);
                startActivityForResult(i,PREGUNTAS_FACILES);

            }


        });

        getBtn_preguntasDificiles=findViewById(R.id.btn_preguntasDificiles);

        getBtn_preguntasDificiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapsActivity.this,PreguntasDificiles.class);
                startActivityForResult(i,PREGUNTAS_DIFICILES);
            }
        });

        der_inferior_pregunta = new Double[2];
        izq_superior_pregunta = new Double[2];

        der_inferior_pregunta[0] = new Double(3.341661);
        der_inferior_pregunta[1] = new Double(-76.53009);

        izq_superior_pregunta[0] = new Double(3.341932);
        izq_superior_pregunta[1] = new Double(-76.52979);

        der_inferior_pregunta_dificil = new Double[2];
        izq_superior_pregunta_dificil = new Double[2];

        der_inferior_pregunta_dificil[0] = new Double(3.341056);
        der_inferior_pregunta_dificil[1] = new Double(-76.530436);

        izq_superior_pregunta_dificil[0] = new Double(3.341245);
        izq_superior_pregunta_dificil[1] = new Double(-76.529862);



        der_inferior_tienda = new Double[2];
        izq_superior_tienda = new Double[2];

        der_inferior_tienda[0] = new Double(3.341056);
        der_inferior_tienda[1] = new Double(-76.530416);

        izq_superior_tienda[0] = new Double(3.341225);
        izq_superior_tienda[1] = new Double(-76.529871);

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {

        } else {
            int resultado = Integer.parseInt(data.getExtras().getString("RESULTADO"));
            int total = resultado+valor_total;

            Toast.makeText(this, ""+total
                    , Toast.LENGTH_SHORT)
                    .show();

            switch (requestCode) {
                case PREGUNTAS_FACILES:
                    valor_total +=resultado;
                    txt_valorTotal.setText(""+valor_total);
                    break;
                case PREGUNTAS_DIFICILES:
                    valor_total +=resultado;
                    txt_valorTotal.setText(""+valor_total);
                    break;
                case TIENDA:
                    valor_total =resultado;
                    txt_valorTotal.setText(""+valor_total);
                    break;
            }

            }


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, REQUEST_CODE);

        LatLng you = new LatLng(3.341057, -76.53041);
        me = mMap.addMarker(new MarkerOptions().position(you).title("You are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(you));



        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.e(">>>","LAT: "+location.getLatitude()+ " , LONG: "+location.getLongitude());

                if(me != null){
                    me.remove();
                }
                me = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .title("Me")
                );
                mMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10));

                Toast.makeText(MapsActivity.this,"LAT: "+location.getLatitude()+ " , LONG: "+location.getLongitude(), Toast.LENGTH_SHORT).show();


                btn_preguntas.setEnabled(false);
                if(me.getPosition().latitude > der_inferior_pregunta[0] && me.getPosition().longitude > der_inferior_pregunta[1]){
                   if(me.getPosition().latitude < izq_superior_pregunta[0] && me.getPosition().longitude < izq_superior_pregunta[1]){
                       btn_preguntas.setEnabled(true);
                       Toast.makeText(MapsActivity.this,"true", Toast.LENGTH_SHORT).show();

                   }


                }

                getBtn_preguntasDificiles.setEnabled(false);
                if(me.getPosition().latitude > der_inferior_pregunta_dificil[0] && me.getPosition().longitude > der_inferior_pregunta_dificil[1]){
                    if(me.getPosition().latitude < izq_superior_pregunta_dificil[0] && me.getPosition().longitude < izq_superior_pregunta_dificil[1]){
                        getBtn_preguntasDificiles.setEnabled(true);
                        Toast.makeText(MapsActivity.this,"true", Toast.LENGTH_SHORT).show();

                    }


                }

                btn_tiendas.setEnabled(false);
                if(me.getPosition().latitude > der_inferior_tienda[0] && me.getPosition().longitude > der_inferior_tienda[1]){
                    if(me.getPosition().latitude < izq_superior_tienda[0] && me.getPosition().longitude < izq_superior_tienda[1]){
                        btn_tiendas.setEnabled(true);
                    }


                }




            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });




}
}
