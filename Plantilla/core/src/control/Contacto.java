package control;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;

import actores.MyActor;
import actores.MyActor2;
import comun.ContactAdapter;
import interfaz.IChocable;

public class Contacto extends ContactAdapter {

	Body bodyA, bodyB;

	@Override
	public void beginContact(Contact contact) {
		bodyA = contact.getFixtureA().getBody();
		bodyB = contact.getFixtureB().getBody();

		if(bodyA.getUserData() instanceof MyActor&&bodyB.getUserData() instanceof MyActor2){
			   ((IChocable)bodyB.getUserData()).chocar();
			  }else if(bodyB.getUserData() instanceof MyActor&&bodyA.getUserData() instanceof MyActor2){
			   ((IChocable)bodyA.getUserData()).chocar();
			  }
	}

	@Override
	public void endContact(Contact contact) {
		// bodyA = contact.getFixtureA().getBody();
		// bodyB = contact.getFixtureB().getBody();
	}
}
