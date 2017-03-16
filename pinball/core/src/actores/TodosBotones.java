package actores;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.TodosBotonesBehavior;
import bodies.TodoBotonesBody;

public class TodosBotones extends MyActor {
	ArrayList<ConjuntoBotones> conjuntoBotones = new ArrayList<ConjuntoBotones>();

	public TodosBotones(World world, float posX, float posY) {
		super(world, posX, posY);
		conjuntoBotones.add(new ConjuntoBotones(world, 32, 305, 0, 1));
		conjuntoBotones.add(new ConjuntoBotones(world, 345, 290, 30, 0.5f));
		conjuntoBotones.add(new ConjuntoBotones(world, 335, 525, 40, 1f));
		conjuntoBotones.add(new ConjuntoBotones(world, 58, 467, -60, 1.5f));
		conjuntoBotones.add(new ConjuntoBotones(world, 43, 587, -58, 1.5f));
		myBody = new TodoBotonesBody(world, posX, posY);
		myBody.myBehavior = new TodosBotonesBehavior(myBody, conjuntoBotones);
	}

	public ArrayList<ConjuntoBotones> getConjuntoBotones() {
		return conjuntoBotones;
	}

}
