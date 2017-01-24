package comun;

import interfaces.Direccionable;
import static comun.Constantes.*;

public class Avance extends Posicion implements Direccionable {

	public Avance(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public Avance(Direccion direccion) {
		direccionar(direccion);
	}

	@Override
	public void direccionar(Direccion direccion) {
		ponerACero();
		switch (direccion) {
		case norte:
			y = VELOCIDAD_NAVE;
			break;
		case sur:
			y = -VELOCIDAD_NAVE;
			break;
		case este:
			x = VELOCIDAD_NAVE;
			break;
		case oeste:
			x = -VELOCIDAD_NAVE;
			break;
		case quieto:
			break;
		}

	}

	private void ponerACero() {
		x = 0;
		y = 0;
	}

}
