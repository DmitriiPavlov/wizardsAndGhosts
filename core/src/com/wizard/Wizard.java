package com.wizard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Wizard extends ApplicationAdapter {

	World w;
	
	@Override
	public void create () {
		w = new World();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.1F, 0.1F, 0.1F, 1);
		w.act();
		w.draw();
	}
	
	@Override
	public void dispose () {
	}
	@Override
	public void resize(int width, int height){
		w.resize(width,height);
	}
}
