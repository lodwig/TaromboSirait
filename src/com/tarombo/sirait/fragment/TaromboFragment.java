package com.tarombo.sirait.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tarombo.sirait.R;
import com.tarombo.sirait.adapter.SiraitParentAdapter;
import com.tarombo.sirait.database.SiraitRepository;
import com.tarombo.sirait.helper.ConstansInterface;
import com.tarombo.sirait.helper.Tools;
import com.tarombo.sirait.model.SiraitFamily;

public class TaromboFragment extends Fragment{

	private ImageView btnBackToFather;
	private TextView tvFatherName;
	private ListView lvSiraitFamily;
	
	private SiraitParentAdapter mAdapter;
	private ArrayList<SiraitFamily> mItem = new ArrayList<SiraitFamily>(); 
	private SiraitRepository mRepo = new SiraitRepository();
	
	public static Fragment newInstance(String fatherID){
		Tools.setFatherId(fatherID);
		Fragment f = new TaromboFragment();
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.layout_tarombo_fragment, null);
		
		btnBackToFather = (ImageView) rootView.findViewById(R.id.btnBackToParent);
		tvFatherName = (TextView) rootView.findViewById(R.id.tvFatherName);
		lvSiraitFamily = (ListView) rootView.findViewById(R.id.lvSirait);
		
		mItem = mRepo.getSonsByFatherId(Tools.getFatherId());
		mAdapter = new SiraitParentAdapter(getActivity(), 
				R.layout.sirait_parent_adapter_layout, 
				mItem, tvFatherName, btnBackToFather);
		lvSiraitFamily.setAdapter(mAdapter);
		
		btnBackToFather.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String tmpFatherId = mRepo.getFathersIdBySonsId(Tools.getFatherId()).equals(ConstansInterface.isEmpty) ? 
						ConstansInterface.FatherIdFirst : mRepo.getFathersIdBySonsId(Tools.getFatherId());
				Tools.setFatherId(tmpFatherId);
				mItem = mRepo.getSonsByFatherId(Tools.getFatherId());
				mAdapter = new SiraitParentAdapter(getActivity(), 
						R.layout.sirait_parent_adapter_layout, 
						mItem, tvFatherName, btnBackToFather);
				lvSiraitFamily.setAdapter(mAdapter);
				if (!Tools.getFatherId().equals(ConstansInterface.FatherIdFirst)){
					String genID = Tools.getFatherId().split("-")[0];
					tvFatherName.setText(genID.subSequence(1, genID.length()) +"."+ mRepo.getNameById(Tools.getFatherId()) + " :");
					btnBackToFather.setVisibility(View.VISIBLE);
				}else{
					tvFatherName.setText("Tarombo Sirait");
					btnBackToFather.setVisibility(View.INVISIBLE);
				}
				
			}
		});
		return rootView;
	}
}
