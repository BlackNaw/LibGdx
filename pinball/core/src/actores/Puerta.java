package actores;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.PuertaBehaior;
import bodies.MuroBody;
import fixturas.MuroFixture;

public class Puerta extends MyActor {

	public Puerta(World world, float posX, float posY, int alto, int ancho, String tipoPuerta) {
		super(world, posX, posY);
		myBody = new MuroBody(world, posX, posY, alto, ancho, tipoPuerta);
		myBody.myBehavior = new PuertaBehaior(myBody);
		myFixture = new MuroFixture(myBody);
	}

	public void setCerrar(boolean cerrar) {
		((PuertaBehaior) myBody.myBehavior).cerrar = cerrar;
	}

}
