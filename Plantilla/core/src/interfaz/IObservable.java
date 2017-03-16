package interfaz;

public interface IObservable {
	
		public void addObserver(IObservador observador);
		public void removeObserver(IObservador observador);
		public void notifyObservers();

}
