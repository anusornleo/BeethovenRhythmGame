package com.badlogic.beethoven;

import com.badlogic.gdx.graphics.Color;
import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class SelectGame implements Screen {

	BitmapFont font;
	RhyGame game;
	String[] song_ls = { "song0", "song1", "song2", "song3" };
	String[] path_cover = { "pic\\song0.png", "pic\\song1.png", "pic\\song2.png", "pic\\song3.png" };
	Texture[] cover = new Texture[4];
	Texture bg,bg_trans;
	SpriteBatch batch;
	Rectangle cover_show, grop;
	boolean play = true,up = true;
	int num = 0;
	int[] position = {600,800,1000,1200};

	public SelectGame(RhyGame rhyGame) {

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font\\Kanit-Light.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 72;
		font = generator.generateFont(parameter);

		this.game = rhyGame;
		for (int i = 0; i < 4; i++) {
			cover[i] = new Texture(Gdx.files.internal(path_cover[i]));
		}
		bg = new Texture(Gdx.files.internal("pic\\bg_select.png"));
		bg_trans = new Texture(Gdx.files.internal("pic\\trans_bg.png"));

		batch = new SpriteBatch();

		game.font.setColor(Color.WHITE);

		cover_show = new Rectangle();
		cover_show.x = 100;
		cover_show.y = 300;
		cover_show.width = 600;
		cover_show.height = 600;

		grop = new Rectangle();
		grop.x = 1100;
		grop.y = 600;

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			ReadNote.readFile(num);
			game.setScreen(new Play(game));
			
		} else if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			num--;
			up = false;
			if (num < 0) {
				num = 0;
			}
		} else if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			num++;
			up = true;
			if (num > 3) {
				num = 3;
			}
			
		}
		textPosition(num);
		batch.begin();
		batch.draw(bg, 0, 0, 1920, 1080);
		batch.draw(cover[num], cover_show.x, cover_show.y, cover_show.width, cover_show.height);
		font.draw(batch, "Song0", grop.x, grop.y);
		font.draw(batch, "Song1", grop.x, grop.y - 200);
		font.draw(batch, "Song2", grop.x, grop.y - 400);
		font.draw(batch, "Song3", grop.x, grop.y - 600);
		batch.draw(bg_trans, 0, 0, 1920, 1080);
		batch.end();

	}
	
	public void textPosition(int num_song) {
		if (grop.y < position[num_song] && up == true) {
			grop.y+=20;
		}
		else if (grop.y > position[num_song] && up == false) {
			grop.y-=20;
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
		batch.dispose();
		bg.dispose();
	}

}
