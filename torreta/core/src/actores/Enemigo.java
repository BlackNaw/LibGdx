package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.sun.org.apache.bcel.internal.generic.FLOAD;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Random;

import interfaz.IChocable;
import interfaz.IReiniciable;
import util.Constantes;

import static util.Constantes.*;

/**
 * Created by JAVI on 10/03/2017.
 */

public class Enemigo extends Actor implements IReiniciable, IChocable {
    Body body;
    BodyDef bodyDef = new BodyDef();
    FixtureDef fixtureDef = new FixtureDef();
    Shape shape;
    Vector2 position;
    Vector2 torreta = new Vector2((Gdx.graphics.getWidth() / 2) / PIXELS_TO_METERS, (Gdx.graphics.getHeight() / 2) / PIXELS_TO_METERS);
    float AX, AY, factor, movX, movY;
    boolean chocado = false;

    public Enemigo(World world) {
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        CircleShape myShape = new CircleShape();
        myShape.setRadius(10 / PIXELS_TO_METERS);
        bodyDef.position.set(sortearPosicion());
        shape = myShape;
        fixtureDef.shape = shape;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        shape.dispose();
        body.setUserData(this);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!chocado)
            irATorre();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void irATorre() {
        position = body.getPosition();

        AX = torreta.x - position.x;
        AY = torreta.y - position.y;

        if (AX != 0 && AY != 0) {
            factor = Math.abs(AX / AY);
            movX = (float) Math.sqrt(Math.pow(VELOCIDAD_ENEMIGO, 2) / (1 + (1 / Math.pow(factor, 2))));
            movY = movX / factor;
        } else {
            movX = AX != 0 ? Constantes.VELOCIDAD_ENEMIGO : 0;
            movY = AY != 0 ? Constantes.VELOCIDAD_ENEMIGO : 0;
        }


        //El enemigo está a la derecha de torre
        movX = AX <= 0 ? movX * -1 : movX;
        //El enemigo está a la encima de torre
        movY = AY < 0 ? movY * -1 : movY;

        //Con setTransform no reconoce la colisión
//        body.setTransform(position.x+movX/PIXELS_TO_METERS,position.y+movY/PIXELS_TO_METERS,body.getAngle());

        //Para solucionarlo le damos fuerzas
        body.setLinearVelocity(movX, movY);

    }

    @Override
    public void reiniciar() {

    }

    public Vector2 sortearPosicion() {
        float posX, posY;
        int ancho = Gdx.graphics.getWidth();
        int alto = Gdx.graphics.getHeight();
        Random random = new Random();

        posX = ((ancho + random.nextInt(ancho)) * (random.nextInt(2) == 1 ? 1 : -1)) / PIXELS_TO_METERS;
        posY = ((alto + random.nextInt(alto)) * (random.nextInt(2) == 1 ? 1 : -1)) / PIXELS_TO_METERS;
        return new Vector2(posX, posY);

    }

    @Override
    public void chocar() {
        body.setLinearVelocity(0, 0);
        chocado = true;
//        body.setActive(false);
//        body.setAwake(false);
//        reiniciar();
    }
}
