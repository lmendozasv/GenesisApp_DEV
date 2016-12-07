package sv.devla.genesisapp.NewItems;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import sv.devla.genesisapp.R;

public class ScanCodeBarActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    SharedPreferences sp;
    SharedPreferences spdef;
    SharedPreferences.Editor spe;
    SharedPreferences.Editor spedef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code_bar);


        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();         // Start camera

    }

    public void QrScanner(View view){


        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();         // Start camera

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        Log.e("CODEBAR LEIDO", rawResult.getText()); // Prints scan results
        Log.e("CODEBAR FORMATEADO", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ScanCodeBarActivity.this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("UPC",rawResult.getText());
        Log.d("odebar",rawResult.getText());
        editor.apply();


        Intent i = new Intent(ScanCodeBarActivity.this, IdentifyByUPCActivity.class);
        startActivity(i);
        finish();

        // show the scanner result into dialog box.
    //    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    //    builder.setTitle("Scan Result");
    //    builder.setMessage(rawResult.getText());
    //    AlertDialog alert1 = builder.create();
    //    alert1.show();

        // If you would like to resume scanning, call this method below:
        // mScannerView.resumeCameraPreview(this);
    }
}
