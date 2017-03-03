package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class FisicasUno extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Sprite sprite;

	//Box2d
	World world;
	Body body;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		//se le puede dar posicion
		sprite=new Sprite(img);
		//ponemos la imagen en el centro de la pantalla
		sprite.setPosition(Gdx.graphics.getWidth()/2-sprite.getWidth()/2, Gdx.graphics.getHeight()/2-sprite.getHeight()/2);
		//definimos el mundo
		world=new World(new Vector2(0, -98f), true);
		//vamoa a adecuar el body al mundo
		
		BodyDef bodyDef=new BodyDef();
		bodyDef.type=BodyType.DynamicBody;
		//No vamos a usar escala. de forma que un pixel en un metro en el mundo real
		bodyDef.position.set(sprite.getX(), sprite.getY());
		//Es hora de crear el cuerpo
		body=world.createBody(bodyDef);
		//Ahora podemos definir las dimensiones fisicas
		PolygonShape shape=new PolygonShape();
		// Lo que hacemos es igualar la forma con el sprite (es una caja)
		//le damos el centro porque la gravedad siempre es sobre el punto central del objeto
		shape.setAsBox(sprite.getWidth()/2, sprite.getHeight()/2);		
		
		//una vez definido el cuerpo, el tipo, y las diemnsiones pasamos a definir las caracteristicas
		//de interactuacion con el mundo
		FixtureDef fixtureDef=new FixtureDef();
		fixtureDef.shape=shape;
		//Densidad en kg/m2
		fixtureDef.density=1f;
		//Restitution valores entre 0..1 elasticidad 0 nada 1 todo
//		fixtureDef.restitution=0;
//		//Y la friccion sería el compoortamiento de un cuerpo cuando se roza con otro
//		//0 no fricciona y 1 fricciona al maximo
//		fixtureDef.friction=0;
		//Pasamos a dar foram a la definicion anterior de FixtureDef //de momento no usamos la fixture
		Fixture fixture=body.createFixture(fixtureDef);
		//borramos la forma porque ya no hace falta. o si
		shape.dispose();
		
	}

	@Override
	public void render() {
	
		//El avance del mundo se mide por la cantidad de tiempo transcurrido
		//desde el ultimo frame. Esto no lo vamos a hacer siempre en el render
		//Lo primero es definir ciertas caracteristicas del intervalo del render
		//el segundo parametro se refiere a la cantidad de calculos para la velocidad
		//el tercero a la cantidad de calculos para la posicion
		//step actualiza lo que haya pasado en el mundo
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		sprite.setPosition(body.getPosition().x, body.getPosition().y);
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(sprite, sprite.getX(), sprite.getY());
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		world.dispose();
	}
}
