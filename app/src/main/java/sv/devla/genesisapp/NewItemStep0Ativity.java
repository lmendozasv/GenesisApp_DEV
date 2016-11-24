package sv.devla.genesisapp;

import android.content.Intent;
import android.os.Bundle;
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
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inicio
                Intent i = new Intent(NewItemStep0Ativity.this, ScanCodeBarActivity.class);
                startActivity(i);
                finish();

                //fin

            }
        });
        //new IntentIntegrator(this).initiateScan();

    }
}
