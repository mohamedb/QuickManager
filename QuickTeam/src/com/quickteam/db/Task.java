package com.quickteam.db;

public class Task {
	
	private int id;
	private int userId;
	private String title;
	
	private String subTaskOne;
	private int oneProgress=0;
	
	private String subTaskTwo;
	private int twoProgress=0;
	
	private String subTaskThree;
	private int threeProgress=0;
	
	private String dateStart;
	private String dateEnd;
	 

	public int getOneProgress() {
		return oneProgress;
	}

	public void setOneProgress(int oneProgress) {
		this.oneProgress = oneProgress;
		if(oneProgress>=100)
			this.oneProgress=100;
		if(oneProgress<=0)
			this.oneProgress=0;
		
	}

	public int getTwoProgress() {
		return twoProgress;
	}

	public void setTwoProgress(int twoProgress) {
		this.twoProgress = twoProgress;
		if(twoProgress>=100)
			this.twoProgress=100;
		if(twoProgress<=0)
			this.twoProgress=0;
	}

	public int getThreeProgress() {
		return threeProgress;
	}

	public void setThreeProgress(int threeProgress) {
		this.threeProgress = threeProgress;
		if(threeProgress>=100)
			this.threeProgress=100;
		if(twoProgress<=0)
			this.threeProgress=0;
	}

	

	public Task() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTaskTwo() {
		return subTaskTwo;
	}

	public void setSubTaskTwo(String subTaskTwo) {
		this.subTaskTwo = subTaskTwo;
	}

	public String getSubTaskThree() {
		return subTaskThree;
	}

	public void setSubTaskThree(String subTaskThree) {
		this.subTaskThree = subTaskThree;
	}

	public String getSubTaskOne() {
		return subTaskOne;
	}

	public void setSubTaskOne(String subTaskOne) {
		this.subTaskOne = subTaskOne;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
