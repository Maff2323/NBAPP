package com.iesfranciscodelosrios.informatica.nbapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iesfranciscodelosrios.informatica.nbapp.R;
import com.iesfranciscodelosrios.informatica.nbapp.models.PersonR;

import java.util.ArrayList;


public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder>implements View.OnClickListener {

        private ArrayList<PersonR> items;
        private View.OnClickListener listener;

        // Clase interna:
        // Se implementa el ViewHolder que se encargará
        // de almacenar la vista del elemento y sus datos
        public static class PersonViewHolder extends RecyclerView.ViewHolder {

            private TextView TextView_id;
            private TextView TextView_nEquipo;

            public PersonViewHolder(View itemView) {
                super(itemView);
                TextView_id = (TextView) itemView.findViewById(R.id.textViewNombre);
                TextView_nEquipo= (TextView) itemView.findViewById(R.id.textViewtSalarial);
            }

            public void PersonBind(PersonR item) {
                TextView_id.setText(item.getnEquipo().toString());
                TextView_nEquipo.setText(item.gettSalarial().toString());
            }
        }

        // Contruye el objeto adaptador recibiendo la lista de datos
    public PersonAdapter(@NonNull ArrayList<PersonR> items) {
            this.items = items;
        }

        // Se encarga de crear los nuevos objetos ViewHolder necesarios
        // para los elementos de la colección.
        // Infla la vista del layout, crea y devuelve el objeto ViewHolder
        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View row = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_data, parent, false);
            row.setOnClickListener(this);

            PersonViewHolder avh = new PersonViewHolder(row);
            return avh;
        }

        // Se encarga de actualizar los datos de un ViewHolder ya existente.
        @Override
        public void onBindViewHolder(PersonViewHolder viewHolder, int position) {
            PersonR item = items.get(position);
            viewHolder.PersonBind(item);
        }

        // Indica el número de elementos de la colección de datos.
        @Override
        public int getItemCount() {
            return items.size();
        }

        // Asigna un listener al elemento
        public void setOnClickListener(View.OnClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            if(listener != null)
                listener.onClick(view);
        }
    }

