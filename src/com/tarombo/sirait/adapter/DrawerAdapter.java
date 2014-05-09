package com.tarombo.sirait.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tarombo.sirait.R;

public class DrawerAdapter extends ArrayAdapter<String> {

	private Context mContext;
	private int mResource;

	public DrawerAdapter(Context context, int resource, ArrayList<String> items) {
		super(context, resource, items);
		mContext = context;
		mResource = resource;
	}

	static class ViewHolder {
		LinearLayout layoutHeader;
		LinearLayout layoutItem;
		ImageView imgHeader;
		TextView tvItem;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final ViewHolder vHolder;
		if (convertView == null) {
			vHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(mResource, null);
			vHolder.layoutHeader = (LinearLayout) convertView.findViewById(R.id.linearDrawerHeader);
			vHolder.layoutItem = (LinearLayout) convertView.findViewById(R.id.linearDrawerItem);
			vHolder.imgHeader = (ImageView) convertView.findViewById(R.id.imgDrawerHeader);
			vHolder.tvItem = (TextView) convertView.findViewById(R.id.tvDrawerItem);
			convertView.setTag(vHolder);
		} else {
			vHolder = (ViewHolder) convertView.getTag();
		}

		String item = getItem(position);
		if (item.equals("Home")) {
			vHolder.layoutHeader.setVisibility(View.VISIBLE);
			vHolder.layoutItem.setVisibility(View.GONE);
		} else {
			vHolder.layoutHeader.setVisibility(View.GONE);
			vHolder.layoutItem.setVisibility(View.VISIBLE);
			vHolder.tvItem.setText(item);
			if (item.equals("Thank You!")) {
				vHolder.tvItem.setVisibility(View.GONE);
			} else {
				vHolder.tvItem.setVisibility(View.VISIBLE);
			}

		}

		return convertView;
	}

}
