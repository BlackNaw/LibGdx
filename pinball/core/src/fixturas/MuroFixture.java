package fixturas;

import com.badlogic.gdx.physics.box2d.PolygonShape;

import bodies.MyBody;
import comunes.Constantes;

public class MuroFixture extends MyFixture {

	public MuroFixture(MyBody myBody) {
		super(myBody);
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(myBody.sprite.getWidth() / 2 / Constantes.PIXELS_TO_METERS,
				myBody.sprite.getHeight() / 2 / Constantes.PIXELS_TO_METERS);
		fixtureDef.shape = polygonShape;
		// TODO nose si sera esto lo que haga que colisione
		fixtureDef.friction = 1;
		myBody.body.createFixture(fixtureDef);
		polygonShape.dispose();
	}

}
