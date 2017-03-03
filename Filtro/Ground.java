package Filtro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Ground {
	Body body;
	Sprite sprite;

	

	public Ground(World world,float x,float y, float width,float height) {
		BodyDef bodyDef = new BodyDef();
		// Si queremos que tenga las propiedades reales
		// hay que pasarlo a metros El valor es más pequeño en el mundo que en
		// el sprite
		bodyDef.type = BodyDef.BodyType.StaticBody;
		//este cero es el medio
//		System.out.println(-Gdx.graphics.getHeight()/2/Utiles.PIXELS_TO_METERS);
		bodyDef.position.set(x/Utiles.PIXELS_TO_METERS,y/Utiles.PIXELS_TO_METERS);
		body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/Utiles.PIXELS_TO_METERS,height/Utiles.PIXELS_TO_METERS);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		body.createFixture(fixtureDef);
		body.setUserData("suelo");
		shape.dispose();
		sprite=new Sprite(new Texture(Gdx.files.internal("basketball_floor.png")));
		sprite.setSize(width*2, height*2);
		sprite.setPosition(body.getPosition().x*Utiles.PIXELS_TO_METERS-width, 
				body.getPosition().y*Utiles.PIXELS_TO_METERS-height);
	}
	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
