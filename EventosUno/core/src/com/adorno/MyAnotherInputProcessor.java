package com.adorno;

import com.badlogic.gdx.InputAdapter;

public class MyAnotherInputProcessor extends InputAdapter{
	@Override
	public boolean keyDown(int keycode) {
		System.out.println("soy el dos");
		return true;
	}
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

}
