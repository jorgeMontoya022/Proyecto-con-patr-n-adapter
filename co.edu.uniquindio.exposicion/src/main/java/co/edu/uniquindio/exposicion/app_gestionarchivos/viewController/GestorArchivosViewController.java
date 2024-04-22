package co.edu.uniquindio.exposicion.app_gestionarchivos.viewController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.exposicion.app_gestionarchivos.adapter.AdaptadorArchivoWord;
import co.edu.uniquindio.exposicion.app_gestionarchivos.controller.GestorArchivosController;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoBase;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoTexto;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoWordDocx;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GestorArchivosViewController {

    ObservableList<ArchivoBase> listaArchivoTexto = FXCollections.observableArrayList();
    ArchivoBase archivoTextoSeleccionado;

    GestorArchivosController gestorArchivosController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdaptarArchivoWord;

    @FXML
    private TableView<ArchivoBase> tableGestionArchivos;

    @FXML
    private TableColumn<ArchivoBase, String> tbArchivos;

    @FXML
    private TextField txtNombreArchivo;

    @FXML
    void onAdaptarArchivoAWord(ActionEvent event) {

        adaptarArchivo();

    }


    @FXML
    void initialize() {
        gestorArchivosController = new GestorArchivosController();
        initView();

    }

    private void initView() {
        initDataBinding();
        obtenerArchivos();
        tableGestionArchivos.getItems().clear();
        tableGestionArchivos.setItems(listaArchivoTexto);
        listenerSeleccion();
    }




    private void initDataBinding() {
        tbArchivos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreArchivo()));
    }

    private void obtenerArchivos() {
        listaArchivoTexto.addAll(gestorArchivosController.obtenerArchivos());
    }

    private void listenerSeleccion() {
        tableGestionArchivos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
         archivoTextoSeleccionado = newSelection;
         mostrarInformacionArchivo(archivoTextoSeleccionado);
        });


    }

    private void mostrarInformacionArchivo(ArchivoBase archivoTextoSeleccionado) {
        if(archivoTextoSeleccionado!=null){
            txtNombreArchivo.setText(archivoTextoSeleccionado.getNombreArchivo());
        }
    }


    private void adaptarArchivo() {
        if (archivoTextoSeleccionado != null) {
            mostrarMensaje("Adaptando Archivo", null, "Adaptando archivo de texto a Word...", Alert.AlertType.INFORMATION);
            ArchivoBase archivoWord = adaptarAWord(archivoTextoSeleccionado);
            if (archivoWord != null) {
                // Agregar el archivo adaptado a la lista de archivos y actualizar la tabla
                listaArchivoTexto.add(archivoWord);

                tableGestionArchivos.refresh();
                mostrarMensaje("Éxito", null, "Archivo adaptado correctamente a Word.", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Error", null, "No se pudo adaptar el archivo a Word.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", null, "Por favor, seleccione un archivo.", Alert.AlertType.ERROR);
        }
    }

    private ArchivoBase adaptarAWord(ArchivoBase archivoBase) {
        if (archivoBase instanceof ArchivoTexto) {
            ArchivoTexto archivoTexto = (ArchivoTexto) archivoBase;
            // Crear un adaptador para el archivo de texto
            AdaptadorArchivoWord adaptador = new AdaptadorArchivoWord(archivoTexto);
            // Utilizar el adaptador para adaptar el archivo de texto a Word
            return adaptador.adaptarAWord();
        }
        return null;
    }


    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

}



