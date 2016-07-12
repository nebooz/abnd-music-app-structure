package com.abnd.mdiaz.musicappstructure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MusicSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_search);
    }

    public void leButtonToast(View view) {
        Toast.makeText(MusicSearch.this, "Que tal", Toast.LENGTH_LONG).show();
    }
}
