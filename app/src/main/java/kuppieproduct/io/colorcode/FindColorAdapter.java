package kuppieproduct.io.colorcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FindColorAdapter extends ArrayAdapter<FindColor> {
    List<FindColor> findColors;

    public FindColorAdapter(Context context, int layoutResouceId, List<FindColor> objects) {
        super(context, layoutResouceId, objects);

        findColors = objects;
    }

    @Override
    public int getCount() {

        return findColors.size();
    }

    @Override
    public FindColor getItem(int position) {

        return findColors.get(position);
    }

    public static class ViewHolder {
        View colorView;
        TextView colorCode;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.choosed_colorlist, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.colorView = convertView.findViewById(R.id.color_View);
            viewHolder.colorCode = (TextView) convertView.findViewById(R.id.colorCodeText);

            convertView.setTag(viewHolder);

        }

        FindColor findColor = getItem(position);
        viewHolder.colorView.setBackgroundColor(findColor.imageId);
        viewHolder.colorCode.setText(findColor.colorCode);

        return convertView;

    }
}
