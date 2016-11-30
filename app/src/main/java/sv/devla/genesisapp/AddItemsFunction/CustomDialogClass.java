package sv.devla.genesisapp.AddItemsFunction;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import sv.devla.genesisapp.R;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public Bitmap bm;
    public CustomDialogClass(Activity a) {
        super(a);
// TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.item_preview_dialog);
        yes = (Button) findViewById(R.id.btndismissdialog);

        yes.setOnClickListener(this);

        String titulo ="";
        String precio="";


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        titulo=preferences.getString("cTitulo", "");
        precio=preferences.getString("cPrecio", "");




        TextView tnombre = (TextView)this.findViewById(R.id.tvNombre);
        TextView tprecio = (TextView)this.findViewById(R.id.tvprecio);
        ImageView ivsavedphoto = (ImageView)this.findViewById(R.id.imgdialog);

        tnombre.setText(titulo);
        tprecio.setText(precio);
        ivsavedphoto.setImageBitmap(bm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btndismissdialog:
                c.finish();
                break;
            default:
                break;
        }
        dismiss();
    }

}