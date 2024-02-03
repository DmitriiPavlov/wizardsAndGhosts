package com.wizard;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
public class CollisionManager {
    //manages collisions between various objects
    //can let you know if your character is colliding with any rects

    public static ArrayList<Block> collidableBlocks = new ArrayList<>();
    public static ArrayList<Enemy> collidableEnemies = new ArrayList<>();
    public static Character character;

    public static Level currentLevel;
    public static boolean isHittingBlock(Entity e){
        Rectangle actorRect = new Rectangle(); e.getHitbox(actorRect);
        Rectangle blockRect = new Rectangle();
        for (int x = (int) actorRect.getX(); x <= (int)(actorRect.getX() + actorRect.getWidth()); x++){
            for (int y = (int) actorRect.getY(); y <= (int)(actorRect.getY() + actorRect.getHeight()); y++){
                if (currentLevel.inBounds(x,y) && currentLevel.blockArray[x][y].collidable()){
                    Block block = currentLevel.blockArray[x][y];
                    blockRect.set(block.getX(), block.getY(), block.getWidth(),block.getHeight());
                    if (blockRect.overlaps(actorRect)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Enemy isCollidingEnemy(Entity entity){
        Rectangle entityRect = new Rectangle(); entity.getHitbox(entityRect);
        Rectangle enemyRect = new Rectangle();
        for (Enemy enemy : collidableEnemies){
            enemy.getHitbox(enemyRect);
            if (enemyRect.overlaps(entityRect)){
                return enemy;
            }
        }
        return null;
    }

    public static boolean isCollidingPlayer(Entity entity){
        Rectangle actorRect = new Rectangle(); entity.getHitbox(actorRect);
        Rectangle playerRect = new Rectangle(); character.getHitbox(playerRect);
        return actorRect.overlaps(playerRect);
    }

}
