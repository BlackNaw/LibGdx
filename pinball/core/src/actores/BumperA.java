package actores;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.BumperABehavior;
import bodies.BumperABody;
import fixturas.BumperAFixture;
import interfaces.IObservador;

public class BumperA extends MyActor {

	public BumperA(World world, float posX, float posY, RejillaAcceso rejillaAcceso) {
		super(world, posX, posY);
		myBody = new BumperABody(world, posX, posY);
		myBody.myBehavior = new BumperABehavior(myBody);
		rejillaAcceso.addObserver((IObservador) myBody.myBehavior);
		myFixture = new BumperAFixture(myBody);
	}

}
