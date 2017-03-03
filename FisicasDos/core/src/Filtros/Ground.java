package Filtros;

import ManyBalls.Utiles;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Ground {
	Body body;
	public Ground(World world,float x,float y, float width,float height) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(x/Utiles.PIXELS_TO_METERS,y/Utiles.PIXELS_TO_METERS);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/Utiles.PIXELS_TO_METERS,height/Utiles.PIXELS_TO_METERS);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		body=world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		shape.dispose();
	}
}
