package actores;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.MuroBehaviors;
import bodies.MuroBody;
import fixturas.MuroFixture;

public class Muro extends MyActor {

	boolean impulsar = false;

	public Muro(World world, float posX, float posY, int alto, int ancho, String tipoMuro) {
		super(world, posX, posY);
		myBody = new MuroBody(world, posX, posY, alto, ancho, tipoMuro);
		myBody.myBehavior = new MuroBehaviors(myBody);
		myFixture = new MuroFixture(myBody);
	}

}
