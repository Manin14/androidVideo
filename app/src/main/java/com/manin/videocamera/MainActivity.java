package com.manin.videocamera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Bundle m=null;  //new
    Bitmap s=null; //new
    static final int permohonan_kamera=1;

    AppCompatButton btn,btnVideo=null;
    AppCompatImageView iv=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deklarasi();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ambilgambardaricamera();
            }
        });


        klikKeVideo();
    }



    private void klikKeVideo() {
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=new Intent(MainActivity.this, RekamVideo.class);
                startActivity(g);
            }
        });
    }

    private void deklarasi() {
        btn=findViewById(R.id.id_btn_foto);
        iv=findViewById(R.id.id_Image);

        btnVideo=findViewById(R.id.id_btn_video);
    }

    private void ambilgambardaricamera(){
        Intent a=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(a, permohonan_kamera);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==permohonan_kamera && resultCode==RESULT_OK){
            //Bundle
            m=data.getExtras();
           // Bitmap
             s=(Bitmap) m.get("data");  // data.getExtras().get("data");
            iv.setImageBitmap(s);
        }
    }



    //new
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBundle("bundle",m);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        m=savedInstanceState.getBundle("bundle");
        s=(Bitmap) m.get("data");
        iv.setImageBitmap(s);
    }
}
