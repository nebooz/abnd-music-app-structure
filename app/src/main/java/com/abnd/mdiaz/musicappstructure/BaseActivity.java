package com.abnd.mdiaz.musicappstructure;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    public DrawerLayout mDrawerLayout;
    public ListView mDrawerList;
    public String[] layers;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    protected void onCreateDrawer(int originalPosition) {

        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // R.id.drawer_layout should be in every activity with exactly the same id.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Replace the title with the label defined in the manifest per activity.
        mActivityTitle = getTitle().toString();

        //Fills the list with the small array of button/options.
        layers = getResources().getStringArray(R.array.layers_array);

        /*
        Still not liking this. I'm passing this int to the method just to know who actually
        is using it!
        */
        addDrawerItems(originalPosition);
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    /* Not entirely sure why the original Position value has to be final...
    All I wanted was to add an icon next to each entry on the list... I had to go ahead and
    make a new custom adapter class! Couldn't find any other way of handling that. Luckily,
    I did the same in the previous exercise. */
    private void addDrawerItems(final int originalPosition) {
        ArrayAdapter<String> mAdapter = new CustomListAdapter();
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;

                if (originalPosition == position) {

                    Toast.makeText(getApplicationContext(), R.string.current_activity_toast_text, Toast.LENGTH_SHORT).show();

                } else {

                    switch (position) {
                        case 0:
                            startAppActivities(SearchActivity.class);
                            break;
                        case 1:
                            startAppActivities(MainActivity.class);
                            break;
                        case 2:
                            startAppActivities(LiveActivity.class);
                            break;
                        case 3:
                            startAppActivities(ExploreActivity.class);
                            break;
                        default:
                            startAppActivities(MainActivity.class);
                            break;
                    }
                }
            }
        });
    }

    private void startAppActivities(Class inputClass) {
        Intent i = new Intent(getApplicationContext(), inputClass);

        /*
        This flag prevents the endless creation of existing activities.
        There is no real reason to remove them in this app, so... with 6 activities at most,
        It would be a maximum of 6 back button taps to leave the app.
        */
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        /*
        On click the activity is up, but, since it is no recreated because of the flag,
        the drawer is open when switching activities and looks super weird.
        This fixes that, but I think the drawer is not entirely closed before it initiates
        the next activity.
        */
        mDrawerLayout.closeDrawers();
        startActivity(i);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.nav_title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Activate the navigation drawer toggle
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class CustomListAdapter extends ArrayAdapter<String> {
        public CustomListAdapter() {
            super(getApplicationContext(), R.layout.nav_list_item, layers);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //Should use a View Holder but it is just 4 entries! (maybe 6 tops)
            convertView = getLayoutInflater().inflate(R.layout.nav_list_item, parent, false);

            ImageView holderImageView = (ImageView) convertView.findViewById(R.id.list_item_icon);
            TextView holderTextView = (TextView) convertView.findViewById(R.id.list_item_text);


            /*
            In my previous project I created an object to hold a bunch of different properties
            for each question. In this one case, I only have 2 things, the image and the text...
            I should probably have made an object anyway, but I thought that even on a real app
            the amount of options on the menu wouldn't be a as dynamic as adding or removing
            questions from a quiz app.
            */
            switch (position) {
                case 0:
                    holderTextView.setText(layers[position]);
                    holderImageView.setImageResource(R.drawable.search);
                    break;
                case 1:
                    holderTextView.setText(layers[position]);
                    holderImageView.setImageResource(R.drawable.w_new);
                    break;
                case 2:
                    holderTextView.setText(layers[position]);
                    holderImageView.setImageResource(R.drawable.live);
                    break;
                case 3:
                    holderTextView.setText(layers[position]);
                    holderImageView.setImageResource(R.drawable.explore);
                    break;
                default:
                    holderTextView.setText(layers[position]);
                    holderImageView.setImageResource(R.drawable.search);
                    break;
            }

            return convertView;
        }
    }
}
