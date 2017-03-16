package behaviors;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actores.Ball;
import actores.HUD;
import actores.MyActor;
import bodies.MyBody;
import comunes.Constantes;
import comunes.Estados;

public class BallBehavior extends MyBehavior {
	Stage stage;
	Ball ball;
	World world;
	int contador = 0;
	boolean pulsado = false;
	float impulso = 0;
	ArrayList<MyActor> e;

	public BallBehavior(MyBody myBody, Ball ball, World world, ArrayList<MyActor> e) {
		super(myBody);
		this.ball = ball;
		this.world = world;
		this.e = e;
	}

	@Override
	public void act(float delta) {
		
		if ((myBody.body.getPosition().x * Constantes.PIXELS_TO_METERS) < 250) {
			Estados.bolaEnJuego.setEstado(true);

		} else if ((myBody.body.getPosition().y * Constantes.PIXELS_TO_METERS) < 20) {
			Estados.bolaEnLanzador.setEstado(true);
		} else {
			Estados.bolaEnLanzador.setEstado(false);
		}

		myBody.sprite.setPosition(
				(myBody.body.getPosition().x * Constantes.PIXELS_TO_METERS) - myBody.sprite.getWidth() / 2,
				(myBody.body.getPosition().y * Constantes.PIXELS_TO_METERS) - myBody.sprite.getHeight() / 2);

		//IMPULSO INICIAL
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			impulso+=0.01f;
		}else if(pulsado&&!Estados.bolaEnJuego.getEstado()){
			impulso=(impulso<1.5f)?impulso:1.5f;
			myBody.body.applyLinearImpulse(new Vector2(0,impulso ),myBody.body.getWorldCenter(), true);
			pulsado=false;
			impulso=0;
		}
		// IMPULSO PARA DESBLOQUEO
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && Estados.bolaEnJuego.getEstado()) {
			myBody.body.applyLinearImpulse(new Vector2(0.01f, 0.05F), myBody.body.getWorldCenter(), true);
		}
		// CONTROLA FIN DEL JUEGO
		if ((myBody.body.getPosition().y * Constantes.PIXELS_TO_METERS) < 15
				&& (myBody.body.getPosition().x * Constantes.PIXELS_TO_METERS) < 250) {
			contador = 0;
			for (Actor actor : stage.getActors()) {
				if (actor.getClass().getSimpleName().contains("Ball")) {
					contador++;
				}
			}
			if (contador > 1) {
				ball.myBody.body.setActive(false);
				ball.remove();
			} else if (HUD.vidas > 0) {
				Estados.bolaEnJuego.setEstado(false);
				HUD.vidas--;
				stage.addActor(new Ball(world, 360, 200, e));
				ball.myBody.body.setActive(false);
				ball.remove();
			} else {
				Estados.juegoTerminado.setEstado(true);
			}
		}

	}

	public void setPulsado(boolean pulsado) {
		this.pulsado = pulsado;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		myBody.sprite.draw(batch);

	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@Override
	public void reiniciar() {
		Estados.bolaEnJuego.setEstado(false);
		stage.addActor(new Ball(world, 360, 200, e));
		ball.myBody.body.setActive(false);
		ball.remove();
	}

}
