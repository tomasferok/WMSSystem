package com.iLog.app.helpers.request;



import java.util.List;

import com.iLog.app.entities.Producto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@EqualsAndHashCode
public class ControlarRecepcionRequest{

private Long idRecep;

private List<Producto> listProds;


}
