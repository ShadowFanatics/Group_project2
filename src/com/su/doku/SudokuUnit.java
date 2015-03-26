package com.su.doku;

import android.content.Context;
import android.widget.ImageView;

public class SudokuUnit extends ImageView {
	private static int images[] = { R.drawable.unit0, R.drawable.unit1,
			R.drawable.unit2, R.drawable.unit3, R.drawable.unit4,
			R.drawable.unit5, R.drawable.unit6, R.drawable.unit7,
			R.drawable.unit8, R.drawable.unit9 };
	private int indexX;
	private int indexY;
	public SudokuUnit(Context context, int indexX, int indexY, int number) {
		super(context);
		this.indexX = indexX;
		this.indexY = indexY;
		setImageDrawable(getResources().getDrawable(images[number]));
		setPadding(0, 0, 0, 0);
	}

	public void setNumber(int number) {
		setImageDrawable(getResources().getDrawable(images[number]));
	}

	public int getIndexX() {
		return indexX;
	}

	public int getIndexY() {
		return indexY;
	}
}
