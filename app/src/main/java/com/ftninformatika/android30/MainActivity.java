package com.ftninformatika.android30;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.ftninformatika.android30.Fragments.DetailsGlumciFragment;
import com.ftninformatika.android30.Fragments.ListGlumciFragment;
import com.ftninformatika.android30.Fragments.SettingsFragment;
import com.ftninformatika.android30.db.DatabaseHelper;
import com.ftninformatika.android30.dialog.About;
import com.ftninformatika.android30.model.Glumac;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public class MainActivity extends AppCompatActivity implements ListGlumciFragment.OnClickListener {
    DatabaseHelper databaseHelper;
    private boolean listShown = false;
    private boolean detailsShown = false;
    private boolean settingsShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showListGlumciFragment();

    }

    private void showListGlumciFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ListGlumciFragment listGlumciFragment = new ListGlumciFragment();
        transaction.replace(R.id.root, listGlumciFragment);
        transaction.commit();

        listShown = true;
        detailsShown = false;

    }

    private void showDetailsFragment(Glumac glumac) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetailsGlumciFragment detailsGlumciFragment = new DetailsGlumciFragment();
        detailsGlumciFragment.setGlumac(glumac);
        transaction.replace(R.id.root, detailsGlumciFragment);
        transaction.commit();

        listShown = false;
        detailsShown = true;

    }

    private void showSettingsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SettingsFragment settingsFragment = new SettingsFragment();
        transaction.replace(R.id.root, settingsFragment);
        transaction.commit();

        listShown = false;
        detailsShown = false;
        settingsShown = true;
    }
    private void showAboutDialog() {
        AlertDialog dialog = new About(this).prepareDialog();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (listShown) {
            finish();
        } else if (detailsShown) {
            showListGlumciFragment();
            listShown = true;
            detailsShown = false;
            settingsShown = false;
        }  else if (settingsShown) {
            showListGlumciFragment();
            listShown = true;
            detailsShown = false;
            settingsShown = false;
        }
    }



    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper== null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onGlumacClicked(Glumac glumac) {
        showDetailsFragment(glumac);
    }

    @Override
    public void onSettingsClicked() {
        showSettingsFragment();

    }

    @Override
    public void onAboutClicked() {
        showAboutDialog();


    }
}