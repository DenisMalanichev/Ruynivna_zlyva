package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Visualization implements Screen {
	private Cell[][] cells;
	private int dropsCount;
	private int maxDepth;
	private ScreenManager manager;
	private int testsResults[];
	private int density;
	private int chance;

	public Visualization(Cell[][] cells, int dropsCount, int maxDepth, ScreenManager manager, int testsResults[], int density, int chance) {
		this.cells = cells;
		this.dropsCount = dropsCount;
		this.maxDepth = maxDepth;
		this.manager = manager;
		this.testsResults = testsResults;
		this.density = density;
		this.chance = chance;
	}

	public int average(){
		int ans = 0;
		for(int i = 0; i <100; ++i){
			ans += testsResults[i];
		}
		return ans/100;
	}

	@Override
	public void show() {
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0.15f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			/** малюємо поле*/
			manager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
				for(int x =0; x< cells.length; ++x){
					for(int y =0; y<cells.length; ++y){
						if(cells[x][y].getDepth() == 0){
							manager.shapeRenderer.setColor(0, 0, 1, 1);
						}else{
							manager.shapeRenderer.setColor((float)cells[x][y].getDepth()/maxDepth,0, 1, 1);
						}
						manager.shapeRenderer.rect(x*((float)1000/cells[0][0].getLength()), y*((float)1000/cells[0][0].getLength()),
								(float)1000/cells[0][0].getLength(), (float)1000/cells[0][0].getLength());
					}
				}
			manager.shapeRenderer.setColor(0, 0.7f, 0, 1);
			for(int i =0; i < testsResults.length-1; ++i) {
				int y1 = testsResults[i] * 4 + 400;
				int y2 = testsResults[i + 1] * 4 + 400;
				manager.shapeRenderer.rectLine(1020 + 5 * i, y1, 1020 + 5 * (i + 1), y2, 2);
			}
			manager.shapeRenderer.setColor(1, 0, 0, 1);
			manager.shapeRenderer.rectLine(1020, average()*4 + 400, 1500, average()*4 + 400, 2);
			manager.shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
			manager.shapeRenderer.rectLine(1020, 400, 1500, 400, 2);
			manager.shapeRenderer.rectLine(1020, 400, 1020, 800, 2);
			manager.shapeRenderer.setColor(1, 0, 0, 1);
			manager.shapeRenderer.rectLine(1020, 320, 1040, 320, 2);
			manager.shapeRenderer.setColor(0, 0.7f, 0, 1);
		    manager.shapeRenderer.rectLine(1150, 320, 1175, 320, 2);
			manager.shapeRenderer.rect(1010, 200, 250, 50, new Color(0,0,1,1), new Color(1, 0,1,1),
					new Color(1, 0,1,1), new Color(0,0,1,1));

			manager.shapeRenderer.end();


		manager.batch.begin();
		manager.font.getData().setScale(1);
		manager.font.setColor(1,1,1,1);
		for(int i =0; i<=100; i+=10){
			manager.font.draw(manager.batch, ""+i, 1020 + i*4.8f, 385);
		}
		int c = 0;
		for(int i = cells.length* cells.length/10; i <= cells.length* cells.length; i+= cells.length* cells.length/10) {
			c += 10;
			manager.font.draw(manager.batch, "" + i, 1000, 400 + c * 4f);
		}


		manager.font.draw(manager.batch, "Average", 1050, 330);
		manager.font.draw(manager.batch, "Number of cells", 1190, 330);
		manager.font.draw(manager.batch,
				"This chart illustrates the number of cells in 100 starts with params \n" + cells.length + " and " + dropsCount,
						1010, 300);
		manager.font.draw(manager.batch, "In this visualization cells variate from 0 depth (blue) to max depth (pink)", 1010, 170);
		manager.font.draw(manager.batch,  "Here max depth is " + maxDepth, 1010, 150);
		manager.font.draw(manager.batch, "(Press more to find out more charts)", 1210, 900);
		manager.font.draw(manager.batch, "0", 1010, 190);
		manager.font.draw(manager.batch, "" + maxDepth, 1255, 190);
		manager.font.getData().setScale(2);
		if(Gdx.input.getX() >= 1420 && Gdx.input.getX() <= 1500 && Gdx.input.getY() >= 0 && Gdx.input.getY() <= 80) {
			manager.font.setColor(0, 1, 1, 1);
			manager.font.draw(manager.batch, "More", 1420, 970);
			if(Gdx.input.isTouched()){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				manager.setScreen(new MoreInfo(cells, dropsCount, maxDepth, manager, testsResults, density, chance));
			}
		}else{
			manager.font.setColor(0, 0, 1, 1);
			manager.font.draw(manager.batch, "More", 1420, 970);
		}
		manager.font.getData().setScale(1.6f);
		if(Gdx.input.getX() >= 1310 && Gdx.input.getX() <= 1470 && Gdx.input.getY() >0 && Gdx.input.getY() >= 960) {
			manager.font.setColor(0, 1, 1, 1);
			manager.font.draw(manager.batch, "Set new params", 1310, 40);
			if(Gdx.input.isTouched()){
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				manager.setScreen(new Main(manager));
			}
		}else{
			manager.font.setColor(0, 0, 1, 1);
			manager.font.draw(manager.batch, "Set new params", 1310, 40);
		}
		manager.batch.end();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}


	@Override
	public void dispose () {

	}
}
