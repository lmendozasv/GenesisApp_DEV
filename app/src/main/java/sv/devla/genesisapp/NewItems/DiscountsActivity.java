package sv.devla.genesisapp.NewItems;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import sv.devla.genesisapp.R;

public class DiscountsActivity extends Activity {


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discounts);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(DiscountsActivity.this);
                SharedPreferences.Editor editor = preferences.edit();

                // editor.putString("Precio",precio);
                editor.putString("Estado","");
                Log.d("depto","");

                editor.apply();

                Intent i = new Intent(DiscountsActivity.this, QuantityActivity.class);
                startActivity(i);

            }
        });

        fab.setImageResource(R.drawable.ic_next);

    }


}