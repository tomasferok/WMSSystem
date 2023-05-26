package com.iLog.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iLog.app.entities.Almacenamiento;
import com.iLog.app.entities.Estado;
import com.iLog.app.entities.Producto;
import com.iLog.app.helpers.ProductoHelper;
import com.iLog.app.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

	
	@Autowired
	private ProductoRepository prodServ;
	@Autowired
	IAlmacenamientoService almaSrv;
	@Autowired
	ProductoHelper helper;
    
	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return (List<Producto>) prodServ.findAll();
	}

	@Override
	public Producto getById(Long id) {
		// TODO Auto-generated method stub
		return prodServ.findById(id).orElse(null);
	}

	@Override
	public void remove(Long id) {

		prodServ.deleteById(id);
	}

	@Override
	public Producto save(Producto producto) {
		// Obtiene el almacenamiento de ese producto en espesifico
		Almacenamiento alma = almaSrv.getById(producto.getIdAlma());
		
		List<Producto> a = new ArrayList();
		
		helper.checkStateProd(producto, a, alma);
		
			// Si el producto no existe en el almacen se agrega a la lista
			helper.addProdToListOrNot(producto, a, alma);
			
			alma.setProds(a);
			almaSrv.save(alma);
			return a.get(0);
		}
			
	

	@Override
	public Producto findByNameProd(String nombreProd) {
		
		return prodServ.findByNameProd(nombreProd);
	}

	@Override
	public Producto findByNameProdAndState(String nombreProd, Estado e) {
		// TODO Auto-generated method stub
		return prodServ.findByNameProdAndState(nombreProd, e);
	}
	

}
