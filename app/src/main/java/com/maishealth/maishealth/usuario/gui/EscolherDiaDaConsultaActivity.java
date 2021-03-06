package com.maishealth.maishealth.usuario.gui;

//import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.maishealth.maishealth.R;

public class EscolherDiaDaConsultaActivity extends AppCompatActivity {

    //isso aq dos nomes e descricoes foram feitas para os testes, mas vcs q tem q adptar para na vdd
    //vir os nomes dos medicos
    int[] IMAGES ={R.drawable.ic_access_time_black_34dp};
    String[] NAMES = {"Doutora Kimbelly Pediatra","GANDHI","CopiChand"};
    String[] DESCRIPTION={"Data 16/03/2018","Data 17/08/2019","Data 23/05/2021"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_dia_da_consulta);

        ListView listView= findViewById(R.id.listView);
        CustomAdapter customAdapter=new CustomAdapter();

        listView.setAdapter(customAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            //return 0;
            return NAMES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        //@SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view= getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView_name= view.findViewById(R.id.textView_name);
            TextView textView_description= view.findViewById(R.id.textView_descriptions);

            //imageView.setImageResource(IMAGES[i]);
            textView_name.setText(NAMES[i]);
            textView_description.setText(DESCRIPTION[i]);

            return view;
        }
    }
    
    public void mudarTela(Class tela){
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    
    @Override
    public void onBackPressed() {
        this.mudarTela(MarcacaoSintomasPacActivity.class);
    }
    
    //nessa classe abaixo era bom vcs olharem o ciclo de activitys para qnd voltar para a tela de Marcar sintoma ela ficar com oq
    //ja foi colocado anteriormente- talvez tenham que mexer na Activity Marcacao de sintoma para fazer isso tbm
    public void voltarParaSintomasCheckbox(View view){
        this.mudarTela(MarcacaoSintomasPacActivity.class);
    }
    //Falta um método ou adptar o de baixo para pegar o dia escolhido do listView para dps ir para tela de info para consulta/confirmacao
    //Falta usar o on click(?!) hm
    public void telaConfirmarConsulta(View view){
        this.mudarTela(ConfirmarConsulta.class);
        
    }

}
