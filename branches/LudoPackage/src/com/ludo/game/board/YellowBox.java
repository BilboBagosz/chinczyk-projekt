package com.ludo.game.board;

import java.awt.Color;
import java.awt.Graphics;

public class YellowBox extends Box{

	public YellowBox(){
		colorBox = Color.YELLOW;
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}
