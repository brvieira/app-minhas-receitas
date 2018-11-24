package com.bruno.minhasreceitas.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bruno.minhasreceitas.R;
import com.bruno.minhasreceitas.model.Categoria;

import java.util.List;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.CategoriasViewHolder> {
    private List<Categoria> listaCategorias;
    OnClickListener mClickListener;

    public interface OnClickListener {
        void onClickItem(Categoria categoria);
    }

    public CategoriasAdapter(OnClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    @NonNull
    @Override
    public CategoriasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.category_button_layout, viewGroup, false);

        return new CategoriasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasViewHolder categoriasViewHolder, int i) {
        Categoria categoria = listaCategorias.get(i);

        categoriasViewHolder.bind(categoria);
    }

    @Override
    public int getItemCount() {
        if(listaCategorias != null) {
            return listaCategorias.size();
        }

        return 0;
    }

    public void setListaCategorias(List<Categoria> lista){
        listaCategorias = lista;
        this.notifyDataSetChanged();
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    class CategoriasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Button mCategoryButton;

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Categoria categoria = listaCategorias.get(position);
            mClickListener.onClickItem(categoria);
        }

        public CategoriasViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mCategoryButton = itemView.findViewById(R.id.mCategoryB);
        }

        public void bind(Categoria categoria) {
            mCategoryButton.setText(categoria.getNome());
        }

    }
}
