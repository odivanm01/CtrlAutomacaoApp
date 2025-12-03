package com.example.ctrlautomacao.ui.dispositivo;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ctrlautomacao.databinding.FragmentConDispositivoBinding;
import com.example.ctrlautomacao.model.Dispositivo;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Dispositivo}.
 * TODO: Replace the implementation with code for your data type.
 */
public class DispositivoRecyclerViewAdapter extends RecyclerView.Adapter<DispositivoRecyclerViewAdapter.ViewHolder> {

    private final List<Dispositivo> mValues;

    public DispositivoRecyclerViewAdapter(List<Dispositivo> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentConDispositivoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getTipo());
        String info = mValues.get(position).getGrupo() + " - " + mValues.get(position).getEstado();
        holder.mContentView.setText(info);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Dispositivo mItem;

        public ViewHolder(FragmentConDispositivoBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}