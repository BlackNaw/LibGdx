package fixturas;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.physics.box2d.CircleShape;

import bodies.AgujeroNegroBody;
import bodies.MyBody;
import comunes.Constantes;

public class AgujeroNegroFixture extends MyFixture {

	public AgujeroNegroFixture(MyBody myBody) {
		super(myBody);
		CircleShape circleShape = new CircleShape();
		Animation<?> animacion = ((AgujeroNegroBody) myBody).animacion;
		circleShape.setRadius(
				(((AtlasRegion) animacion.getKeyFrames()[0]).getRegionHeight() / 2) / Constantes.PIXELS_TO_METERS);
		fixtureDef.shape = circleShape;
		fixtureDef.isSensor = true;
		myBody.body.createFixture(fixtureDef);
		circleShape.dispose();
	}

}
