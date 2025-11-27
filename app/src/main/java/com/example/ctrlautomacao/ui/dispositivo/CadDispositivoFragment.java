package com.example.ctrlautomacao.ui.dispositivo;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ctrlautomacao.R;
import com.example.ctrlautomacao.model.Ambiente;
import com.example.ctrlautomacao.model.Dispositivo;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CadDispositivoFragment extends Fragment implements View.OnClickListener {
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

        this.etEstado = (EditText) view.findViewById(R.id.etEstado);
        this.etGrupo = (EditText) view.findViewById(R.id.etGrupo);
        this.etTipo = (EditText) view.findViewById(R.id.etTipo);
        this.btSalvar = (Button) view.findViewById(R.id.btSalvar);
        //definindo o listener do botão
        this.btSalvar.setOnClickListener(this);
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
                                for (int pos = 0; pos < response.length(); pos++) {
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
            throw new RuntimeException(e);
        }
        //colocando nova request para fila de execução
        requestQueue.add(jsonArrayReq);
    }

    private void cadastrarDispositivo(Dispositivo dispositivo) throws JSONException {
        //requisição para o Rest Server
        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(
                Request.Method.POST,
                "http://10.0.2.2/php/cadDispositivo.php",
                dispositivo.toJsonObject(),
                response -> {
                    try {
                        //se o request não veio vazia
                        if (response != null) {
                            Context context = requireContext();
                            if (response.getBoolean("success")) {
                                //limpar campos da tela
                                this.etEstado.setText("");
                                this.etGrupo.setText("");
                                this.etTipo.setText("");
                                //primeiro item dos spinners
                                this.spAmbiente.setSelection(0);
                            }
                            //mostrando a mensagem que veio do JSON
                            Toast toast = Toast.makeText(
                                    view.getContext(),
                                    response.getString("message"),
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            //mostrar mensagem do response == null
                            Snackbar mensagem = Snackbar.make(
                                    view,
                                    "A consulta não retornou nada!",
                                    Snackbar.LENGTH_LONG);
                            mensagem.show();
                        }
                    } catch (Exception e) {
                        //mostrar mensagem da exception
                        Snackbar mensagem = Snackbar.make(
                                view,
                                "Ops!Problema com o arquivo JSON: " + e,
                                Snackbar.LENGTH_LONG);
                        mensagem.show();
                    }
                },
                error -> {
                    //mostrar mensagem que veio do servidor
                    Snackbar mensagem = Snackbar.make(
                            view,
                            "Ops! Houve um problema: " + error.toString(),
                            Snackbar.LENGTH_LONG);
                    mensagem.show();
                }
        );
        //colocando nova request para fila de execução
        requestQueue.add(jsonObjectReq);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btSalvar) {
            try {
                //instanciando objeto de negócio
                Dispositivo dispositivo = new Dispositivo();
                //populando objeto com dados da tela
                dispositivo.setEstado(this.etEstado.getText().toString());
                dispositivo.setGrupo(this.etGrupo.getText().toString());
                dispositivo.setTipo(this.etTipo.getText().toString());
                //objeto do item selecionado do Spinner
                int pos = this.spAmbiente.getSelectedItemPosition();
                Ambiente ambiente = (Ambiente) spAmbiente.getItemAtPosition(pos);
                dispositivo.setIdAmbiente(ambiente.getIdAmbiente());
                //chamada do web service de cadastro
                try {
                    cadastrarDispositivo(dispositivo);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}