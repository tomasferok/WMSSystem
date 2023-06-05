package com.iLog.app.IServices;

import java.util.HashMap;
import java.util.List;


import com.iLog.app.entities.Recepcion;
import com.iLog.app.helpers.request.ControlarRecepcionRequest;

public interface IRecepcionService {

	List<Recepcion> getAll();
	Recepcion getById(Long id);
	void remove(Long id);
	Recepcion save( Recepcion recep);
	HashMap<String, Object>controlarRecepcion(ControlarRecepcionRequest recepReq);
	void confirmRecep(ControlarRecepcionRequest recepReq);
}
