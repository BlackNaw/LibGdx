package com.jumpdontdie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Yo on 04/01/2017.
 */

public class Box2DScreen extends BaseScreen {
    public Box2DScreen(MainGame game) {
        super(game);
    }

    private Box2DDebugRenderer renderer;
    private World world;
    private OrthographicCamera camara;
    private Body minijoeBody, sueloBody, pinchoBody;
    private Fixture minijoeFixture, sueloFixture, pinchoFixture;
    private boolean debeSaltar, joeSaltando, joeVivo;

    @Override
    public void show() {
        world = new World(new Vector2(0, -10), true);
        renderer = new Box2DDebugRenderer();
        camara = new OrthographicCamera(16, 9);
        camara.translate(0, 1);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();

                if ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("floor")) ||
                        (fixtureA.getUserData().equals("floor") && fixtureB.getUserData().equals("player"))) {
                    if (Gdx.input.justTouched()) {
                        debeSaltar = true;
                    }
                    joeSaltando = false;
                }
                if ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("spike")) ||
                        (fixtureA.getUserData().equals("spike") && fixtureB.getUserData().equals("player"))) {
                    joeVivo = false;
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
        minijoeBody = world.createBody(createJoeBodyDef());
        sueloBody = world.createBody(createSueloBodyDef());
        pinchoBody = world.createBody(createPinchoBodyDef(0.5f));

        PolygonShape minijoeShape = new PolygonShape();
        minijoeShape.setAsBox(0.5f, 0.5f);
        minijoeFixture = minijoeBody.createFixture(minijoeShape, 1);
        minijoeShape.dispose();
        PolygonShape sueloshape = new PolygonShape();
        sueloshape.setAsBox(500, 1);
        sueloFixture = sueloBody.createFixture(sueloshape, 1);
        sueloshape.dispose();
        pinchoFixture = createPinchoFixture(pinchoBody);

        minijoeFixture.setUserData("player");
        sueloFixture.setUserData("floor");
        pinchoFixture.setUserData("spike");
    }

    private BodyDef createPinchoBodyDef(float x) {
        BodyDef def = new BodyDef();
        def.position.set(x, 0.5f);
        return def;
    }

    private BodyDef createSueloBodyDef() {

        BodyDef def = new BodyDef();
        def.position.set(0, -1);
        return def;
    }

    private BodyDef createJoeBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0, 10);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    private Fixture createPinchoFixture(Body pinchoBody) {
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, -0.5f);
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0, 0.5f);
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        Fixture fix = pinchoBody.createFixture(shape, 1);
        shape.dispose();
        return fix;
    }

    @Override
    public void dispose() {
        pinchoBody.destroyFixture(pinchoFixture);
        sueloBody.destroyFixture(sueloFixture);
        minijoeBody.destroyFixture(minijoeFixture);
        pinchoBody.destroyFixture(pinchoFixture);
        world.destroyBody(minijoeBody);
        world.destroyBody(sueloBody);
        world.destroyBody(pinchoBody);
        world.dispose();
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (debeSaltar) {
            saltar();
            debeSaltar = true;
        }
        if (Gdx.input.justTouched() && !joeSaltando) {
            debeSaltar = true;
        }
        if (joeVivo) {
            float velocidadY = minijoeBody.getLinearVelocity().y;
            minijoeBody.setLinearVelocity(8, velocidadY);
        }
        world.step(delta, 6, 2);
        camara.update();
        renderer.render(world, camara.combined);
    }

    private void saltar() {
        Vector2 position = minijoeBody.getPosition();
        minijoeBody.applyLinearImpulse(0, 20, position.x, position.y, true);
    }
}
