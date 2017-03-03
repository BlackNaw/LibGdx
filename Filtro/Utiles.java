package Filtro;

import java.util.Random;

public class Utiles {
	public final static float PIXELS_TO_METERS = 100f;

	public static float getEnteroIntervalo(int width) {
		return new Random().nextInt(width/2);
	}

}
