package appmoviles.com.parcial1estebanmolano;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PreguntasFaciles extends AppCompatActivity {

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
        setContentView(R.layout.activity_preguntas);

        verificacion = false;

        txt_pregunta = findViewById(R.id.preguntas);
        txt_respuesta =findViewById(R.id.txt_respuesta);
        btn_respuesta =findViewById(R.id.btn_respuesta);
        txt_validacion =findViewById(R.id.txt_validacion);
        btn_salir = findViewById(R.id.btn_salir);

        double a = Math.random()*100;
        double b = Math.random()*100;

        int ai=(int) a;
        int bi=(int) b;

        String pregunta = ""+ ai + " + " + bi;

        respuesta = ai +bi;

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
                if (verificacion== true) {
                    int valor = 20;
                    Intent i = getIntent();
                    i.putExtra("RESULTADO", ""+valor);
                    setResult(RESULT_OK, i);

                } else {
                    Toast.makeText(PreguntasFaciles.this,
                            "No se ha introducido nada en el campo de texto",
                            Toast.LENGTH_SHORT).show();
                    int valor = 0;
                    Intent i = getIntent();
                    i.putExtra("RESULTADO", ""+valor);
                    setResult(RESULT_OK, i);

                }
                finish();
            }
        });

    }
}
