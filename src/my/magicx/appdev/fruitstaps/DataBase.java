package my.magicx.appdev.fruitstaps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
	
	private static final int db_version = 1;
	private static final String db_name = "ft_score";
	public static final String _ID = "id";
	public static final String _NAME = "player_name";
	public static final String _SCORE = "player_score";

	public DataBase(Context context) {
		super(context, db_name, null, db_version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
        String create_table_easy = "CREATE TABLE score_easy (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
        		+ _NAME + " TEXT, " + _SCORE + " INTEGER);";
        String create_table_hard = "CREATE TABLE score_hard (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
        		+ _NAME + " TEXT, " + _SCORE + " INTEGER);";
 
        db.execSQL(create_table_easy);
        db.execSQL(create_table_hard);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
}
