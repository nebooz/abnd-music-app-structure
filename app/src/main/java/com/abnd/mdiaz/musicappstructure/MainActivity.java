package com.abnd.mdiaz.musicappstructure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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

        //Sure, let's add pointless buttons. Identify main layout and add a new inflated view.
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.button_set_container);
        View buttonView = getLayoutInflater().inflate(R.layout.button_setup, null);

        buttonSetup(buttonView);
        mainLayout.addView(buttonView);
    }

    private void buttonSetup(View buttonView) {
        //Get all 3 buttons within the view and assign them.
        ImageButton buttonA = (ImageButton) buttonView.findViewById(R.id.imageButton_A);
        ImageButton buttonB = (ImageButton) buttonView.findViewById(R.id.imageButton_B);
        ImageButton buttonC = (ImageButton) buttonView.findViewById(R.id.imageButton_C);

        //Add the listeners in another method to reduce some lines of code.
        addButtonListener(buttonA, SearchActivity.class);
        addButtonListener(buttonB, LiveActivity.class);
        addButtonListener(buttonC, ExploreActivity.class);

        //Each activity has a different set of icons. All but itself basically.
        buttonA.setImageResource(R.drawable.search);
        buttonB.setImageResource(R.drawable.live);
        buttonC.setImageResource(R.drawable.explore);
    }

    private void addButtonListener(ImageButton button, final Class inputClass) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, inputClass);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });
    }

}