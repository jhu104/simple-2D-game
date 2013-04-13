/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.characters;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import core.patches.Patch;

/**
 * 
 * @author jay
 *
 */
public class Player extends HealthCharacter{

	private String name;
	
	boolean ingame = false;
	final int blocksize = 24;
	final int nrofblocks = 15;
	final int scrsize = nrofblocks * blocksize;


	int  score;
	int deathcounter;
	int currentspeed = 3;
	short[] screendata;
	Timer timer;
	
	public Player(String name,int x,int y,Patch[][] _world,int health,int dmg,ArrayList<Character> characters,ArrayList<Player> players){
		super(x,y,_world,health,dmg, characters, players);
		this.name = name;
		screendata = new short[nrofblocks * nrofblocks];
		setImage("/images/animal.png");
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return name+"::Attributes"+super.toString()+"}";
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println("Moving");
		if (key == KeyEvent.VK_LEFT)
		{
			move('L');   
		}
		else if (key == KeyEvent.VK_RIGHT)
		{
			move('R'); 
		}
		else if (key == KeyEvent.VK_UP)
		{
			move('U'); 

		}
		else if (key == KeyEvent.VK_DOWN)
		{
			move('D'); 
		}
	}
}
