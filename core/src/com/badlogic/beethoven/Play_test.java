package com.badlogic.beethoven;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Play_test implements Screen {
	int perfect = 100;
	Texture imgNote;
	Texture imgBGkeyNote, imgkeyNote, line;
	Music Music;
	SpriteBatch batch;
	OrthographicCamera camera;
	Rectangle bucket;
	Array<Rectangle> raindrops;
	long lastTime;
	long count_a, count_b;
	RhyGame game;
	static GetNote[] notes;
	int i = 0;
	int[] keys = { Keys.D, Keys.F, Keys.J, Keys.K };
	int[] notes_x = { 768, 864, 960, 1056 };
	String[] key = { "D", "F", "J", "K" };
	long time, ftime;
	long lastDropTime;
	
	double wait;
	int c=0;
	double fnote = notes[c].delay*1000;

	boolean isClick = false;
	
	private Thread thread;
	
	double delay = 0;

	public Play_test(RhyGame rhyGame) {

		this.game = rhyGame;
		imgNote = new Texture(Gdx.files.internal("assets\\droplet.png"));
		imgBGkeyNote = new Texture(Gdx.files.internal("assets\\bucket.png"));
		imgkeyNote = new Texture(Gdx.files.internal("assets\\keyBack.png"));
		line = new Texture(Gdx.files.internal("assets\\line.png"));
		batch = new SpriteBatch();
		raindrops = new Array<Rectangle>();
		ftime = TimeUtils.nanoTime();
		thread();
		
	}

	public void addNote() {
		Rectangle raindrop = new Rectangle();
		if (i < notes.length) {
			if (notes[i].slot == 0) {
				raindrop.x = 768;
			} else if (notes[i].slot == 1) {
				raindrop.x = 864;
			} else if (notes[i].slot == 2) {
				raindrop.x = 960;
			} else if (notes[i].slot == 3) {
				raindrop.x = 1056;
			} else if (notes[i].slot == 4) {
				// Gdx.app.log("Go "+notes[i].slot, null);
				game.setScreen(new ShowScore(game));
			}
			raindrop.y = 1280;
			raindrop.width = 96;
			raindrop.height = 25;
			
			
			
			if (i < notes.length) {
				raindrops.add(raindrop);
			}
			wait = notes[i].delay;
			lastDropTime = TimeUtils.nanoTime();

		}
		i++;
	}

	public static void addNewNote(GetNote[] notes2) {
		notes = notes2;
	}

//	public void noteThread() {
//
//		for (int i = 0; i < notes.length; i++) {
//			int timer = (int) (notes[i].delay * 1000);
//			
//			if (timer > 0) {
//				if(time > timer) {
//					addNote();
//				}
//			}
//		}
//	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Start
		game.batch.begin();
		game.batch.draw(imgBGkeyNote, 768, 200, 384, 50);
		game.batch.draw(line, 768, 100, 384, 5);
		game.batch.draw(line, 768, 150, 384, 5);
		game.batch.draw(line, 768, 280, 384, 5);
		game.batch.draw(line, 768, 350, 384, 5);
		for (Rectangle raindrop : raindrops) {
			game.batch.draw(imgNote, raindrop.x, raindrop.y, raindrop.width, raindrop.height);
			game.batch.draw(imgBGkeyNote, 768, 200, 384, 50);
			for (int key = 0; key < 4; key++) {
				game.batch.draw(imgkeyNote, notes_x[key], 0, 96, 80);
			}
		}
		game.batch.end();

		
		if (TimeUtils.nanoTime() - lastDropTime > wait * 1000000000) {
			if (c<notes.length) {
				delay = notes[c].delay;
				Gdx.app.log(c+"", null);
				wait = notes[c].delay;
				lastDropTime = TimeUtils.nanoTime();
				c++;
			}
			else {
				game.setScreen(new ShowScore(game));
			}
			
		}
		time = (TimeUtils.nanoTime() - ftime)/1000000;
		
		if (time - ftime < delay*1000) {
			Gdx.app.log(time+"", null);
			addNote();
			ftime = (TimeUtils.nanoTime() - ftime)/1000000;
		}

		int n = 0;
		for (Rectangle note : raindrops) {
			note.y -= 10;

			if (note.y < -1000) {
				raindrops.removeIndex(n);
			} else if (note.y <= 100) {
				if (!isClick) {
					// Gdx.app.log("Miss["+n+"]", String.valueOf(note.y));
				}
				isClick = false;
			}
			n++;
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
		// TODO Auto-generated method stub
		batch.dispose();
	}
}
