package core.characters;

import java.util.ArrayList;

import core.patches.Patch;

/**
 * 
 * @author jay
 *
 */
public class HealthCharacter extends Character {

	private int health;
	private int dmg;
	
	public HealthCharacter(int x,int y,Patch[][] _world, int health,int dmg,ArrayList<Character> characters,ArrayList<Player> players){
		super(x,y,_world, characters, players);
		this.health = health;
		this.dmg = dmg;
	}
	
	public void move(char direction){
		super.move(direction);
		decreaseHealth(_world[getY()][getX()].getCost());
	}
	
	public void decreaseHealth(int dmg){
		health -= dmg;
	}
	
	public void increaseHealth(int heal){
		health += heal;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int attack(){
		return dmg;
	}
	
	public String toString(){
		return "{'health':"+health+", "+"damage:"+dmg+", Location"+super.toString()+"}";
	}
}
