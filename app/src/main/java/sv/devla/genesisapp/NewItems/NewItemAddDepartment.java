package sv.devla.genesisapp.NewItems;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import sv.devla.genesisapp.R;

public class NewItemAddDepartment extends AppCompatActivity {
    private String array_spinner[];
    private String array_spinnercatsellos[];
    private String array_spinnercatsellas[];
    private String array_spinnercatninos[];

    private String array_spinnercatbabyandkids[];

    private String array_spinnercatjoyeria[];

    private String array_spinnercathogar[];
    Spinner scats =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item_add_department);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get seleted

                Spinner spinner = (Spinner)findViewById(R.id.spinner);

                Spinner spinnercats = (Spinner)findViewById(R.id.spncat);

                String departamento = spinner.getSelectedItem().toString();

                String spinnercatss = spinnercats.getSelectedItem().toString();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(NewItemAddDepartment.this);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("NDepto",departamento);
                editor.putString("CCat",spinnercatss);
                Log.d("depto",departamento);
                Log.d("categ",spinnercatss);
                editor.apply();

                Intent i = new Intent(NewItemAddDepartment.this, AvailabilityActivity.class);
                startActivity(i);

            }
        });

        fab.setImageResource(R.drawable.ic_next);

        this.setTitle("Clasificación por departamento");



        array_spinner=new String[6];
        array_spinner[0]="Ellas";
        array_spinner[1]="Ellos";
        array_spinner[2]="Niños";
        array_spinner[3]="Baby & Kids";
        array_spinner[4]="Joyeria";
        array_spinner[5]="Hogar";


        Spinner s = (Spinner) findViewById(R.id.spinner);
         scats = (Spinner) findViewById(R.id.spncat);

        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.item_spinner, array_spinner);
        array_spinnercatsellos =new String[8];
        array_spinnercatsellos[0]="Camisas";
        array_spinnercatsellos[1]="Calzado";
        array_spinnercatsellos[2]="Corbatas";
        array_spinnercatsellos[3]="Jeans";
        array_spinnercatsellos[4]="Pantalones";
        array_spinnercatsellos[5]="Shorts";
        array_spinnercatsellos[6]="Suéter y Chumpas";
        array_spinnercatsellos[7]="Trajes";


        array_spinnercatsellas =new String[12];
        array_spinnercatsellas[0]="Abrigos";
        array_spinnercatsellas[1]="Accesorios";
        array_spinnercatsellas[2]="Blusas";
        array_spinnercatsellas[3]="Calzado";
        array_spinnercatsellas[4]="Carteras";
        array_spinnercatsellas[5]="Chaquetas y suéteres";
        array_spinnercatsellas[6]="Faldas";
        array_spinnercatsellas[7]="Pantalones y jeans";
        array_spinnercatsellas[8]="Trajes de baño";
        array_spinnercatsellas[9]="Ropa de dormir";
        array_spinnercatsellas[10]="Ropa interior";
        array_spinnercatsellas[11]="Vestidos";

        array_spinnercatninos=new String[3];
        array_spinnercatninos[0]="Juguetes";
        array_spinnercatninos[1]="Niñas";
        array_spinnercatninos[2]="Niños";


        array_spinnercatbabyandkids=new String[6];
        array_spinnercatbabyandkids[0]="Calzado";
        array_spinnercatbabyandkids[1]="Coches y asientos";
        array_spinnercatbabyandkids[2]="Juguetes";
        array_spinnercatbabyandkids[3]="Ropa de niño";
        array_spinnercatbabyandkids[4]="Ropa de niña";
        array_spinnercatbabyandkids[5]="Ropa de bebé";


        array_spinnercatjoyeria=new String[3];
        array_spinnercatjoyeria[0]="Accesorios";
        array_spinnercatjoyeria[1]="Bisutería";
        array_spinnercatjoyeria[2]="Relojes";


        array_spinnercathogar=new String[3];
        array_spinnercathogar[0]="Maletas";
        array_spinnercathogar[1]="Muebles";
        array_spinnercathogar[2]="Cocina";


        final ArrayAdapter adaptercats0 = new ArrayAdapter(this,R.layout.item_spinner, array_spinnercatsellas);
        final ArrayAdapter adaptercats1 = new ArrayAdapter(this,R.layout.item_spinner, array_spinnercatsellos);
        final ArrayAdapter adaptercats2 = new ArrayAdapter(this,R.layout.item_spinner, array_spinnercatninos);
        final ArrayAdapter adaptercats3 = new ArrayAdapter(this,R.layout.item_spinner, array_spinnercatbabyandkids);
        final ArrayAdapter adaptercats4 = new ArrayAdapter(this,R.layout.item_spinner, array_spinnercatjoyeria);
        final ArrayAdapter adaptercats5 = new ArrayAdapter(this,R.layout.item_spinner, array_spinnercathogar);


        s.setAdapter(adapter);



        //Listeners
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                if(id==0){
                    scats.setAdapter(adaptercats0);
                }
                if(id==1){
                    scats.setAdapter(adaptercats1);

                }
                if(id==2){
                    scats.setAdapter(adaptercats2);

                }
                if(id==3){
                    scats.setAdapter(adaptercats3);

                }
                if(id==4){
                    scats.setAdapter(adaptercats4);

                }
                if(id==5){
                    scats.setAdapter(adaptercats5);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        //Listener fin

    }

}
