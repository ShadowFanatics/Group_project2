package com.su.doku;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends Activity
{
	//timer variables
	private Timer gameTimer;
	private TextView timeTextView;
	private int tsec=0,csec=0,cmin=0;
	//private boolean startFlag = true;
	
	private Button finishButton;
	
	private GridLayout gridLayout;
	private SudokuUnit[][] sudokuUnits;
	private ImageView imageView1;
	private Button resetButton;
	private Button backButton;
	
	private int answer[][] = new int[9][9];
	private int givenNumber[][] = new int[9][9];
	private int time = 0;
	private int number = 1;
	
	private final int sudokuSize = 9;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		produce sudokuProduce = new produce();
		answer = sudokuProduce.generatePuzzleMatrix();
		givenNumber = sudokuProduce.generatePuzzleQuestion(1);
		
		initializeViews();
		receiveData();
	}
	
	/*
	@Override
	protected void onPause()
	{
		super.onPause();
		
	}*/
	
	private void initializeViews()
	{
		timeTextView = (TextView) findViewById(R.id.textView_time);
		timeTextView.setText(String.valueOf(time));
		
		//�ΨӨ��o�ù��j�p
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int preferSize = displayMetrics.widthPixels/sudokuSize;
		
		gridLayout = (GridLayout) findViewById(R.id.GridLayout1);
		sudokuUnits = new SudokuUnit[sudokuSize][sudokuSize];
		for(int i = 0; i < sudokuSize; i++)
		{
			for(int j = 0; j < sudokuSize; j++)
			{
				if ( givenNumber[i][j] == 1 ) {
					sudokuUnits[i][j] = new SudokuUnit(GameActivity.this, i, j, answer[i][j]);
				}
				else {
					sudokuUnits[i][j] = new SudokuUnit(GameActivity.this, i, j, 0);
				}
				sudokuUnits[i][j].setOnDragListener(dragListener);
				gridLayout.addView(sudokuUnits[i][j], i*sudokuSize+j);
				//����e
				sudokuUnits[i][j].getLayoutParams().height = preferSize;
				sudokuUnits[i][j].getLayoutParams().width = preferSize;
			}
		}
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView1.getLayoutParams().height = preferSize;
		imageView1.getLayoutParams().width = preferSize;
		imageView1.setOnTouchListener(touchListener);
		resetButton = (Button) findViewById(R.id.button_reset);
		resetButton.setOnClickListener(resetButtonListener);
		backButton = (Button) findViewById(R.id.button_back);
		backButton.setOnClickListener(backButtonListener);
		
		finishButton = (Button) findViewById(R.id.button_finish);
		finishButton.setOnClickListener(finishButtonListener);
		
		gameTimer = new Timer();
		gameTimer.schedule(timer_task, 0, 1000);
	}
	
	private void receiveData()
	{
		Bundle bundle = getIntent().getExtras();
		if(bundle.getBoolean("isStart"))
		{
			// TODO �ͦ��s�C��
		}
		else
		{
			// TODO Ū���������C����T
		}
	}
	
	private OnTouchListener touchListener = new OnTouchListener()
	{
		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			//�e�̧ڶö�,�]�S�Ψ�; ��̬O���a�����
			ClipData clipData = ClipData.newPlainText("number", number+"");
			//�Ф@�Ӹ�imageView1���o�@�˪�shadow
			View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(imageView1);
			v.startDrag(clipData, shadowBuilder, null, 0);
			return true;
		}
	};
	
	private OnDragListener dragListener = new OnDragListener()
	{
		@Override
		public boolean onDrag(View v, DragEvent event)
		{
			switch(event.getAction())
			{
			case DragEvent.ACTION_DRAG_STARTED:
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				break;
			case DragEvent.ACTION_DRAG_LOCATION:
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				break;
			case DragEvent.ACTION_DROP:	//��Q�즲�����󲾰ʨ즹view���d��é�U��
				SudokuUnit sudokuUnit = (SudokuUnit) v;
				//���o���a���
				int data = Integer.parseInt(event.getClipData().getItemAt(0).getText().toString());
				if(data == answer[sudokuUnit.getIndexX()][sudokuUnit.getIndexY()])
				{
					sudokuUnit.setNumber(data);
				}
				break;
			case DragEvent.ACTION_DRAG_ENDED:
				break;
			default:
				return false;
			}
			return true;
		}
	};
	
	private Button.OnClickListener resetButtonListener = new Button.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			showResetDialog();
		}
	};
	
	private Button.OnClickListener backButtonListener = new Button.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			showBackDialog();
		}
	};
	
	private Button.OnClickListener finishButtonListener = new Button.OnClickListener(){
		@Override
		public void onClick(View v)
		{
			showBackDialog();
			//restart
			//tsec = 0;
			//timeTextView.setTag("00:00");
		}
	};
	
	private TimerTask timer_task = new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			//if(startFlag = true){
			tsec++;
			Message msg = new Message();
			msg.what = 1;
			timer_handler.sendMessage(msg);
			//}
		}
	};
	
	private Handler timer_handler = new Handler(){
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				csec = tsec % 60;
				cmin = tsec / 60;
				String s = "";
				if(cmin < 10){
					s = "0" + cmin;
				}
				else {
					s = "" + cmin;
				}
				if (csec < 10) {
					s = s + ":0" + csec;
				} else {
					s = s + ":" + csec;
				}
				timeTextView.setText(s);
				//if(startFlag = false){
				//	gameTimer.cancel();
				//}
				break;

			default:
				break;
			}
		}
	};
	
	
	
	private void showResetDialog()
	{
		new AlertDialog.Builder(GameActivity.this)
		.setTitle(R.string.choice_reset)
		.setMessage(R.string.confirm_reset)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO ���m�C��
			}
		})
		.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				//nothing
			}
		})
		.show();
	}
	
	private void showBackDialog()
	{
		new AlertDialog.Builder(GameActivity.this)
		.setTitle(R.string.choice_back)
		.setMessage(R.string.confirm_back)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO �^�ǹC�����
				Bundle bundle = new Bundle();
				
				Intent intent = new Intent();
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				
				//back to title
				GameActivity.this.finish();
			}
		})
		.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				//nothing
			}
		})
		.show();
	}
}
