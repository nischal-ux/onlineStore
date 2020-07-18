package com.example.shamsonlinemark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Detail extends AppCompatActivity {

DatabaseHelper databaseHelper;
GridView cont;
Gridview gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        cont=findViewById(R.id.cont);

        display();

    }

    @Override
    protected void onResume() {
        super.onResume();
        display();
    }

    public  void display(){
        databaseHelper=new DatabaseHelper(this);
        gridview=new Gridview(this,databaseHelper.getStorelist());
        cont.setAdapter(gridview);

    }

}
