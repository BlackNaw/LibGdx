package actores;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.MuroDesagueBehaviors;
import bodies.MuroBody;
import fixturas.MuroFixture;
import interfaces.IObservador;

public class MuroDesague extends MyActor implements IObservador {

	public MuroDesague(World world, float posX, float posY, int alto, int ancho, String tipoMuro) {
		super(world, posX, posY);
		myBody = new MuroBody(world, posX, posY, alto, ancho, tipoMuro);
		myBody.myBehavior = new MuroDesagueBehaviors(myBody);
		myFixture = new MuroFixture(myBody);
	}

	@Override
	public void update() {
		setDesactivar(false);
	}

	public void setDesactivar(boolean activar) {
		((MuroDesagueBehaviors) myBody.myBehavior).desactivar = activar;
	}

}
