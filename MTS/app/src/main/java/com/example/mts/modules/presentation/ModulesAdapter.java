package com.example.mts.modules.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mts.R;
import com.example.mts.modules.domain.entity.Module;

import java.util.List;

/**
 * Адаптер для списка модулей.
 */
public class ModulesAdapter extends RecyclerView.Adapter<ModulesViewHolder> {

    /**
     * Коллекция модулей, для которой создан адаптер.
     */
    private List<Module> moduleList;

    /**
     * Конструктор класса ModulesAdapter.
     * @param moduleList коллекция модулей.
     */
    public ModulesAdapter(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    @NonNull
    @Override
    public ModulesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_module, parent, false);
        return new ModulesViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ModulesViewHolder holder, int position) {
        CardView cardView = holder.getCardView();
        TextView textView = (TextView) cardView.findViewById(R.id.text_card);
        textView.setText(moduleList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }
}
