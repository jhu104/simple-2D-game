/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.*;

import core.characters.Animal;
import core.characters.Healer;
import core.characters.Monster;
import core.characters.Player;
import core.exceptions.DescriptionException;

/**
 *
 * @author Jay
 */
public class GameGui extends JFrame {
	public GameGui () {
		super ("Game GUI");
		setLayout(new FlowLayout());
		World world = (World) getWorldView ();
		setContentPane(world);
		this.addKeyListener(world);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible (true);
	}

	// Creates JPanel to display in JFrame.
	// COMPLETE THIS METHOD TO DISPLAY YOUR OWN WIDGETS
	private JPanel getWorldView () {
		int column = 20, row = column;
		setSize(column*30,row*30+100);
		World world = new World(row, column);
		for(int i = 0;i < column;i++){
			try {
				world.addFromLine(i, Util.generateRow(column));
			} catch (DescriptionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i = 0;i < 2;i++){
			world.addCharacter(new Monster(world.nextX(),world.nextY(),world.getWorld(),50,5,world.getCharacters(),world.getPlayers()));
			world.addCharacter(new Healer(world.nextX(),world.nextY(),world.getWorld(),10,world.getCharacters(),world.getPlayers()));
			world.addCharacter(new Animal(world.nextX(),world.nextY(),world.getWorld(),world.getCharacters(),world.getPlayers()));
			world.addPlayer(new Player("p"+i,world.nextY(),world.nextY(),world.getWorld(),15,10,world.getCharacters(),world.getPlayers()));
		}
		world.setGUI();
		world.setOpaque(true);
		return world;
	}
}
