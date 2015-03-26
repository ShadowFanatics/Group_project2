package com.su.doku;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SudokuUnit extends RelativeLayout {
	private static int images[] = { R.drawable.unit_back, R.drawable.unit1,
			R.drawable.unit2, R.drawable.unit3, R.drawable.unit4,
			R.drawable.unit5, R.drawable.unit6, R.drawable.unit7,
			R.drawable.unit8, R.drawable.unit9 };
	private int indexX;
	private int indexY;
	private ImageView num;
	public SudokuUnit(Context context, int indexX, int indexY, int number) {
		super(context);
		this.indexX = indexX;
		this.indexY = indexY;
<<<<<<< HEAD

		ImageView back = new ImageView(context);
		back.setImageDrawable(getResources().getDrawable(images[0]));
		back.setPadding(0, 0, 0, 0);
		this.addView(back);

		num = new ImageView(context);
		num.setImageDrawable(getResources().getDrawable(images[number]));
		num.setPadding(0, 0, 0, 0);
		num.setBackgroundColor(0);
		this.addView(num);
=======
		setBackground(getResources().getDrawable(images[0]));
		setImageDrawable(getResources().getDrawable(images[number]));
		setPadding(0, 0, 0, 0);
>>>>>>> origin/硬硬
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
