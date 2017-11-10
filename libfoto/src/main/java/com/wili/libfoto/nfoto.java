package com.wili.libfoto;

import android.app.Activity;
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
import android.app.Activity;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import java.io.File;

public class nfoto {

    private ImageView imageHolder;
    private final int requestCode = 1;
    String path_photo = "";
    Uri ImageCaptureUri;
    Bitmap bitmap;
    Context context;
    private static final String KEY_LAST_CAMERA_PHOTO="";
    static File file;
    static Uri fileUri;
    final int RC_TAKE_PHOTO = 1;

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