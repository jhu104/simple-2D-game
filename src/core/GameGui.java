/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import javax.swing.*;

/**
 *
 * @author Jay
 */
public class GameGui extends JFrame {
	public GameGui () {
		super ("Game GUI");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(getWorldView ());

		setSize (300, 200);
		setVisible (true);
	}

	// Creates JPanel to display in JFrame.
	// COMPLETE THIS METHOD TO DISPLAY YOUR OWN WIDGETS
	private JPanel getWorldView () {
		JPanel panel = new JPanel ();
		panel.add (new JLabel ("World View"));

		return panel;
	}
}
