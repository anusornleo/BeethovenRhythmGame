package com.badlogic.beethoven;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Play implements Screen {
	int perfect = 100;
	Texture imgNote;
	Texture imgBGkeyNote, imgkeyNote, line;
	Sound dropSound;
	Music Music;
	SpriteBatch batch;
	OrthographicCamera camera;
	Rectangle bucket;
	Array<Rectangle> raindrops;
	long lastDropTime;
	long count_a, count_b;
	RhyGame game;
	static GetNote[] notes;
	int i = 0;
	double wait;
	int[] keys = { Keys.D, Keys.F, Keys.J, Keys.K };
	int[] notes_x = { 768, 864, 960, 1056 };
	String[] key = { "D", "F", "J", "K" };
	boolean isClick = true, click = false;

	public Play(RhyGame rhyGame) {

		this.game = rhyGame;
		imgNote = new Texture(Gdx.files.internal("assets\\droplet.png"));
		imgBGkeyNote = new Texture(Gdx.files.internal("assets\\bucket.png"));
		imgkeyNote = new Texture(Gdx.files.internal("assets\\keyBack.png"));
		line = new Texture(Gdx.files.internal("assets\\line.png"));

		// Sound effect
		// dropSound = Gdx.audio.newSound(Gdx.files.internal("song\\song0.wav"));
		Music = Gdx.audio.newMusic(Gdx.files.internal("song\\music\\song0.mp3"));

		// Play Music
		Music.setLooping(false);
		// Music.play(); // Start Play Music
		batch = new SpriteBatch();

		// Start First Note
		raindrops = new Array<Rectangle>();
		addNote();

	}

	@Override
	public void render(float delta) {

		// Wallpaper
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Start
		batch.begin();
		batch.draw(imgBGkeyNote, 768, 200, 384, 50);
		batch.draw(line, 768, 100, 384, 5);
		batch.draw(line, 768, 150, 384, 5);
		batch.draw(line, 768, 280, 384, 5);
		batch.draw(line, 768, 350, 384, 5);
		for (Rectangle raindrop : raindrops) {
			batch.draw(imgNote, raindrop.x, raindrop.y, raindrop.width, raindrop.height);
			batch.draw(imgBGkeyNote, 768, 200, 384, 50);
			for (int key = 0; key < 4; key++) {
				batch.draw(imgkeyNote, notes_x[key], 0, 96, 80);
			}
		}
		batch.end();

		// New code

		// End new code

		// Check time next Note
		if (TimeUtils.nanoTime() - lastDropTime > (wait * 1000000000)) {
			addNote();
		}

		// Note come
		// Iterator<Rectangle> iter = raindrops.iterator();
		int n = 0;
		keyPress();
		for (Rectangle note : raindrops) {
			note.y -= 10;
			if (note.y < -1000) {
				raindrops.removeIndex(n);
			} else if (note.y <= 100) {
//				if (!isClick) {
//					//Gdx.app.log("Miss["+n+"]", String.valueOf(note.y));
//				}
//				isClick = false;
			}
			if (note.y <= 200 && n == 0) {
				Music.play(); // Start Play Music
			}
			n++;
		}
	}

	public void keyPress() {
		for (int a = 0; a < 4; a++) {
			if (Gdx.input.isKeyJustPressed(keys[a])) {
				for (Rectangle note : raindrops) {
					if (note.x == notes_x[a]) {
						isClick = true;
						//Gdx.app.log("Click", null);
						if (note.y < 280 && note.y > 150) {
							Gdx.app.log("Perfect", String.valueOf(note.y));
						} else if (note.y < 350 && note.y > 120) {
							Gdx.app.log("Good", String.valueOf(note.y));
						}
						break;
					}
				}
			}
		}
	}

	private void addNote() {
		Rectangle raindrop = new Rectangle();
		if (i < notes.length) {
			if (notes[i].slot == 4) {
				game.setScreen(new ShowScore(game));
			} else if (notes[i].slot == 0) {
				raindrop.x = 768;
			} else if (notes[i].slot == 1) {
				raindrop.x = 864;
			} else if (notes[i].slot == 2) {
				raindrop.x = 960;
			} else if (notes[i].slot == 3) {
				raindrop.x = 1056;
			}
			raindrop.y = 1280;
			raindrop.width = 96;
			raindrop.height = 25;
			wait = notes[i].delay;
			if (notes[i].delay == 0) {
				raindrop.y = 1290;
			}

			if (i < notes.length && notes[i].slot != 4) {
				raindrops.add(raindrop);
			}
			lastDropTime = TimeUtils.nanoTime();

		} else if (i == notes.length) {
			game.setScreen(new ShowScore(game));
		}
		i++;

	}

	public static void addNewNote(GetNote[] notes2) {
		notes = notes2;
	}

	@Override
	public void dispose() {
		// dispose resourse ทุกๆอย่าง
		imgNote.dispose();
		imgBGkeyNote.dispose();
		// dropSound.dispose();
		Music.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
		// ให้ยูเซอร์แตะหน้าจอเพื่อเริ่มเกมต่อ
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}
}