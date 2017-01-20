package com.jumpdontdie.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Yo on 04/01/2017.
 */

public class ActorPinchos extends Actor {

    private Texture pinchos;
    public ActorPinchos(Texture pincho) {
        this.pinchos=pincho;
        setSize(pinchos.getWidth(),pinchos.getHeight());
    }

    @Override
    public void act(float delta) {
        setX(getX()-250*delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(pinchos,getX(),getY());
    }
}
