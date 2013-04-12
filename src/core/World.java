package core;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import core.characters.Animal;
import core.characters.Character;
import core.characters.Healer;
import core.characters.Monster;
import core.characters.Player;
import core.exceptions.DescriptionException;
import core.patches.Patch;


/**
 * The World class holds a 2D array of patches.
 * Methods are also provided to set up the array from
 * input data.
 *
 * @author Jay
 */
public class World extends JPanel implements ActionListener {

	private boolean inGame;
	private Patch[][] _world; // array to hold the world definitions
	private ArrayList<Character> characters;
	private ArrayList<Player> players;
	private Random xRand;
	private Random yRand;
	private int[] availableRow;
	private int rowCounter;
	private int iterations;
	private int currPlayer;
	private boolean isGUI;
	private boolean isInit;

	final int scrsize; //nrofblocks * blocksize;

	/**
	 *	To create a World instance, provide the number of 
	 * rows and columns for the landscape.
	 * It is an error for the rows or columns to be 0 or less.
	 * @param rows amount of rows
	 * @param columns amount of columns
	 */
	public World (int rows, int columns) {
		assert (rows > 0 && columns > 0);
		inGame = false;
		_world = new Patch[rows][columns];
		xRand = new Random(System.nanoTime());
		yRand = new Random(System.nanoTime());
		availableRow = new int[columns];
		rowCounter = 0;
		iterations = 0;
		characters = new ArrayList<Character>();
		players = new ArrayList<Player>();
		currPlayer = 0;
		isGUI = false;
		scrsize = rows * 30;
		//gui related settings
		setFocusable(true);
		setBackground(Color.black);
		setDoubleBuffered(true);
		addKeyListener(new World.TAdapter());
	}

	/**
	 * helps in generating a random value for x
	 * @return value of x where the value is less than the size of the array of _world[0] subarray
	 */
	public int nextX(){
		return xRand.nextInt(_world[0].length);
	}

	/**
	 * helps in generating a random number for y
	 * @return value of y where the value is less than the size of _world array
	 */
	public int nextY(){
		return availableRow[yRand.nextInt(rowCounter)];
	}

	public void setGUI() {
		isGUI = true;
	}

	/**
	 *  Method to add a row of patches to the world.
	 *	First argument is the row number.
	 *  Second argument is a string, each character
	 *  representing a patch name.
	 * @param row which line to add
	 * @param description the patches to add
	 * @throws DescriptionException if amount of patches is longer than each row of _world
	 */
	public  void addFromLine (int row, String description) throws DescriptionException {
		if (description.length () != _world[0].length) {
			throw new DescriptionException (description.length (), _world[0].length);
		}
		// COMPLETE THIS METHOD
		// Break the string into characters.
		// For each character, create an appropriate patch instance.
		// Place the patch in the world at the given row number,
		// and a column number based on the position of the character
		// in the description string.

		// For each character, create an appropriate patch instance.
		Patch[] Newpatch = new Patch[description.length()]; 
		availableRow[rowCounter++] = row;
		for (int index = 0; index < Newpatch.length; index++){
			Newpatch[index] = new Patch(row, description.charAt(index));
		}          

		_world[row] = Newpatch;
	}

	// Convert the 2D array of patches to a string.
	public String displayWorld () {
		String output = "";
		// COMPLETE CODE HERE TO CONVERT ARRAY INTO A STRING 
		for (int row=0; row < _world.length; row++) {
			if(_world[row][0] != null){
				for (int col=0; col < _world[0].length; col++){ 
					output+=_world[row][col].toString();                     
				}
				output+="\n";
			}
		}
		return output;
	}

	public String toString () {
		return "World: \n" + displayWorld ();
	}

	/**
	 * Display a grid of the world
	 * @return String representation of Patch world with characters and players on top
	 */
	public String displayWholeWorld() {
		String output = "";
		String[][] display =new String[_world.length][_world[0].length]; //string representation of patch world

		//create copy of _world 2D array in string form
		for (int row=0; row < _world.length; row++) {
			if(_world[row][0] != null){
				for (int col=0; col < _world[0].length; col++){ 
					display[row][col] = _world[row][col].toString();                     
				}
			}
		}

		//replace patch in display with character name and player name
		for(int i = 0;i < characters.size();i++){
			Character character = characters.get(i);
			if(character instanceof Healer)
				display[character.getX()][character.getY()] = "cH";
			if(character instanceof Animal)
				display[character.getX()][character.getY()] = "cA";
			if(character instanceof Monster)
				display[character.getX()][character.getY()] = "cM";
		}
		for(int i = 0; i < players.size();i++){
			display[players.get(i).getX()][players.get(i).getY()]="p"+players.get(i).getHealth();
		}

		//turn array of strings into a single string to be returned
		for (int row=0; row < display.length; row++) {
			if(display[row][0] != null){
				for (int col=0; col < display[0].length; col++){ 
					output+=display[col][row].toString()+" ";                     
				}
				output+="\n";
			}
		}
		return output;
	}

	// accessor to a square of the world.
	// No error checking performed.
	public Patch getPatch (int row, int column) {
		return _world[row][column];
	}

	/**
	 * insert characters into the world
	 * @param character
	 */
	public void addCharacter(Character character){
		characters.add(character);
	}

	/**
	 * insert players into the world
	 * @param player
	 */
	public void addPlayer(Player player){
		players.add(player);
	}

	/**
	 * return reference to the _world
	 * @return
	 */
	public Patch[][] getWorld(){
		return _world;
	}

	/**
	 * return reference to list of characters
	 * @return
	 */
	public ArrayList<Character> getCharacters(){
		return characters;
	}

	/**
	 * return reference to list of players
	 * @return
	 */
	public ArrayList<Player> getPlayers(){
		return players;
	}

	/**
	 * read initialization information from a URL to a file to initialize world file should contain only two value, row and column
	 * @param filename
	 * @return
	 */
	public static World readFromFile (String filename){
		int row=0, column=0;
		try {
			Scanner scanner = new Scanner(new File(filename));
			if(scanner.hasNext()){
				String s = scanner.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(s,",");
				if(tokenizer.hasMoreTokens()){
					row = Integer.parseInt(tokenizer.nextToken());
					column = Integer.parseInt(tokenizer.nextToken());
				}
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return new World(row,column);

	}

	/**
	 * start the game, while there is still players or 100 has not pass then game will continue
	 */
	public void startGame(Graphics2D graphic){
		System.out.println("Game started");
		System.out.println(players.size()+" players");
		Scanner scanner = null;
		if(!isGUI){
			scanner = new Scanner(System.in);
		}
		int m=0;
		char[] directions = {'L','R','U','D'}; //used for random movement of characters
		while(players.size() > 0 || iterations < 100){
			if(!isGUI) {
				iterations++; //increment iteration in beginning if not using GUI
				for(int i = 0;i < players.size();i++){ //for each player wait for input
					Player player = players.get(i);
					int px = player.getX();
					int py = player.getY();
					int nearByPlayerIndex = playerNear(px,py); //look for nearby player to possibily attack
					Player nearByPlayer = null;
					if(nearByPlayerIndex != -1)
						nearByPlayer = players.get(nearByPlayerIndex);
					System.out.println("Move Player ( L | R | D | U | A):"); //prompt user for input
					char direction = scanner.next().charAt(0);
	
					if(direction != 'A' || nearByPlayer == null) { //if there are no nearby players or current selected player doesn't attack
						player.move(direction);
						if(player.getHealth() <= 0) //if player lose all health after moving then remove the player from players list
							players.remove(player);
						System.out.println(displayWholeWorld());
					}
					else {
						nearByPlayer.decreaseHealth(player.attack()); //if player attack nearby player
						if(nearByPlayer.getHealth() <= 0)
							players.remove(nearByPlayer);
						System.out.println(displayWholeWorld());
					}
				}
			}

			Random randomMove = new Random(System.nanoTime());
			for(Character character : characters){
				int moveIndex = randomMove.nextInt(4); //create a random direction for each character to move
				int nearByPlayerIndex = playerNear(character.getX(),character.getY()); //find nearby player to attack
				Player nearByPlayer = null;
				if(nearByPlayerIndex != -1) { //if there  is a nearby player
					nearByPlayer = players.get(nearByPlayerIndex);
				}
				if(character instanceof Monster) { //if character is an instance of monster then attack any one player nearby
					if(nearByPlayer != null) {
						nearByPlayer.decreaseHealth(((Monster) character).attack());
						if(nearByPlayer.getHealth() <= 0)
							players.remove(nearByPlayerIndex);
					}
					else { //no players, then just move
						character.move(directions[moveIndex]);
					}	
				}
				else if(character instanceof Healer){ //if character is an instance of healer, then heal any one player nearby
					if(nearByPlayer != null) {
						nearByPlayer.increaseHealth(((Healer) character).heal());
					}
					else { //no players, then just move
						character.move(directions[moveIndex]);
					}	
				}
				else { //otherwise if its neither healer nor monster than just move
					character.move(directions[moveIndex]);
				}	
			}
			if(!isGUI)
				System.out.println(displayWholeWorld());
			else
				repaint();
		}
		System.out.println("Game Over!!");
	}

	/*
	 * used by world to determine if there are any players near the given coordinate.
	 * near means with one unit of x or y in any direction
	 */
	private int playerNear(int x,int y){
		for(int i = 0;i < players.size();i++) {
			int px = players.get(i).getX();
			int py = players.get(i).getY();
			if(px == x){
				if(py == y+1 || py == y-1)
					return i;
			}
			if(px == x-1)
				if(py == y+1 || py == y-1 || py == y)
					return i;
			if(px == x+1)
				if(py == y+1 || py == y-1 || py == y)
					return i;
		}
		return -1;
	}

	/*#####################################
	 *###########GUI Codes#################
	 *#####################################
	 */

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (inGame)
			if(!isInit) {
				initialize(g2d);
				repaint();
			}
			else {
				startGame(g2d);
			}
		else {
			ShowIntroScreen(g2d);
			repaint();
		}
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	private void initialize(Graphics2D g2d) {
		System.out.println("Initializing");
		for(int i = 0;i < _world.length;i++)
			for(int j = 0;j < _world[0].length;j++)
				_world[j][i].drawPatch(g2d, j, i);
		for(int i = 0;i < characters.size();i++)
			g2d.drawImage(characters.get(i).getImage(), characters.get(i).getX(), characters.get(i).getY(),null);	
		for(int i = 0;i < players.size();i++)
			g2d.drawImage(players.get(i).getImage(), players.get(i).getX(), players.get(i).getY(),null);	
//		ImageIcon leftButtonIcon = createImageIcon("/images/right.gif");
//		ImageIcon rightButtonIcon = createImageIcon("/images/left.gif");
//		ImageIcon upButtonIcon = createImageIcon("/images/up.gif");
//		ImageIcon downButtonIcon = createImageIcon("/images/down.gif");
//		JButton left = new JButton("", leftButtonIcon);
//		JButton right = new JButton("",rightButtonIcon);
//		JButton up= new JButton("", upButtonIcon);
//		JButton down = new JButton("",downButtonIcon);
//		left.setActionCommand("left");
//		right.setActionCommand("right");
//		up.setActionCommand("up");
//		down.setActionCommand("down");
//		up.setMnemonic(KeyEvent.VK_UP);
//		down.setMnemonic(KeyEvent.VK_DOWN);
//		left.setMnemonic(KeyEvent.VK_LEFT);
//		right.setMnemonic(KeyEvent.VK_RIGHT);
//		up.addActionListener(this);
//		down.addActionListener(this);
//		left.addActionListener(this);
//		right.addActionListener(this);
//		up.setToolTipText("Move up");
//		down.setToolTipText("Move down");
//		left.setToolTipText("Move left");
//		right.setToolTipText("Move right");
//		add(up);
//		add(down);
//		add(left);
//		add(right);
		isInit = true;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Before repainting");
		repaint();  
		System.out.println("Repainting");
	}

	public void ShowIntroScreen(Graphics2D g2d) {
		System.out.println("Intro");
		g2d.setColor(new Color(0, 32, 48));
		g2d.fillRect(50, scrsize / 2 - 30, scrsize - 100, 50);
		g2d.setColor(Color.white);
		g2d.drawRect(50, scrsize / 2 - 30, scrsize - 100, 50);
		String s = "Press s to start.";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(small);
		g2d.setColor(Color.white);
		g2d.setFont(small);
		g2d.drawString(s, (scrsize - metr.stringWidth(s)) / 2, scrsize / 2);
	}

	public static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = World.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			System.out.println("Key pressed");
			int key = e.getKeyCode();
			if (key == 's' || key == 'S') {
				inGame = true;
			}
			else {
				players.get(currPlayer).keyPressed(e);
				if(players.get(currPlayer).getHealth() <= 0) {
					players.remove(currPlayer);
					System.out.println("Removing player");
				}
			}
			System.out.println("S pressed");
		}

		public void keyReleased(KeyEvent e) {
			if(inGame) {
				currPlayer = (currPlayer + 1) % players.size();
				if(currPlayer == 0)
					iterations++;
			}
		}
	}
}