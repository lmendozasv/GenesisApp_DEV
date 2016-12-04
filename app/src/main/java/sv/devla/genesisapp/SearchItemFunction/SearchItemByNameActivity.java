package sv.devla.genesisapp.SearchItemFunction;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;

import sv.devla.genesisapp.R;

public class SearchItemByNameActivity extends Activity {

    CustomAutoCompleteView myAutoComplete;

    // adapter for auto-complete
    ArrayAdapter<String> myAdapter;

    // for database operations
    DatabaseHandler databaseH;

    // just to add some initial value
    String[] item = new String[] {"Please search..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item_by_name);

        try{

            // instantiate database handler
            databaseH = new DatabaseHandler(SearchItemByNameActivity.this);

            // put sample data to database
            insertSampleData();

            // autocompletetextview is in activity_main.xml
            myAutoComplete = (CustomAutoCompleteView) findViewById(R.id.myautocomplete);

            // add the listener so it will tries to suggest while the user types
            myAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));

            // set our adapter
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            myAutoComplete.setAdapter(myAdapter);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertSampleData(){

        // CREATE
        databaseH.create( new ProductSearchObject("January","") );
        databaseH.create( new ProductSearchObject("February","") );
        databaseH.create( new ProductSearchObject("March","") );
        databaseH.create( new ProductSearchObject("April","") );
        databaseH.create( new ProductSearchObject("May","") );
        databaseH.create( new ProductSearchObject("June","") );
        databaseH.create( new ProductSearchObject("July","") );
        databaseH.create( new ProductSearchObject("August","") );
        databaseH.create( new ProductSearchObject("September","") );
        databaseH.create( new ProductSearchObject("October","") );
        databaseH.create( new ProductSearchObject("November","") );
        databaseH.create( new ProductSearchObject("December","") );
        databaseH.create( new ProductSearchObject("New Caledonia","") );;
        databaseH.create( new ProductSearchObject("New Zealand","") );
        databaseH.create( new ProductSearchObject("Papua New Guinea","") );
        databaseH.create( new ProductSearchObject("COFFEE-1K","") );
        databaseH.create( new ProductSearchObject("coffee raw","") );
        databaseH.create( new ProductSearchObject("authentic COFFEE","") );
        databaseH.create( new ProductSearchObject("k12-coffee","") );
        databaseH.create( new ProductSearchObject("view coffee","") );
        databaseH.create( new ProductSearchObject("Indian-coffee-two","") );

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