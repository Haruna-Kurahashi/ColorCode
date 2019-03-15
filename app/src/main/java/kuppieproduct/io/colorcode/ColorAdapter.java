package kuppieproduct.io.colorcode;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;


import java.util.List;

public class ColorAdapter extends ArrayAdapter<Color> {
    List<Color> Colors;

    public ColorAdapter(Context context, int layoutResouceId, List<Color> objects) {
        super(context, layoutResouceId, objects);

        Colors = objects;
    }

    @Override
    public int getCount() {

        return Colors.size();
    }

    @Override
    public Color getItem(int position) {

        return Colors.get(position);
    }

    public static class ViewHolder {
        ImageView photoImage;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.colorlist, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.photoImage = (ImageView) convertView.findViewById(R.id.photo);

            convertView.setTag(viewHolder);
        }

        final Color color = getItem(position);

        return convertView;

    }
}
