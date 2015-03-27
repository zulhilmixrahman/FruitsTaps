package my.magicx.appdev.fruitstaps;

import java.util.ArrayList;

import my.magicx.appdev.fruitstaps.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class FruitsGraphics extends BaseAdapter implements View.OnClickListener  {
	
	private Context context;
	private int width;
	private int column;
	ArrayList<Integer> mThumbIds = new ArrayList<Integer>();
	
	public FruitsGraphics(Context cntxt, int column, int hole, int width){
		context = cntxt;
		this.width = width;
		this.column = column;
		for (int loop = 0; loop < hole; loop++) {
			mThumbIds.add(R.drawable.hole);
		}
	}
	
	@Override
	public boolean areAllItemsEnabled() {
		return super.areAllItemsEnabled();
	}

	@Override
	public boolean isEnabled(int position) {
		return super.isEnabled(position);
	}

	@Override
	public int getCount() {
		return mThumbIds.size();
	}

	@Override
	public Integer getItem(int position) {

		Integer item = 0;

		for (int i = 0; i < getCount(); i++) {
			if (mThumbIds.get(i).equals(item)) {
				item = mThumbIds.get(i);
			}
		}

		return item;
	}

	public void setItem(Integer index, Integer item) {
		mThumbIds.set(index, item);
	}

	@Override
	public long getItemId(int position) {

		long item = 0;

		for (int i = 0; i < getCount(); i++) {
			if (mThumbIds.get(i).equals(position)) {
				item = mThumbIds.get(i);
			}
		}
		return item;
	}

	// create a new ImageView for each item referenced by the Adapter
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			
			int size = (width-40)/column;
			
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(size, size));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(1, 1, 1, 1);
		} else {
			imageView = (ImageView) convertView;
		}
		imageView.setImageResource(mThumbIds.get(position));
		return imageView;
	}
	
	public ArrayList<Integer> getmThumbIds() {
		return mThumbIds;
	}

	@Override
	public void onClick(View v) {
		this.notifyDataSetChanged();
	}
	
}
