package com.ivan.fisicas.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ivan.fisicas.FisicasUno;
import com.ivan.fisicas.Fuerza;
import com.ivan.fisicas.DebugEjemplo;
import com.ivan.fisicas.MyGdxGame;

import Collision.CollisionUno;
import Filtros.ManyWaterBalls;
import ManyBalls.ManyBalls;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.fullscreen=true;
//		new LwjglApplication(new FisicasUno(), config);
//		new LwjglApplication(new MyGdxGame(), config);
//		new LwjglApplication(new DebugEjemplo(), config);
//		new LwjglApplication(new Fuerza(), config);
//		new LwjglApplication(new ManyBalls(), config);
//		new LwjglApplication(new CollisionUno(), config);
		new LwjglApplication(new ManyWaterBalls(), config);
	}
}
