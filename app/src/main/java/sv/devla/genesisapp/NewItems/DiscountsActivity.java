package sv.devla.genesisapp.NewItems;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import sv.devla.genesisapp.R;

public class  DiscountsActivity extends Activity {
    private ListView myList;
    private MyAdapter myAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discounts);

        myList = (ListView) findViewById(R.id.MyList);
        myList.setItemsCanFocus(true);
        myAdapter = new MyAdapter();
        myList.setAdapter(myAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get seleted

                //Spinner spinner = (Spinner)findViewById(R.id.spinner2);


                //String estado = spinner.getSelectedItem().toString();


                //  EditText pre = (EditText)findViewById(R.id.tvPRECIO);
                // String precio = pre.getText().toString();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(DiscountsActivity.this);
                SharedPreferences.Editor editor = preferences.edit();

                // editor.putString("Precio",precio);
                editor.putString("Estado","");
                Log.d("depto","");

                editor.apply();

                Intent i = new Intent(DiscountsActivity.this, DiscountsActivity.class);
                startActivity(i);

            }
        });

        fab.setImageResource(R.drawable.ic_next);

    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        public ArrayList myItems = new ArrayList();

        public MyAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (int i = 0; i < 20; i++) {
                ListItem listItem = new ListItem();
                listItem.caption = "Caption" + i;
                myItems.add(listItem);
            }
            notifyDataSetChanged();
        }

        public int getCount() {
            return myItems.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.price_list_item, null);
                holder.caption = (EditText) convertView
                        .findViewById(R.id.ItemCaption);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //Fill EditText with the value you have in data source
            holder.caption.setText("test");
            holder.caption.setId(position);

            //we need to update adapter once we finish with editing
            holder.caption.setOnFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        final int position = v.getId();
                        final EditText Caption = (EditText) v;
                        //myItems.get(position). = Caption.getText().toString();
                    }
                }
            });

            return convertView;
        }
    }

    class ViewHolder {
        EditText caption;
    }

    class ListItem {
        String caption;
    }
}