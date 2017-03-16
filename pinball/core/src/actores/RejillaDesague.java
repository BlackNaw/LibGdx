package actores;

import java.util.ArrayList;

import behaviors.MuroDesagueBehaviors;
import interfaces.IObservador;

public class RejillaDesague implements IObservador {

	ArrayList<MuroDesague> muros = null;

	public RejillaDesague(ArrayList<MuroDesague> muros) {
		this.muros = muros;
	}

	@Override
	public void update() {
		for (MuroDesague muro : muros) {
			((MuroDesagueBehaviors) muro.myBody.myBehavior).desactivar = false;
		}
	}

	public ArrayList<MuroDesague> getMuros() {
		return muros;
	}

}
