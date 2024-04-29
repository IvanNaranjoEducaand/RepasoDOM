package repasoDefinitivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

public class repaso {
	public static void main(String[] args) {
		// DOM
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File("RUTA"));
			
			//Cojo el elemento (etiqueta) raíz
            Element root = doc.getDocumentElement();

            //Obtengo todos los nodos hijos del elemento raíz
            NodeList nl = root.getChildNodes();
            
            // RECORRER TODOS LOS ELEMENTOS Y SUS ELEMENTOS HIJOS
            for(int i = 0; i < nl.getLength(); i++) {
            	if(nl.item(i).getNodeType() == Node.ELEMENT_NODE) { //SE COMPRUEBA QUE DONDE NOS ENCONTRAMOS ES UN NODO DE TIPO ELEMENTO
            		NodeList HijosElemento = nl.item(i).getChildNodes();
            		
            		// BUCLE QUE RECORRE LOS HIJOS DEL ELEMENTO SELECCIONADO
            		for(int j = 0; j < HijosElemento.getLength(); j++) {
                    	if(HijosElemento.item(j).getNodeType() == Node.ELEMENT_NODE) { //SE COMPRUEBA QUE DONDE NOS ENCONTRAMOS ES UN NODO DE TIPO ELEMENTO
                    		System.out.println(HijosElemento.item(j).getNodeName()+": "+HijosElemento.item(j).getTextContent());
                    	}
                    }
            		
            		System.out.println(); // ESPACIO EN BLANCO
            	}
            }
          
            // JSON
            Gson gson = new Gson(); // DECLARACIÓN DE LA VARIABLE GSON
            String json = gson.toJson("HOLA"); // AQUÍ PODRÁ IR CUALQUIER TIPO DE ELEMENTO, TANTO OBJETO COMO LISTA
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("RUTA"))) {
    		    bw.write(json);
    		    System.out.println("Fichero creado");
            }
            
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
