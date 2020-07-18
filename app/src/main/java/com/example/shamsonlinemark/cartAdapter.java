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

public class cartAdapter extends ArrayAdapter<cart> {
    Context context;
    List<cart> cartlist;
    DatabaseHelper databaseHelper;
    public cartAdapter(Context context,ArrayList<cart> cartlist) {
        super(context, 0,cartlist);
        this.context=context;
        this.cartlist=cartlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.items,null);
        final cart Cart=getItem(position);
        TextView name=view.findViewById(R.id.product);
        TextView price=view.findViewById(R.id.price);
        final EditText quantity=view.findViewById(R.id.quantity);
        TextView total=view.findViewById(R.id.total);

        ImageView remove=view.findViewById(R.id.remove);
        Button up=view.findViewById(R.id.up);
        databaseHelper=new DatabaseHelper(getContext());
        name.setText(Cart.getName());
        price.setText(Cart.getPrice());
        quantity.setText(Cart.getQuantity());
        total.setText(Cart.getTotal());
//       image.setImageBitmap(AddData.getBitmap(Store.getImage()));
//        Button add=view.findViewById(R.id.add);
for (cart li:cartlist){
int sum=0;
sum=sum+li.getTotal().length();
}
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            databaseHelper.deletecart(Cart.getId());
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ContentValues contentValues=new ContentValues();
                contentValues.put("quantity",quantity.getText().toString());
                int add=Integer.parseInt(Cart.getPrice())*Integer.parseInt(quantity.getText().toString());

                contentValues.put("Total",add);
                //contentValues.put("total",txtEmail.getText().toString());
                databaseHelper.update(Cart.getId(),contentValues);

            }
        });
        return view;
    }

}
