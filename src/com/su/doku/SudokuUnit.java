package com.su.doku;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SudokuUnit extends RelativeLayout {
	private static int images[] = { R.drawable.unit_back, R.drawable.unit01,
			R.drawable.unit02, R.drawable.unit03, R.drawable.unit04,
			R.drawable.unit05, R.drawable.unit06, R.drawable.unit07,
			R.drawable.unit08, R.drawable.unit09 };
	private int indexX;
	private int indexY;
	private ImageView num;
	public SudokuUnit(Context context, int indexX, int indexY, int number) {
		super(context);
		this.indexX = indexX;
		this.indexY = indexY;

		ImageView back = new ImageView(context);
		back.setImageDrawable(getResources().getDrawable(images[0]));
		back.setPadding(0, 0, 0, 0);
		this.addView(back);

		num = new ImageView(context);
		num.setImageDrawable(getResources().getDrawable(images[number]));
		num.setPadding(0, 0, 0, 0);
		num.setBackgroundColor(0);
		this.addView(num);
	}

	public void setNumber(int number) {
		num.setImageDrawable(getResources().getDrawable(images[number]));
	}

	public int getIndexX() {
		return indexX;
	}

	public int getIndexY() {
		return indexY;
	}
}
