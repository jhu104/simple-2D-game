/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.characters;


import java.awt.Image;
import javax.swing.ImageIcon;

import core.patches.Patch;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


/**
 *
 * @author Jay
 */
public class Animal extends Character{

	public  Animal (int x, int y,Patch[][] _world,ArrayList<Character> characters,ArrayList<Player> players){
		super(x,y,_world, characters, players);
		setImage("C://images//visa.png");
	}

	public void Drawanimals(Graphics2D g2d, int x, int y) {
		g2d.drawImage(image, x, y, null);
	}
	
	public String toString(){
		return "Animal::"+super.toString();
	}
}
