package sv.devla.genesisapp.AddItemsFunction;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sv.devla.genesisapp.R;

public class AvailabilityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i = new Intent(AvailabilityActivity.this, ResumenFinalActivity.class);
                startActivity(i);


            }
        });

        fab.setImageResource(R.drawable.ic_next);
        this.setTitle("Plataformas");
    }

}
