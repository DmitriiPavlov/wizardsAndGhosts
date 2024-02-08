package com.wizard.staffs;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.wizard.Loot;

public abstract class Staff {


    public abstract void fire(float x, float y, float targetX, float targetY);
    public abstract boolean canFire();
    public String itemTexture;

}
