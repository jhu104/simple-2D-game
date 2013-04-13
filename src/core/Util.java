package core;

import java.util.Random;

import javax.swing.ImageIcon;

public class Util {

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
	
	public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Util.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
