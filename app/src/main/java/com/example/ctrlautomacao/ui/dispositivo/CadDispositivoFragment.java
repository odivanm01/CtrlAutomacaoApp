package com.example.ctrlautomacao.ui.dispositivo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ctrlautomacao.R;

public class CadDispositivoFragment extends Fragment {
    private EditText etEstado;
    private EditText etGrupo;
    private EditText etTipo;
    private Spinner spAmbiente;
    private Button btSalvar;

    public CadDispositivoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cad_dispositivo, container, false);
    }
}