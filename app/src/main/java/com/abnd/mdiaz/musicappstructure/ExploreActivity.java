package com.abnd.mdiaz.musicappstructure;

import android.os.Bundle;

public class ExploreActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        super.onCreateDrawer(3);
    }
}
