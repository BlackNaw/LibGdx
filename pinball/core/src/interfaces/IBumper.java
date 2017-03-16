package interfaces;

import actores.Ball;

public interface IBumper {
	/**
	 * La clase Bumper se iluminará cuando choque la bola La clase Bumper tiene
	 * un parametro puntuación que entrará por parametro en el constructor, para
	 * tener diferentes bumper con diferentes puntuaciones
	 * 
	 * @param ball
	 *            entra la bola para saber la dirección que lleva y modificar su
	 *            direccion al chocar
	 * @return devuelve la puntuación que tiene el Bumper
	 */
	public int choque(Ball ball);

	/**
	 * Este método lo que hace es aumentar la puntuación y cambia el color
	 */
	public void subirNivel();
}
