package crazycalculator.core;

import javax.swing.ImageIcon;

import crazycalculator.gui.Frame;

public class Main {
	private Frame frame;
	
	public Main() {
		frame = new Frame();
		frame.setIconImage(new ImageIcon("src/img/icon.png").getImage());
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
