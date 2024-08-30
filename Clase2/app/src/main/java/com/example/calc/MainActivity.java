package com.example.calc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero;
    private TextView tvResultado;
    private Button btnSumar, btnRestar, btnMultiplicar, btnDividir, btnIgual;

    private double numero1 = 0;
    private double numero2 = 0;
    private String operacion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular elementos de la UI
        etNumero = findViewById(R.id.etNumero);
        tvResultado = findViewById(R.id.tvResultado);
        btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        btnIgual = findViewById(R.id.btnIgual);

        // Configurar botones de operaciones
        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("+");
            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("-");
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("*");
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("/");
            }
        });

        // Configurar botón de igual
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado();
            }
        });

    }

    private void guardarNumeroYOperacion(String op) {
        String numeroIngresado = etNumero.getText().toString();
        if (!numeroIngresado.isEmpty()) {
            numero1 = Double.parseDouble(numeroIngresado);
            operacion = op;
            etNumero.setText(""); // Limpiar el EditText para el segundo número
        } else {
            Toast.makeText(this, "Por favor ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void calcularResultado() {
        String numeroIngresado = etNumero.getText().toString();
        if (!numeroIngresado.isEmpty()) {
            numero2 = Double.parseDouble(numeroIngresado);
            double resultado = 0;

            switch (operacion) {
                case "+":
                    resultado = numero1 + numero2;
                    break;
                case "-":
                    resultado = numero1 - numero2;
                    break;
                case "*":
                    resultado = numero1 * numero2;
                    break;
                case "/":
                    if (numero2 != 0) {
                        resultado = numero1 / numero2;

                    } else {
                        Toast.makeText(this, "No se puede dividir por cero.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
            }
            int finalAns = removeDecAns(resultado, operacion);

            if (finalAns == 0 && operacion.equals("/")){
                String divAns;
                if (resultado == Math.floor(resultado)) {
                    divAns = String.format("%.0f", resultado);
                } else {
                    divAns = String.valueOf(resultado);
                }
                tvResultado.setText("Resultado: " + String.valueOf(divAns));
                etNumero.setText(String.valueOf(divAns));
            } else {
                tvResultado.setText("Resultado: " + String.valueOf(finalAns));
                etNumero.setText(String.valueOf(finalAns)); // Limpiar el EditText para nuevas operaciones
            }

        } else {
            Toast.makeText(this, "Por favor ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }
    public Integer removeDecAns(double doubAns, String operation) {

        if (operation.equals("+") || operation.equals("-") || operation.equals("*")){
            int intAns = (int) doubAns;
            return intAns;
        } else {
            return 0;
        }
    }
}
