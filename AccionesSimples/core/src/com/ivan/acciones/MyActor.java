package com.ivan.acciones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class MyActor extends Actor{

	Texture texture=new Texture(Gdx.files.internal("halcon.png"));
	//Algo en lo que pondremos mas enfasis es la maquina de estados
	public boolean started=false;
	
	//Acciones simples
	MoveToAction mover=new MoveToAction();
	MoveToAction moverDos=new MoveToAction();
	//Secuencias de acciones
	SequenceAction secuencia=new SequenceAction(mover,moverDos);
	//Repetir el movimiento
	RepeatAction repeticion=new RepeatAction();
	
	public MyActor() {
		// darle las dimensiones
		setBounds(0, 0, texture.getWidth(), texture.getHeight());
		//Doy las caracteristicas de moviento a la accion
		
		mover.setPosition(300f, Gdx.graphics.getWidth());
		mover.setDuration(1f);
		//añade la accion al actor
//		addAction(mover);
		moverDos.setPosition(Gdx.graphics.getHeight(),0f);
		moverDos.setDuration(6f);
		secuencia.addAction(mover);
		secuencia.addAction(moverDos);
		//programando la repetion
		repeticion.setCount(RepeatAction.FOREVER);
		repeticion.setAction(secuencia);
		this.addAction(repeticion);
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
//		moveBy(0, 1);
	}
}
