package com.su.doku;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DragUnit extends RelativeLayout {
	private static int images[] = { R.drawable.unit_back, R.drawable.unit01,
			R.drawable.unit02, R.drawable.unit03, R.drawable.unit04,
			R.drawable.unit05, R.drawable.unit06, R.drawable.unit07,
			R.drawable.unit08, R.drawable.unit09 };
	private static int backs[] = { R.drawable.light1, R.drawable.light2,
			R.drawable.light3, R.drawable.light4, R.drawable.light5 };
	private static int breaks[] = { R.drawable.slit1, R.drawable.slit2,
		R.drawable.slit3, R.drawable.slit4 };
	private int number;
	private int queueIndex;
	private boolean isCorrect = false;
	private boolean isInQueue = false;
	private Timer timer;
	private int scoreLevel = 0;
	private int breakLevel = 0;
	private ImageView back;
	private ImageView slit;
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
		
		slit = new ImageView(context);
		slit.setImageDrawable(getResources().getDrawable(breaks[breakLevel]));
		slit.setPadding(0, 0, 0, 0);
		slit.setBackgroundColor(0);
		this.addView(slit);

		this.number = number;
		this.queueIndex = index;
		timer = new Timer();
		timer.schedule(timer_task, 1000, 1000);
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
				if ( breakLevel < 3 ) {
					breakLevel++;
					slit.setImageDrawable(getResources().getDrawable(breaks[breakLevel]));
				}
				else {
					if ( scoreLevel < 4 ) {
						breakLevel = 0;
						slit.setImageDrawable(getResources().getDrawable(breaks[breakLevel]));
						scoreLevel++;
						back.setImageDrawable(getResources().getDrawable(backs[scoreLevel]));
					}
					else {
						//timer.cancel();
					}
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
		//timer = new Timer();
	//	timer.schedule(timer_task, 1000, 1000);
		isInQueue = true;
	}

	public void removeFromQueue() {
		isInQueue = false;
		
		//timer = null;
	}

	public boolean isInQueue() {
		return isInQueue;
	}

	public int getIndex() {
		return queueIndex;
	}
	
	public int getScoreLevel() {
		return scoreLevel;//inverse
	}
}
