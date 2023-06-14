package com.iLog.app.controllers;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iLog.app.IServices.IProductoService;
import com.iLog.app.entities.Producto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;



@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ProductoController {
	
	@Autowired
	private IProductoService prodServ;
	
	
	
	@GetMapping("/productos/{id}")
	public Producto getById(@PathVariable Long id) {
		return prodServ.getById(id);
	}
	
	@GetMapping("/productos")
	public List<Producto>getAll(){
		
		return prodServ.getAll();
	}
	@GetMapping("/productosbyAlmaid/{idAlma}")
	public List<Producto>getByAlmaId(@PathVariable Long idAlma){
		
		return prodServ.findByIdAlma(idAlma);
	}
	
	@PutMapping("/productos/{idProd}")
	public Producto update(@RequestBody Producto producto, @PathVariable Long idProd, RedirectAttributes flash) {
		
		Producto productoActual = prodServ.getById(idProd);
		
		if(idProd > 0) {
			producto = prodServ.getById(idProd);
			flash.addFlashAttribute("error", "El ID de el producto no existe en la BBDD!");
			
		}else {
			flash.addFlashAttribute("error", "El ID del producto no puede ser cero!");
		}
			productoActual.setNameProd(producto.getNameProd());
			productoActual.setPrice(producto.getPrice());
			productoActual.setAmount(producto.getAmount());
		
		return prodServ.save(productoActual);
		
		
	}
	
	 @PostMapping("/productos")
	    public Producto createProducto(@RequestBody Producto producto) {
		 		 	
		 	return prodServ.save(producto);
	    }
	 
	 @DeleteMapping("/productos/{idProd}")
	    public void deleteProducto(@PathVariable Long idProd) {
	        prodServ.remove(idProd);
	    }
	
	 @PostMapping(value = "/barcodes", produces = MediaType.APPLICATION_PDF_VALUE)
	 public ResponseEntity<byte[]> generarCodigosDeBarra(@RequestBody List<Producto> listaProductos) {
//	     try {
//	         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//	         Document document = new Document(PageSize.A4);
//	         PdfWriter writer = PdfWriter.getInstance(document, outputStream);
//	         document.open();
//
//	         Font font = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
//	         for (Producto product : listaProductos) {
//	             String barcodeNumber = generateBarcodeNumber();
//	             for (int i = 0; i < product.getAmount(); i++) {
//	                 Barcode128 barcode = new Barcode128();
//	                 barcode.setCode(barcodeNumber);
//	                 barcode.setCodeType(Barcode128.CODE128);
//	                 com.itextpdf.text.Image image = barcode.createImageWithBarcode(writer.getDirectContent(), BaseColor.BLACK, BaseColor.BLUE);
//
//	                 Paragraph paragraph = new Paragraph("ID Producto: " + product.getIdProd() + " Nombre Producto: " + product.getNameProd(), font);
//	                 document.add(paragraph);
//	                 document.add(image);
//	             }
//	             product.setCodigoDeBarras(barcodeNumber);
//	             prodServ.save(product);
//	         }
//
//	         document.close();
//	         HttpHeaders headers = new HttpHeaders();
//	         headers.setContentType(MediaType.APPLICATION_PDF);
	         return prodServ.generarEtiquetas(listaProductos);
//	     } catch (DocumentException e) {
//	         return ResponseEntity.status(500).build();
//	     }
	 }

	 private String generateBarcodeNumber() {
	     Random random = new Random();
	     StringBuilder barcodeNumber = new StringBuilder();
	     for (int i = 0; i < 10; i++) {
	         barcodeNumber.append(random.nextInt(10));
	     }
	     return barcodeNumber.toString();
	 }

	
	}