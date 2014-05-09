package com.tarombo.sirait.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tarombo.sirait.R;

public class HomeFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View rooView = inflater.inflate(R.layout.home_fragment, null);
		return rooView;
	}
	
}
