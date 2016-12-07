package sv.devla.genesisapp.AddItemsFunction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;

import sv.devla.genesisapp.R;
import sv.devla.genesisapp.SearchItemFunction.CustomAutoCompleteView;
import sv.devla.genesisapp.SearchItemFunction.DatabaseHandler;
import sv.devla.genesisapp.SearchItemFunction.ProductSearchObject;

public class BrandActivity extends AppCompatActivity {

    DatabaseHandler databaseH;
    CustomAutoCompleteView myAutoComplete;
    ArrayAdapter<String> myAdapter;
    String[] item = new String[] {"Please search..."};
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

                CustomAutoCompleteView etd = (CustomAutoCompleteView) findViewById(R.id.customAutoCompleteView);

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
        try{

            databaseH = new DatabaseHandler(BrandActivity.this);
            myAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customAutoCompleteView);
            myAutoComplete.addTextChangedListener(new NewCustomAutoCompleteTextChangedListenerBrand(this));

            // set our adapter
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            myAutoComplete.setAdapter(myAdapter);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String[] getItemsFromDb(String searchTerm){

        // add items on the array dynamically
        List<ProductSearchObject> products = databaseH.readBrand(searchTerm);
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
