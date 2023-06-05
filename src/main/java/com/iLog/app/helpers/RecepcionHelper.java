package com.iLog.app.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iLog.app.IServices.IProductoService;
import com.iLog.app.IServices.IRecepcionService;
import com.iLog.app.entities.Estado;
import com.iLog.app.entities.Producto;
import com.iLog.app.entities.Recepcion;
import com.iLog.app.helpers.request.ControlarRecepcionRequest;

@Component
public class RecepcionHelper {
	@Autowired
	IRecepcionService recepServ;

	@Autowired
	IProductoService prodServ;
	public HashMap<String,Object> controlarRecepcion(ControlarRecepcionRequest controlReq) {
		Map<String, Object>response = new HashMap<>();
		Recepcion recep = recepServ.getById(controlReq.getIdRecep());
		AtomicReference<Integer> i = new AtomicReference<Integer>();
		AtomicReference<Boolean> estado = new AtomicReference<Boolean>();
		AtomicReference<String> mensaje = new AtomicReference<String>();
		estado.set(false);
		mensaje.set("-");
		i.set(0);
		recep.getListaProds().stream().forEach((prod) ->{
			if(controlReq.getListProds().contains(prod)&&controlReq.getListProds().get(i.get()).getAmount() == prod.getAmount()) {
				
//				Producto productRecep = prodServ.getById(prod.getIdProd());
//				productRecep.setState(Estado.ENSTOCK);
//				productRecep.setIdAlma(1l);
				prod.setState(Estado.ENSTOCK);
				prod.setIdAlma(1l);
				prodServ.save(prod);
				i.set(i.get()+1);
			}else {
				mensaje.set(mensaje.get() + "El producto " + prod.getNameProd() + " tiene diferencias en su stock, ");
				recep.setDescripEstado(mensaje.get());
				estado.set(true);
				i.set(i.get()+1);
			}
		}
		
		
		);
		
		if(estado.get()==true) {
			recep.setEstadoRecep(Estado.DIFERENCIAS);
			recepServ.save(recep);
			mensaje.set("Recepcion");
			response.put(mensaje.get(), recep);
		}else {
			recep.setEstadoRecep(Estado.CONTROLADO);
			recep.setDescripEstado("Recepcion Controlada Exitosamente!!");
			recepServ.save(recep);
			mensaje.set("Recepcion");
			response.put(mensaje.get(), recep);
		}
		
		return (HashMap<String, Object>) response;
	}
	
	
	public void confirmRecep(ControlarRecepcionRequest controlReq) {
		Recepcion recep = recepServ.getById(controlReq.getIdRecep());
		AtomicReference<Integer> i = new AtomicReference<Integer>();
		i.set(0);
		recep.getListaProds().stream().forEach((prod)->{
			if(controlReq.getListProds().get(i.get()).getNameProd().equals(prod.getNameProd())) {
			prod.setAmount(controlReq.getListProds().get(i.get()).getAmount());
			prod.setState(Estado.ENSTOCK);
			prod.setIdAlma(1l);
			prodServ.save(prod);
			}
			i.set(i.get()+ 1);
		});
		
		recep.setEstadoRecep(Estado.CONTROLADO);
		recep.setDescripEstado("Recepcion Controlada Exitosamente!!");
		recepServ.save(recep);
	}
}
