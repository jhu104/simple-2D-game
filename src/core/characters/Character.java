package core.characters;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import core.patches.Patch;


/**
 * 
 * @author jay
 *
 */
public class Character {

	private int x;
	private int y;
	private Image image;
	protected Patch[][] _world;
	protected ArrayList<Character> characters;
	protected ArrayList<Player> players;


	public Character(int x, int y,Patch[][] _world,ArrayList<Character> characters,ArrayList<Player> players){
		this.characters = characters;
		this.players = players;
		this._world = _world;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y){
		this.y = y;
	}

	public void move(char direction){
		boolean canMove = true;
		if(direction == 'L' && x > 0) {
			for(Character character : characters){
				if(character.getY() == y && character.getX() == x-1)
					canMove = false;
			}
			if(canMove) x--;
		}
		else if(direction == 'R' && x < _world.length -1) {
			for(Character character : characters){
				if(character.getY() == y && character.getX() == x+1)
					canMove = false;
			}
			if(canMove) x++;
		}
		else if(direction == 'U' && y > 0) {
			for(Character character : characters){
				if(character.getX() == x && character.getY() == y-1)
					canMove = false;
			}
			if(canMove) y--;
		}
		else if(direction == 'D' && y < _world[0].length -1) {
			for(Character character : characters){
				if(character.getX() == x && character.getY() == y+1)
					canMove = false;
			}
			if(canMove) y++;
		}
	}

	public String toString(){
		return "{'x':"+x+", 'y':"+y+"}";
	}
	
	protected void setImage(String location){
		ImageIcon a = new ImageIcon(location);
		image = a.getImage(); 
	}

	public Image getImage(){
		return image;
	}
	
	/*public boolean canMove(int x, int y, char direction) {
		if(direction == 'L'){
			if(x > 0)	return true;
			return false;
		}
		if(direction == 'R'){
			if(x < _world.length-1)	return true;
			return false;
		}
		if(direction == 'U'){
			if(y > 0)	return true;
			return false;
		}
		if(direction == 'D'){
			if(y < _world[0].length-1)	return true;
			return false;
		}
		return false;
	}*/
}
