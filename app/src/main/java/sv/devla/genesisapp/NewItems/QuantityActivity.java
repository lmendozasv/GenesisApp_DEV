package sv.devla.genesisapp.NewItems;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import sv.devla.genesisapp.R;

public class QuantityActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(QuantityActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Estado","");
                Log.d("depto","");
                editor.apply();
              //  Intent i = new Intent(QuantityActivity.this, ResumenFinalActivity.class);
                Intent i = new Intent(QuantityActivity.this, SelectSymbolActivity.class);
                startActivity(i);

            }
        });

        fab.setImageResource(R.drawable.ic_next);

    }

}
