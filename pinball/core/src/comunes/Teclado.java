package comunes;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actores.MyActor;
import behaviors.BallBehavior;
import behaviors.FlipperBehaviorL;
import behaviors.FlipperBehaviorR;
import interfaces.IObservable;
import interfaces.IObservador;

public class Teclado extends InputAdapter implements IObservable {
	public ArrayList<IObservador> observadores;
	private boolean ctrl = false;
	public boolean space = false;
	Stage stage;
	float contador = 0;
	boolean anadido = false;
	FlipperBehaviorL flipperBehaviorL;
	FlipperBehaviorR flipperBehaviorR;

	public Teclado(Stage stage) {
		observadores = new ArrayList<IObservador>();
		this.stage = stage;
		for (Actor actor : stage.getActors()) {
			if (actor.getClass().getSimpleName().contains("Flipper")) {
				if (((((MyActor) actor).myBody.myBehavior)).getClass().getSimpleName().contains("L"))
					flipperBehaviorL = ((FlipperBehaviorL) (((MyActor) actor).myBody.myBehavior));
				if (((((MyActor) actor).myBody.myBehavior)).getClass().getSimpleName().contains("R"))
					flipperBehaviorR = ((FlipperBehaviorR) (((MyActor) actor).myBody.myBehavior));
			}
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		comprobarCombinacion(keycode);
		if (keycode == Keys.Z) {
			flipperBehaviorL.up();
		}
		if (keycode == Keys.M) {
			flipperBehaviorR.down();
		}
		if (Estados.juegoTerminado.getEstado() && keycode == Keys.R) {
			Estados.reiniciarJuego.setEstado(true);
		}

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == 129) {
			ctrl = false;
		}
		if (keycode == Keys.SPACE) {
			for (Actor actor : stage.getActors()) {
				if (actor.getClass().getSimpleName().contains("Ball") && !Estados.bolaEnJuego.getEstado()
						&& Estados.bolaEnLanzador.getEstado()) {
					((BallBehavior) (((MyActor) actor).myBody.myBehavior)).setPulsado(true);

				}
			}
		}
		if (keycode == Keys.Z) {
			flipperBehaviorL.down();
		}
		if (keycode == Keys.M) {
			flipperBehaviorR.up();
		}

		return true;
	}

	private void comprobarCombinacion(int keycode) {
		if (ctrl) {

			if (keycode == Input.Keys.D) {
				notifyObservers();

			}
		}
		if (keycode == 129)
			ctrl = true;

	}

	@Override
	public void addObserver(IObservador observador) {
		observadores.add(observador);
	}

	@Override
	public void removeObserver(IObservador observador) {
		observadores.remove(observador);
	}

	@Override
	public void notifyObservers() {
		for (IObservador iObservador : observadores) {
			iObservador.update();
		}

	}

}
