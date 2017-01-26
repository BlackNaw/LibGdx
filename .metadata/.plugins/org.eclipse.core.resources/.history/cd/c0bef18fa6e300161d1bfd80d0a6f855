package elementos;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import comun.Constantes;
import interfaces.Reiniciable;

public class GestorEnemigos implements Reiniciable {
	private LinkedList<Enemigos> ties;
	private ArrayList<Enemigos> enemigos;
	private LinkedList<Enemigos> destructores;
	public GestorEnemigos() {
		// TODO Auto-generated constructor stub
	}
	public void generarEnemigos() {
		ties=new LinkedList();
		enemigos=new ArrayList<>();
		destructores=new LinkedList<>();
		for (int i = 0; i < Constantes.NUMERO_TIES; i++) {
			ties.add(new Tie(new Texture(Gdx.files.internal("tie.png")),"explosion"));
		}
		for (int i = 0; i < Constantes.NUMERO_DESTRUCTORES; i++) {
			destructores.add(new Destructores(new Texture(Gdx.files.internal("destructor1.png")),"explosionDes"));
		}
		
	}
	public void anadirEnemigos(int numero){
		if(enemigos.size()<ties.size())
		for (int i = 0; i < numero; i++) {
			enemigos.add(ties.getFirst());
			ties.addLast(ties.getFirst());
			ties.removeFirst();
		}
	}
	public void anadirDestructores(int numero){
			for (int i = 0; i < numero; i++) {
				enemigos.add(destructores.getFirst());
				destructores.addLast(destructores.getFirst());
				destructores.removeFirst();
			}
	}
	
	public ArrayList<Enemigos> obtenerNaves(){
		return enemigos;
	}
	public void pintarTodo(SpriteBatch batch,float f){
		for (Enemigos asteroide : enemigos) {
			asteroide.pintar(batch,f);
			asteroide.disparo.pintar(batch,0);
		}
	}
	
	public void disposeTodo(){
		for (Enemigos asteroide : enemigos) {
			asteroide.dispose();
			asteroide.disparo.dispose();
		}
	}
	
	public void reiniciarTodo(){
		for (Enemigos asteroide : enemigos) {
			asteroide.reiniciar();
			asteroide.disparo.reiniciar();
		}
	}
	@Override
	public void reiniciar() {
		enemigos.removeAll(enemigos);
		anadirEnemigos(2);
		
	}
}
