package com.tarombo.sirait;

import java.util.ArrayList;
import java.util.Arrays;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tarombo.sirait.adapter.DrawerAdapter;
import com.tarombo.sirait.fragment.AcaraFragment;
import com.tarombo.sirait.fragment.FotoFragment;
import com.tarombo.sirait.fragment.HistoryFragment;
import com.tarombo.sirait.fragment.HomeFragment;
import com.tarombo.sirait.fragment.TaromboFragment;
import com.tarombo.sirait.helper.ConstansInterface;
import com.tarombo.sirait.helper.Tools;

public abstract class BaseClassActivity extends Activity{

	
	private ArrayList<String> mListItem;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
	private CharSequence mTitle;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private DrawerAdapter mAdapter;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

        mListItem = new ArrayList<String>( Arrays.asList(getResources().getStringArray(R.array.action_bar_menu_array)));
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,         
                R.drawable.ic_drawer, R.string.drawer_open,  
                R.string.drawer_close) {

	            public void onDrawerClosed(View view) {
	                super.onDrawerClosed(view);
	                getActionBar().setTitle(mTitle);
	            }
	
	            public void onDrawerOpened(View drawerView) {
	                super.onDrawerOpened(drawerView);
	                getActionBar().setTitle(mDrawerTitle);
	            }
        };

        selectItem(0);
        
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mAdapter = new DrawerAdapter(this, R.layout.drawer_list_item, mListItem);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> p, View v, int pos,long id) {
				selectItem(pos);
				
			}
		});
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
	}
	
	

	@SuppressWarnings("static-access")
	private void selectItem(int position) {
		Fragment fragment = null;

		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;

		case 1:
			fragment = new TaromboFragment().newInstance(ConstansInterface.FatherIdFirst);
			break;
		case 2:
			fragment = new FotoFragment();
			break;
		case 3:
			fragment = new AcaraFragment();
			break;
		case 4:
			fragment = new HistoryFragment();
			break;
		}

	    FragmentManager fragmentManager = getFragmentManager();
	    fragmentManager.beginTransaction()
	                   .replace(R.id.content_frame, fragment)
	                   .commit();

	    mDrawerList.setItemChecked(position, true);
	    setTitle(mListItem.get(position));
	    mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
	    mTitle = title;
	    getActionBar().setTitle(mTitle);
	}
	
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
