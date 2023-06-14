package com.example.mts.connectedEquipment.presentation.view.adapters;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mts.connectedEquipment.presentation.presenter.ConnectedEquipmentListPresenter;

/**
 * ViewHolder подключённого оборудования.
 */
public class ConnectedEquipmentViewHolder extends RecyclerView.ViewHolder {

    /**
     * Представление конкретного подключённого оборудования в списке.
     */
    private CardView cardView;

    /**
     * Представитель активности подключённого оборудования.
     */
    private ConnectedEquipmentListPresenter presenter;

    /**
     * Конструктор класса ModulesViewHolder.
     * @param view представление элемента коллекции.
     */
    public ConnectedEquipmentViewHolder(CardView view) {
        super(view);
        cardView = view;
    }

    /**
     * Возвращает представление модуля в коллекции.
     * @return представление
     */
    public CardView getCardView() {
        return cardView;
    }
}
