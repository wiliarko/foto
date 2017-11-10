package com.wili.foto;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore.MediaColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wili.libfoto.nfoto;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView imageHolder;
    private final int requestCode = 1;
    String path_photo = "";
    Bitmap bitmap;
    final nfoto foto = new nfoto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageHolder = (ImageView)findViewById(R.id.image_view_image);
        Button capturedImageButton = (Button)findViewById(R.id.btn_take_image);
        capturedImageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foto.runfoto(MainActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("berhasil a");
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){
            System.out.println("berhasil");
            imageHolder.setImageURI(foto.url());

        }
    }
}