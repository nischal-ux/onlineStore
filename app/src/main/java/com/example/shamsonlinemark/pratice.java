package com.example.shamsonlinemark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class pratice extends Fragment {
    DatabaseHelper databaseHelper;
SearchView search;
    public static ArrayList<store> employeeArrayList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail,null);
        final GridView cont=view.findViewById(R.id.cont);
        search=view.findViewById(R.id.search1);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<store> result=new ArrayList<>();


                for(store n :employeeArrayList){



                        if(n.getName().contains(newText))

                            result.add(n);
                }

                ((Gridview)cont.getAdapter()).update(result);
                return true;
            }
        });
        databaseHelper=new DatabaseHelper(getContext());
        Gridview gridview=new Gridview(getContext(),databaseHelper.getStorelist());
        cont.setAdapter(gridview);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
