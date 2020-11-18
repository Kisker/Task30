package com.ftninformatika.android30.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import androidx.fragment.app.Fragment;
import com.ftninformatika.android30.MainActivity;
import com.ftninformatika.android30.R;
import com.ftninformatika.android30.model.Glumac;

import java.sql.SQLException;


public class DetailsGlumciFragment extends Fragment {

    private Glumac glumac;
    private EditText etIme, etPrezime, etBio;
    private RatingBar ratingBar;

    public DetailsGlumciFragment() {
        // Required empty public constructor
    }

    public void setGlumac(Glumac glumac) {
        this.glumac = glumac;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_glumci, container, false);

        setHasOptionsMenu(true);

        etIme = view.findViewById(R.id.editText_Ime_Details);
        etPrezime = view.findViewById(R.id.editText_Prezime_Details);
        etBio = view.findViewById(R.id.editText_Bio_Details);
        ratingBar = view.findViewById(R.id.ratingBar_Details);

        etIme.setText(glumac.getIme());
        etPrezime.setText(glumac.getPrezime());
        etBio.setText(glumac.getBiografija());
        ratingBar.setRating(glumac.getOcena());

        return view;
    }

        @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.details_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                addFilm();
                break;
            case R.id.action_update:
                update();
                break;
            case R.id.action_delete:
                delete();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void addFilm() {
    }

    private void update() {
        glumac.setIme(etIme.getText().toString());
        glumac.setPrezime(etPrezime.getText().toString());
        glumac.setBiografija(etBio.getText().toString());
        glumac.setOcena(ratingBar.getRating());


        try {
            ((MainActivity) getActivity()).getDatabaseHelper().getGlumacDao().update(glumac);
            getActivity().onBackPressed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void delete() {
        glumac.setIme(etIme.getText().toString());
        glumac.setPrezime(etPrezime.getText().toString());
        glumac.setBiografija(etBio.getText().toString());
        glumac.setOcena(ratingBar.getRating());


        try {
            ((MainActivity) getActivity()).getDatabaseHelper().getGlumacDao().delete(glumac);
            getActivity().onBackPressed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

