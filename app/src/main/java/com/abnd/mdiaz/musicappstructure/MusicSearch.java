package com.abnd.mdiaz.musicappstructure;

import android.os.Bundle;

public class MusicSearch extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_search);
        super.onCreateDrawer(0);
    }

}
