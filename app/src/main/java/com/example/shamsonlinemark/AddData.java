package com.example.shamsonlinemark;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddData extends AppCompatActivity {
    EditText txtName,txtEmail,txtContact;
    Button btn_insert;
    ImageView uploadImage;
    DatabaseHelper databaseHelper;
    String encodedImage;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        txtName     = findViewById(R.id.edtName);
        txtEmail    = findViewById(R.id.edtprice);
        txtContact  = findViewById(R.id.cateory);
        databaseHelper=new DatabaseHelper(this);
        btn_insert = findViewById(R.id.btnInsert);
        id = getIntent().getIntExtra("id",0);

        uploadImage=findViewById(R.id.imagetobeUpload);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);

            }
        });
        if (id != 0){

            store Store = databaseHelper.getUserInfo(id + "");
            txtName.setText(Store.getId());
            txtEmail.setText(Store.getPrice());
            txtContact.setText(Store.getCategory());
        }
            btn_insert.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",txtName.getText().toString());
        contentValues.put("Price",txtEmail.getText().toString());
        contentValues.put("Categories",txtContact.getText().toString());
        //contentValues.put("image",getBlob(bitmap));
        databaseHelper.insertStore(contentValues);

    }
});

    }


    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            uploadImage.setImageBitmap(bitmap);
        }

    }


    public static byte[] getBlob(Bitmap bitmap) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
        Log.i("bytearray", "bytearray:" + bArray);

        return bArray;
    }

    public static Bitmap getBitmap(byte[] byteArray) {

        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

}
