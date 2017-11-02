package com.wili.libfoto;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class nfoto extends AppCompatActivity {

    private ImageView imageHolder;
    private final int requestCode = 1;
    String path_photo = "";
    Uri ImageCaptureUri;
    Bitmap bitmap;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        capturedImageButton.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                foto.berhasil();
//                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                startActivityForResult(photoCaptureIntent, requestCode);
//
//                File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"takePicture");
//                mediaStorageDir.mkdirs();
//                File mediaFile;
//                mediaFile = new File(mediaStorageDir.getPath() + File.separator+ "IMG_" + System.currentTimeMillis() + ".jpg");
//                path_photo = mediaFile.toString();
//
//                //cek pembuatan folder
//                if (!mediaStorageDir.exists()) {
//                    if (!mediaStorageDir.mkdirs()) {
//                        Toast.makeText(getApplicationContext(), "Oops! Failed create directory",Toast.LENGTH_LONG).show();
//                        mediaFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator+ "IMG_" + System.currentTimeMillis() + ".jpg");
//                        path_photo = mediaFile.toString();
//                    }
//                }
//                ImageCaptureUri = Uri.fromFile(mediaFile);
//                photoCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, ImageCaptureUri);
//                startActivityForResult(photoCaptureIntent, requestCode);
//            }
//        });
    }

    public void nfoto(Context context){
        this.context=context;
    }

    public void runfoto(){
//        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//        startActivity(intent);

//        Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        context.startActivityForResult(photoCaptureIntent, requestCode);
    }


    public void decodeFile(String filePath) {
        // Decode image size
        BitmapFactory.Options bm = new BitmapFactory.Options();
        bm.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, bm);
        final int REQUIRED_SIZE = 1024;
        int width_tmp = bm.outWidth,
                height_tmp = bm.outHeight;

        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        bitmap = BitmapFactory.decodeFile(filePath, o2);



        imageHolder.setImageBitmap(bitmap);
        imageHolder.getLayoutParams().height=600;
        imageHolder.getLayoutParams().width=600;
    }


    @SuppressWarnings("deprecation")
    public String getPath(Uri uri ) {
        String[] projection = { MediaColumns.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
            cursor.moveToFirst();
            path_photo = cursor.getString(column_index);
            return cursor.getString(column_index);
        } else
            return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){
//            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
//            imageHolder.setImageBitmap(bitmap);

            System.out.println("FILE PATH FROM CAMERA: "+path_photo);
            decodeFile(path_photo.toString());
        }
    }
}