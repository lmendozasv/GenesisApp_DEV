package sv.devla.genesisapp.NewItems;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import sv.devla.genesisapp.R;

public class PricingActivity extends Activity {

    EditText txtpreciominorista =null;
    EditText txtpreciomayorista =null;
    EditText txtprecioweb =null;
    EditText txtpreciosucursal=null;


    TextView ttmin,ttmax,ttweb,ttsuc;




    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing);

        txtpreciominorista=(EditText)this.findViewById(R.id.txtpreciominorista);
        txtpreciomayorista=(EditText)this.findViewById(R.id.txtpreciomayorista);
        txtpreciosucursal=(EditText)this.findViewById(R.id.txtpreciosucursal);
        txtprecioweb=(EditText)this.findViewById(R.id.txtprecioweb);

        ttmin=(TextView) this.findViewById(R.id.ttmin);
        ttmax=(TextView) this.findViewById(R.id.ttmax);
        ttweb=(TextView) this.findViewById(R.id.ttweb);
        ttsuc=(TextView) this.findViewById(R.id.ttsuc);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        final String appmin = preferences.getString("NAppmin","");
        final String appmax = preferences.getString("NAppmax","");
        final String appweb = preferences.getString("NAppweb","");
        final String appphys = preferences.getString("NPhys","");

    Log.d("PRECIOS",appmin+appmax+appweb+ appphys);
        if(appmin.equals("1")){
            txtpreciominorista.setEnabled(true);
            ttmin.setVisibility(View.VISIBLE);
        }
        else{
            txtpreciominorista.setEnabled(false);
            txtpreciominorista.setHint("");
            ttmin.setText("N/A");
            //ttmin.setVisibility(View.GONE);
        }

        if(appmax.equals("1")){
            txtpreciomayorista.setEnabled(true);

            //ttmax.setVisibility(View.VISIBLE);
        }
        else{
            txtpreciomayorista.setEnabled(false);
            txtpreciomayorista.setHint("");
            ttmax.setText("N/A");
            //ttmax.setVisibility(View.GONE);
        }

        if(appweb.equals("1")){
        txtprecioweb.setEnabled(true);
            ttweb.setVisibility(View.VISIBLE);
        }
        else{
            txtprecioweb.setEnabled(false);
            txtprecioweb.setHint("");
            ttweb.setText("N/A");
            //ttweb.setVisibility(View.GONE);
        }
        if(appphys.equals("1")){
            txtpreciosucursal.setEnabled(true);
            ttsuc.setVisibility(View.VISIBLE);

        }
else{
            txtpreciosucursal.setEnabled(false);
            txtpreciosucursal.setHint("");
            ttsuc.setText("N/A");
            //ttsuc.setVisibility(View.GONE);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PricingActivity.this);
                SharedPreferences.Editor editor = preferences.edit();


                if(appmin.equals("1")){
                    editor.putString("NPhysPrecio",txtpreciosucursal.getText().toString());
                }

                if(appmax.equals("1")) {
                    editor.putString("NAppminPrecio", txtpreciominorista.getText().toString());
                }
                if(appweb.equals("1")) {
                    editor.putString("NWebPrecio", txtprecioweb.getText().toString());
                }
                if(appphys.equals("1")) {
                    editor.putString("NAppmaxPrecio", txtpreciomayorista.getText().toString());
                }

                //Log.d("depto","");
                editor.apply();
                Intent i = new Intent(PricingActivity.this, DiscountsActivity.class);
                startActivity(i);

            }
        });
        fab.setImageResource(R.drawable.ic_next);
    }

}