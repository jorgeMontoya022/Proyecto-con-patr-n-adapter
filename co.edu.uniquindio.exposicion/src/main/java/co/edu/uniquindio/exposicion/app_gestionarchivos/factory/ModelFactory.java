package co.edu.uniquindio.exposicion.app_gestionarchivos.factory;

import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoBase;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoTexto;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.GestorArchivos;

import java.util.List;

public class ModelFactory {

    private static ModelFactory modelFactory;
    private GestorArchivos gestorArchivos;

    private ModelFactory() {
        gestorArchivos = new GestorArchivos();
        initializeData();
    }


    public static ModelFactory getInstance() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }


    private void initializeData() {
        ArchivoBase archivoBase = new ArchivoBase("DocumentoProgramación.");
        ArchivoBase archivoBase2 = new ArchivoBase("programación2021");


        gestorArchivos.agregarArchivo(archivoBase);
        gestorArchivos.agregarArchivo(archivoBase2);
    }


    public List<ArchivoBase> obtenerArchivos() {
        return gestorArchivos.getListaArchivosTexto();
    }
}
