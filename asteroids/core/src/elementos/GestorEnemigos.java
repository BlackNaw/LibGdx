package elementos;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import interfaces.Reiniciable;

public class GestorEnemigos implements Reiniciable {
	private LinkedList<Enemigos> todasTie;
	private ArrayList<Enemigos> tie;
	private Enemigos estrella;
	public GestorEnemigos() {
		// TODO Auto-generated constructor stub
	}
	public void generarEnemigos(int numeroNaves,String imagen) {
		todasTie=new LinkedList();
		tie=new ArrayList<>();
		estrella=new EstrellaMuerte(new Texture(Gdx.files.internal("nave.png")));
		for (int i = 0; i < numeroNaves; i++) {
			todasTie.add(new Tie(new Texture(Gdx.files.internal(imagen))));
		}
		
	}
	public void anadirEnemigos(int numero){
		if(tie.size()<todasTie.size())
		for (int i = 0; i < numero; i++) {
			tie.add(todasTie.getFirst());
			todasTie.addLast(todasTie.getFirst());
			todasTie.removeFirst();
		}
	}
	
	public ArrayList<Enemigos> obtenerNaves(){
		return tie;
	}
	public void pintarTodo(SpriteBatch batch,float f){
		for (Enemigos asteroide : tie) {
			asteroide.pintar(batch,f);
			asteroide.disparo.pintar(batch,0);
		}
	}
	
	public void disposeTodo(){
		for (Enemigos asteroide : tie) {
			asteroide.dispose();
			asteroide.disparo.dispose();
		}
	}
	
	public void reiniciarTodo(){
		for (Enemigos asteroide : tie) {
			asteroide.reiniciar();
			asteroide.disparo.reiniciar();
		}
	}
	@Override
	public void reiniciar() {
		tie.removeAll(tie);
		anadirEnemigos(2);
		
	}
}
