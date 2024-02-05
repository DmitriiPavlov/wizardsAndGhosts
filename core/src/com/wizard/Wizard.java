package com.wizard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.wizard.UIpackage.Overlay;

public class Wizard extends ApplicationAdapter {

	World w;
	Overlay o;
	
	@Override
	public void create () {
		w = new World();
		o = new Overlay();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.1F, 0.1F, 0.1F, 1);
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
