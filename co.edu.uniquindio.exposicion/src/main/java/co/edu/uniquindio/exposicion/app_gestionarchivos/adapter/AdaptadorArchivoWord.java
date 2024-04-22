package co.edu.uniquindio.exposicion.app_gestionarchivos.adapter;

import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoTexto;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoWordDocx;
import co.edu.uniquindio.exposicion.app_gestionarchivos.services.IArchivo;

public class AdaptadorArchivoWord implements IArchivo {
    private ArchivoTexto archivoTexto;

    public AdaptadorArchivoWord(ArchivoTexto archivoTexto) {
        this.archivoTexto = archivoTexto;
    }

    @Override
    public void abrir() {
        // Aquí podríamos implementar la lógica para abrir un archivo de texto
        System.out.println("Abriendo archivo de texto: " + archivoTexto.getNombreArchivo());
    }

    @Override
    public void cerrar() {
        // Aquí podríamos implementar la lógica para cerrar un archivo de texto
        System.out.println("Cerrando archivo de texto: " + archivoTexto.getNombreArchivo());
    }

    public ArchivoWordDocx adaptarAWord() {
        // Obtener el nombre del archivo de texto

            String nombreArchivoTexto = archivoTexto.getNombreArchivo();
            // Cambiar la extensión a ".docx"
            String nombreArchivoWord = nombreArchivoTexto.substring(0, nombreArchivoTexto.lastIndexOf('.')) + ".docx";
            // Crear un nuevo archivo de Word con el nombre modificado
            return new ArchivoWordDocx(nombreArchivoWord);
        }
}
