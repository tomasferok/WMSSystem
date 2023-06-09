package com.iLog.app.IServices;

import java.util.List;
import com.iLog.app.entities.Proveedor;

public interface IProveedorService {
	List<Proveedor> getAll();
	Proveedor getById(Long id);
	void remove(Long id);
	Proveedor save( Proveedor prov);
}
