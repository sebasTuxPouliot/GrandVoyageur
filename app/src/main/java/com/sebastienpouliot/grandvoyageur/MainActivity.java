package com.sebastienpouliot.grandvoyageur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private final BigDecimal TAUX_USD_EN_CAD = new BigDecimal("1.3189");
    private final BigDecimal TAUX_CAD_EN_USD = new BigDecimal("0.7582");
    private String nomDeviseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner deviseDestination = (Spinner)findViewById(R.id.activity_main_spn_devise_destination);
        final Spinner deviseSource = (Spinner)findViewById(R.id.activity_main_spn_devise_source);

        deviseSource.setSelection(0); // CAD
        deviseDestination.setSelection(1); // USD

        deviseSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (deviseSource.getSelectedItemPosition() == 1) {
                    nomDeviseSource = "USD";
                    deviseDestination.setSelection(0);
                } else {
                    nomDeviseSource = "CAD";
                    deviseDestination.setSelection(1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        deviseDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (deviseDestination.getSelectedItemPosition() == 1) {
                    nomDeviseSource = "CAD";
                    deviseSource.setSelection(0);
                } else {
                    nomDeviseSource = "USD";
                    deviseSource.setSelection(1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Button btnConvertir = (Button)findViewById(R.id.activity_main_btn_convertir);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txvReponse = (TextView)findViewById(R.id.activity_main_txv_reponse);
                txvReponse.setText(convertirMonnaie());
            }
        });
    }

    private String convertirMonnaie(){
        EditText etvMontantSource = (EditText)findViewById(R.id.activity_main_etv_montant_source);

        BigDecimal montantDeviseInitiale;
        String strMontant = etvMontantSource.getText().toString();

        montantDeviseInitiale = new BigDecimal(strMontant).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal montantDeviseDemandee;

        if (nomDeviseSource == "USD") {
            montantDeviseDemandee = Monnaie.usdEnCad(montantDeviseInitiale);
        } else {
            montantDeviseDemandee = Monnaie.cadEnUsd(montantDeviseInitiale);
        }

        return montantDeviseDemandee.toPlainString();
    }
}
