package com.iLog.app.helpers;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.iLog.app.IServices.IProductoService;
import com.iLog.app.entities.Producto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class GenerarEtiquetaHelper {
	@Autowired
	private IProductoService prodServ;
	

	public ResponseEntity<byte[]> generarCodigosDeBarra(@RequestBody List<Producto> listaProductos) {
	    try {
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        Document document = createDocument();
	        PdfWriter writer = createPdfWriter(document, outputStream);
	        document.open();

	        Font font = createFont();
	        generarCodigosDeBarras(document, writer, listaProductos, font);

	        document.close();
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        return ResponseEntity.ok().headers(headers).body(outputStream.toByteArray());
	    } catch (DocumentException e) {
	        return ResponseEntity.status(500).build();
	    }
	}

	private Document createDocument() {
	    return new Document(PageSize.A4);
	}

	private PdfWriter createPdfWriter(Document document, ByteArrayOutputStream outputStream) throws DocumentException {
	    return PdfWriter.getInstance(document, outputStream);
	}

	private Font createFont() {
	    return FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
	}

	private void generarCodigosDeBarras(Document document, PdfWriter writer, List<Producto> listaProductos, Font font) throws DocumentException {
	    for (Producto product : listaProductos) {
	        String barcodeNumber = generateBarcodeNumber();
	        generarCodigoDeBarras(document, writer, product, barcodeNumber, font);
	        saveProduct(product, barcodeNumber);
	    }
	}

	private void generarCodigoDeBarras(Document document, PdfWriter writer, Producto product, String barcodeNumber, Font font) throws DocumentException {
	    for (int i = 0; i < product.getAmount(); i++) {
	        Barcode128 barcode = createBarcode128(barcodeNumber);
	        com.itextpdf.text.Image image = createBarcodeImage(barcode, writer.getDirectContent());

	        Paragraph paragraph = createParagraph(product, font);
	        document.add(paragraph);
	        document.add(image);
	    }
	}

	private Barcode128 createBarcode128(String barcodeNumber) {
	    Barcode128 barcode = new Barcode128();
	    barcode.setCode(barcodeNumber);
	    barcode.setCodeType(Barcode128.CODE128);
	    return barcode;
	}

	private com.itextpdf.text.Image createBarcodeImage(Barcode128 barcode, PdfContentByte contentByte) {
	    return barcode.createImageWithBarcode(contentByte, BaseColor.BLACK, BaseColor.BLUE);
	}

	private Paragraph createParagraph(Producto product, Font font) {
	    String text = "ID Producto: " + product.getIdProd() + " Nombre Producto: " + product.getNameProd();
	    return new Paragraph(text, font);
	}

	private void saveProduct(Producto product, String barcodeNumber) {
	    product.setCodigoDeBarras(barcodeNumber);
	    prodServ.save(product);
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