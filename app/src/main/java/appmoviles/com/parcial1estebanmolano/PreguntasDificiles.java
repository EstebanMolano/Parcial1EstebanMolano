package appmoviles.com.parcial1estebanmolano;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PreguntasDificiles extends AppCompatActivity {

    private TextView txt_pregunta;
    private EditText txt_respuesta;
    private Button btn_respuesta;
    private TextView txt_validacion;
    private Button btn_salir;
    private Boolean verificacion;
    private int respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_dificiles);

        verificacion = false;

        txt_pregunta = findViewById(R.id.preguntasD);
        txt_respuesta =findViewById(R.id.txt_respuestaD);
        btn_respuesta =findViewById(R.id.btn_respuestaD);
        txt_validacion =findViewById(R.id.txt_validacionD);
        btn_salir = findViewById(R.id.btn_salirD);

        double a = Math.random()*100;
        double b = Math.random()*100;

        int ai=(int) a;
        int bi=(int) b;

        String pregunta = ""+ ai + " * " + bi;

        respuesta = ai *bi;

        txt_pregunta.setText(pregunta);

        btn_respuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int resp = Integer.parseInt(txt_respuesta.getText().toString());

                if(resp == respuesta){
                    verificacion=true;
                    txt_validacion.setText("Respondistes Bien");
                }
                else{

                    txt_validacion.setText("Respondistes Mal");
                }
                btn_salir.setEnabled(true);
                btn_respuesta.setEnabled(false);
            }
        });

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificacion== false) {

                    Toast.makeText(PreguntasDificiles.this,
                            "No se ha introducido nada en el campo de texto",
                            Toast.LENGTH_SHORT).show();
                    int valor = 00;
                    Intent i = getIntent();
                    i.putExtra("RESULTADO", ""+valor);
                    setResult(RESULT_OK, i);
                    finish();
                } else {
                    int valor = 40;
                    Intent i = getIntent();
                    i.putExtra("RESULTADO", ""+valor);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });

    }
}
