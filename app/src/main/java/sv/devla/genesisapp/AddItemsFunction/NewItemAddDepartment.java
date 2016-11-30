package sv.devla.genesisapp.AddItemsFunction;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import sv.devla.genesisapp.R;

public class NewItemAddDepartment extends AppCompatActivity {
    private String array_spinner[];

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setImageResource(R.drawable.ic_next);

        this.setTitle("Clasificación por departamento");



        array_spinner=new String[5];
        array_spinner[0]="Ellas";
        array_spinner[1]="Ellos";
        array_spinner[2]="Niños";
        array_spinner[3]="Baby & Kids";
        array_spinner[4]="Joyeria";
        array_spinner[4]="Hogar";
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
    }

}
