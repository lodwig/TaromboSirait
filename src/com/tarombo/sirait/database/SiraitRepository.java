package com.tarombo.sirait.database;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.tarombo.sirait.helper.ConstansInterface;
import com.tarombo.sirait.model.SiraitFamily;
/**
 * <code> SiraitRepository </code> using for Connecting and Query to Database
 * @author Hengki Lodwig Sirait
 * @since  04 Oktober 2013
 */
public class SiraitRepository {
	private static TaromboSiraitDatabase db ;
	
	public static void Init(Context context){
		if (db == null){
			db = new TaromboSiraitDatabase(context, ConstansInterface.DBName, null, ConstansInterface.DBVersion);
			db.OpenDatabase();
		}
	}
	
	public static boolean isDataWasInserted(){
		boolean result = false;
		String query = "SELECT Count(*) FROM [SiraitFamily]";
		Cursor c = null;
		try {
			if (!db.IsDatabaseOpen())
				db.OpenDatabase();
			
			c = db.Query(query, new String[]{});
			if (c != null && c.moveToFirst()){
				int tmp = c.getInt(0);
				if (tmp > 0)
					result = true;
				else
					result = false;
			}
		} catch (Exception e) {
			result = false;
		}
		finally{
			if ( c != null && !c.isClosed())
				c.close();
		}
		return result;
	}
	
	public ArrayList<SiraitFamily>  getAllSiraitFamily()
	{
		ArrayList<SiraitFamily> result = new ArrayList<SiraitFamily>();
		String query = "SELECT * FROM [SiraitFamily]";
		Cursor c = null;
		
		try {
			if (!db.IsDatabaseOpen())
				db.OpenDatabase();
			
			c = db.Query(query, new String[]{});
			if (c != null && c.moveToFirst()){
				do {
					
					String GenerationId = c.getString(c.getColumnIndex("GenerationId")) != null ? c.getString(c.getColumnIndex("GenerationId"))  : "";
					String Name = c.getString(c.getColumnIndex("Name")) != null ? c.getString(c.getColumnIndex("Name")) : "";
					String NumberOfSons = c.getString(c.getColumnIndex("NumberOfSons")) != null ?c.getString(c.getColumnIndex("NumberOfSons")) : "0";
					String Description = c.getString(c.getColumnIndex("Description")) != null ? c.getString(c.getColumnIndex("Description")) : "";
					
					SiraitFamily sirait = new SiraitFamily();
					sirait.setGenerationId(GenerationId);
					sirait.setName(Name);
					sirait.setNumberOfSons(Integer.parseInt(NumberOfSons));
					sirait.setDescription(Description);
					
					result.add(sirait);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("GetAllSirait", e.getMessage());
		}
		finally{
			if ( c != null && !c.isClosed())
				c.close();
		}
		
		return result;
		
	}
	
	public SiraitFamily getByGenerationId(String generationId){
		SiraitFamily result = new SiraitFamily();
		
		String query = "Select * from SiraitFamily Where GenerationId = ?";
		Cursor c = null;
		try {
			if (! db.IsDatabaseOpen())
				db.OpenDatabase();
			
			c = db.Query(query, new String[]{generationId});
			if (c != null && c.moveToFirst()){
				result.setGenerationId(c.getString(c.getColumnIndex("GenerationId")) != null ? c.getString(c.getColumnIndex("GenerationId"))  : "");
				result.setName(c.getString(c.getColumnIndex("Name")) != null ? c.getString(c.getColumnIndex("Name")) : "");
				result.setNumberOfSons(c.getString(c.getColumnIndex("NumberOfSons")) != null ? Integer.parseInt(c.getString(c.getColumnIndex("NumberOfSons"))) : 0);
				result.setDescription(c.getString(c.getColumnIndex("Description")) != null ? c.getString(c.getColumnIndex("Description")) : "");
			}
		} catch (Exception e) {
		}finally{
			if (c != null && !c.isClosed())
				c.close();
		}
		
		return result;
	}
	
	public ArrayList<SiraitFamily> getSonsByFatherId(String fatherId){
		ArrayList<SiraitFamily> result = new ArrayList<SiraitFamily>();
		ArrayList<String> childIds = new ArrayList<String>(); 
		String query = "Select Id, ChildsId, Description from [Generation] Where FatherId = ?";
		Cursor c = null;
		try {
			if (! db.IsDatabaseOpen())
				db.OpenDatabase();
			
			c = db.Query(query, new String[]{fatherId});
			if (c != null && c.moveToFirst()){
				do{
					childIds.add(c.getString(c.getColumnIndex("ChildsId")) != null ?c.getString(c.getColumnIndex("ChildsId")) : "Empty");
				}while(c.moveToNext());
			}
			
			for (String childId :childIds)
			{
				SiraitFamily s = new SiraitFamily();
				s = getByGenerationId(childId);
				if (s != null)
					result.add(s);
			}
		} catch (Exception e) {
		}finally{
			if (c != null && !c.isClosed())
				c.close();
		}
		return result;
	}
	
	public String getFathersIdBySonsId(String sonId){
		
		String result = ConstansInterface.isEmpty;
		String query = "Select FatherId from [Generation] Where ChildsId = ?";
		Cursor c = null;
		try {
			if (! db.IsDatabaseOpen())
				db.OpenDatabase();
			
			c = db.Query(query, new String[]{sonId});
			if (c != null && c.moveToFirst()){
				result = c.getString(0) != null ? c.getString(0) : ConstansInterface.isEmpty;				
			}
			
		} catch (Exception e) {
		}finally{
			if (c != null && !c.isClosed())
				c.close();
		}
		return result;
	}
	
	public static void InsertNewDataToTable(){
		String err = "";
		try{
			if (!db.IsDatabaseOpen())
				db.OpenDatabase();
			
			db.beginTransaction();
			for (String str : ConstansInterface.DataToInsertSiraitFamily){
				String query = "INSERT INTO [SiraitFamily] values('" + 
						str.split("#")[0] + "','" +
						str.split("#")[1] + "'," + 
						str.split("#")[2] + ",'" + 
						str.split("#")[3] + "')";
				err = query;
				db.ExecQuery(query);
			}
			for (String str : ConstansInterface.DataToInsertGeneration){
				String query = "INSERT INTO [Generation] values('" +
						UUID.randomUUID().toString() + "','" +
						str.split("#")[0] + "','" +
						str.split("#")[1] + "','Empty')";
				db.ExecQuery(query);
			}
		}
		catch(SQLException e){
			Log.e("INSERTING",err + "//" + e.getMessage());
		}
	
	}

	public String getNameById(String id){
		String result = "";
		String query = "SELECT Name from SiraitFamily Where GenerationId = ?";
		Cursor c = null;
		try {
			c = db.Query(query, new String[]{id});
			if (c != null && c.moveToFirst())
				result = c.getString(0);
		} catch (Exception e) {
			Log.e("GET NAME", e.getMessage());
		}finally{
			if (c != null && !c.isClosed())
				c.close();
		}
		return result;
	}
	
}
