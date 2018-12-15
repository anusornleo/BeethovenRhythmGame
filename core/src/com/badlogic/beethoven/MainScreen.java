package com.badlogic.beethoven;

import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainScreen implements Screen {

	RhyGame game;
	Texture imgST,imgE,imgbg;
	SpriteBatch batch;
	Rectangle st,ex,bg;
	boolean play = true;
	private Thread thread;
	
	public MainScreen(RhyGame rhyGame) {
		game = rhyGame;
		batch = new SpriteBatch();
		imgST = new Texture(Gdx.files.internal("pic\\keyST.png"));
		imgE = new Texture(Gdx.files.internal("pic\\keyE.png"));
		imgbg = new Texture(Gdx.files.internal("pic\\bg_home.jpg"));
		
		bg = new Rectangle();
		bg.x = 0;
		bg.y = 0;
		bg.width = Gdx.graphics.getWidth();
		bg.height = Gdx.graphics.getHeight();
		
		st = new Rectangle();
		st.x = -60;
		st.y = 250;
		st.width = 300;
		st.height = 100;
		
		ex = new Rectangle();
		ex.x = -60;
		ex.y = 100;
		ex.width = 300;
		ex.height = 90;
	
		thread();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		game.batch.draw(imgbg, bg.x, bg.y, bg.width, bg.height);
		game.batch.draw(imgST, st.x, st.y, st.width, st.height);
		
		if (play == true) {
			game.batch.draw(imgbg, bg.x, bg.y, bg.width ,bg.height);
			if (st.x < 0) {
				st.x += 10;
			}
			if (ex.x > -60) {
				ex.x -= 10;
			}
			game.batch.draw(imgST, st.x, st.y, st.width, st.height);
			game.batch.draw(imgE, ex.x, ex.y, ex.width, ex.height);
			
		}
		
		else if (play == false) {
			game.batch.draw(imgbg, bg.x, bg.y, bg.width ,bg.height);
			if (st.x > -60) {
				st.x -= 10;
			}
			if (ex.x < 0) {
				ex.x += 10;
			}
			game.batch.draw(imgST, st.x, st.y, st.width, st.height);
			game.batch.draw(imgE, ex.x, ex.y, ex.width, ex.height);
		}
		
		
		game.batch.end();
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			play = true;
		}
		else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			play = false;
		}
		if (Gdx.input.isKeyPressed(Keys.ENTER) && play==true) {
			game.setScreen(new SelectGame(game));
			dispose();
			
		}
		else if (Gdx.input.isKeyPressed(Keys.ENTER) && play == false) {
			
		}
		
	}

	public void thread() {
		
	}
	
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		imgbg.dispose();
		imgST.dispose();
		imgE.dispose();
	}
}