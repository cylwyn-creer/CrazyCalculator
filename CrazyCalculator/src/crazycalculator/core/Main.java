package crazycalculator.core;

import javax.swing.ImageIcon;

import crazycalculator.gui.EventHandler;

public class Main {
	private EventHandler eventHandler;
	
	public Main() {
		eventHandler = new EventHandler();
		eventHandler.setIconImage(new ImageIcon("src/img/icon.png").getImage());
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
