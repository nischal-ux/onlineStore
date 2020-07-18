package com.example.shamsonlinemark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

public class Home extends Fragment {
DatabaseHelper databaseHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home,null);
      GridView cont=view.findViewById(R.id.cont1);
//        SearchView seach=view.findViewById(R.id.search);
        databaseHelper=new DatabaseHelper(getContext());
         Gridview gridview=new Gridview(getContext(),databaseHelper.getStorelist());
        cont.setAdapter(gridview);
        return view;
    }
}
