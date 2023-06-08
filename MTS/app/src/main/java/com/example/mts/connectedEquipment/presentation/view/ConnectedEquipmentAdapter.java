package com.example.mts.connectedEquipment.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mts.R;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.modules.domain.entity.Module;
import com.example.mts.modules.presentation.view.ModulesViewHolder;

import java.util.List;

/**
 * Адаптер для списка подключённого оборудования.
 */
public class ConnectedEquipmentAdapter extends RecyclerView.Adapter<ConnectedEquipmentViewHolder> {

    /**
     * Интерфейс слушателя события нажатия на элемент списка.
     */
    interface OnClickListener {
        /**
         * Возникает при нажатии на элемент списка.
         * @param connectedEquipment элемент, на который произошло нажатие.
         * @param position позиция нажатого модуля.
         */
        void onClick(ConnectedEquipment connectedEquipment, int position);
    }

    /**
     * Слушатель события нажатия на элемент списка.
     */
    private final OnClickListener onClickListener;
    /**
     * Коллекция, для которой создан адаптер.
     */
    private List<ConnectedEquipment> connectedEquipments;

    /**
     * Конструктор класса ModulesAdapter.
     * @param connectedEquipments подключённого оборудования.
     * @param onClickListener слушатель события onClick.
     */
    public ConnectedEquipmentAdapter(List<ConnectedEquipment> connectedEquipments, OnClickListener onClickListener) {
        this.connectedEquipments = connectedEquipments;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ConnectedEquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_module, parent, false);
        return new ConnectedEquipmentViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConnectedEquipmentViewHolder holder, int position) {
        ConnectedEquipment connectedEquipment = connectedEquipments.get(position);

        CardView cardView = holder.getCardView();
        TextView textView = (TextView) cardView.findViewById(R.id.text_card);
        textView.setText(connectedEquipment.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(connectedEquipment, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return connectedEquipments.size();
    }
}