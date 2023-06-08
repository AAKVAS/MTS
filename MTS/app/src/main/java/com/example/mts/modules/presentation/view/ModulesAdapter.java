package com.example.mts.modules.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
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
     * Интерфейс слушателя события нажатия на элемент списка.
     */
    interface OnClickListener {
        /**
         * Возникает при нажатии на элемент списка.
         * @param module модуль, на который произошло нажатие.
         * @param position позиция нажатого модуля.
         */
        void onClick(Module module, int position);
    }

    /**
     * Слушатель события нажатия на элемент списка.
     */
    private final OnClickListener onClickListener;
    /**
     * Коллекция модулей, для которой создан адаптер.
     */
    private List<Module> moduleList;

    /**
     * Конструктор класса ModulesAdapter.
     * @param moduleList коллекция модулей.
     * @param onClickListener слушатель события onClick.
     */
    public ModulesAdapter(List<Module> moduleList, OnClickListener onClickListener) {
        this.moduleList = moduleList;
        this.onClickListener = onClickListener;
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
        Module module = moduleList.get(position);

        CardView cardView = holder.getCardView();
        TextView textView = (TextView) cardView.findViewById(R.id.text_card);
        textView.setText(module.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(module, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }
}
