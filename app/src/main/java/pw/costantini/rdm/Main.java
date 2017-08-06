package pw.costantini.rdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void calcola (View view) {




        //Ottieni l'elemento che contiene il valore della fattura
        EditText contenitore_valore_fattura =
                (EditText) findViewById(R.id.input_fatturato);

        //Ottieni il valore dell'elemento
        String valore_fattura = contenitore_valore_fattura.getText().toString();


        //CONTROLLO ESISTENZA DATO SUL FATTURATO

        if(TextUtils.isEmpty(valore_fattura)) {
            contenitore_valore_fattura.setError("Inserisci il valore della fattura");
            return;
        }

        //Converti il valore in un numero
        Double importo_fattura = Double.parseDouble(valore_fattura);

        //Ottieni l'elemento che contiene il coefficiente di redditività
        EditText contenitore_coefficiente_redditivita =
                 (EditText) findViewById(R.id.input_c_redd);

        //Ottieni il valore dell'elemento
        String valore_coefficiente_redditivita = contenitore_coefficiente_redditivita.getText().toString();

        //CONTROLLO ESISTENZA DATO SUL COEFFICIENTE DI REDDITIVITA'

        if(TextUtils.isEmpty(valore_coefficiente_redditivita)) {
            contenitore_coefficiente_redditivita.setError("Inserisci il valore della coefficiente di redditività");
            return;
        }

        //Converti il valore in un numero
        Double coefficiente_redditivita = Double.parseDouble(valore_coefficiente_redditivita);

        Double reddito = 0.0;

        reddito = importo_fattura / 100 * coefficiente_redditivita;

        //Converti il valore del reddito in testo
        String reddito_string = String.valueOf(reddito);

        //Ottieni l'elemento che conterrà il valore del reddito
        TextView reddito_content =
                (TextView) findViewById(R.id.output_reddito);

        //Ottieni l'elemento che contiene la pecentuale per inps
        EditText contenitore_inps =
                (EditText) findViewById(R.id.input_inps);

       //Ottieni il valore dell'elemento
        String valore_cnt_inps = contenitore_inps.getText().toString();

        //CONTROLLO ESISTENZA DATO SULLA % INPS

        if(TextUtils.isEmpty(valore_cnt_inps)) {
            contenitore_inps.setError("Inserisci il valore della % INPS");
            return;
        }

        //Converti il valore in un numero
        Double percent_inps = Double.parseDouble(valore_cnt_inps);


        Double contributi_inps = 0.0;

        contributi_inps = reddito / 100 * percent_inps;

        //Converti i contributi INPS in testo
        String inps_string = String.valueOf(contributi_inps);

        //Ottieni l'elemento che conterrà il valore dell'inps da versare
        TextView inps_output =
                (TextView) findViewById(R.id.output_contributi_inps);



        //Mostra il nuovo valore nell'elemento testo
        reddito_content.setText(reddito_string);
        inps_output.setText(inps_string);
        //String.format("%1$,.2f", myDouble)

    }
}
