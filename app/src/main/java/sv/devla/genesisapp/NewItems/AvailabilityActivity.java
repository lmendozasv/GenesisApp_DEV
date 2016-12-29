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
    String appmin_="0";
    String appmax_="0";
    String appweb_="0";
    String phys_="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        appmin = (CheckBox) this.findViewById(R.id.appmin);
        appmax = (CheckBox) this.findViewById(R.id.appmax);
        appweb = (CheckBox) this.findViewById(R.id.appweb);
        phys = (CheckBox) this.findViewById(R.id.phys);

       final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();

        FloatingActionButton fab = (FloatingActionButton) this.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(appmax.isChecked()){
                appmax_="1";
            }else{
                appmax_="0";
            }

                if(appmin.isChecked()){
                    appmin_="1";
                }else{
                    appmin_="0";
                }

                if(appweb.isChecked()){
                    appweb_="1";
                }else{
                    appweb_="0";
                }

                if(phys.isChecked()){
                    phys_="1";
                }else{
                    phys_="0";
                }


                editor.putString("NAppmin",appmin_);
                editor.putString("NAppmax",appmax_);
                editor.putString("NAppweb",appweb_);
                editor.putString("NPhys",phys_);
                editor.apply();

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
