package kuppieproduct.io.colorcode;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
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

        adapter2 = new FindColorAdapter(this, 0, new ArrayList<FindColor>());
        colorListView.setAdapter(adapter2);

    }

    public void addColor(View view) {
        int color = ((ColorDrawable) colorPreviewView.getBackground()).getColor();
        String colorCode = "#" + Integer.toHexString(color).substring(2).toUpperCase();
        adapter.add(new FindColor(colorCode, color));
        adapter.notifyDataSetChanged();
    }
}
