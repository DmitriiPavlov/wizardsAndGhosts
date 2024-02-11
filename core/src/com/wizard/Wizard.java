package com.wizard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.ScreenUtils;
import com.wizard.UIpackage.Overlay;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Wizard extends ApplicationAdapter {

	public static World w;
	public static Overlay o;
	private Music Music;

	@Override
	public void create () {
		w = new World();
		o = new Overlay();
		o.bottomText.setText("Use [WASD] to move and click to shoot!");
		o.displayText("A mysterious feeling tells you to pick up that candy...");

		Music = Gdx.audio.newMusic(Gdx.files.internal("Kubbi - Digestive biscuit  NO COPYRIGHT 8-bit Music.mp3"));
		Music.setLooping(true);
		Music.play();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0F, 0F, 0F, 1);
		w.act();
		w.draw();
		o.act();
		o.draw();
	}
	
	@Override
	public void dispose () {
	}
	@Override
	public void resize(int width, int height){
		w.resize(width,height);
		o.resize(width,height);
	}
}
