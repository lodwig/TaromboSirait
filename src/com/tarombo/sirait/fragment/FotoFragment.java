package com.tarombo.sirait.fragment;

import com.tarombo.sirait.R;
import com.tarombo.sirait.adapter.ImageAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FotoFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.layout_foto_fragment, null);
		ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
		ImageAdapter adapter = new ImageAdapter(getActivity());
		viewPager.setAdapter(adapter);
		return rootView;
	}
}
