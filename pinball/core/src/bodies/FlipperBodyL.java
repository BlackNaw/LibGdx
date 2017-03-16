package bodies;

import static comunes.Constantes.DOWN_SPEED;
import static comunes.Constantes.LOWER;
import static comunes.Constantes.UPPER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

import comunes.BoxBodyBuilder;

public class FlipperBodyL extends MyBody {

	Body pivot;
	Body spike;
	BoxBodyBuilder bod;
	BoxBodyBuilder bodyFactory;
	public RevoluteJoint cosa;
	World world;
	float torque = 50f;
	float scale = 0;

	public FlipperBodyL(World world, float posX, float posY, float width) {
		super(world, posX, posY);
		this.world = world;
		bodyFactory = new BoxBodyBuilder();
		pivot = bodyFactory.CreateCircleBody(world, BodyType.StaticBody, posX, posY, 10f);
		sprite = new Sprite(new Texture(Gdx.files.internal("flipperL.png")));
		scale = sprite.getWidth() / width;
		sprite.setSize(sprite.getWidth() / scale, sprite.getHeight() / scale);
		Vector2 puntoSpike = calculaAncla(posX, posY, width);
		body = bodyFactory.createIrregular(world, BodyType.DynamicBody, puntoSpike.x, puntoSpike.y, "flipperL.json",
				width, "flipperL");

		pivot.setTransform(pivot.getPosition(), (float) Math.toRadians(0));
		body.setTransform(body.getPosition(), (float) Math.toRadians(0));
		body.setUserData(this);
		sprite.setOrigin(0, 0);
		MakeJoint();
	}

	private Vector2 calculaAncla(float posX, float posY, float width) {
		Vector2 ancla = new Vector2();
		float factor = width / 4;
		ancla.set(posX - factor, posY - factor);
		return ancla;
	}

	private void MakeJoint() {
		RevoluteJointDef revdef = new RevoluteJointDef();
		Vector2 pivotaje = new Vector2(pivot.getPosition());
		pivotaje.x += 0;
		pivotaje.y -= 0;
		revdef.initialize(pivot, body, pivotaje);
		revdef.referenceAngle = -.00f;
		revdef.lowerAngle = (float) (LOWER * Math.PI / 180);
		revdef.upperAngle = (float) (UPPER * Math.PI / 180);
		revdef.enableLimit = true;
		revdef.maxMotorTorque = torque;
		revdef.motorSpeed = DOWN_SPEED;
		revdef.enableMotor = true;
		cosa = (RevoluteJoint) world.createJoint(revdef);
	}
}