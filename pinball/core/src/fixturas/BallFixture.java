package fixturas;

import com.badlogic.gdx.physics.box2d.CircleShape;

import bodies.MyBody;
import comunes.Constantes;

public class BallFixture extends MyFixture {

	public BallFixture(MyBody myBody) {
		super(myBody);
		shape = new CircleShape();
		shape.setRadius(myBody.sprite.getWidth() / 2 / Constantes.PIXELS_TO_METERS);
		fixtureDef.shape = shape;
		fixtureDef.density = 1.2f;
		myBody.body.createFixture(fixtureDef);
		shape.dispose();
		myBody.body.setFixedRotation(false);
	}

}
