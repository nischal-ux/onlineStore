package com.example.shamsonlinemark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CartSystem extends Fragment {

    DatabaseHelper databaseHelper;
    ListView cont;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cartsystem,null);
      cont=view.findViewById(R.id.box);

        databaseHelper=new DatabaseHelper(getContext());
        cartAdapter gridview=new cartAdapter(getContext(),databaseHelper.getcartlist());
        cont.setAdapter(gridview);


        return view;
    }



    @Override
    public void onResume() {
        super.onResume();

        databaseHelper=new DatabaseHelper(getContext());
        cartAdapter gridview=new cartAdapter(getContext(),databaseHelper.getcartlist());
        cont.setAdapter(gridview);

    }
}
