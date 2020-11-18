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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.Toast;

import com.ftninformatika.android30.MainActivity;
import com.ftninformatika.android30.R;
import com.ftninformatika.android30.model.Glumac;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListGlumciFragment extends Fragment {

    private ListView lvGlumci;
    private OnClickListener listener;

    private ListAdapter adapter;
    private List<Glumac> glumci;

    public ListGlumciFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_glumci, container, false);

        setHasOptionsMenu(true);
        //
        lvGlumci = view.findViewById(R.id.listGlumci);
        //obvezno pozovemo interface, kako bi nam otvorio sledeci, drugi prozor
        lvGlumci.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listener.onGlumacClicked((Glumac) adapter.getItem(i));
            }
        });
             //Bez adaptera nas sacuvane vrednosti uopste ne bi bile vidljive iz baze na samoj aplikaciji
            setAdapter();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setAdapter() {
        try {
            glumci = new ArrayList<>();
            //getDatabaseHelper kreirati prvo u Main-u!!!
            glumci = ((MainActivity) getActivity()).getDatabaseHelper().getGlumacDao().queryForAll();

            if (glumci.size() != 0) {
                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, glumci);
                lvGlumci.setAdapter(adapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.list_glumci_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                addItem();
                break;
            case R.id.action_settings:
                listener.onSettingsClicked();
                break;
            case R.id.action_about:
                listener.onAboutClicked();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addItem() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.add_glumci_dialog);

        final EditText etIme = dialog.findViewById(R.id.editText_Ime);
        final EditText etPrezime = dialog.findViewById(R.id.editText_Prezime);
        final EditText etBio = dialog.findViewById(R.id.editText_Bio);
        final RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
       // final EditText etDatumRodjenja = dialog.findViewById(R.id.editText_Datum);

        Button button = dialog.findViewById(R.id.button_Dodaj_Glumca);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Glumac glumac = new Glumac();
                    glumac.setIme(etIme.getText().toString());
                    glumac.setPrezime(etPrezime.getText().toString());
                    glumac.setBiografija(etBio.getText().toString());
                    glumac.setOcena(ratingBar.getRating());
                    //glumac.setDatumRodjenja(etDatumRodjenja.getText().toString());
                    //glumac.setFilmovi("");
                    ((MainActivity) getActivity()).getDatabaseHelper().getGlumacDao().create(glumac);

                    dialog.dismiss();
                    //setAdapter mora biti i ovde postavljen, ukoliko bismo zeleli da, kada stisnemo na dugme Unesi glumca,
                    //odmah vidimo taj unos. U slucaju da to ne uradimo, ne bismo videli, nego tek kada klikom na prethodnu sacuvanu
                    //vrednost udjemo i izadjemo iz tog fragmenta, tek onda bismo videli sacuvane nove vrednost1!
                    setAdapter();

                    Toast.makeText(getActivity(), "Glumac uspesno upisan u tabeli", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(getActivity(), "Glumac nije uspesno upisan u tabeli", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickListener) {
            listener = (OnClickListener) context;
        } else {
            Toast.makeText(getActivity(), "Morate implementirati interface", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnClickListener {
        void onGlumacClicked(Glumac glumac);

        void onSettingsClicked();

        void onAboutClicked();
    }
}

