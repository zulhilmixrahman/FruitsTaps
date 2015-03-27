package my.magicx.appdev.fruitstaps;

import my.magicx.appdev.fruitstaps.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {

	private boolean exitGame = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button easyButton = (Button) findViewById(R.id.btn_easy);
		easyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent startGame = new Intent(Main.this, GameStart.class);
				startGame.putExtra("level", "easy");
				startGame.putExtra("column", 3);
				startActivity(startGame);
				finish();
			}
		});

		Button hardButton = (Button) findViewById(R.id.btn_hard);
		hardButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent startGame = new Intent(Main.this, GameStart.class);
				startGame.putExtra("level", "hard");
				startGame.putExtra("column", 4);
				startActivity(startGame);
				finish();
			}
		});

		Button highscoreButton = (Button) findViewById(R.id.btn_highscore);
		highscoreButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Main.this, HighScore.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		return;
//		if (exitGame) {
//			finish();
//			return;
//		} else {
//			exitGame = true;
//			Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT)
//					.show();
//			new Handler().postDelayed(new Runnable() {
//				@Override
//				public void run() {
//					exitGame = false;
//				}
//			}, 2000);
//			return;
//		}
	}

}
