package control;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actores.MyActor;
import actores.MyActor2;

public class GestorActores {

	Actor myActor;
	Actor myActor2;
	/**
	 * Crea los actores
	 * @param world
	 */
	public GestorActores(World world,Stage stage) {
		myActor = new MyActor(world,20f,20f);
		myActor2 = new MyActor2(world,40f,40f);
		this.anadirActoresStage(stage);
	}

	/**
	 *  A�ade los actores al stage
	 * @param stage
	 */
	private void anadirActoresStage(Stage stage) {
		stage.addActor(myActor);
		stage.addActor(myActor2);
    }
}
