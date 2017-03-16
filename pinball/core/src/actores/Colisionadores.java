package actores;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

public class Colisionadores {

	ArrayList<MyActor> colisionadores = new ArrayList<MyActor>();

	public Colisionadores(World world) {
		colisionadores.add(new ColisionadorIzq(world, 72, 87));
		colisionadores.add(new ColisionadorDrch(world, 227, 87));
		colisionadores.add(new BarraColisionador(world, 120, 95, 32));
		colisionadores.add(new BarraColisionador(world, 230, 95, -32));

	}

	public ArrayList<MyActor> getColisionadores() {
		return colisionadores;
	}

}
