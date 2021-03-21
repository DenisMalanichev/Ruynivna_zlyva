package com.mygdx.game.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.*;

import java.util.Scanner;

public class DesktopLauncher {
	static Cell[][] cells;
	public static void main (String[] arg){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1500;
		config.height = 1000;
		config.resizable = false;

		/** отримуємо параметри
		 Scanner scanner = new Scanner(System.in);
		 System.out.println("Enter num of cells");
		 	int length = scanner.nextInt();
		 System.out.println("Enter num of drops");
		 	int dropsCount = scanner.nextInt();
		  cells = new Cell[length][length];*/

		/** ініціалізурємо поле length на length
		 for (int x = 0; x < cells.length; x++) {
			 for (int y = 0; y < cells.length; y++) {
				 cells[x][y] = new Cell(0, x, y, length);
			 }
		 }*/
		 /** cтворюємо краплі
		 for (int i = 0; i < dropsCount; ++i) {
		 	int x = (int) (Math.random() * length - 1);
			int y = (int) (Math.random() * length - 1);
			Drop drop = new Drop(x, y);
			drop.corruption(cells[x][y], cells);
		 }*/
		 /** знаходимо найбільшу глибину
		 int maxDepth = 0;
		 for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells.length; y++) {
				if(cells[x][y].getDepth() > maxDepth)
					maxDepth = cells[x][y].getDepth();
			}
		 }*/

		new LwjglApplication(new ScreenManager(), config);
	}
}
