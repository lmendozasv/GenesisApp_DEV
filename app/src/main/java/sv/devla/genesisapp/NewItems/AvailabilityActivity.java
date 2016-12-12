package sv.devla.genesisapp.NewItems;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import sv.devla.genesisapp.R;

public class AvailabilityActivity extends AppCompatActivity {
CheckBox appmin,appmax,appweb,phys;
    String appmin_,appmax_,appweb_,phys_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
        appmin = (CheckBox) this.findViewById(R.id.appmin);
        appmax = (CheckBox) this.findViewById(R.id.appmax);
        appweb = (CheckBox) this.findViewById(R.id.appweb);
        phys = (CheckBox) this.findViewById(R.id.phys);

        FloatingActionButton fab = (FloatingActionButton) this.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(appmax.isChecked()){
                appmax_="1";
            }else{
                appmax_="0";
            }

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AvailabilityActivity.this);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("NAppmin",appmin_);
                editor.putString("NAppmax",appmax_);
                editor.putString("NAppweb",appweb_);
                editor.putString("Nphys",phys_);

                Log.d("depto",appmin_);
                Log.d("categ",appmax_);



                //Intent i = new Intent(AvailabilityActivity.this, ResumenFinalActivity.class);
                Intent i = new Intent(AvailabilityActivity.this, StatusAndCommentsActivity.class);
                startActivity(i);


            }
        });

        fab.setImageResource(R.drawable.ic_next);
        this.setTitle("Plataformas");
    }

}
