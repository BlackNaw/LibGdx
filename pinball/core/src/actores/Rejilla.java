package actores;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.RejillaBehaviors;
import bodies.RejillaBody;
import fixturas.RejillaFixture;
import interfaces.IObservador;

public class Rejilla extends MyActor {

	public Rejilla(World world, float posX, float posY, String color) {
		super(world, posX, posY);
		myBody = new RejillaBody(world, posX, posY, color);
		myBody.myBehavior = new RejillaBehaviors(myBody);
		myFixture = new RejillaFixture(myBody);
	}

	public void addObserver(IObservador observador) {
		((RejillaBody) myBody).addObserver(observador);
	}

	public void removeObserver(IObservador observador) {
		((RejillaBody) myBody).removeObserver(observador);
	}

}
