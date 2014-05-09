package com.tarombo.sirait;


import android.app.Application;
import android.content.Context;

import com.tarombo.sirait.database.SiraitRepository;

public class SiraitApplication extends Application{
	
	private static Context _context;
	
	public static Context GetContext(){
		return _context;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		_context = getApplicationContext();
		SiraitRepository.Init(_context);
		
		if (!SiraitRepository.isDataWasInserted()){
			SiraitRepository.InsertNewDataToTable();
		}
		
	}

	public static String GetApplicationPath(){
		return _context.getPackageName();
	}
}
