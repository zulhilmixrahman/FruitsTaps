package my.magicx.appdev.fruitstaps;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Score implements Serializable {
	private int id;
	private int topNo;
	private String level;
	private String player_name;
	private String player_score;
	
	public Score(){}
	
	public Score(String level, String player_name, String player_score){
		super();
		this.player_name = player_name;
		this.player_score = player_score;
	}
	
	public void setTopNo(int topNo){
		this.topNo = topNo;
	}
	
	public String getTopNo(){
		String txtTopNo = this.topNo + ":";
		return txtTopNo;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setLevel(String level){
		this.level = level;
	}
	
	public String getLevel(){
		return this.level;
	}
	
	public void setPlayerName(String player_name){
		this.player_name = player_name;
	}
	
	public String getPlayerName(){
		return this.player_name;
	}
	
	public void setPlayerScore(String player_score){
		this.player_score = player_score;
	}
	
	public String getPlayerScore(){
		return this.player_score;
	}
	
	@Override
	public String toString(){
		return player_name + " : " + player_score;
	}
	
}
