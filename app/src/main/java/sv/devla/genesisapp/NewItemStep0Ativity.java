package sv.devla.genesisapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NewItemStep0Ativity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item_step0_ativity);
        this.setTitle("Nuevo artículo en Almacén");


        Button btn1 = (Button) this.findViewById(R.id.btnsi);
        Button btn2 = (Button) this.findViewById(R.id.btnno);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inicio

if(isNetworkAvailable()){
    Intent i = new Intent(NewItemStep0Ativity.this, ScanCodeBarActivity.class);
    startActivity(i);
    finish();
}
                else {
    new AlertDialog.Builder(NewItemStep0Ativity.this)
            .setTitle("No hay internet")
            .setMessage("No hay una conexión de internet estable, por favor solucione el problema de internet e intente de nuevo")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // continue with delete
                }
            })

            .setIcon(android.R.drawable.ic_dialog_alert)
            .show();
}


                //fin

            }
        });
        //new IntentIntegrator(this).initiateScan();


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inicio

                if(isNetworkAvailable()){
                    Intent i = new Intent(NewItemStep0Ativity.this, NewItemStep1Activity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    new AlertDialog.Builder(NewItemStep0Ativity.this)
                            .setTitle("No hay internet")
                            .setMessage("No hay una conexión de internet estable, por favor solucione el problema de internet e intente de nuevo")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })

                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }


                //fin

            }
        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
