package com.example.ctrlautomacao.ui.dispositivo;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.ctrlautomacao.R;
import com.example.ctrlautomacao.model.Dispositivo;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class ConDispositivoFragment extends Fragment implements Response.ErrorListener, Response.Listener{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    //Lista que vai armazenar os objetos que retornam do Web Service
    private ArrayList<Dispositivo> usuarios;
    //Fila de requests da biblioteca Volley
    private RequestQueue requestQueue;
    //Objeto da biblioteca Volley que faz o request para o Web Service
    private JsonArrayRequest jsonArrayReq;
    //Objeto view que representa a tela utilizado em diversos metodos
    private View view;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ConDispositivoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ConDispositivoFragment newInstance(int columnCount) {
        ConDispositivoFragment fragment = new ConDispositivoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_con_dispositivo_list, container, false);


        return this.view;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {

    }
}