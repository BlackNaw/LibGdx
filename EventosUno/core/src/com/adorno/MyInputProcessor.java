package com.adorno;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {

	// Esto es dejar de usar polling y empezar a usar eventos

	// Eventos de teclado
	// Cada vez que pulsamos una tecla
	// Ligdx no puede interpretar que una tecla se quede pulsada
	// para solucionar esto tenemos banderas
	// Las banderas se usan para controlar que la pulsacion ha terminado y que
	// por tanto
	// la accion termina
	boolean levantada = true;

	/*
	 * 22 Derecha 19 Arriba 20 Abajo 21 Izquierda
	 */
	@Override
	public boolean keyDown(int keycode) {
		if (!levantada) {
			// El keycode nos da el codigo de la teclada pulsada
			// que debemos comparar con una constante de Keys
			System.out.println("Has pulsado la tecla " + keycode);
			if (keycode == Input.Keys.ESCAPE) {
				System.out.println("Has pulsado ESC");
			}
//			switch (keycode) {
//			case Input.Keys.LEFT:
//				System.out.println("Izquierda");
//				break;
//			case Input.Keys.RIGHT:
//				System.out.println("Derecha");
//				break;
//			case Input.Keys.UP:
//				System.out.println("Arriba");
//				break;
//			case Input.Keys.DOWN:
//				System.out.println("Abajo");
//				break;
//			default:
//				break;
//			}
			
		}
		System.out.println("Soy el uno");
		levantada = false;
//		return true;
		//Si lo que hace este proceso no es suficiente 
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// System.out.println("levantada " + levantada);
		levantada = true;
		return true;
	}

	// Se pulsa una tecla pero con codigo imprimible
	@Override
	public boolean keyTyped(char character) {
		// System.out.println("tecla imprimible -"+character+"-");
		return false;
	}

	// Eventos de dedos y raton
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		System.out.println("pulsando");
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	// Este es solo de dedos y raton
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	// Este es solo de raton
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	// Este es solo de raton
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
