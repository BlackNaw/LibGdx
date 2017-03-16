package actores;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.BarraColisionadorBehavior;
import bodies.BarraColisionadorBody;
import fixturas.BarraColisionadorFixture;

public class BarraColisionador extends MyActor {

	public BarraColisionador(World world, float posX, float posY, float giro) {
		super(world, posX, posY);
		myBody = new BarraColisionadorBody(world, posX, posY, giro);
		myBody.myBehavior = new BarraColisionadorBehavior(myBody);
		myFixture = new BarraColisionadorFixture(myBody, giro);
	}

}
