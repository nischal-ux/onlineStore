package com.example.shamsonlinemark;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Gridview extends ArrayAdapter<store> {
Context context;
    List<store> list;
    DatabaseHelper databaseHelper;
    public Gridview(@NonNull Context context, ArrayList<store> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.item,null);
        final store Store=getItem(position);
        TextView id=view.findViewById(R.id.id);
        TextView category=view.findViewById(R.id.cat);
        final TextView name=view.findViewById(R.id.name);
        ImageView image=view.findViewById(R.id.image1);
        ImageView minus=view.findViewById(R.id.m);
        ImageView plus=view.findViewById(R.id.p);
        final EditText[] quanity = {view.findViewById(R.id.quantity)};
        databaseHelper=new DatabaseHelper(getContext());
        id.setText(Store.getName());
        category.setText(Store.getCategory());
        name.setText(Store.getPrice());
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int i=parseInt(quanity.toString());
            i++;
            }
        });
         //image.setImageBitmap(AddData.getBitmap(Store.getImage()));
        Button add=view.findViewById(R.id.add);
//        view.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(getContext(),AddData.class);
//                intent.putExtra("id",Store.getId());
//                getContext().startActivity(intent);
//            }
//        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues=new ContentValues();
                contentValues.put("name",name.getText().toString());
                contentValues.put("price",Store.getPrice());

                contentValues.put("quantity", quanity[0].getText().toString());
                int add= parseInt(Store.getPrice())* parseInt(quanity[0].getText().toString());

                contentValues.put("Total",add);

//                int sum = 0;
//
//
//                contentValues.put("sum",sum);
              //  contentValues.put("quantity",txtContact.getText().toString());
                //contentValues.put("image",getBlob(bitmap));
                databaseHelper.insertcart(contentValues);
            }
        });
       return view;

    }

    public void update(ArrayList<store> result) {
        list=new ArrayList<>();
        list.addAll(result);
        notifyDataSetChanged();
    }

}
