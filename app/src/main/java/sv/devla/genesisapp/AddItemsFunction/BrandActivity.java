package sv.devla.genesisapp.AddItemsFunction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

import sv.devla.genesisapp.R;
import sv.devla.genesisapp.SearchItemFunction.CustomAutoCompleteView;

public class BrandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
      // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(BrandActivity.this);
                SharedPreferences.Editor editor = preferences.edit();

                CustomAutoCompleteView etd = (CustomAutoCompleteView) findViewById(R.id.customAutoCompleteView2);

                editor.putString("cTitulo",etd.getText().toString());
                //editor.putString("cPrecio","$"+PricePrds.get(position));
                Log.d("nombre",etd.getText().toString());
                editor.apply();

                //Intent i = new Intent(SetItemNameActivity.this, NewItemAddDepartment.class);
                Intent i = new Intent(BrandActivity.this, NewItemAddDepartment.class);
                startActivity(i);

            }
        });

        fab.setImageResource(R.drawable.ic_next);

    }


    public String[] getItemsFromDb(String searchTerm){

        // add items on the array dynamically
        List<ProductSearchObject> products = databaseH.read(searchTerm);
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (ProductSearchObject record : products) {

            item[x] = record.objectName;
            x++;
        }

        return item;
    }
}
