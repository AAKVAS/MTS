package com.example.mts.modules.presentation.view;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mts.modules.presentation.presenter.ModulesActivityPresenter;

/**
 * ViewHolder модуля.
 */
public class ModulesViewHolder extends RecyclerView.ViewHolder {

    /**
     * Представление конкретного модуля в списке.
     */
    private CardView cardView;

    /**
     * Представитель активности "Модули".
     */
    private ModulesActivityPresenter presenter;

    /**
     * Конструктор класса ModulesViewHolder.
     * @param view представление элемента коллекции.
     */
    public ModulesViewHolder(CardView view) {
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
