package comun;

import interfaces.Direccionable;

public class Avance extends Posicion implements Direccionable{
	
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
		case norte: y=Constantes.SALTO;
			break;
		case sur: y=-Constantes.SALTO;
		break;
		case este: x=Constantes.SALTO;
		break;
		case oeste: x=-Constantes.SALTO;
		break;
		default:
			break;
		}
		
	}

	private void ponerACero(){
		x=0;
		y=0;
	}

}
