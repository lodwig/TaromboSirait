package com.tarombo.sirait.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tarombo.sirait.R;
import com.tarombo.sirait.database.SiraitRepository;
import com.tarombo.sirait.helper.Tools;
import com.tarombo.sirait.model.SiraitFamily;

public class SiraitParentAdapter extends ArrayAdapter<SiraitFamily>{

	private static int _resource;
	private static Context _context;
	private LayoutInflater _inflater;
	private ArrayList<SiraitFamily> _listItem = new ArrayList<SiraitFamily>();
	private TextView _tvJudul ;
	private SiraitRepository _repo = new SiraitRepository();
	private ImageView _btnUpToParent;
	public SiraitParentAdapter(Context context, int resource, ArrayList<SiraitFamily> listSirait, TextView tvJudul, ImageView btnUpToParent) {
		super(context, resource, listSirait);
		_resource = resource;
		_context = context;
		_inflater = LayoutInflater.from(_context);
		_listItem = listSirait;
		_tvJudul = tvJudul;
		_btnUpToParent = btnUpToParent;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder v ;
		
		if (convertView == null){
			v = new ViewHolder();
			convertView = _inflater.inflate(_resource, null);
			v.tvName 	= (TextView) convertView.findViewById(R.id.tvName);
			v.lyDescription = (LinearLayout) convertView.findViewById(R.id.layoutForDescription);
			v.imgNext	= (ImageView) convertView.findViewById(R.id.imgNext);
			convertView.setTag(v);
		}
		else{
			v = (ViewHolder) convertView.getTag();
		}
		
		final SiraitFamily item = getItem(position);
		if (item != null)
		{
			v.tvName.setText(item.getName() != null ? String.valueOf(position + 1) + "." + item.getName() : "No Name");
			TextView tvDesc = new TextView(_context);
			tvDesc.setText(item.getDescription());
			v.lyDescription.removeAllViews();
			v.lyDescription.addView(tvDesc);
			v.lyDescription.setVisibility(View.GONE);
			
			v.tvName.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					if (v.lyDescription.getVisibility() == View.VISIBLE)
						v.lyDescription.setVisibility(View.GONE);
					else
						v.lyDescription.setVisibility(View.VISIBLE);
							
				}
			});
			if (item.getGenerationId().contains("ADMIN"))
				v.tvName.setTextColor(_context.getResources().getColor(R.color.Merah));
			else
				v.tvName.setTextColor(_context.getResources().getColor(R.color.Biru));
			
			
			v.imgNext.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Tools.setFatherId(item.getGenerationId());
					Refresh();
				}
			});
			
			if (item.getNumberOfSons() == 0){
				v.imgNext.setVisibility(View.GONE);
			}
			else{
				v.imgNext.setVisibility(View.VISIBLE);
			}
		}

		return convertView;
	}
	
	private void Refresh(){
		_listItem.clear();
		_listItem.addAll(_repo.getSonsByFatherId(Tools.getFatherId()));
		notifyDataSetChanged();
		_btnUpToParent.setVisibility(View.VISIBLE);
		if (_listItem.size() > 0){
			String genID = Tools.getFatherId().split("-")[0];
			_tvJudul.setText(genID.subSequence(1, genID.length()) + "." + _repo.getNameById(Tools.getFatherId()) + " :");
		}
		else {
			_tvJudul.setText("Data [" +_repo.getNameById(Tools.getFatherId()) + "] belum ada!");
		}
		
	}
	
	private static class ViewHolder
	{
		TextView tvName;
		LinearLayout lyDescription;
		ImageView imgNext;
	}
	
}
