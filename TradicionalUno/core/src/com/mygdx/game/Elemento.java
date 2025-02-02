package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class Elemento implements Disposable {
	
	public Rectangulo cuerpo;
	public Posicion posicion;
	public Texture imagen;
	
	public Elemento(Posicion posicion, Texture imagen) {
		super();
		this.posicion = posicion;
		this.imagen = imagen;
		this.cuerpo = new Rectangulo(this.posicion, imagen.getWidth(), imagen.getHeight());
	}

	@Override
	public void dispose() {
		imagen.dispose();
	}

}
