package com.abnd.mdiaz.musicappstructure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class LiveActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        super.onCreateDrawer(2);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.button_set_container);
        View buttonView = getLayoutInflater().inflate(R.layout.button_setup, null);

        buttonSetup(buttonView);
        mainLayout.addView(buttonView);
    }

    private void buttonSetup(View buttonView) {
        ImageButton buttonA = (ImageButton) buttonView.findViewById(R.id.imageButton_A);
        ImageButton buttonB = (ImageButton) buttonView.findViewById(R.id.imageButton_B);
        ImageButton buttonC = (ImageButton) buttonView.findViewById(R.id.imageButton_C);

        addButtonListener(buttonA, SearchActivity.class);
        addButtonListener(buttonB, MainActivity.class);
        addButtonListener(buttonC, ExploreActivity.class);

        buttonA.setImageResource(R.drawable.search);
        buttonB.setImageResource(R.drawable.w_new);
        buttonC.setImageResource(R.drawable.explore);
    }

    private void addButtonListener(ImageButton button, final Class inputClass) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LiveActivity.this, inputClass);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });
    }

}