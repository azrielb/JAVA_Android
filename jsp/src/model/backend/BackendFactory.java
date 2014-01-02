package model.backend;

public class BackendFactory {
	static Backend instance = null;

	public final static Backend getInstance() {
		if (instance == null)
			instance = new model.backend.ServerBackend(); 
		return instance;
	}

}
