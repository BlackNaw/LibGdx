package behaviors;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;

import actores.ConjuntoBotones;
import bodies.MyBody;
import interfaces.IObservable;
import interfaces.IObservador;

public class TodosBotonesBehavior extends MyBehavior implements IObservable {
	ArrayList<ConjuntoBotones> conjuntoBotones;
	public static ArrayList<IObservador> observadores;

	public TodosBotonesBehavior(MyBody myBody, ArrayList<ConjuntoBotones> conjuntoBotones) {
		super(myBody);
		this.conjuntoBotones = conjuntoBotones;
		observadores = new ArrayList<IObservador>();
	}

	@Override
	public void act(float delta) {
		if (comprobarBotones()) {
			notifyObservers();
			reiniciar();
		}

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub

	}

	private boolean comprobarBotones() {
		boolean activado = true;
		for (ConjuntoBotones b : conjuntoBotones) {
			if (!b.comprobarConjuntoBotones())
				activado = false;
		}
		return activado;
	}

	@Override
	public void reiniciar() {
		for (ConjuntoBotones conjuntoBotones2 : conjuntoBotones) {
			conjuntoBotones2.reiniciarConjuntoBotones();
		}

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
