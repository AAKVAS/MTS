package com.example.mts.connectedEquipment.presentation.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mts.R;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;

import java.util.List;

/**
 * Адаптер для списка подключённого оборудования.
 */
public class ConnectedEquipmentAdapter extends RecyclerView.Adapter<ConnectedEquipmentViewHolder> {

    /**
     * Интерфейс слушателя события нажатия на элемент списка.
     */
    public interface OnClickListener {
        /**
         * Возникает при нажатии на элемент списка.
         * @param connectedEquipment элемент, на который произошло нажатие.
         * @param position позиция нажатого элемента.
         */
        void onClick(ConnectedEquipment connectedEquipment, int position);

        /**
         * Возникает при долгом нажатии на элемент списка.
         * @param connectedEquipment элемент, на который произошло нажатие.
         * @param position позиция нажатого элемента.
         * @return
         */
        void onLongClick(ConnectedEquipment connectedEquipment, int position);
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
     * Конструктор класса ConnectedEquipmentAdapter.
     * @param connectedEquipments список подключённого оборудования.
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
                .inflate(R.layout.card_view_connected_equipment, parent, false);
        return new ConnectedEquipmentViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConnectedEquipmentViewHolder holder, int position) {
        ConnectedEquipment connectedEquipment = connectedEquipments.get(position);

        CardView cardView = holder.getCardView();
        setCardTitle(cardView, connectedEquipment);
        setCardSubtitle(cardView, connectedEquipment);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(connectedEquipment, position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClickListener.onLongClick(connectedEquipment, position);
                return true;
            }
        });
    }

    /**
     * Устанавливает заголовок в строке о подключённом оборудовании.
     * @param cardView представление строки.
     * @param connectedEquipment исходная запись о подключённом оборудовании.
     */
    private void setCardTitle(CardView cardView, ConnectedEquipment connectedEquipment) {
        String title = connectedEquipment.getSwitchboard().getBuilding().getAddress()
                + ".\nИнв. номер: " + connectedEquipment.getSwitchboard().getInventoryNumber()
                + ". Модель: " + connectedEquipment.getSwitchboard().getModel().getName()
                + ".\nПорт: " + connectedEquipment.getPortNumber();
        TextView textView = (TextView) cardView.findViewById(R.id.title);
        textView.setText(title);
    }

    /**
     * Устанавливает подзаголовок в строке о подключённом оборудовании.
     * @param cardView представление строки.
     * @param connectedEquipment исходная запись о подключённом оборудовании.
     */
    private void setCardSubtitle(CardView cardView, ConnectedEquipment connectedEquipment) {
        String subtitle = "IP: " + connectedEquipment.getIp() + "\nMAC: " + connectedEquipment.getMac();
        TextView textView = (TextView) cardView.findViewById(R.id.subtitle);
        textView.setText(subtitle);
    }

    @Override
    public int getItemCount() {
        return connectedEquipments.size();
    }
}