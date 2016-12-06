package sv.devla.genesisapp.AddItemsFunction;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;




public class NewCustomAutoCompleteTextChangedListener implements TextWatcher{

    public static final String TAG = "NewCustomAutoCompleteTextChangedListener.java";
    Context context;

    public NewCustomAutoCompleteTextChangedListener(Context context){
        this.context = context;
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onTextChanged(CharSequence userInput, int start, int before, int count) {

        // if you want to see in the logcat what the user types
        Log.e(TAG, "User input: " + userInput);

        SetItemNameActivity mainActivity = ((SetItemNameActivity) context);

        // query the database based on the user input
        mainActivity.item = mainActivity.getItemsFromDb(userInput.toString());

        // update the adapater
        mainActivity.myAdapter.notifyDataSetChanged();
        mainActivity.myAdapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_dropdown_item_1line, mainActivity.item);
        mainActivity.myAutoComplete.setAdapter(mainActivity.myAdapter);

    }

}