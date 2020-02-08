package com.manin.videocamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class RekamVideo extends AppCompatActivity {

    static final int permohonan_video=1;

    AppCompatButton btnv=null;
    VideoView v=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekam_video);

        deklarsi();
        klikButtonnya();
    }




    private void klikButtonnya() {
        btnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ambilVideo();
            }
        });
    }

    private void deklarsi() {
        btnv=findViewById(R.id.id_btn_rekam_video);
        v=findViewById(R.id.id_VV);
    }

    private void ambilVideo(){
        Intent d=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (d.resolveActivity(getPackageManager())!=null);
        startActivityForResult(d,permohonan_video);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==permohonan_video && resultCode==RESULT_OK) {
            Uri h=data.getData();
            v.setVideoURI(h);

            v.setMediaController(new MediaController(this));
            v.start();
        }
    }



}
