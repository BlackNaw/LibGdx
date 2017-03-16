package actores;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.FlipperBehaviorR;
import bodies.FlipperBodyR;
import fixturas.FlipperFixtura;

public class FlipperR extends MyActor {

	public FlipperR(World world, float posX, float posY, float width) {
		super(world, posX, posY);
		myBody = new FlipperBodyR(world, posX, posY, width);
		myFixture = new FlipperFixtura(myBody);
		myBody.myBehavior = new FlipperBehaviorR(myBody);
	}

}
