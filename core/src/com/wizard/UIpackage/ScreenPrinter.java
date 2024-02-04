package com.wizard.UIpackage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.wizard.TextureManager;

public class ScreenPrinter extends Label {
    public ScreenPrinter(Stage parent){
        super("Welcome to Wizards and Ghosts!", TextureManager.mSkin);
        float height = 20;
        this.setFontScale(0.5F);
        this.setBounds(0,parent.getHeight()-height, parent.getWidth(), height);
    }


}
