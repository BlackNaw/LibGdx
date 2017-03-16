package actores;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import interfaz.IChocable;
import util.Constantes;

/**
 * Created by JAVI on 12/03/2017.
 */

public class Disparo extends Actor implements IChocable {

    public Body body;
    BodyDef bodyDef = new BodyDef();
    FixtureDef fixtureDef = new FixtureDef();
    Shape shape;
    float giro;

    public Disparo(World world, float posX, float posY, float giro) {
        this.giro = giro;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, posY);

        PolygonShape myShape = new PolygonShape();
        myShape.setAsBox(5 / Constantes.PIXELS_TO_METERS, 5 / Constantes.PIXELS_TO_METERS, new Vector2(posX / Constantes.PIXELS_TO_METERS, posY / Constantes.PIXELS_TO_METERS), giro);

        shape = myShape;
        fixtureDef.shape = shape;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        shape.dispose();
        body.setUserData(this);

        body.setFixedRotation(false);

        mover();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }


    public void mover() {


        body.setLinearVelocity((float) (Math.cos(giro) * Constantes.VELOCIDAD_DISPARO), (float) (Math.sin(giro)) * Constantes.VELOCIDAD_DISPARO);
    }

    @Override
    public void chocar() {
        body.setLinearVelocity(0, 0);
//        body.setActive(false);
//        body.setAwake(false);

    }
}
