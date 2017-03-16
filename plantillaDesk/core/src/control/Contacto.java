package control;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;

import comun.ContactAdapter;

public class Contacto extends ContactAdapter {

	Body bodyA, bodyB;

	@Override
	public void beginContact(Contact contact) {
		bodyA = contact.getFixtureA().getBody();
		bodyB = contact.getFixtureB().getBody();
		
//		if (bodyB.getUserData() instanceof Torreta && bodyA.getUserData() instanceof Enemigo) {
//            ((IChocable) bodyA.getUserData()).chocar();
//            ((IChocable) bodyB.getUserData()).chocar();
//        } else if (bodyA.getUserData() instanceof Torreta && bodyB.getUserData() instanceof Enemigo) {
//            ((IChocable) bodyA.getUserData()).chocar();
//            ((IChocable) bodyB.getUserData()).chocar();
//        }
//        if (bodyB.getUserData() instanceof Disparo && bodyA.getUserData() instanceof Enemigo) {
//            ((IChocable) bodyA.getUserData()).chocar();
//            ((IChocable) bodyB.getUserData()).chocar();
//        } else if (bodyA.getUserData() instanceof Disparo && bodyB.getUserData() instanceof Enemigo) {
//            ((IChocable) bodyA.getUserData()).chocar();
//            ((IChocable) bodyB.getUserData()).chocar();
//        }
	}

	@Override
	public void endContact(Contact contact) {
//		bodyA = contact.getFixtureA().getBody();
//		bodyB = contact.getFixtureB().getBody();
	}
}
