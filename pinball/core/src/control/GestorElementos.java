package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actores.AgujeroNegro;
import actores.Ball;
import actores.BotonesSuperBumper;
import actores.BumperA;
import actores.Colisionadores;
import actores.ConjuntoBotones;
import actores.FlipperL;
import actores.FlipperR;
import actores.HUD;
import actores.Muro;
import actores.MuroDesague;
import actores.MyActor;
import actores.Puerta;
import actores.Rejilla;
import actores.RejillaAcceso;
import actores.RejillaDesague;
import actores.SuperBumper;
import actores.Tablero;
import behaviors.BotonesSuperBumperBehavior;
import interfaces.IObservador;

public class GestorElementos {
	MyActor ball, tablero, hud, rejillaAcceso, superBumper;
	ArrayList<MyActor> bumpers = new ArrayList<MyActor>();
	ArrayList<ConjuntoBotones> arrayBotonesLaberinto = new ArrayList<ConjuntoBotones>();
	ArrayList<ConjuntoBotones> arrayBotonesSuper = new ArrayList<ConjuntoBotones>();
	MyActor todosBotones;
	MyActor botonesLaberinto;
	HashMap<String, RejillaAcceso> rejillaAccceso;
	Puerta trampilla;
	RejillaDesague desague;
	Colisionadores colisionadores;
	MuroDesague muro;
	ArrayList<MuroDesague> muros = new ArrayList<MuroDesague>();
	ArrayList<Rejilla> rejillas = new ArrayList<Rejilla>();
	ArrayList<AgujeroNegro> agujeroNegro;
	ArrayList<MyActor> e = new ArrayList<MyActor>();
	MyActor flipperR, flipperL;

	public GestorElementos(World world) {
		ball = new Ball(world, 360, 200, e);
		tablero = new Tablero(world, 0, 0);
		hud = new HUD(world, Gdx.graphics.getWidth() - 210, Gdx.graphics.getHeight() - 100);

		flipperL = new FlipperL(world, 128, 52, 50);
		flipperR = new FlipperR(world, 200, 51.5f, 50);

		colisionadores = new Colisionadores(world);

		arrayBotonesLaberinto.add(new ConjuntoBotones(world, 58, 467, -60, 1.5f));
		arrayBotonesLaberinto.add(new ConjuntoBotones(world, 43, 587, -58, 1.5f));

		arrayBotonesSuper.add(new ConjuntoBotones(world, 32, 305, 0, 1));
		arrayBotonesSuper.add(new ConjuntoBotones(world, 345, 290, 30, 0.5f));
		arrayBotonesSuper.add(new ConjuntoBotones(world, 335, 525, 40, 1f));

		todosBotones = new BotonesSuperBumper(world, arrayBotonesSuper, 0, 0);
		botonesLaberinto = new BotonesSuperBumper(world, arrayBotonesLaberinto, 0, 0);

		superBumper = new SuperBumper(world, 121, 207);

		((BotonesSuperBumperBehavior) todosBotones.myBody.myBehavior).addObserver((IObservador) ball);

		((BotonesSuperBumperBehavior) todosBotones.myBody.myBehavior)
				.addObserver((IObservador) superBumper.myBody.myBehavior);

		e.add(ball);
		agujeroNegro = new ArrayList<AgujeroNegro>();
		agujeroNegro.add(new AgujeroNegro(world, 24, 250, e));
		agujeroNegro.add(new AgujeroNegro(world, 301, 250, e));

		// ------ DAVID----//
		rejillaAccceso = new HashMap<String, RejillaAcceso>();
		rejillaAccceso.put("rejillaAcceso", new RejillaAcceso(3, world, 168, 575, "Amarillo"));
		rejillaAccceso.put("rejillaAccesoLab", new RejillaAcceso(2, world, 15, 450, "Verde"));
		// -- DAVID--//

		bumpers.add(new BumperA(world, 186, 514, rejillaAccceso.get("rejillaAcceso")));
		bumpers.add(new BumperA(world, 246, 478, rejillaAccceso.get("rejillaAcceso")));
		// bumpers.add(new BumperA(world, 172, 442,
		// rejillaAccceso.get("rejillaAcceso")));
		bumpers.add(new BumperA(world, 150, 430, rejillaAccceso.get("rejillaAcceso")));
		// bumpers.add(new BumperA(world, 236, 398,
		// rejillaAccceso.get("rejillaAcceso")));
		bumpers.add(new BumperA(world, 220, 380, rejillaAccceso.get("rejillaAcceso")));
		bumpers.add(new BumperA(world, 65, 525, rejillaAccceso.get("rejillaAccesoLab")));

		// -- DAVID--//
		trampilla = new Puerta(world, 254, 602, 30, 2, "bloqueo.png");
		trampilla.setCerrar(false);

		rejillas.add(new Rejilla(world, 16, 136, "Azul"));
		rejillas.add(new Rejilla(world, 50, 136, "Azul"));
		rejillas.add(new Rejilla(world, 316, 136, "Azul"));
		rejillas.add(new Rejilla(world, 280, 136, "Azul"));
		rejillaAccceso.put("rejillaDesague", new RejillaAcceso(world, rejillas, new ArrayList<Muro>()));

		muro = new MuroDesague(world, 16, 132, 2, 20, "bloqueo.png");
		muros.add(muro);
		muro = new MuroDesague(world, 316, 132, 2, 20, "bloqueo.png");
		muros.add(muro);
		desague = new RejillaDesague(muros);
		rejillaAccceso.get("rejillaDesague").addObserver(desague);
		// -- DAVID--//
	}

	public void anadirElementosStage(Stage stage) {
		stage.addActor(tablero);
		for (Entry<String, RejillaAcceso> rejillas : rejillaAccceso.entrySet()) {
			for (Muro trump : rejillas.getValue().getMuros()) {
				stage.addActor(trump);
			}
			for (Rejilla rejilla : rejillas.getValue().getRejillas()) {
				stage.addActor(rejilla);
			}
		}
		for (MyActor myActor : bumpers) {
			stage.addActor(myActor);
		}
		stage.addActor(todosBotones);

		for (ConjuntoBotones conjunto : ((BotonesSuperBumper) todosBotones).getConjuntoBotones()) {
			for (MyActor myActor : conjunto.getBotones()) {
				stage.addActor(myActor);
			}

		}
		for (ConjuntoBotones conjunto : ((BotonesSuperBumper) botonesLaberinto).getConjuntoBotones()) {
			for (MyActor myActor : conjunto.getBotones()) {
				stage.addActor(myActor);
			}
		}
		for (MuroDesague desague : this.desague.getMuros()) {
			stage.addActor(desague);
		}

		for (AgujeroNegro agujeroNegro2 : agujeroNegro) {
			stage.addActor(agujeroNegro2);
		}

		for (MyActor colisionador : colisionadores.getColisionadores()) {
			stage.addActor(colisionador);
		}
		stage.addActor(flipperL);
		stage.addActor(flipperR);
		stage.addActor(botonesLaberinto);
		stage.addActor(superBumper);
		stage.addActor(ball);
		stage.addActor(hud);

		stage.addActor(trampilla);

		((Ball) ball).setStage(stage);

	}

	public MyActor getBall() {
		return ball;
	}

}
