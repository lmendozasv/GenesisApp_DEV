package sv.devla.genesisapp.AddItemsFunction;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import sv.devla.genesisapp.GenUtils.ServiceHandler;
import sv.devla.genesisapp.R;

public class IdentifyByUPCActivity extends AppCompatActivity {
    String upc ="";
    String upc2 ="";
    String myArticleUrl = "http://www.searchupc.com/handlers/upcsearch.ashx";
    String myArticleUrlCurrency = "http://api.fixer.io/latest";

    String product_name ="";
    String product_price="";
    String image_url="";
    int ResultSet=0;
    String product_currency="";
    TextView txtname=null;
    TextView txtprice=null;
    TextView txtcurrency=null;
    String otherCurrency="";
    String eRate ="";
    String namestr="";
    String resu=null;
    String resulting_json = null;

    private getUserDetailsTask mAuthTask = null;
    ProgressDialog progress;

    AdaptadorLista ma ;

    Button load_img;
    ImageView img;
    Bitmap bitmap;

    SharedPreferences sp;
    SharedPreferences.Editor spe;
    TextView allDebug=null;

    ArrayList<String> NamePrds = new ArrayList<String>();
    ArrayList<String> PricePrds = new ArrayList<String>();
    ArrayList<String> ImagePrds = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_by_upc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setImageResource(R.drawable.ic_next);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(IdentifyByUPCActivity.this);
        upc=preferences.getString("UPC", "0");
        upc2= "";

        TextView tvUPC = (TextView)this.findViewById(R.id.txtUPC);
        tvUPC.setText(upc);
        mAuthTask = new getUserDetailsTask("","");

        mAuthTask.execute((Void) null);

        progress = ProgressDialog.show(this, "Buscando", "Espere un momento...", true);

        img=(ImageView)this.findViewById(R.id.ivProduct);

        this.setTitle("Información de referencia");

        //allDebug  = (TextView) this.findViewById(R.id.txtall);
    }


    public class getUserDetailsTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        getUserDetailsTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            Log.d("cuentaback",mEmail);
            String url_select = myArticleUrl;
            ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
            param.add(new BasicNameValuePair("request_type", "3"));
            param.add(new BasicNameValuePair("access_token", "35B2E35C-3DEC-4502-8BE4-6D7661D3CE9B"));
            //param.add(new BasicNameValuePair("upc", upc));
            param.add(new BasicNameValuePair("upc", "031262053596"));//887961037814//031262053596//044194959157
            //param.add(new BasicNameValuePair("upc", "887961037814"));
            //044194959157
            try {
                 resulting_json = null;
                ServiceHandler jsonParser = new ServiceHandler();
                resulting_json = jsonParser.makeServiceCall(url_select,ServiceHandler.GET, param);
                Log.d("Parametros",param.toString());

                Log.d("RESPUESTA",resulting_json.toString());
                JSONObject ja = new JSONObject(resulting_json);
                resu=resulting_json;





                if (ja != null || !ja.equals("{ }") || ja.length()>10) { //si la respuesta del servidor fur valida

                    NamePrds.clear();
                    PricePrds.clear();
                    ImagePrds.clear();

                    JSONObject jsonObject=new JSONObject(resulting_json);

                    //CONTAR ELEMENTOS DEVUELTOS Y MOSTRAR LISTA O UNITARIO

                    JSONObject resobj = new JSONObject(resulting_json);
                    Iterator<?> keys = resobj.keys();
                    while(keys.hasNext() ) {
                        String key = (String)keys.next();
                        if ( resobj.get(key) instanceof JSONObject ) {
                            JSONObject xx = new JSONObject(resobj.get(key).toString());

                            Log.d("res1",xx.getString("productname"));
                            Log.d("res2",xx.getString("price"));

                            product_name = xx.getString("productname");
                            image_url = xx.getString("imageurl");
                            product_name=xx.getString("productname");
                            product_price=xx.getString("price");
                            image_url=xx.getString("imageurl");
                            product_currency=xx.getString("currency");
                            namestr=image_url;

                            NamePrds.add(product_name.toString());
                            PricePrds.add(product_price.toString());
                            ImagePrds.add(image_url.toString());
                            ResultSet=ResultSet+1;
                        }
                    }

                    Log.d("NOMBRE",product_name);
                    Log.d("PRECIO",product_price);
                    Log.d("URL_IMAGEN",image_url);
                    Log.d("MONEDA",product_currency);

                    if(product_name.length()<3){
                        // producto no encontrado

                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progress.dismiss();
                                        new AlertDialog.Builder(IdentifyByUPCActivity.this)
                                                .setTitle("Producto no encontrado :(")
                                                .setMessage("No encontramos el producto, ingrese el nombre y buscaremos de nuevo")
                                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        // continue with delete
                                                    }
                                                })
                                                .setIcon(android.R.drawable.ic_dialog_alert)
                                                .show();
                                        try{
                                        allDebug.setText(resu);
                                        }

                                        catch (Exception jds) {
                                            Log.d("ERROR ",jds.toString());
                                        }
                                    }
                                });
                            }
                        },  10); // End of your timer code.
                    }
                    else{
                        //si hay producto






                    }
                }
                else{
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progress.dismiss();
                    new AlertDialog.Builder(IdentifyByUPCActivity.this)
                            .setTitle("No encontrado")
                            .setMessage("No hemos encontrado información del producto, ingrese el nombre del producto tal y como se muestra en la caja e intentaremos buscar más referencias")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                                }
                            });
                        }
                    },  10); // End of your timer code.



                }

            } catch (Exception jds) {
                Log.d("ERROR ",jds.toString());



                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                new AlertDialog.Builder(IdentifyByUPCActivity.this)
                                        .setTitle("Error ")
                                        .setMessage("Al parecer la conexión a internet presenta problemas, vamos a intentar nuevamente.")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // continue with delete
                                            }
                                        })

                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();



                            }
                        });
                    }
                },  10); // End of your timer code.



            }

if(product_currency.equals("USD") || product_currency.equals("N/A")){ // si es dolar o no aplia
}
else{
    otherCurrency=product_currency;
}




// convertir si no es USD

//http://api.fixer.io/latest?
// symbols=USD,GBP&
// base=USD
if(otherCurrency.length()>0){

    String url_selectCurrency = myArticleUrlCurrency;
    ArrayList<NameValuePair> paramc = new ArrayList<NameValuePair>();
    paramc.add(new BasicNameValuePair("symbols", otherCurrency));
    paramc.add(new BasicNameValuePair("base", "USD"));


    try {
        String aresulting_json = null;
        ServiceHandler jsonParsera = new ServiceHandler();
        aresulting_json = jsonParsera.makeServiceCall(url_selectCurrency,
                ServiceHandler.GET, paramc);

        Log.d("Parametros",paramc.toString());
        Log.d("RESPUESTA currency",aresulting_json.toString());

        JSONObject ja = new JSONObject(aresulting_json);

        if (ja != null) {

            JSONObject jsonObjects=new JSONObject(aresulting_json);

            jsonObjects=jsonObjects.getJSONObject("rates");

            eRate=jsonObjects.getString(otherCurrency);


        }

    } catch (Exception jds) {
        Log.d("ERROR ",jds.toString());
    }


    Timer timera = new Timer();
    timera.schedule(new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Double converted=0.0;
                    converted = Double.parseDouble(product_price)/Double.parseDouble(eRate);
                        txtcurrency=(TextView)IdentifyByUPCActivity.this.findViewById(R.id.txtPrice);
                        txtcurrency.setText("Moneda (C): "+String.valueOf(converted));

                }
            });
        }
    },  10); // End of your timer code.
}


            return false;


        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            progress.dismiss();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(IdentifyByUPCActivity.this);
            upc=preferences.getString("UPC", "0");
            //upc2= "";



            if(ResultSet>1){//display lista

                TextView vtxtUPC =(TextView)IdentifyByUPCActivity.this.findViewById(R.id.txtUPC);
                ImageView vivProduct =(ImageView)IdentifyByUPCActivity.this.findViewById(R.id.ivProduct);
                TextView vtextView5 =(TextView)IdentifyByUPCActivity.this.findViewById(R.id.textView5);
                TextView vtxtPrice=(TextView)IdentifyByUPCActivity.this.findViewById(R.id.txtPrice);
                TextView vtxtName=(TextView)IdentifyByUPCActivity.this.findViewById(R.id.txtName);
                View vview2=(View)IdentifyByUPCActivity.this.findViewById(R.id.view2);
                View vview1=(View)IdentifyByUPCActivity.this.findViewById(R.id.view1);
                //TextView vtxtall=(TextView)IdentifyByUPCActivity.this.findViewById(R.id.txtall);

                vtxtUPC.setVisibility(View.GONE);
                vivProduct.setVisibility(View.GONE);
                vtextView5.setVisibility(View.GONE);
                vtxtPrice.setVisibility(View.GONE);
                vtxtName.setVisibility(View.GONE);
                vview1.setVisibility(View.GONE);
                vview2.setVisibility(View.GONE);
                //vtxtall.setVisibility(View.GONE);

                ListView listContent = (ListView)findViewById(R.id.contenttask);
                ma = new AdaptadorLista();
                listContent.setAdapter(ma);


                listContent.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View v, int position,
                                            long arg3)
                    {
                        String value = (String)adapter.getItemAtPosition(position);
                        Log.d("Click Click","Clicked: "+value.toString());
                    }
                });

            }
            else
            {//solo es un reporte

                Timer timerg = new Timer();
                timerg.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {




                                txtname=(TextView)IdentifyByUPCActivity.this.findViewById(R.id.txtName);
                                txtname.setText(""+product_name);

                                txtprice=(TextView)IdentifyByUPCActivity.this.findViewById(R.id.txtPrice);
                                txtprice.setText("Precio $: "+product_price);




                            }
                        });
                    }
                },  10);

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                IdentifyByUPCActivity.this.loadImage();

                            }
                        });
                    }
                },  1);
            }





        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            progress.dismiss();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(IdentifyByUPCActivity.this);
            upc=preferences.getString("UPC", "0");
            upc2= "";
        }
    }


    public void loadImage(){


        new LoadImage().execute(namestr);
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(IdentifyByUPCActivity.this);
            progress.setMessage("Cargando adjunto ....");
            progress.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {





            if(image != null){
                img.setImageBitmap(image);
                progress.dismiss();

            }else{

                progress.dismiss();
                Toast.makeText(IdentifyByUPCActivity.this, "El adjunto no se pudo cargar", Toast.LENGTH_SHORT).show();

            }
        }
    }





    class AdaptadorLista extends BaseAdapter implements CompoundButton.OnCheckedChangeListener
    {  private SparseBooleanArray mCheckStates;
        LayoutInflater mInflater;
        TextView lblnombreprofesor,lblprecio,lblfecha,txt_hora;
        TextDrawable drawable =null;
        ImageView image=null;


        AdaptadorLista()
        {
          //  mCheckStates = new SparseBooleanArray(optionsArray.size());
            mInflater = (LayoutInflater)IdentifyByUPCActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return NamePrds.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub

            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View vi=convertView;
            if(convertView==null)
                vi = mInflater.inflate(R.layout.prd_row, null);
            lblprecio= (TextView) vi.findViewById(R.id.title_row);
            lblfecha= (TextView) vi.findViewById(R.id.fecha);




            //Log.d("array ",names.toString());
            String title=NamePrds.get(position);
            String fecha_row=PricePrds.get(position);

            lblprecio.setText(String.valueOf(title));
            lblfecha.setText("$"+String.valueOf(fecha_row));








            return vi;
        }
        public boolean isChecked(int position) {
            //Toast.makeText(ContactsActivity.this, "checkeyadasasosasdasdasdasdsadasdsadsassss",1000).show();
            return mCheckStates.get(position, false);

        }

        public void setChecked(int position, boolean isChecked) {
            mCheckStates.put(position, isChecked);
            //Toast.makeText(ContactsActivity.this, "checkeyado",1000).show();
        }

        public void toggle(int position) {
            setChecked(position, !isChecked(position));
            //Toast.makeText(ContactsActivity.this, "checkeyadosasdhaks",1000).show();
        }
        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // TODO Auto-generated method stub
            //Toast.makeText(ContactsActivity.this, "checkeyadosssss",1000).show();



        }
    }
}