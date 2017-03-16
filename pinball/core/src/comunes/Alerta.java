package comunes;

import interfaces.IObservable;
import interfaces.IObservador;

public class Alerta implements IObservador {

	private IObservable observable;

	public Alerta(IObservable observable) {
		this.observable = observable;
		this.observable.addObserver(this);
	}

	@Override
	public void update() {
		System.out.println("CHOCA");

	}
}
