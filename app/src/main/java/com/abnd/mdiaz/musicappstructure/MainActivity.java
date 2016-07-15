package com.abnd.mdiaz.musicappstructure;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        I really don't like this way of initializing this stuff. Couldn't think of another way
        of doing it. I tried to get the current activity through some method, but I kept on
        getting the BaseActivity class back instead of the actual top activity class. This
        comment applies to all activity classes.
        */
        super.onCreateDrawer(1);
    }

}