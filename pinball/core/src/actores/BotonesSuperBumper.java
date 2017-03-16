package actores;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.BotonesSuperBumperBehavior;
import bodies.BotonesSuperBumperBody;

public class BotonesSuperBumper extends MyActor {
	ArrayList<ConjuntoBotones> conjuntoBotones = new ArrayList<ConjuntoBotones>();

	public BotonesSuperBumper(World world, ArrayList<ConjuntoBotones> conjuntoBotones, float posX, float posY) {
		super(world, posX, posY);
		this.conjuntoBotones = conjuntoBotones;
		myBody = new BotonesSuperBumperBody(world, posX, posY);
		myBody.myBehavior = new BotonesSuperBumperBehavior(myBody, conjuntoBotones);
	}

	public ArrayList<ConjuntoBotones> getConjuntoBotones() {
		return conjuntoBotones;
	}

}
