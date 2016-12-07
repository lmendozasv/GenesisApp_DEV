package sv.devla.genesisapp.NewItems;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import sv.devla.genesisapp.MainActivity;
import sv.devla.genesisapp.R;

public class PrintQRGenActivity extends AppCompatActivity {


    public final static String STR = "A string to be encoded as QR code";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_qrgen);

        ImageView imageView = (ImageView) findViewById(R.id.qrCode);


        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(STR, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ((ImageView) findViewById(R.id.qrCode)).setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }


        Button btnend =(Button)findViewById(R.id.btnend);


        btnend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inicio

                Intent i = new Intent(PrintQRGenActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }






}
