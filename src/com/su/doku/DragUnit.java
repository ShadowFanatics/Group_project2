package com.su.doku;

import android.content.Context;
import android.widget.ImageView;

public class DragUnit extends ImageView {
	private static int images[] = { R.drawable.unit0, R.drawable.unit1,
			R.drawable.unit2, R.drawable.unit3, R.drawable.unit4,
			R.drawable.unit5, R.drawable.unit6, R.drawable.unit7,
			R.drawable.unit8, R.drawable.unit9 };
	private int number;
	private int queueIndex;
	private boolean isCorrect = false;
	private boolean isInQueue = false;
	public DragUnit(Context context, int number, int index) {
		super(context);
		setImageDrawable(getResources().getDrawable(images[number]));
		setPadding(0, 0, 0, 0);
		this.number = number;
		this.queueIndex = index;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void correct() {
		isCorrect = true;
	}
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void showInQueue() {
		isInQueue = true;
	}
	
	public void removeFromQueue() {
		isInQueue = false;
	}
	
	public boolean isInQueue() {
		return isInQueue;
	}
	
	public int getIndex() {
		return queueIndex;
	}
}
