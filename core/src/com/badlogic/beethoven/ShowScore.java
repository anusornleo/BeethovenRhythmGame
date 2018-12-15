package com.badlogic.beethoven;

import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShowScore implements Screen{
	
	RhyGame game;
	SpriteBatch batch;
	Texture bg;
	
	public ShowScore(RhyGame rhyGame) {
		game = rhyGame;
		batch = new SpriteBatch();
		bg = new Texture(Gdx.files.internal("pic\\bg_select.png"));
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		game.batch.draw(bg, 0, 0, 1920, 1080);
		game.batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			game.setScreen(new SelectGame(game));
		}
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
		// TODO Auto-generated method stub
		
	}

}
