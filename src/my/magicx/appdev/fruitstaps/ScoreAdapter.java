package my.magicx.appdev.fruitstaps;

import java.util.ArrayList;

import my.magicx.appdev.fruitstaps.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ScoreAdapter extends BaseAdapter {
	
	Score ScoreObj;
	ArrayList<Score> scoreList;
	LayoutInflater inflater;
	
	public ScoreAdapter(Context context, ArrayList<Score> list){
		inflater = LayoutInflater.from(context);
		this.scoreList = list;
	}
	
	public ArrayList<Score> getList(){
		return scoreList;
	}

	@Override
	public int getCount() {
		return scoreList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextHolder holder;
		if (convertView == null){
			convertView = inflater.inflate(R.layout.row, null);
			
			holder = new TextHolder();
			holder.topNo = (TextView) convertView.findViewById(R.id.txtNo);
			holder.pScore = (TextView) convertView.findViewById(R.id.txtPlayerScore);
			holder.pName = (TextView) convertView.findViewById(R.id.txtPlayerName);
		} else {
			holder = (TextHolder) convertView.getTag();
		}
		System.out.println(scoreList.toString());
		if (scoreList != null) {
			holder.topNo.setText(scoreList.get(position).getTopNo());
			holder.pScore.setText(scoreList.get(position).getPlayerScore());
			holder.pName.setText(scoreList.get(position).getPlayerName());
		}
		
		return convertView;
	}
	
	static class TextHolder {
		TextView topNo;
		TextView pScore;
		TextView pName;
	}

}
