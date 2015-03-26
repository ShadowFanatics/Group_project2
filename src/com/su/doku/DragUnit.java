package com.su.doku;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DragUnit extends RelativeLayout {
	private static int images[] = { R.drawable.unit_back, R.drawable.unit1,
			R.drawable.unit2, R.drawable.unit3, R.drawable.unit4,
			R.drawable.unit5, R.drawable.unit6, R.drawable.unit7,
			R.drawable.unit8, R.drawable.unit9 };
	private static int backs[] = { R.drawable.light1, R.drawable.light2,
			R.drawable.light3, R.drawable.light4, R.drawable.light5 };
	private int number;
	private int queueIndex;
	private boolean isCorrect = false;
	private boolean isInQueue = false;
	private Timer timer;
	private int scoreLevel = 0;
	private ImageView back;
	public DragUnit(Context context, int number, int index) {
		super(context);

		back = new ImageView(context);
		back.setImageDrawable(getResources().getDrawable(backs[scoreLevel]));
		back.setPadding(0, 0, 0, 0);
		this.addView(back);

		ImageView num = new ImageView(context);
		num.setImageDrawable(getResources().getDrawable(images[number]));
		num.setPadding(0, 0, 0, 0);
		num.setBackgroundColor(0);
		this.addView(num);

		this.number = number;
		this.queueIndex = index;
	}

	private TimerTask timer_task = new TimerTask() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			// if(startFlag = true){
			Message msg = new Message();
			msg.what = 1;
			timer_handler.sendMessage(msg);
			// }
		}
	};

	private Handler timer_handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				if ( scoreLevel < 4 ) {
					scoreLevel++;
					back.setImageDrawable(getResources().getDrawable(backs[scoreLevel]));
				}
				else {
					timer.cancel();
				}
				
				break;
			default:
				break;
			}
		}
	};

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
		scoreLevel = 0;
		timer = new Timer();
		timer.schedule(timer_task, 1000, 1000);
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
	
	public int getScoreLevel() {
		return 5 - scoreLevel;//inverse
	}
}
