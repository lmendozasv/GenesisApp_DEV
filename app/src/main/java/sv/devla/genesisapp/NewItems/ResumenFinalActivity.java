package sv.devla.genesisapp.NewItems;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mvc.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;

import sv.devla.genesisapp.R;
import sv.devla.genesisapp.Utils.ImagePickerCustom;

public class ResumenFinalActivity extends AppCompatActivity {
    private ImageView imageView;
    Boolean isImageAttached=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_final);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResumenFinalActivity.this, PrintQRGenActivity.class);
                startActivity(i);
            }
        });

        fab.setImageResource(R.drawable.ic_print);
        this.setTitle("Revisión de datos");

        imageView = (ImageView) findViewById(R.id.imageView4);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResumenFinalActivity.this.onPickImage(ResumenFinalActivity.this.findViewById(R.id.imageView));
            }
        });



        ImagePicker.setMinQuality(600, 600);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String path="";

        Bitmap bitmap = ImagePickerCustom.getImageFromResult(this, requestCode, resultCode, data);

        String selectedImagePath = MediaStore.Images.Media.insertImage(
                getContentResolver(),
                bitmap,
                "title",
                "description"
        );

//        Log.d("path",selectedImagePath);

        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            imageView.setAlpha(200);



            /*test upload start*/
            try{

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                Log.d("paths",picturePath);
                path=picturePath;
                isImageAttached=true;
            }
            catch(Exception l){
                Log.d("paths",l.toString());
                // picture taken


                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri tempUri = getImageUri(getApplicationContext(), bitmap);

                // CALL THIS METHOD TO GET THE ACTUAL PATH
                File finalFile = new File(getRealPathFromURI(tempUri));

                path=finalFile.getPath().toString();
                Log.d("TOMADA",finalFile.getPath().toString());
                isImageAttached=true;
            }


            /*test upload end*/
        }
        // TODO do something with the bitmap

      //  uploadFilePath=path;
        String filename=path.substring(path.lastIndexOf("/")+1);
      //-  uploadFileName=filename;

    }


    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public void onPickImage(View view) {
        // Click on image button
        ImagePickerCustom.pickImage(this, "Agregar imagen/foto");


    }
}
