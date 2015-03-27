package my.magicx.appdev.fruitstaps;

import my.magicx.appdev.fruitstaps.R;

public class Items {
	
	private int banana;
	private int durian;
	private int grape;
	private int orange;
	private int papaya;
	private int pineapple;
	private int strawberry;
	private int watermelon;
	
	private int bat;
	private int bird;
	
	public Items() {
		banana = R.drawable.f_banana;
		durian = R.drawable.f_durian;
		grape = R.drawable.f_grape;
		orange = R.drawable.f_orange;
		papaya = R.drawable.f_papaya;
		pineapple = R.drawable.f_pineapple;
		strawberry = R.drawable.f_strawberry;
		watermelon = R.drawable.f_watermelon;
		
		bat = R.drawable.a_bat;
		bird = R.drawable.a_bird;
	}
	
	public int getBanana(){
		return banana;
	}
	
	public int getBananaScore(){
		return 10;
	}
	
	public int getDurian(){
		return durian;
	}
	
	public int getDurianScore(){
		return 30;
	}
	
	public int getGrape(){
		return grape;
	}
	
	public int getGrapeScore(){
		return 25;
	}
	
	public int getOrange(){
		return orange;
	}
	
	public int getOrangeScore(){
		return 25;
	}
	
	
	public int getPapaya(){
		return papaya;
	}
	
	
	public int getPapayaScore(){
		return 10;
	}
	
	public int getPineapple(){
		return pineapple;
	}
	
	public int getPineappleScore(){
		return 15;
	}
	
	public int getStrawberry(){
		return strawberry;
	}
	
	public int getStrawberryScore(){
		return 30;
	}
	
	public int getWatermelon(){
		return watermelon;
	}
	
	public int getWatermelonScore(){
		return 20;
	}

	public int getBat(){
		return bat;
	}
	
	public int getBatScore(){
		return -15;
	}
	
	public int getBird(){
		return bird;
	}
	
	public int getBirdScore(){
		return -10;
	}

}
