package com.wizard;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Align;
import com.wizard.staffs.Staff;
import org.w3c.dom.Text;

public class Loot extends Entity{
    Staff matchingStaff = null;
    String interactionText = "Press [E] to pick up item.";
    public Loot(float x, float y,String lootTexture, Staff matchinStaff) {
        super(0, 0, lootTexture);
        matchingStaff = matchinStaff;
        float aspectRatio = (float)TextureManager.get(lootTexture).getHeight()/ (float)TextureManager.get(lootTexture).getWidth();
        this.setBounds(x,y,1.0f, 1.0f*aspectRatio);

    }
    public void act(float deltaTime) {
        super.act(deltaTime);
        if (CollisionManager.isCollidingPlayer(this)) {
            Wizard.o.bottomText.setText(interactionText);
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
