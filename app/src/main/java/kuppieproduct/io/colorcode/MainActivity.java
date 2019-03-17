package kuppieproduct.io.colorcode;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Color> Colors;
    ColorAdapter adapter;
    ListView listView;

    List<FindColor> findColors;
    ImageView imageView;
    View colorPreviewView;

    ListView colorListView;
    FindColorAdapter adapter2;

    FloatingActionButton selectImageButton;

    public static int READ_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_color);
        listView = (ListView) findViewById(R.id.choosedcolorList);

        selectImageButton = (FloatingActionButton) findViewById(R.id.addphoto);

        Colors = new ArrayList<Color>();

        adapter = new ColorAdapter(this, 0, new ArrayList<Color>());
        listView.setAdapter(adapter);


        imageView = (ImageView) findViewById(R.id.imageView);
        colorPreviewView = findViewById(R.id.colorPreview);
        colorListView = (ListView) findViewById(R.id.choosedcolorList);

        imageView.setImageResource(R.drawable.suisai01);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ImageView img = (ImageView) view;

                final int evX = (int) motionEvent.getX();
                final int evY = (int) motionEvent.getY();

                img.setDrawingCacheEnabled(true);
                Bitmap imgbmp = Bitmap.createBitmap(img.getDrawingCache());
                img.setDrawingCacheEnabled(false);

                try {
                    int pxl = imgbmp.getPixel(evX, evY);
                    colorPreviewView.setBackgroundColor(pxl);
                } catch (Exception ignore) {
                    //何もしない
                }

                imgbmp.recycle();

                return true;
            }

        });

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                intent.setType("image/*");
                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });

        adapter2 = new FindColorAdapter(this, 0, new ArrayList<FindColor>());
        colorListView.setAdapter(adapter2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent resultData) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void addColor(View view) {
        int color = ((ColorDrawable) colorPreviewView.getBackground()).getColor();
        String colorCode = "#" + Integer.toHexString(color).substring(2).toUpperCase();
        adapter2.add(new FindColor(color, colorCode));
        adapter2.notifyDataSetChanged();
    }
}
