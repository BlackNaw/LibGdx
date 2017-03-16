package actores;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.BotonBehaivor;
import interfaces.IReiniciable;

public class ConjuntoBotones {

	ArrayList<Boton> botones;
	boolean notificado = false;

	public ConjuntoBotones(World world, float posX, float posY, float giro, float factorDesplazamiento) {
		botones = new ArrayList<Boton>();
		for (int i = 0; i < 4; i++) {
			if (giro != 0 && giro != 180 && giro != 90) {
				posX -= (10 / Math.sin(Math.toRadians(giro))) * factorDesplazamiento;
			}
			botones.add(new Boton(world, posX, posY, giro));
			if (Math.abs(giro) > 45) {
				posY += 9.8f;

			} else {
				posY += 17;
			}
		}

	}

	public ArrayList<Boton> getBotones() {
		return botones;
	}

	public boolean comprobarConjuntoBotones() {
		for (Boton boton : botones) {
			if (!((BotonBehaivor) (boton.myBody.myBehavior)).activado) {
				return false;
			}
		}
		if (!notificado) {
			notificado = true;
		}
		return true;
	}

	public void reiniciarConjuntoBotones() {
		for (Boton boton : botones) {
			((IReiniciable) (boton.myBody.myBehavior)).reiniciar();
		}
		notificado = false;
	}

}
