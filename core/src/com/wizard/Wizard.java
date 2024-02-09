package com.wizard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.wizard.UIpackage.Overlay;

public class Wizard extends ApplicationAdapter {

	public static World w;
	public static Overlay o;
	
	@Override
	public void create () {
		w = new World();
		o = new Overlay();
		o.bottomText.setText("Use [WASD] to move and click to shoot!");
		o.displayText("A mysterious feeling tells you to pick up that candy...");
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
