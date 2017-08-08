package pw.costantini.rdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import static pw.costantini.rdm.R.string.reddito;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void calcola (View view) {

        // FATTURA: Ottieni l'elemento che contiene il valore della fattura
        EditText contenitore_valore_fattura =
                (EditText) findViewById(R.id.input_fatturato);

        // FATTURA: Ottieni il valore dell'elemento
        String valore_fattura = contenitore_valore_fattura.getText().toString();


        //FATTURA: CONTROLLO ESISTENZA DATO SUL FATTURATO

        if(TextUtils.isEmpty(valore_fattura)) {
            contenitore_valore_fattura.setError("Inserisci il valore della fattura");
            return;
        }

        //FATTURA: Converti il valore in un numero double
        Double importo_fattura = Double.parseDouble(valore_fattura);

        //COEFF REDD.: Ottieni l'elemento che contiene il coefficiente di redditività
        EditText contenitore_coefficiente_redditivita =
                 (EditText) findViewById(R.id.input_c_redd);

        //COEFF REDD.: Ottieni il valore dell'elemento
        String valore_coefficiente_redditivita = contenitore_coefficiente_redditivita.getText().toString();

        //COEFF REDD.: CONTROLLO ESISTENZA DATO SUL COEFFICIENTE DI REDDITIVITA'

        if(TextUtils.isEmpty(valore_coefficiente_redditivita)) {
            contenitore_coefficiente_redditivita.setError("Inserisci il valore della coefficiente di redditività");
            return;
        }

        //COEFF REDD.: Converti il valore in un numero double
        Double coefficiente_redditivita = Double.parseDouble(valore_coefficiente_redditivita);

        //ALGO: Calcolo della base imponibile
        double reddito = 0.0;
        reddito = importo_fattura / 100 * coefficiente_redditivita;

        //ALGO: Magia Magia per gestire le valute
        BigDecimal redditoRounded = new BigDecimal(reddito)
                .setScale(2, BigDecimal.ROUND_HALF_UP);

        NumberFormat valutaMaker = NumberFormat.getCurrencyInstance(new Locale("it", "IT"));

        //ALGO: Converti il valore del reddito in testo
        String reddito_string = valutaMaker.format(redditoRounded);

        //OUTPUT: Ottieni l'elemento che conterrà il valore del reddito
        TextView reddito_content =
                (TextView) findViewById(R.id.output_reddito);

        //COEFF INPS: Ottieni l'elemento che contiene la percentuale per inps
        EditText contenitore_inps =
                (EditText) findViewById(R.id.input_inps);

       //COEFF INPS: Ottieni il valore dell'elemento
        String valore_cnt_inps = contenitore_inps.getText().toString();

        //COEFF INPS: CONTROLLO ESISTENZA DATO SULLA % INPS

        if(TextUtils.isEmpty(valore_cnt_inps)) {
            contenitore_inps.setError("Inserisci il valore della % INPS");
            return;
        }

        //COEFF INPS: Converti il valore in un numero double
        Double percent_inps = Double.parseDouble(valore_cnt_inps);

        //ALGO: Calcolo della quota inps
        double contributi_inps = 0.0;
        contributi_inps = reddito / 100 * percent_inps;

        //ALGO: Magia Magia per gestire le valute
        BigDecimal contributiInpsRounded = new BigDecimal(contributi_inps)
                .setScale(2, BigDecimal.ROUND_HALF_UP);

        ////ALGO: Converti i contributi INPS in testo
        String inps_string = valutaMaker.format(contributiInpsRounded);

        //OUTPUT: Ottieni l'elemento che conterrà il valore dell'inps da versare
        TextView inps_output =
                (TextView) findViewById(R.id.output_contributi_inps);

        //PUBLISH: Mostra il nuovo valore nell'elemento testo
        reddito_content.setText(reddito_string);
        inps_output.setText(inps_string);


    }
}
