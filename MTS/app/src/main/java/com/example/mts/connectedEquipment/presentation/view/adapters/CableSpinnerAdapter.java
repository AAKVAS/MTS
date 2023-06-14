package com.example.mts.connectedEquipment.presentation.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mts.R;
import com.example.mts.connectedEquipment.domain.entity.Cable;

import java.util.List;

public class CableSpinnerAdapter extends ArrayAdapter<Cable> {

    public CableSpinnerAdapter(Context context, List<Cable> cables) {
        super(context, 0, cables);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Cable cable = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.base_spinner_item, parent, false);
        }

        TextView textView = view.findViewById(R.id.tv_spinner_item);
        textView.setText(cable.getName());

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Cable cable = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.base_spinner_item, parent, false);
        }

        TextView textView = view.findViewById(R.id.tv_spinner_item);
        textView.setText(cable.getName());

        return view;
    }
}
