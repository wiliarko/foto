package com.wili.libfoto;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class nfoto {

    static File file;
    static Uri fileUri;

    public static void runfoto(Activity activity){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        file = new File(activity.getExternalCacheDir(),
                String.valueOf(System.currentTimeMillis()) + ".jpg");
        fileUri = Uri.fromFile(file);
        System.out.println("ini apa ; "+file.getPath());

//        Uri selectedImage = imageToUploadUri;
        activity.getContentResolver().notifyChange(fileUri, null);
        Bitmap reducedSizeBitmap = getBitmap(file.getPath(),activity);
//        if(reducedSizeBitmap != null){
//            imageview.setImageBitmap(reducedSizeBitmap);
//        }

//        Uri selectedImage = imageToUploadUri;
//        getContentResolver().notifyChange(selectedImage, null);
//        Bitmap reducedSizeBitmap = getBitmap(imageToUploadUri.getPath());
//        if(reducedSizeBitmap != null){
//            imageview.setImageBitmap(reducedSizeBitmap);
//        }else{
//            Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
//        }

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        activity.startActivityForResult(intent,1);
    }

    public String url(){
        return file.getPath();
    }

    private static Bitmap getBitmap(String path, Activity activity) {

        Uri uri = Uri.fromFile(new File(path));
        InputStream in = null;
        try {
            final int IMAGE_MAX_SIZE = 1200000; // 1.2MP
            in = activity.getContentResolver().openInputStream(uri);

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, o);
            in.close();


            int scale = 1;
            while ((o.outWidth * o.outHeight) * (1 / Math.pow(scale, 2)) >
                    IMAGE_MAX_SIZE) {
                scale++;
            }
            Log.d("", "scale = " + scale + ", orig-width: " + o.outWidth + ", orig-height: " + o.outHeight);

            Bitmap b = null;
            in = activity.getContentResolver().openInputStream(uri);
            if (scale > 1) {
                scale--;
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                o = new BitmapFactory.Options();
                o.inSampleSize = scale;
                b = BitmapFactory.decodeStream(in, null, o);

                // resize to desired dimensions
                int height = b.getHeight();
                int width = b.getWidth();

                double y = Math.sqrt(IMAGE_MAX_SIZE
                        / (((double) width) / height));
                double x = (y / height) * width;

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(b, (int) x,
                        (int) y, true);
                b.recycle();
                b = scaledBitmap;

                System.gc();
            } else {
                b = BitmapFactory.decodeStream(in);
            }
            in.close();

            return b;
        } catch (IOException e) {
            return null;
        }
    }
}