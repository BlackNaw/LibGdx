package comun;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import actores.Torreta;
import interfaz.IObservable;
import interfaz.IObservador;

/**
 * Created by JAVI on 10/03/2017.
 */

public class Teclado extends InputAdapter implements IObservable{
    private ArrayList<IObservador> observadores=new ArrayList<IObservador>();
    private boolean ctrl = false;
    private Torreta torreta;
    public Teclado(Torreta actor) {
        torreta=actor;
    }

    @Override
    public boolean keyDown(int keycode) {
        comprobarCombinacion(keycode);
        if(keycode==Input.Keys.LEFT){
            torreta.girarIzquierda();
        }
        if(keycode==Input.Keys.RIGHT){
            torreta.girarDerecha();
        }
        if(keycode== Input.Keys.SPACE){
            torreta.disparar();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.CONTROL_LEFT) {
            ctrl = false;
        }
        if(keycode==Input.Keys.LEFT||keycode==Input.Keys.RIGHT){
            torreta.parar();
        }
        return true;
    }

    private void comprobarCombinacion(int keycode) {
        if (ctrl) {
            if (keycode == Input.Keys.D) {
                notifyObservers();
            }
        }
        if (keycode == 129)
            ctrl = true;
    }



    @Override
    public void addObserver(IObservador observador) {
        observadores.add(observador);
    }

    @Override
    public void removeObserver(IObservador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notifyObservers() {
        for (IObservador observador: observadores) {
            observador.update();
        }
    }



}
