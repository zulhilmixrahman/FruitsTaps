package my.magicx.appdev.fruitstaps;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataSource {
	
	@SuppressWarnings("unused")
	private SQLiteDatabase database;
	private DataBase dbhelper;
	private String[] allColumn = {DataBase._ID, DataBase._NAME, DataBase._SCORE};
	
	public DataSource(Context context){
		dbhelper = new DataBase(context);
	}
	
	public void open() throws SQLException {
		database = dbhelper.getWritableDatabase();
	}
	
	public void close() {
		dbhelper.close();
	}
	
	public void addScore(String level, String player_name, int player_score){
		if(level.equals("easy") || level.equals("hard")){
			String table_name = "score_"+level;
			SQLiteDatabase db = dbhelper.getWritableDatabase();
			
			ContentValues values = new ContentValues();
	        values.put(allColumn[1], player_name); 
	        values.put(allColumn[2], player_score);
	        
	        db.insert(table_name, null,values);
	        db.close();
	        
	        Log.d("addScore()", values.toString());
		}
	}
	
	public void truncateTable(String level){
		if(level.equals("easy") || level.equals("hard")){
			SQLiteDatabase db = dbhelper.getWritableDatabase();
			String truncate = "DELETE FROM score_" + level;
			String reset = "DELETE FROM SQLITE_SEQUENCE WHERE name='score_" + level + "'";
			db.execSQL(truncate);
			db.execSQL(reset);
		}
	}
	
	public List<Score> getAllScoreByLevel(String level) {
		List<Score> scores = new LinkedList<Score>();
		
		if(level.equals("easy") || level.equals("hard")){
		   String query = "SELECT  * FROM score_" + level;
		 
		   SQLiteDatabase db = dbhelper.getWritableDatabase();
		   Cursor cursor = db.rawQuery(query, null);
		 
		   Score score = null;
		   if (cursor.moveToFirst()) {
			   do {
				   score = new Score();
				   score.setId(Integer.parseInt(cursor.getString(0)));
				   score.setLevel(level);
				   score.setPlayerName(cursor.getString(1));
				   score.setPlayerScore(cursor.getString(2));
				   scores.add(score);

			   }while (cursor.moveToNext());
		   }
		}
		Log.d("getAllScoreByLevel()", scores.toString());
		return scores;
	}

	public ArrayList<Score> getTop3ScoreByLevel(String level){
		ArrayList<Score> scores = new ArrayList<Score>();
		
		if(level.equals("easy") || level.equals("hard")){
		   String query = "SELECT * FROM score_" + level + " Order By " + allColumn[2] + " DESC LIMIT 5";
		 
		   SQLiteDatabase db = dbhelper.getWritableDatabase();
		   Cursor cursor = db.rawQuery(query, null);
		 
		   Score score = null;
		   int topNo = 1;
		   if (cursor.moveToFirst()) {
			   do {
				   score = new Score();
				   score.setTopNo(topNo); topNo++;
				   score.setId(Integer.parseInt(cursor.getString(0)));
				   score.setLevel(level);
				   score.setPlayerName(cursor.getString(1));
				   score.setPlayerScore(cursor.getString(2));
				   scores.add(score);

			   }while (cursor.moveToNext());
		   }
		}
		Log.d("getTop3ScoreByLevel()::" + level, scores.toString());
		return scores;
	}
	
}
