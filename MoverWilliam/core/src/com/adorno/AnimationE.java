package com.adorno;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

public enum AnimationE {
attack("williamattack"),damage("williamdamage"),idle("williamidle"),death("williamdeath"),power("williampower"),move("williammove");
	private String titulo;
	public static Animation animation;
	TextureAtlas textureAtlas;
	
	AnimationE(String titulo){
		this.titulo=titulo;
	}
	@Override
	public String toString() {
		return this.titulo;
	}
	public static Animation getAnimation(AnimationE animationE) {
		String titulo=animationE.toString();
		TextureAtlas textureAtlas= new TextureAtlas(Gdx.files.internal("williamclase.atlas"));
		return new Animation(1/30f,textureAtlas.findRegions(titulo));
	}
}
