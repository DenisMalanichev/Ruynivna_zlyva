package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.Scanner;


public class Main implements Screen {

    private ScreenManager manager;
    private Texture moreButtonTexture;
    private Cell[][] cells;
    private int length;
    private int dropsCount;
    private int maxDepth = 0;

    public Main(ScreenManager manager) {
        this.manager = manager;
    }

    @Override
    public void show() {
        moreButtonTexture = new Texture(Gdx.files.internal("visualizeButton.jpg"));
        /** отримуємо параметри*/
         Scanner scanner = new Scanner(System.in);
         System.out.println("Enter num of cells");
         length = scanner.nextInt();
         System.out.println("Enter num of drops");
         dropsCount = scanner.nextInt();
         cells = new Cell[length][length];

        /** ініціалізурємо поле length на length*/
         for (int x = 0; x < cells.length; x++) {
         for (int y = 0; y < cells.length; y++) {
         cells[x][y] = new Cell(0, x, y, length);
         }
         }
        /** cтворюємо краплі*/
         for (int i = 0; i < dropsCount; ++i) {
         int x = (int) (Math.random() * length - 1);
         int y = (int) (Math.random() * length - 1);
         Drop drop = new Drop(x, y);
         drop.corruption(cells[x][y], cells);
         }
        /** знаходимо найбільшу глибину*/

         for (int x = 0; x < cells.length; x++) {
             for (int y = 0; y < cells.length; y++) {
                 if(cells[x][y].getDepth() > maxDepth)
                   maxDepth = cells[x][y].getDepth();
                 }
         }
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.getX() >= 0 && Gdx.input.getX() <= moreButtonTexture.getWidth() && Gdx.input.getY() >= 0 && 1000 - Gdx.input.getY() <= moreButtonTexture.getHeight()
                && Gdx.input.isTouched()) {
            this.dispose();
            manager.setScreen(new Visualization(cells, dropsCount, maxDepth, manager));
        }
        manager.batch.begin();

        manager.batch.draw(moreButtonTexture, 0, 0);

        manager.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
    public void dispose() {
        moreButtonTexture.dispose();
    }
}
