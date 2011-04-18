package com.example.testsurf;

/*This is the user class 
 * need I say more?
 */
public class User {
	private int money;
	private int score;
	private String username;
	private int lives;
	public User(int money_starting, int score_starting, int gamelives, String name){
		money = money_starting;
		score = score_starting;
		username = name;
		lives = gamelives;
	}
	public int getMoney(){
		return money;
	}
	public void setMoney(int value){
		money = value;
	}
	public void incMoney(int value){
		money = money + value;
	}
	public void decMoney(int value){
		money = money - value;
	}
	public int getScore(){
		return score;
	}
	public void setScore(int value){
		score = value;
	}
	public void incScore(int value){
		score = score + value;
	}
	public void decScore(int value){
		score = score - value;
	}
	public String getUsername(){
		return username;
	}
	public void setUsername(String name){
		username = name;
	}
	public void decLives(int value){
		lives -= value;
	}
	public void incLives(int value){
		lives += value;
	}
	public int getLives(){
		return lives;
	}
}
