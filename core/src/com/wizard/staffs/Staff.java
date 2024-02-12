package com.wizard.staffs;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.wizard.Loot;
import com.wizard.ProjectileManager;

public abstract class Staff {


    public abstract void fire(float x, float y, float targetX, float targetY);
    public abstract boolean canFire();
    public String itemTexture;

    public static void ability(String name, float dx, float dy, float x, float y){ // Used to allow actions after contact with varrious projectile (couldent figure out Overide)
        if (name.equals("ChocBar"))
        {
            ProjectileManager.createChocChunk(dx,dy,x,y);
        }
    }

    public String getItemTexture() {
        return "badlogic.jpg";
    }

    public static Staff getStaffType(Staff staff){  // used to determain witch scroll to drop on the ground when new item pickup


        if (staff.getItemTexture().equals("MintScroll.png")) {
            return new MintStaff();
        }
        else if (staff.getItemTexture().equals("CandyBlastScroll.png")) {
            return new CandyStaff();
        }
        else if (staff.getItemTexture().equals("ChocBarWHitBox.png")) {
            return new ChocBarStaff();
        }
        else if (staff.getItemTexture().equals("LolipopWHitBox.png")) {
            return new LollipopStaff();
        }
        else if (staff.getItemTexture().equals("ghostStaff")) {
            return new GhostStaff();
        }
        else { return new MintStaff();}

    }

}
