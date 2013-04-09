/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.characters;

import java.util.ArrayList;

import core.patches.Patch;


/**
 *
 * @author jay
 */
public class Monster extends HealthCharacter{

	public Monster(int x,int y,Patch[][] _world,int health,int dmg,ArrayList<Character> characters,ArrayList<Player> players){
		super(x,y,_world,health,dmg, characters, players);
	}
	
	public String toString(){
		return "Monster::"+super.toString();
	}
}
