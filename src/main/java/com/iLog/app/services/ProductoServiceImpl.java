package com.iLog.app.services;

import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iLog.app.IServices.IAlmacenamientoService;
import com.iLog.app.IServices.IProductoService;
import com.iLog.app.entities.Almacenamiento;
import com.iLog.app.entities.Estado;
import com.iLog.app.entities.Producto;
import com.iLog.app.helpers.ProductoHelper;
import com.iLog.app.repositories.ProductoRepository;


@Service
public class ProductoServiceImpl implements IProductoService {

	
	@Autowired
    ProductoRepository prodServ;
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
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Producto save(Producto producto) {
		// Obtiene el almacenamiento de ese producto en espesifico
		Almacenamiento alma = almaSrv.getById(producto.getIdAlma());
		
		List<Producto> listaProductosAlmacen = new ArrayList();
		
		helper.checkStateProd(producto, listaProductosAlmacen, alma);
		
			// Si el producto no existe en el almacen se agrega a la lista
//			helper.addProdToListOrNot(producto, listaProductosAlmacen, alma);
//			listaProductosAlmacen.stream().parallel().forEach(prod->{
//				if(prod.getNameProd().equals(producto.getNameProd()) && producto.getAmount()!=prod.getAmount()) {
//					this.remove(producto.getIdProd());
//				}
//			});
			alma.setProds(listaProductosAlmacen);
			almaSrv.save(alma);
			return producto;
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
