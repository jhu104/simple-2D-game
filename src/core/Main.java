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
import core.characters.Healer;
import core.characters.Monster;
import core.characters.Player;
import core.exceptions.DescriptionException;

/**
 *
 * @author Jay
 */
public class Main extends JFrame {

	// Call this method to run the text version of the game.
	// Complete the method for Stage 2.
	public static String generateRow(int column){
		String result = "";
		char[] patchTypes = {'g','f','m','d','r'};
		Random rand = new Random(System.nanoTime());
		for(int i = 0;i < column;i++){
			int randNum = rand.nextInt(patchTypes.length);
			result += patchTypes[randNum];
		}
		return result;
	}

	public static void runTextVersion () {
		System.out.println ("Game - Text Output");

		int row = 10,column = row;

		/////////////////////////////////////////
		World w = new World(row,column);

		try {
			for(int i = 0;i < column;i++){
				w.addFromLine(i, generateRow(column));
			}
			for(int i = 0;i < 2;i++){
				w.addCharacter(new Monster(w.nextX(),w.nextY(),w.getWorld(),50,5,w.getCharacters(),w.getPlayers()));
				w.addCharacter(new Healer(w.nextX(),w.nextY(),w.getWorld(),10,w.getCharacters(),w.getPlayers()));
				w.addCharacter(new Animal(w.nextX(),w.nextY(),w.getWorld(),w.getCharacters(),w.getPlayers()));
				w.addPlayer(new Player("p"+i,w.nextY(),w.nextY(),w.getWorld(),15,10,w.getCharacters(),w.getPlayers()));
			}
			//			h = new Healer();
			//			a = new Animal();
		} catch (DescriptionException ex) {
			ex.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println(w.displayWholeWorld());
		w.startGame(null);
		//////////////////////////////////////
	}

	// Call this method to instantiate the main class
	public static void runGuiVersion () {
		GameGui gui = new GameGui ();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(300,330);
		gui.setVisible (true);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
//		runTextVersion (); // comment out to run GUI version
		runGuiVersion (); // uncomment for the GUI version
	}
}
