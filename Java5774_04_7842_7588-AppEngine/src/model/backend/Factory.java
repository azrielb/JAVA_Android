package model.backend;

public class Factory {
	static Backend instance = null;

	public final static Backend getInstance() {
		if (instance == null) 
			instance = new model.backend.DBbackend();
		return instance;
	}

}
