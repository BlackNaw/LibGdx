package actores;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.BotonBehaivor;
import bodies.BotonBody;
import fixturas.BotonFixture;

public class Boton extends MyActor {
	public Boton(World world, float posX, float posY, float giro) {
		super(world, posX, posY);
		myBody = new BotonBody(world, posX, posY, giro);
		myBody.myBehavior = new BotonBehaivor(myBody);
		myFixture = new BotonFixture(myBody, giro);

	}

}
