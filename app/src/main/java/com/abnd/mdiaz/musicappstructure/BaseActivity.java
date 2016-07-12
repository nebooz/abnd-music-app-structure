package com.abnd.mdiaz.musicappstructure;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
        mActivityTitle = getTitle().toString();
        layers = getResources().getStringArray(R.array.layers_array);

        addDrawerItems(originalPosition);
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void addDrawerItems(final int originalPosition) {
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, R.layout.nav_list_item, layers);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String classNameStorage = this.getClass().getName();

                if (originalPosition == position) {
                    Toast.makeText(getApplicationContext(), R.string.current_activity_toast_text, Toast.LENGTH_SHORT).show();
                } else {

                    switch (position) {
                        case 0:
                            startActivity(new Intent(getApplicationContext(), MusicSearch.class));
                            finish();
                            break;
                        case 1:
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                            break;
                        default:
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                            break;
                    }
                }

            }
        });
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
}
