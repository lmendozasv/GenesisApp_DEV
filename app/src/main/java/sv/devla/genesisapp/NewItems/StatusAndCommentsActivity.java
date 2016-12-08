package sv.devla.genesisapp.NewItems;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import sv.devla.genesisapp.R;

public class StatusAndCommentsActivity extends AppCompatActivity {
    private String array_spinner[];
    final Context context = this;
    private  TextView txde;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices_range);
     //   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get seleted

                Spinner spinner = (Spinner)findViewById(R.id.spinner2);


                String estado = spinner.getSelectedItem().toString();


              //  EditText pre = (EditText)findViewById(R.id.tvPRECIO);
               // String precio = pre.getText().toString();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(StatusAndCommentsActivity.this);
                SharedPreferences.Editor editor = preferences.edit();

               // editor.putString("Precio",precio);
                editor.putString("Estado",estado);
                Log.d("depto",estado);

                editor.apply();

                Intent i = new Intent(StatusAndCommentsActivity.this, AvailabilityActivity.class);
                startActivity(i);

            }
        });

        fab.setImageResource(R.drawable.ic_next);

        this.setTitle("Parámetros de venta");

        array_spinner=new String[6];
        array_spinner[0]="Muy Mal estado";
        array_spinner[1]="Mal estado";
        array_spinner[2]="Regular";
        array_spinner[3]="Bueno";
        array_spinner[4]="Muy Bueno";
        array_spinner[5]="Excelente (Nuevo)";

        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.item_spinner, array_spinner);

        Spinner s = (Spinner) findViewById(R.id.spinner2);
        s.setAdapter(adapter);



        //ToggleButton btndesuento = (ToggleButton)findViewById(R.id.toggleButton) ;
         //txde = (TextView)findViewById(R.id.txtdes);
        /*
        btndesuento.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
                //if is heked
                if(isChecked){



                    // get prompts.xml view
                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.prompts, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set prompts.xml to alertdialog builder
                    alertDialogBuilder.setView(promptsView);

                    final EditText userInput = (EditText) promptsView
                            .findViewById(R.id.editTextDialogUserInput);

                    // set dialog message
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // get user input and set it to result
                                            // edit text
                                            txde.setText(userInput.getText()+"%");
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();


                }
            }
        }) ;

    }*/
///on hange and selet yes



    /*

    * */
}
}
