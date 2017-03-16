package fixturas;

import com.badlogic.gdx.physics.box2d.CircleShape;

import bodies.MyBody;
import comunes.Constantes;

public class RejillaAccesoFixture extends MyFixture {

	public RejillaAccesoFixture(MyBody myBody) {
		super(myBody);
		shape = new CircleShape();
		shape.setRadius(myBody.sprite.getWidth() / 2 / Constantes.PIXELS_TO_METERS);
		fixtureDef.shape = shape;
		fixtureDef.isSensor = true;
		myBody.body.createFixture(fixtureDef);
		shape.dispose();
		myBody.body.setFixedRotation(false);
	}

}
