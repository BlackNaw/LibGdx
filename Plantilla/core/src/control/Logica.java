package control;

import interfaz.IReiniciable;

public class Logica {
	
	GestorActores gestor;
	
	public Logica() {
	}
	
	public Logica(GestorActores gestor) {
		this.gestor=gestor;
	}
	
	public void act(){
		if(MaquinaEstados.juegoTerminado.getEstado()){
			for (IReiniciable actor : gestor.getActoresReiniciable()) {
				actor.reiniciar();
			}
		}
	}

}
