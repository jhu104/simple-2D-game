package core.patches;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import core.World;
import core.exceptions.PatchException;

/**
 * The Patch class is a parent class of the different
 * patch types which are placed in the World object.
 *
 * @author jay
 */
public class Patch {

	private int _cost; // _cost is the energy required for a character to move across this patch of land.
	private char _letter; // _letter is the letter to use when printing the patch
	String description;
	protected Image image;

	public Patch (int cost, char letter) {
		_cost = cost;
		_letter = letter;
		if(letter == 'M'){
			image = World.createImageIcon("/images/mountain.png").getImage();
		}
		else if(letter == 'R'){
			image = World.createImageIcon("/images/river.png").getImage();
		}
		else if(letter == 'D'){
			image = World.createImageIcon("/images/desert.png").getImage();
		}
		else if(letter == 'F'){
			image = World.createImageIcon("/images/forest.png").getImage();
		}
	}
	/**
	 * 
	 * @param c
	 * @return
	 * @throws PatchException 
	 */
	// Helper method for creating a patch type from
	// a character.
	// Needs extending to cope with other patch types.
	static Patch fromCharacter (char c) throws PatchException {
		switch (c) {
		case 'g': case 'G': 
			return new GrassPatch ();

		case 'd': case 'D': 
			return new  DesertPatch();

		case 'f': case 'F': 
			return new  ForestPatch();


		case 'm': case 'M': 
			return new  MountainPatch();

		case 'r': case 'R': 
			return new  RiverPatch();

		default:
			throw new PatchException (c);

		}
	}

	public void drawPatch(Graphics2D graphic,int x,int y) {
		graphic.drawImage(image,x*30,y*30,null);
	}
	
	// accessor to the cost variable
	public int getCost () {
		return _cost;
	}

	// accessor to the letter variable
	public char getLetter () {
		return _letter;
	}

	// convert the patch to a string, using the _letter.
	public String toString () {
		return "" + Character.toUpperCase(getLetter());
	}
}