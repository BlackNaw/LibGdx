package actores;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.AgujeroNegroBehaviors;
import bodies.AgujeroNegroBody;
import fixturas.AgujeroNegroFixture;

public class AgujeroNegro extends MyActor {

	public AgujeroNegro(World world, float posX, float posY, ArrayList<MyActor> actores) {
		super(world, posX, posY);
		myBody = new AgujeroNegroBody(world, posX, posY);
		myBody.myBehavior = new AgujeroNegroBehaviors(myBody, actores);
		myFixture = new AgujeroNegroFixture(myBody);
	}

}
