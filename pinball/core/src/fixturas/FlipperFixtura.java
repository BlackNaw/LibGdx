package fixturas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import bodies.MyBody;
import comunes.BodyEditorLoader;

public class FlipperFixtura extends MyFixture {
	MyBody myBody;

	public FlipperFixtura(MyBody myBody) {
		super(myBody);
		// TODO Auto-generated constructor stub
	}

	public static float WORLD_TO_BOX = 0.01f;
	public static float BOX_TO_WORLD = 100f;

	static float ConvertToBox(float x) {
		return x * WORLD_TO_BOX;
	}

	static float ConvertToWorld(float x) {
		return x * BOX_TO_WORLD;
	}

	// SOLO USAMOS ESTE METODO
	public Body createIrregular(World world, BodyType bodyType, float posx, float posy, String jsonPath, float width,
			String fixtureName) {
		FileHandle file = Gdx.files.internal(jsonPath);
		String cadena = file.readString();
		BodyEditorLoader loader = new BodyEditorLoader(cadena);
		BodyDef bd = new BodyDef();
		bd.position.set(ConvertToBox(posx), ConvertToBox(posy));
		bd.type = bodyType;
		FixtureDef fd = new FixtureDef();
		fd.density = 4;
		fd.friction = 3f;
		fd.restitution = 0.3f;
		Body body = world.createBody(bd);
		loader.attachFixture(body, fixtureName, fd, width / BOX_TO_WORLD);

		return body;
	}

	public Body CreateCircleBody(World world, BodyType bodyType, float posx, float posy, float radius) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bodyType;
		bodyDef.position.set(ConvertToBox(posx), ConvertToBox(posy));
		bodyDef.angle = 0;

		Body body = world.createBody(bodyDef);
		// (Body body,float radius,BodyDef.BodyType bodyType,float density,float
		// restitution,float angle,float friction)
		MakeCircleBody(body, radius, bodyType, 4, .5f, 0, 0);
		return body;
	}

	public Body createBoxBody(World world, BodyType bodyType, float posx, float posy, float width, float height) {
		BodyDef bodyDef = new BodyDef();
		// Si queremos que tenga las propiedades reales hay que pasarlo a metros
		bodyDef.type = bodyType;

		bodyDef.position.set((posx) / BOX_TO_WORLD, (posy) / BOX_TO_WORLD);

		Body body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / BOX_TO_WORLD, height / BOX_TO_WORLD);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.1f;

		body.createFixture(fixtureDef);
		shape.dispose();
		return body;
	}

	void MakeCircleBody(Body body, float radius, BodyDef.BodyType bodyType, float density, float restitution,
			float angle, float friction) {

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		fixtureDef.shape = new CircleShape();
		fixtureDef.shape.setRadius(ConvertToBox(radius));

		body.createFixture(fixtureDef);
		fixtureDef.shape.dispose();
	}
}
