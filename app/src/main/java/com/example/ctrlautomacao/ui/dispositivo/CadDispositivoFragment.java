package com.example.ctrlautomacao.ui.dispositivo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ctrlautomacao.R;
import com.example.ctrlautomacao.model.Ambiente;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CadDispositivoFragment extends Fragment {
    private EditText etEstado;
    private EditText etGrupo;
    private EditText etTipo;
    private Spinner spAmbiente;
    private Button btSalvar;
    private RequestQueue requestQueue;
    private View view;


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
        this.view = inflater.inflate(R.layout.fragment_cad_dispositivo, container, false);

        //spinner
        this.spAmbiente = (Spinner) view.findViewById(R.id.spAmbiente);

        //instanciando a fila de requests - caso o objeto seja o view
        this.requestQueue = Volley.newRequestQueue(view.getContext());
        //inicializando a fila de requests do SO
        this.requestQueue.start();

        try {
            this.consultaAmbiente();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        //return default
        return this.view;
    }
    private void consultaAmbiente() throws JSONException {
        //requisição para o Rest Server
        JsonArrayRequest jsonArrayReq = null;
        try {
            jsonArrayReq = new JsonArrayRequest(
                    Request.Method.POST,
                    "http://10.0.2.2/php/conAmbiente.php",
                    new JSONArray("[{}]"),
                    response -> {
                        try {
                            //se a consulta não veio vazia
                            if (response != null) {
                                //array list para receber a resposta
                                ArrayList<Ambiente> lista = new ArrayList<>();
                                //preenchendo ArrayList com JSONArray recebido
                                for (int pos = 0;pos < response.length() ;pos++){
                                    JSONObject jo = response.getJSONObject(pos);
                                    Ambiente ambiente = new Ambiente();
                                    ambiente.setIdAmbiente(jo.getInt("idAmbiente"));
                                    ambiente.setNmAmbiente(jo.getString("nmAmbiente"));
                                    lista.add(pos, ambiente);
                                }
                                //Criando um adapter para o spinner
                                ArrayAdapter<Ambiente> adapter = new ArrayAdapter<>(
                                        requireContext(),
                                        android.R.layout.simple_spinner_dropdown_item,
                                        lista);

                                //colocando o adapter no spinner
                                this.spAmbiente.setAdapter(adapter);
                            } else {
                                Snackbar mensagem = Snackbar.make(view,
                                        "A consulta não retornou nenhum registro!",
                                        Snackbar.LENGTH_LONG);
                                mensagem.show();
                            }
                        } catch (JSONException e) {
                            Snackbar mensagem = Snackbar.make(view,
                                    "Ops!Problema com o arquivo JSON: " + e,
                                    Snackbar.LENGTH_LONG);
                            mensagem.show();
                        }
                    },
                    error -> {
                        //mostrar mensagem que veio do servidor
                        Snackbar mensagem = Snackbar.make(view,
                                "Ops! Houve um problema ao realizar a consulta: " +
                                        error.toString(), Snackbar.LENGTH_LONG);
                        mensagem.show();
                    }
            );
        } catch (JSONException e) {
            throw new RuntimeException(e);}
        //colocando nova request para fila de execução
        requestQueue.add(jsonArrayReq);
    }

}