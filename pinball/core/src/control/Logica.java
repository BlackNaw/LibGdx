package control;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actores.MyActor;
import bodies.MyBody;
import comunes.ContactAdapter;
import comunes.Estados;
import fixturas.ISensor;

public class Logica extends ContactAdapter {

	MyBody bodyA, bodyB;
	Stage stage;

	public Logica(Stage stage) {
		this.stage = stage;
	}

	public void act() {
		if (Estados.reiniciarJuego.getEstado()) {
			for (Actor actor : stage.getActors()) {
				((MyActor) actor).myBody.myBehavior.reiniciar();
			}
			Estados.juegoTerminado.setEstado(false);
			Estados.reiniciarJuego.setEstado(false);
		}
	}

	@Override
	public void beginContact(Contact contact) {
		super.beginContact(contact);
		bodyA = (MyBody) (contact.getFixtureA().getBody().getUserData());
		bodyB = (MyBody) (contact.getFixtureB().getBody().getUserData());

		// SENSORES
		if (contact.getFixtureA().isSensor()) {
			((ISensor) bodyA).sensorPulsado();
		} else if (contact.getFixtureB().isSensor()) {
			((ISensor) bodyB).sensorPulsado();
		}

		if (bodyA.getClass().getSimpleName().contains("Ball")) {
			bodyB.myBehavior.chocar(bodyA.body);
		} else {
			bodyA.myBehavior.chocar(bodyB.body);

		}
	}

	@Override
	public void endContact(Contact contact) {
		super.endContact(contact);
		bodyA = (MyBody) (contact.getFixtureA().getBody().getUserData());
		bodyB = (MyBody) (contact.getFixtureB().getBody().getUserData());
		if (bodyA.getClass().getSimpleName().contains("Muro")) {
			bodyB.myBehavior.chocar(bodyA.body);
		} else if (bodyB.getClass().getSimpleName().contains("Muro")) {
			bodyA.myBehavior.chocar(bodyB.body);

		}
	}

}
