package my.magicx.appdev.fruitstaps;

import java.util.ArrayList;

import my.magicx.appdev.fruitstaps.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class HighScore extends Activity {

	private DataSource ds;
	private ScoreAdapter adapterEasy;
	private ScoreAdapter adapterHard;
	private ListView listEasy;
	private ListView listHard;
	private AlertDialog.Builder builder;
	private Button btnClearEasy;
	private Button btnClearHard;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscore);
		Typeface font = Typeface
				.createFromAsset(getAssets(), "skaterdudes.ttf");

		builder = new AlertDialog.Builder(new ContextThemeWrapper(this,
				R.style.AppBaseTheme));

		TextView txtEasy = (TextView) findViewById(R.id.txtEasy);
		txtEasy.setTypeface(font);

		TextView txtHard = (TextView) findViewById(R.id.txtHard);
		txtHard.setTypeface(font);

		btnClearEasy = (Button) findViewById(R.id.btnClearEasy);
		btnClearHard = (Button) findViewById(R.id.btnClearHard);

		ds = new DataSource(this);
		ds.open();

		ArrayList<Score> valuesEasy = ds.getTop3ScoreByLevel("easy");
		setEasyList(valuesEasy);

		ArrayList<Score> valuesHard = ds.getTop3ScoreByLevel("hard");
		setHardList(valuesHard);

		btnClearEasy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				builder.setTitle("Reset Highscore");
				builder.setMessage("Are you sure to reset 'Top 5: Easy' record");
				builder.setPositiveButton("YES",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								ds.truncateTable("easy");
								setEasyList(ds
										.getTop3ScoreByLevel("easy"));
								listEasy.refreshDrawableState();
								dialog.dismiss();
							}
						});
				builder.setNegativeButton("NO",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});

		btnClearHard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				builder.setTitle("Reset Highscore");
				builder.setMessage("Are you sure to reset 'Top 5: Hard' record?");
				builder.setPositiveButton("YES",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								ds.truncateTable("hard");
								setHardList(ds
										.getTop3ScoreByLevel("hard"));
								listHard.refreshDrawableState();
								dialog.dismiss();
							}
						});
				builder.setNegativeButton("NO",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});

		ds.close();
	}

	private void setEasyList(ArrayList<Score> list) {
		if (list.size() > 0)
			btnClearEasy.setEnabled(true);
		else
			btnClearEasy.setEnabled(false);

		adapterEasy = new ScoreAdapter(this, list);
		listEasy = (ListView) findViewById(R.id.listEasy);
		listEasy.setAdapter(adapterEasy);
	}

	private void setHardList(ArrayList<Score> list) {
		if (list.size() > 0)
			btnClearHard.setEnabled(true);
		else
			btnClearHard.setEnabled(false);

		adapterHard = new ScoreAdapter(this, list);
		listHard = (ListView) findViewById(R.id.listHard);
		listHard.setAdapter(adapterHard);
	}

}
