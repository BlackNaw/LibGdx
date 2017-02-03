package ManyBalls;

import com.badlogic.gdx.Input;

public class Combinacion {
	private static boolean ctrl = false;
	
	private static int keycode;
	private static boolean render = true, draw = true;
	
	public static void reconocer(int keycode) {
		if (ctrl) {
			System.out.println("teclas" + keycode);
			if (keycode == Input.Keys.A) {
				render = !render;
			}
			if (keycode == Input.Keys.S)
				draw = !draw;
		}
		if (keycode == 129)
			ctrl = true;
	}

	public static boolean isRender() {
		return render;
	}

	public static void comprobarLevantada(int keycode){
		if (keycode == 129)
			ctrl = false;
	}
	public static boolean isDraw() {
		return draw;
	}
}
