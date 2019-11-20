package de.thro.inf.prg3.a08.Services;

import de.thro.inf.prg3.a08.api.OpenMensaAPI;

public class OpenMensaAPIService {

	OpenMensaAPIService instance = null;
	OpenMensaAPI mensaAPIInstace = null;

	public OpenMensaAPIService(){

		if(instance == null){
			instance = new OpenMensaAPIService();
		}

	}

	public OpenMensaAPIService getInstance(){

		return instance;

	}

	public OpenMensaAPI getAPI(){

		return mensaAPIInstace;

	}

}
