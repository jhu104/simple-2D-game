/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import core.World;
import core.characters.Animal;
import core.characters.CharacterFactory;
import core.characters.Healer;
import core.characters.Monster;
import core.characters.Player;
import core.exceptions.DescriptionException;
import core.patches.Patch;

/**
 *
 * @author Jay
 */
public class Main extends JFrame {

	public static void runTextVersion () {
		System.out.println ("Game - Text Output");

		int row = 20,column = row;
		/////////////////////////////////////////
		World world = new World(row,column);
		CharacterFactory  cFactory = new CharacterFactory(world);
		try {
			for(int i = 0;i < column;i++){
				world.addFromLine(i, Util.generateRow(column));
			}
			for(int i = 0;i < 2;i++){
				world.addCharacter(cFactory.createMonster(20,5));
				world.addCharacter(cFactory.createHealer(10));
				world.addCharacter(cFactory.createAnimal());
				world.addPlayer(cFactory.createPlayer("p"+i,50,10));
			}
		} catch (DescriptionException ex) {
			ex.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println(world.displayWholeWorld());
		world.startGame(null);
		//////////////////////////////////////
	}

	// Call this method to instantiate the main class
	public static void runGuiVersion () {
		new GameGui ();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		runTextVersion (); // comment out to run GUI version
//		runGuiVersion(); // uncomment for the GUI version
	}
}
