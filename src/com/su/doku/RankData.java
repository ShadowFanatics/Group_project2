package com.su.doku;

import android.graphics.drawable.Drawable;

public class RankData {
	private String name;
	private String tsec;
	private String date;
	private Drawable icon;
	
	public String getName() {
		return name;
	}
	
	public String getTSec() {
		return tsec;
	}
	
	public String getDate() {
		return date;
	}
	
	public Drawable getIcon(){
		return icon;
	}
	
    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
	
	public RankData(String tsec,String date, String name) {
		this.tsec = tsec;
		this.date = date;
		this.name = name;
	}
}
