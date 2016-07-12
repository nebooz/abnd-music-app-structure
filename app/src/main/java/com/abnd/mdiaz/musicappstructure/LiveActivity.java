package com.abnd.mdiaz.musicappstructure;

import android.os.Bundle;

public class LiveActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        super.onCreateDrawer(2);
    }
}
