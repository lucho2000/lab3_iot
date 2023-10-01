package com.example.lab3_iot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3_iot.entity.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder>{

    private List<Result> listaContactos;
    private Context context;

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_rv1, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        Result result = listaContactos.get(position);

        holder.result = result;

        ImageView imageView = holder.itemView.findViewById(R.id.imageView3);
        String imageUrl = result.getPicture().getLarge();

        Picasso.get()
                .load(imageUrl)
                .into(imageView);


        TextView textViewNomApe = holder.itemView.findViewById(R.id.textViewNombreApellido);
        textViewNomApe.setText(result.getName().getFirst() + " " + result.getName().getLast());

        TextView textViewGenero = holder.itemView.findViewById(R.id.textViewGenero);
        textViewGenero.setText("Genero: " + result.getGender());

        TextView textViewCity = holder.itemView.findViewById(R.id.textViewCiudad);
        textViewCity.setText("Ciudad: " + result.getLocation().getCity());

        TextView textViewPais = holder.itemView.findViewById(R.id.textViewPais);
        textViewPais.setText("Pais: " + result.getLocation().getCountry());

        TextView textViewEmail = holder.itemView.findViewById(R.id.textViewEmail);
        textViewEmail.setText("Email: " + result.getEmail());

        TextView textViewPhone = holder.itemView.findViewById(R.id.textViewPhone);
        textViewPhone.setText("phone: " + result.getPhone());


    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }


    public class ResultViewHolder extends RecyclerView.ViewHolder{

        Result result;
        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public List<Result> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(List<Result> listaContactos) {
        this.listaContactos = listaContactos;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
