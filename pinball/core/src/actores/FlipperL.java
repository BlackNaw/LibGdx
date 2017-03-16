package actores;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.FlipperBehaviorL;
import bodies.FlipperBodyL;
import fixturas.FlipperFixtura;

public class FlipperL extends MyActor {

	public FlipperL(World world, float posX, float posY, float width) {
		super(world, posX, posY);
		myBody = new FlipperBodyL(world, posX, posY, width);
		myFixture = new FlipperFixtura(myBody);
		myBody.myBehavior = new FlipperBehaviorL(myBody);
	}

}
