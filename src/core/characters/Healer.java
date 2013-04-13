/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.characters;

import java.util.ArrayList;
import core.patches.Patch;



/**
 *
 * @author Jay
 */
public class Healer extends Character{

	private int healAmount;

	public Healer(int x,int y,Patch[][] _world, int healAmount,ArrayList<Character> characters,ArrayList<Player> players){
		super(x, y,_world, characters, players);
		setImage("/images/healer.png");
		this.healAmount = healAmount;
	}

	//healer heals a player
	public int heal(){
		return healAmount;
	}
	
	public String toString(){
		return "Healer::{'heal':"+healAmount+", Attributes"+super.toString();
	}
}
