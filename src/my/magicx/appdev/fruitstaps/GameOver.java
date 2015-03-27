package my.magicx.appdev.fruitstaps;

import my.magicx.appdev.fruitstaps.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameOver extends Activity {

	private TextView viewScore, gameOver;
	private EditText playerName;
	private Button submit;
	private Button restart;
	private String level;
	private int score;
	private DataSource ds = new DataSource(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_over);
		Typeface font = Typeface
				.createFromAsset(getAssets(), "skaterdudes.ttf");

		level = getIntent().getExtras().getString("level");
		score = getIntent().getExtras().getInt("score", 0);

		gameOver = (TextView) findViewById(R.id.txtOver);
		gameOver.setTypeface(font);

		viewScore = (TextView) findViewById(R.id.txtScore);
		viewScore.setTypeface(font);
		viewScore.setText(String.valueOf(score));

		playerName = (EditText) findViewById(R.id.inputName);
		submit = (Button) findViewById(R.id.btnSaveScore);
		restart = (Button) findViewById(R.id.btnRestart);

		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = playerName.getText().toString();
				System.out.println(name);
				if (name.length() > 0) {
					ds.open();
					try {
						ds.addScore(level, name, score);
						ds.getAllScoreByLevel(level);
					} catch (Exception e) {
						System.out.println("Error save db: ");
						e.printStackTrace();
					}
					ds.close();
				}
				Intent intent = new Intent(GameOver.this, Main.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}
		});

		restart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent startGame = new Intent(GameOver.this, GameStart.class);
				startGame.putExtra("level", level);
				if (level.equals("hard"))
					startGame.putExtra("column", 4);
				else
					startGame.putExtra("column", 3);
				startActivity(startGame);
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		return;
	}

}
