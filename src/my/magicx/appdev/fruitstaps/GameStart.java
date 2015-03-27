package my.magicx.appdev.fruitstaps;

import my.magicx.appdev.fruitstaps.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class GameStart extends Activity {

	private FruitsGraphics graphic;
	private FruitsGames game;
	private Items items = new Items();

	private String level;
	private int column;
	private int hole;

	private int selectedPosition = 0, curentItemPostion = -1,
			curentItemPostion1 = -1, curentItemPostion2 = -1;
	private int curentScore = 0;
	private Handler step;
	private Handler update;
	private boolean isFruit1 = true, isFruit2 = true, isFruit3 = true;
	private double probality = 0.80;
	private int itemScore1 = 0, itemScore2 = 0, itemScore3 = 0;

	private TextView timerText, txtScoreAnimetion;
	private long startTime = 0L;
	private Handler timeHandler = new Handler();
	private long timeInMilliseconds = 0L;
	private long timeSwapBuff = 0L;
	private long updatedTime = 0L;
	private int time_limit = 0;
	// private int resume_time = 0;
	private boolean pause = false;

	// private Button btnPause, btnResume;
	// private RelativeLayout pauseLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_layout);
		Typeface font = Typeface
				.createFromAsset(getAssets(), "skaterdudes.ttf");

		time_limit = Integer.valueOf(getString(R.string.game_timer_in_seconds));
		final Vibrator vibe = (Vibrator) GameStart.this
				.getSystemService(Context.VIBRATOR_SERVICE);

		this.level = getIntent().getExtras().getString("level");
		this.column = getIntent().getExtras().getInt("column");
		this.hole = this.column * this.column;

		final GridView gridView = (GridView) findViewById(R.id.gridView);
		gridView.setNumColumns(this.column);

		final TextView scoreText = (TextView) findViewById(R.id.score);
		scoreText.setTypeface(font);

		timerText = (TextView) findViewById(R.id.timer);
		timerText.setTypeface(font);

		txtScoreAnimetion = (TextView) findViewById(R.id.score_animetion);
		txtScoreAnimetion.setTypeface(font);

		startTime = SystemClock.uptimeMillis();
		timeHandler.postDelayed(updateTimerThread, 0);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		step = new ChangeImage();
		update = new Handler();

		graphic = new FruitsGraphics(this, column, hole, metrics.widthPixels);
		gridView.setAdapter(graphic);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View v,
					int position, long id) {
				if (curentItemPostion == position
						|| curentItemPostion1 == position
						|| curentItemPostion2 == position) {
					selectedPosition = position;
					update.post(new Runnable() {
						int curentStage = 1;

						@Override
						public void run() {
							boolean isFruit = true;
							int itemScore = 0;

							if (curentItemPostion == selectedPosition) {
								isFruit = isFruit1;
								itemScore = itemScore1;
								itemScore1 = 0;
							} else if (curentItemPostion1 == selectedPosition) {
								isFruit = isFruit2;
								itemScore = itemScore2;
								itemScore2 = 0;
							} else if (curentItemPostion2 == selectedPosition) {
								isFruit = isFruit3;
								itemScore = itemScore3;
								itemScore3 = 0;
							}

							if (isFruit == true) {
								if (itemScore <= 200) {
									curentScore = curentScore + itemScore;
									scoreText.setText("" + curentScore);
								}
								scoreText.refreshDrawableState();
								if (level.equals("hard")) {
									if (curentStage == 1 && curentScore >= 100
											&& curentScore <= 150) {
										setProbability(0.70);
										game.setPopupTime(1000);
										curentStage = 2;
									} else if (curentStage == 2
											&& curentScore > 150
											&& curentScore <= 200) {
										setProbability(0.60);
										game.setPopupTime(700);
										curentStage = 3;
									} else if (curentStage == 3
											&& curentScore > 200) {
										setProbability(0.50);
										game.setPopupTime(400);
										curentStage = 4;
									}
								}
							} else {
								if (itemScore < 0) {
									vibe.vibrate(100);
									curentScore = curentScore + itemScore;
									scoreText.setText("" + curentScore);
								}
								scoreText.refreshDrawableState();
							}
						}
					});

					graphic.setItem(position, R.drawable.hole);
					graphic.notifyDataSetChanged();
				}
			}
		});
		game = new FruitsGames(step, (hole - 1));
		game.start();

		/*
		 * btnPause = (Button) findViewById(R.id.btnPause);
		 * pauseLayout = (RelativeLayout) findViewById(R.id.layoutPause);
		 * btnResume = (Button) findViewById(R.id.btnResume);
		 * 
		 * btnPause.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { game.stopThread(); pause =
		 * true; timeHandler.removeCallbacksAndMessages(this);
		 * pauseLayout.setVisibility(View.VISIBLE);
		 * btnResume.setVisibility(View.VISIBLE); } });
		 * 
		 * btnResume.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * pauseLayout.setVisibility(View.INVISIBLE);
		 * btnResume.setVisibility(View.INVISIBLE); try {
		 * FruitsGames.sleep(1000); } catch (InterruptedException e) {
		 * e.printStackTrace(); } game = new FruitsGames(step, (hole - 1));
		 * game.start(); time_limit = resume_time; updateTimerThread.run(); }
		 * });
		 */
	}
	
	public void setProbability(double probality) {
		this.probality = probality;
	}

	public Activity getActivity() {
		return this.getActivity();
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@SuppressLint("HandlerLeak")
	private class ChangeImage extends Handler {

		Grid grid = new Grid();
		private int oldPosition = -1, oldPosition1 = -1, oldPosition2 = -1;

		@Override
		public void handleMessage(Message msg) {
			Bundle bundle = msg.getData();

			curentItemPostion = bundle.getInt("newPosition");
			curentItemPostion1 = bundle.getInt("newPosition1");
			curentItemPostion2 = bundle.getInt("newPosition2");

			setItem(curentItemPostion, 1);
			setItem(curentItemPostion1, 2);
			setItem(curentItemPostion2, 3);

			if (oldPosition != -1 && curentItemPostion != oldPosition) {
				graphic.setItem(oldPosition, grid.getGrid());
			}
			oldPosition = curentItemPostion;

			if (oldPosition1 != -1 && curentItemPostion1 != oldPosition1) {
				graphic.setItem(oldPosition1, grid.getGrid());
			}
			oldPosition1 = curentItemPostion1;

			if (oldPosition2 != -1 && curentItemPostion2 != oldPosition2) {
				graphic.setItem(oldPosition2, grid.getGrid());
			}
			oldPosition2 = curentItemPostion2;

			graphic.notifyDataSetChanged();
		}

		public void setItem(int position, int sequence) {
			int itemScore = 0;
			if (Math.random() < probality) {
				/*** For Fruit - Add Score ***/
				switch (getRandomNo(1, 8)) {
				case 1:
					graphic.setItem(position, items.getBanana());
					itemScore = items.getBananaScore();
					break;
				case 2:
					// graphic.setItem(position, items.getDurian());
					// itemScore = items.getDurianScore();
					// break;
				case 3:
					graphic.setItem(position, items.getGrape());
					itemScore = items.getGrapeScore();
					break;
				case 4:
					graphic.setItem(position, items.getOrange());
					itemScore = items.getOrangeScore();
					break;
				case 5:
					graphic.setItem(position, items.getPapaya());
					itemScore = items.getPapayaScore();
					break;
				case 6:
					// graphic.setItem(position, items.getPineapple());
					// itemScore = items.getPineappleScore();
					// break;
				case 7:
					graphic.setItem(position, items.getStrawberry());
					itemScore = items.getStrawberryScore();
					break;
				case 8:
					graphic.setItem(position, items.getWatermelon());
					itemScore = items.getWatermelonScore();
					break;
				}
				if (sequence == 1) {
					isFruit1 = true;
					itemScore1 = itemScore;
				} else if (sequence == 2) {
					isFruit2 = true;
					itemScore2 = itemScore;
				} else if (sequence == 3) {
					isFruit3 = true;
					itemScore3 = itemScore;
				}
			} else {
				/*** For Non-Fruit - Deduct Score ***/
				switch (getRandomNo(1, 2)) {
				case 1:
					graphic.setItem(position, items.getBat());
					itemScore = items.getBatScore();
					break;
				case 2:
					graphic.setItem(position, items.getBird());
					itemScore = items.getBirdScore();
					break;
				}
				if (sequence == 1) {
					isFruit1 = false;
					itemScore1 = itemScore;
				} else if (sequence == 2) {
					isFruit2 = false;
					itemScore2 = itemScore;
				} else if (sequence == 3) {
					isFruit3 = false;
					itemScore3 = itemScore;
				}
			}
		}

		private int getRandomNo(int min, int max) {
			int rand = min + (int) (Math.random() * ((max - min) + 1));
			return rand;
		}
	}
	
	private Runnable updateTimerThread = new Runnable() {
		@Override
		public void run() {
			int secs = (int) (updatedTime / 1000);
			if (pause == false) {
				timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
				updatedTime = timeSwapBuff + timeInMilliseconds;
				int secsView = time_limit - secs;
				timerText.setText(String.format("%d", secsView));
				timeHandler.postDelayed(this, 0);
				if (secsView == 1) {
					Intent intent = new Intent(GameStart.this, GameOver.class);
					intent.putExtra("level", level);
					intent.putExtra("score", curentScore);
					startActivity(intent);
					finish();
				}
			} else {
				// resume_time = time_limit - secs;
			}
		}
	};

}
