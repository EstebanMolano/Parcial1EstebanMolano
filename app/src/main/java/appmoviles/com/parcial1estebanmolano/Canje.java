package appmoviles.com.parcial1estebanmolano;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Canje extends AppCompatActivity {

    public final static int PUNTOS_LAPICERO = 20;
    public final static int PUNTOS_SACO = 100;
    public final static int PUNTOS_CAMISETA = 80;
    public final static int PUNTOS_LIBRETA = 40;
    public final static int PUNTOS_CUADERNO = 30;

    private Button btn_lapicero,btn_saco,btn_camiseta,btn_libreta,btn_cuaderno,btn_terminar;
    private TextView txt_tusPuntos;
    private int valorTotal;

    private Dialog dialog;
    private TextView txt_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canje);

        dialog = new Dialog(this);

        btn_lapicero = findViewById(R.id.btn_lapicero);
        btn_saco = findViewById(R.id.btn_saco);
        btn_camiseta = findViewById(R.id.btn_camiseta);
        btn_libreta = findViewById(R.id.btn_libreta);
        btn_cuaderno = findViewById(R.id.btn_cuaderno);
        btn_terminar = findViewById(R.id.btn_terminar);
        txt_tusPuntos =findViewById(R.id.txt_tusPuntos);

        btn_camiseta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valorTotal >= PUNTOS_CAMISETA){

                    dialog.setContentView(R.layout.dialog_codigo);
                    txt_codigo = dialog.findViewById(R.id.txt_codigo);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    txt_codigo.setText(generarCodigo());
                    valorTotal-=PUNTOS_CAMISETA;
                    txt_tusPuntos.setText(""+valorTotal);

                }
                else{
                    Toast.makeText(Canje.this,"No tienes suficiente Puntos", Toast.LENGTH_SHORT).show();

                }
            }
        });



        btn_libreta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valorTotal >= PUNTOS_LIBRETA){

                    dialog.setContentView(R.layout.dialog_codigo);
                    txt_codigo = dialog.findViewById(R.id.txt_codigo);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    txt_codigo.setText(generarCodigo());
                    valorTotal-=PUNTOS_LIBRETA;
                    txt_tusPuntos.setText(""+valorTotal);

                }
                else{
                    Toast.makeText(Canje.this,"No tienes suficiente Puntos", Toast.LENGTH_SHORT).show();

                }
            }
        });


        btn_saco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valorTotal >= PUNTOS_SACO){

                    dialog.setContentView(R.layout.dialog_codigo);
                    txt_codigo = dialog.findViewById(R.id.txt_codigo);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    txt_codigo.setText(generarCodigo());
                    valorTotal-=PUNTOS_SACO;
                    txt_tusPuntos.setText(""+valorTotal);

                }
                else{
                    Toast.makeText(Canje.this,"No tienes suficiente Puntos", Toast.LENGTH_SHORT).show();

                }
            }
        });


        btn_lapicero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valorTotal >= PUNTOS_LAPICERO){

                    dialog.setContentView(R.layout.dialog_codigo);
                    txt_codigo = dialog.findViewById(R.id.txt_codigo);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    txt_codigo.setText(generarCodigo());
                    valorTotal-=PUNTOS_LAPICERO;
                    txt_tusPuntos.setText(""+valorTotal);

                }
                else{
                    Toast.makeText(Canje.this,"No tienes suficiente Puntos", Toast.LENGTH_SHORT).show();

                }
            }
        });


        btn_cuaderno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valorTotal >= PUNTOS_CUADERNO){

                    dialog.setContentView(R.layout.dialog_codigo);
                    txt_codigo = dialog.findViewById(R.id.txt_codigo);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    txt_codigo.setText(generarCodigo());
                    valorTotal-=PUNTOS_CUADERNO;
                    txt_tusPuntos.setText(""+valorTotal);

                }
                else{
                    Toast.makeText(Canje.this,"No tienes suficiente Puntos", Toast.LENGTH_SHORT).show();

                }
            }
        });

        valorTotal = getIntent().getIntExtra("valor",0);
        txt_tusPuntos.setText(""+valorTotal);

        btn_terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                i.putExtra("RESULTADO", ""+valorTotal);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

    public String generarCodigo(){

        double a = Math.random()*100000;
        int ai=(int) a;

        return ""+a;
    }


}
