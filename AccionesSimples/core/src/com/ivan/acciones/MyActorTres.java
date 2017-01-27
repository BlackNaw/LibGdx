package com.ivan.acciones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;

public class MyActorTres extends Actor {
	Texture texture=new Texture(Gdx.files.internal("halcon.png"));
	RotateToAction rotar= new RotateToAction();
	
	public MyActorTres() {
		// darle las dimensiones
		setBounds(0, 0, texture.getWidth(), texture.getHeight());
		
		rotar.setRotation(120f);
		rotar.setDuration(20f);
		
		this.addAction(rotar);
	}
	
	//Aqui nos encargamos de dibujar
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(texture,this.getX(),this.getY());
	}
	
	//Aqui nos encargamos del comportamiento
	@Override
	public void act(float delta) {
		super.act(delta);
	}
}
