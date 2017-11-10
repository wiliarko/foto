package com.wili.libfoto;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

public class nfoto {

    static File file;
    static Uri fileUri;

    public static void runfoto(Activity activity){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        file = new File(activity.getExternalCacheDir(),
                String.valueOf(System.currentTimeMillis()) + ".jpg");
        fileUri = Uri.fromFile(file);
        System.out.println("ini apa ; "+fileUri);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        activity.startActivityForResult(intent,1);
    }

    public Uri url(){
        return fileUri;
    }

}