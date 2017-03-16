package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sun.org.apache.xpath.internal.SourceTree;

import interfaz.IChocable;
import util.Constantes;

/**
 * Created by JAVI on 10/03/2017.
 */

public class Torreta extends Actor implements IChocable{
    public Body body;
    BodyDef bodyDef=new BodyDef();
    FixtureDef fixtureDef=new FixtureDef();
    Shape shape;
    World world;
    float radioDisparo;
    Vector2 posicionDisparo=new Vector2();
    float velocidadGiro=Constantes.VELOCIDAD_GIRO_TORRE;

    public Torreta(World world, float posX, float posY) {
        this.world=world;
        posX/=Constantes.PIXELS_TO_METERS;
        posY/=Constantes.PIXELS_TO_METERS;
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(posX, posY);

        PolygonShape myShape=new PolygonShape();
        myShape.setAsBox(10/Constantes.PIXELS_TO_METERS,10/Constantes.PIXELS_TO_METERS);

        shape=myShape;
        fixtureDef.shape = shape;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        shape.dispose();

        body.setUserData(this);

        body.setFixedRotation(false);
        //PARAMETRO DISPARO (distancia a la que aparece el disparo de la torre)
        radioDisparo=10/Constantes.PIXELS_TO_METERS;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }


    public void girarIzquierda(){
        body.setAngularVelocity(velocidadGiro);

    }

    public void girarConAcelerometro(float velocidadGiro){
        body.setAngularVelocity(velocidadGiro);
    }


    public void girarDerecha(){
        body.setAngularVelocity(-velocidadGiro);
    }

    public void parar(){
        body.setAngularVelocity(0);
    }

    public void disparar(){
        Gdx.app.log("Angulo Torreta", String.valueOf(body.getAngle()));
        obtenerPosicionDisparo(body.getAngle());
        new Disparo(world,posicionDisparo.x,posicionDisparo.y,body.getAngle());
    }

    private void obtenerPosicionDisparo(float angle) {
        posicionDisparo.set((float)(body.getPosition().x+radioDisparo*Math.cos(angle)),(float)(body.getPosition().y+radioDisparo*Math.sin(angle)));

    }


    @Override
    public void chocar() {
        System.out.println("TORRE DAÑADA");
    }
}
