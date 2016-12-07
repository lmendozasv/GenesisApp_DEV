package sv.devla.genesisapp.NewItems;

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
import sv.devla.genesisapp.Search.CustomAutoCompleteView;
import sv.devla.genesisapp.Search.DatabaseHandler;
import sv.devla.genesisapp.Search.ProductSearchObject;

public class SetItemNameActivity extends AppCompatActivity {

    CustomAutoCompleteView myAutoComplete;

    ArrayAdapter<String> myAdapter;

    DatabaseHandler databaseH;

    String[] item = new String[] {"Please search..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_item_step1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SetItemNameActivity.this);
                SharedPreferences.Editor editor = preferences.edit();

                CustomAutoCompleteView etd = (CustomAutoCompleteView) findViewById(R.id.customAutoCompleteView);

                editor.putString("cTitulo",etd.getText().toString());
                //editor.putString("cPrecio","$"+PricePrds.get(position));
                Log.d("nombre",etd.getText().toString());
                editor.apply();

                //Intent i = new Intent(SetItemNameActivity.this, NewItemAddDepartment.class);
                Intent i = new Intent(SetItemNameActivity.this, BrandActivity.class);
                startActivity(i);

            }
        });



        fab.setImageResource(R.drawable.ic_next);
        try{

            // instantiate database handler
            databaseH = new DatabaseHandler(SetItemNameActivity.this);

            // put sample data to database


            // autocompletetextview is in activity_main.xml
            myAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customAutoCompleteView);

            // add the listener so it will tries to suggest while the user types
            myAutoComplete.addTextChangedListener(new NewCustomAutoCompleteTextChangedListener(this));

            // set our adapter
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            myAutoComplete.setAdapter(myAdapter);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }



    // this function is used in NewCustomAutoCompleteTextChangedListener.java
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
