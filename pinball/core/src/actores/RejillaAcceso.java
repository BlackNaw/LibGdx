package actores;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

import bodies.RejillaBody;
import interfaces.IObservable;
import interfaces.IObservador;

public class RejillaAcceso implements IObservador, IObservable {

	public ArrayList<IObservador> observadores = null;
	public ArrayList<Rejilla> rejillas = null;
	ArrayList<Muro> muros = null;

	public RejillaAcceso(int numeroRejillas, World world, int posX, int posY, String color) {
		observadores = new ArrayList<IObservador>();
		rejillas = new ArrayList<Rejilla>();
		muros = new ArrayList<Muro>();
		crearElementos(numeroRejillas, world, posX, posY, color);
	}

	public RejillaAcceso(World world, ArrayList<Rejilla> rejillas, ArrayList<Muro> muros) {
		this.muros = muros;
		this.observadores = new ArrayList<IObservador>();
		this.rejillas = rejillas;
		for (Rejilla rejilla : rejillas) {
			rejilla.addObserver(this);
		}
	}

	private void crearElementos(int numeroRejillas, World world, int posX, int posY, String color) {
		for (int i = 0; i < numeroRejillas; i++) {
			Rejilla rejilla = new Rejilla(world, posX, posY, color);
			posX += rejilla.myBody.sprite.getWidth() + 10;
			rejillas.add(rejilla);
		}
		for (Rejilla rejilla : rejillas) {
			rejilla.addObserver(this);
		}
		if (numeroRejillas > 1) {
			Muro muro = new Muro(world,
					(rejillas.get(0).myBody.sprite.getX() + rejillas.get(0).myBody.sprite.getWidth()) + 2,
					rejillas.get(0).myBody.sprite.getY(), (int) rejillas.get(0).myBody.sprite.getHeight(), 6,
					"pared.png");
			muros.add(muro);
			for (int i = 2; i < numeroRejillas; i++) {
				muro = new Muro(world,
						(rejillas.get(i - 1).myBody.sprite.getX() + rejillas.get(i - 1).myBody.sprite.getWidth()) + 2,
						rejillas.get(i - 1).myBody.sprite.getY(), (int) rejillas.get(i - 1).myBody.sprite.getHeight(),
						6, "pared.png");
				muros.add(muro);
			}
		}
	}

	@Override
	public void update() {
		if (isNivelSuperado()) {
			reiniciarRejillas();
			notifyObservers();
		}
	}

	private boolean isNivelSuperado() {
		for (Rejilla rejilla : rejillas) {
			if (!((RejillaBody) rejilla.myBody).isActivo()) {
				return false;
			}
		}
		return true;
	}

	private void reiniciarRejillas() {
		for (Rejilla rejilla : rejillas) {
			((RejillaBody) rejilla.myBody).cambiarColor();
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

	public ArrayList<Rejilla> getRejillas() {
		return rejillas;
	}

	public ArrayList<Muro> getMuros() {
		return muros;
	}

}
