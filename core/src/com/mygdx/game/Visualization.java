package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Visualization implements Screen {
	private Cell[][] cells;
	private int dropsCount;
	private int maxDepth;
	private Scanner scanner;
	private int data[] = new int[100];
	private ScreenManager manager;
	private Texture backButtonTexture;


	public Visualization(Cell[][] cells, int dropsCount, int maxDepth, ScreenManager manager) {
		this.cells = cells;
		this.dropsCount = dropsCount;
		this.maxDepth = maxDepth;
		this.manager = manager;
		File file = new File("C:/Users/user/Desktop/results.txt");
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int line =0;
		while (scanner.hasNext()){
			data[line] = scanner.nextInt();
			++line;
		}
		backButtonTexture = new Texture(Gdx.files.internal("moreButton.jpg"));
	}

	@Override
	public void show() {
		manager.batch.begin();
			manager.batch.draw(backButtonTexture, 1500-backButtonTexture.getWidth(), 1000 - backButtonTexture.getHeight());
		manager.batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


			manager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
				for(int x =0; x< cells.length; ++x){
					for(int y =0; y<cells.length; ++y){
						if(cells[x][y].getDepth() == 0){
							manager.shapeRenderer.setColor(0, 0, 1, 1);
						}else{
							manager.shapeRenderer.setColor((float)cells[x][y].getDepth()/maxDepth,0, 1, 1);
						}
						manager.shapeRenderer.rect(x*((float)1000/cells[0][0].getLength()), y*((float)1000/cells[0][0].getLength()), (float)1000/cells[0][0].getLength(), (float)1000/cells[0][0].getLength());
					}
				}
			manager.shapeRenderer.setColor(0, 0.7f, 0, 1);
			for(int i =0; i < data.length-1; ++i){
				manager.shapeRenderer.rectLine(1000+5*i, data[i]*5 + 340, 1000+5*(i+1), data[i+1]*5 + 340, 2);
			}
			manager.shapeRenderer.setColor(1, 0, 0, 1);
			manager.shapeRenderer.rectLine(1000, 500 , 1500, 500, 2);
			manager.shapeRenderer.end();


		manager.batch.begin();
		manager.font.getData().setScale(1);
		manager.font.setColor(1,1,1,1);
		manager.batch.draw(backButtonTexture, 1500-backButtonTexture.getWidth(), 1000 - backButtonTexture.getHeight());
		for(int i =0; i<100; i+=10){
			manager.font.draw(manager.batch, ""+i, 1000 + i*5, 450);
		}

		manager.font.draw(manager.batch,
				"This chart illustrates the number of cells in 100 starts with params 10 and 50",
						1010, 300);
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
		backButtonTexture.dispose();
	}
}
