package com.badlogic.beethoven;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RhyGame extends Game{

	public SpriteBatch batch;
	BitmapFont font;
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainScreen(this));
	}
	
	public void render() {
		super.render();
	}

}
