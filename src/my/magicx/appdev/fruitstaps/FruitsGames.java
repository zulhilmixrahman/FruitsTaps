package my.magicx.appdev.fruitstaps;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class FruitsGames extends Thread {
	
	private int bound;
	private int popupTime;
	private Handler handler;
	
	public enum GameState{RUNNING, STOPED};
	private GameState gState;
	
	public FruitsGames(Handler h, int bound){
		super();
		this.bound = bound;
		gState = GameState.RUNNING;
		this.handler = h;
		this.popupTime = 1500;	
	}
	
	@Override
	public void run(){
		while(gState == GameState.RUNNING){
			int temp1, temp2;
			int newPosition = (int) (Math.random()*(bound+1));
			int newPosition1 = ( (temp1 = (int) (Math.random()*(bound+1))) != newPosition) ? temp1 : 0;
			int newPosition2 = ( (temp2 = (int) (Math.random()*(bound+1))) != newPosition) ? temp2 : 0;
			nextStep(newPosition, newPosition1, newPosition2);
			try {
				sleep(popupTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void nextStep(int newPosition, int newPosition1, int newPosition2){
		Message msg = handler.obtainMessage();
		Bundle b = new Bundle();
		b.putInt("newPosition", newPosition);
		b.putInt("newPosition1", newPosition1);
		b.putInt("newPosition2", newPosition2);
		msg.setData(b);
		handler.sendMessage(msg);
	}
	
	public int getBound(){
		return bound;
	}
	
	public void setBound(int b){
		this.bound = b;
	}
	
	public String getGameState(){
		return gState.name();
	}
	
	public void setState(String state){
		if(state.equals(GameState.RUNNING.name()))
			this.gState = GameState.RUNNING;
		else
			this.gState = GameState.STOPED;
	}
	
	public synchronized void restartThread(){
		this.gState = GameState.RUNNING;
	}
	
	public synchronized void stopThread(){
		this.gState = GameState.STOPED;
	}
	
	public void setPopupTime(int time){
		this.popupTime = time;
	}
	
}
