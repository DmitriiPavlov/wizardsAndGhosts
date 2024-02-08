package com.wizard.UIpackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.wizard.TextureManager;
import org.w3c.dom.Text;

public class BottomText extends Label {
    public BottomText(){
        super("", TextureManager.mSkin);
        this.setBounds(Gdx.graphics.getWidth()/2 -50 ,10,100,10);
        this.setAlignment(Align.center);
        this.setColor(0,1,1,1);
        this.setFontScale(0.8F);
    }

    public void resize(int width, int height){
        this.setBounds(width/2 ,10,0,10);
    }

}
