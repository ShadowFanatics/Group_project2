package com.su.doku;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Ranking extends Activity{
	 	private ListView lv;  
	    private List<RankData> mListlist;
		private static int images[] = {R.drawable.record_number_1,
			R.drawable.record_number_2, R.drawable.record_number_3, R.drawable.unit4,
			R.drawable.unit5, R.drawable.unit6};
		private static final String TAG = "Ranking_activity";
	    /** Called when the activity is first created. */  
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.activity_rank);  
	        lv = new ListView(this); 
	        mListlist = new ArrayList<RankData>();
	        
	        initData();
	        
	        //list sort
	        Collections.sort(mListlist,new Comparator<RankData>() {
	        	
				@Override
				public int compare(RankData lhs, RankData rhs) {
					// TODO Auto-generated method stub
					//Date date1 = stringToDate(lhs.getTSec());
					//Date date2 = stringToDate(rhs.getTSec());
					int n1 = Integer.parseInt(lhs.getTSec());
					int n2 = Integer.parseInt(rhs.getTSec());
					if(n1 < n2){
						return 1;
					}
					return -1;
				}
			});
	        //setIcon
	        Iterator<RankData> it = mListlist.listIterator();
	        int i = 0;//這變數要重寫
	        while (it.hasNext()) {
	        	RankData rankData = it.next(); 
				rankData.setIcon(getResources().getDrawable(images[i]));
				i++;	
			}
				
	       
	        lv.setAdapter(new MyAdapter(this, mListlist));
	        
	        lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Toast.makeText(Ranking.this, "當前選中列表項為：" + position, Toast.LENGTH_SHORT).show();
					Ranking.this.finish();				
				}
	        	
	        });
	        
	        this.setContentView(lv);
	          
	    }
	    
	    private void initData() {
			mListlist.add(new RankData("30","2012-12-12 00:30", "lin"));
			mListlist.add(new RankData("25","2012-12-12 00:25","Chou"));
			mListlist.add(new RankData("37","2012-12-12 00:37","xia"));
			mListlist.add(new RankData("67","2012-12-12 00:67","tsu"));
			mListlist.add(new RankData("18","2012-12-12 00:18","hi"));
			
			//load file
	    	/*
			FileInputStream fileIn = null;
			BufferedReader br = null;
			BufferedInputStream bufferedInputStream = null;
			File file;
			File fileDir = new File(Environment.getExternalStorageDirectory().getPath());
			Toast.makeText(Ranking.this, fileDir.toString(), Toast.LENGTH_SHORT).show();
			String tmp;
			try {
				
				
				fileIn = openFileInput("file record.txt");
				//fileIn = new FileInputStream(fileDir + "/assets/record.txt");
				bufferedInputStream = new BufferedInputStream(fileIn);
				byte[] bufBytes = new byte[10];
				do{
					int c = bufferedInputStream.read(bufBytes);
					if(c == -1)
						break;
					else
						Log.v(TAG, new String(bufBytes));//test bufferedInputStream有沒有檔案
				}while(true);
				//if(file.exists()){
				//	Toast.makeText(Ranking.this, "yoyoyoyoyo", Toast.LENGTH_LONG).show();
				//}
				br = new BufferedReader(new InputStreamReader(fileIn));
				
				while ((tmp = br.readLine()) != null) {
					String[] spilt = tmp.split(",");
					mListlist.add(new RankData(spilt[0], spilt[1], spilt[2]));
				}
				
				bufferedInputStream.close();
				br.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
		}
	 
	    /*
	    public static Date stringToDate(String rankString) {
			ParsePosition position = new ParsePosition(0);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date dateValue = simpleDateFormat.parse(rankString, position);
	    	return dateValue;
		}*/
	    
	    
	    public class MyAdapter extends BaseAdapter {  
	    	  
	        private Context mContext;  
	        private List<RankData> mList;  
	      
	        public MyAdapter(Context context, List<RankData> list) {  
	            this.mContext = context;  
	            this.mList = list;  
	        }  
	      
	        @Override  
	        public int getCount() {  
	            return mList != null ? mList.size() : 0;  
	        }  
	      
	        @Override  
	        public Object getItem(int position) {  
	            return mList.get(position);  
	        }  
	      
	        @Override  
	        public long getItemId(int position) {  
	            return position;  
	        }  
	          
	        private class ViewHolder {  
	            private TextView textView1;  
	            private TextView textVeiw2;
	            private ImageView imageview;
	            private TextView timerView;
	            //private Button button;
	        }

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
	            ViewHolder holder = null;  
	            if (convertView == null) {
	            	
	            	LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            	convertView = li.inflate(R.layout.activity_rank, parent, false);
	            	//convertView = (LinearLayout)LayoutInflater.from(mContext).inflate(R.layout.activity_rank, null); 
	                holder = new ViewHolder();  
	                holder.textView1 = (TextView) convertView.findViewById(R.id.smalltv);  
	                holder.textVeiw2 = (TextView) convertView.findViewById(R.id.bigtv);
	                holder.timerView = (TextView) convertView.findViewById(R.id.timertv);
	                holder.imageview = (ImageView)convertView.findViewById(R.id.iv);
	               // holder.button = (Button)convertView.findViewById(R.id.bn);
	                
	                convertView.setTag(holder);
	            } else {  
	                holder = (ViewHolder) convertView.getTag();  
	            }  
	      
	            holder.textView1.setText(mList.get(position).getDate());  
	            holder.textVeiw2.setText(mList.get(position).getName());
	            holder.timerView.setText("記錄：" + mList.get(position).getTSec());
	            holder.imageview.setImageDrawable(mList.get(position).getIcon());
	            
	            /*holder.button.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
		                Toast.makeText(Ranking.this, "當前選中列表項為:sdfsdf", 
		                        Toast.LENGTH_SHORT).show();
		                Ranking.this.finish();
					}
				});*/
	            return convertView;  
			}
			
			
	      
	    } 
}
