package com.bruno.minhasreceitas.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bruno.minhasreceitas.R;
import com.bruno.minhasreceitas.model.Receita;

import java.util.List;

public class ReceitaAdapter extends  RecyclerView.Adapter<ReceitaAdapter.ReceitaViewHolder>{

    private List<Receita> listaReceitas;
    private OnClickListener mClickListener;

    public interface OnClickListener{
        void onClickItem(Receita receita);
    }

    public ReceitaAdapter(OnClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public void setListaReceitas(List<Receita> listaReceitas) {
        this.listaReceitas = listaReceitas;
        this.notifyDataSetChanged();
    }

    public List<Receita> getListaReceitas() {
        return listaReceitas;
    }

    @NonNull
    @Override
    public ReceitaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.activity_receita_layout, viewGroup, false);

        return new ReceitaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceitaViewHolder receitaViewHolder, int i) {
        Receita receita = listaReceitas.get(i);
        receitaViewHolder.bind(receita);
    }

    @Override
    public int getItemCount() {
        if(listaReceitas != null) {
            return listaReceitas.size();
        }

        return 0;
    }


    public class ReceitaViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {

        TextView tituloText;

        public ReceitaViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloText = itemView.findViewById(R.id.titulo_text);
            tituloText.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Receita receita = listaReceitas.get(position);
            mClickListener.onClickItem(receita);
        }

        public void bind(Receita receita) {
            tituloText.setText(receita.getTitulo());
        }
    }
}
