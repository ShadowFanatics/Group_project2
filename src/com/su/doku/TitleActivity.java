package com.su.doku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TitleActivity extends Activity
{
	private static final int GAME_REQUEST = 0;	//need >= 0
	private static final int RANK_REQUEST = 1;
	private Button startButton;
	private Button continueButton;
	private Button rankButton;
	private Button exitButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);
		
		initializeViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.title, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id == R.id.action_help)
		{
			showHelpDialog();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		//�Ū�...�h�B�[�@�U
		super.onActivityResult(requestCode, resultCode, data);
		
		//�����Ӧ۹C��(GameActivity.setResult())��result
		if(requestCode == GAME_REQUEST)
		{
			if(resultCode == RESULT_OK)	//�Ǧ^��result���`
			{
				// TODO �@save������(ex.�x�s���������C�� or �x�s�w�������C���å[�J�Ʀ�])
			}
		}
	}
	
	private void initializeViews()
	{
		startButton = (Button) findViewById(R.id.button_start);
		startButton.setOnClickListener(startButtonListener);
		continueButton = (Button) findViewById(R.id.button_continue);
		continueButton.setOnClickListener(continueButtonListener);
		rankButton = (Button) findViewById(R.id.button_rank);
		rankButton.setOnClickListener(rankButtonListener);
		exitButton = (Button) findViewById(R.id.button_exit);
		exitButton.setOnClickListener(exitButtonListener);
	}
	
	private Button.OnClickListener startButtonListener = new Button.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			showStartDialog();
		}
	};
	
	private Button.OnClickListener continueButtonListener = new Button.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			// TODO �ˬd�O�_�����������C��,�Y�S���h�L�k�~��C��(or�۰ʶ}�s�C��?)
			
			Bundle bundle = new Bundle();
			bundle.putBoolean("isStart", false);	//���a�ëD��FStart(��Fcontinue)
			
			// TODO Ū��save�ɪ����(or���e�w�gŪ�n�F)��ibundle
			
			Intent intent = new Intent();
			intent.putExtras(bundle);
			intent.setClass(TitleActivity.this, GameActivity.class);
			
			//GameActivity������,�|�Nresult�HrequestCode == GAME_REQUEST
			//�I�sTitleActivity��onActivityResult
			startActivityForResult(intent, GAME_REQUEST);
		}
	};
	
	private Button.OnClickListener rankButtonListener = new Button.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			// TODO startActivity�Ʀ�]
			
			Bundle bundle = new Bundle();
			bundle.putBoolean("isStart", false);
			Intent intent = new Intent();
			intent.putExtras(bundle);
			intent.setClass(TitleActivity.this, Ranking.class);
			startActivityForResult(intent, RANK_REQUEST);
			
		}
	};
	
	private Button.OnClickListener exitButtonListener = new Button.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			showExitDialog();
		}
	};
	
	private void showHelpDialog()
	{
		new AlertDialog.Builder(TitleActivity.this)
		.setTitle(R.string.action_help)
		.setMessage(R.string.help_message)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				//nothing
			}
		})
		.show();
	}
	
	private void showStartDialog()
	{
		new AlertDialog.Builder(TitleActivity.this)
		.setTitle(R.string.choice_start)
		.setMessage(R.string.confirm_start)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Bundle bundle = new Bundle();
				bundle.putBoolean("isStart", true);	//���a��FStart
				
				Intent intent = new Intent();
				intent.putExtras(bundle);
				intent.setClass(TitleActivity.this, GameActivity.class);
				
				//GameActivity������,�|�Nresult�HrequestCode == GAME_REQUEST
				//�I�sTitleActivity��onActivityResult
				startActivityForResult(intent, GAME_REQUEST);
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
	
	private void showExitDialog()
	{
		new AlertDialog.Builder(TitleActivity.this)
		.setTitle(R.string.choice_exit)
		.setMessage(R.string.confirm_exit)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				finish();
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