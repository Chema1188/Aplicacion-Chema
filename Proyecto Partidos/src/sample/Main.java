package sample;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logica.logica;
import models.PartidoRugby;

import java.text.ParseException;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Aplicación de control y registro de partidos de rugby.");
        TableView tablaPartidos = new TableView(logica.getInstance().getListaPartidos());           //Establecer los tamaños, las columnas
        AnchorPane.setTopAnchor(tablaPartidos,20d);                                        //y la ejecución de los botones
        AnchorPane.setLeftAnchor(tablaPartidos,40d);
        AnchorPane.setRightAnchor(tablaPartidos,40d);
        AnchorPane.setBottomAnchor(tablaPartidos,50d);

        TableColumn<String, PartidoRugby> columna1 = new TableColumn<>("Local");
        columna1.setCellValueFactory(new PropertyValueFactory<>("local"));

        TableColumn<String, PartidoRugby> columna2 = new TableColumn<>("Visitante");
        columna2.setCellValueFactory(new PropertyValueFactory<>("visitante"));

        TableColumn<String, PartidoRugby> columna3 = new TableColumn<>("Division");
        columna3.setCellValueFactory(new PropertyValueFactory<>("division"));

        TableColumn<String, PartidoRugby> columna4 = new TableColumn<>("Resultado");
        columna4.setCellValueFactory(new PropertyValueFactory<>("resultado"));

        TableColumn<String, PartidoRugby> columna5 = new TableColumn<>("Fecha");
        columna5.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        tablaPartidos.getColumns().addAll(columna1, columna2, columna3, columna4, columna5);

        Button buttonAlta = new Button("Alta");
        AnchorPane.setBottomAnchor(buttonAlta, (double) 10);
        AnchorPane.setLeftAnchor(buttonAlta, (double) 10);
        AnchorPane.setRightAnchor(buttonAlta, (double) 5);
        AnchorPane.setTopAnchor(buttonAlta, (double) 10);


        Button buttonBorrar = new Button("Borrar");
        AnchorPane.setBottomAnchor(buttonBorrar, (double) 10);
        AnchorPane.setRightAnchor(buttonBorrar, (double) 10);
        AnchorPane.setRightAnchor(buttonAlta, (double) 5);
        AnchorPane.setTopAnchor(buttonAlta, (double) 10);

        Button buttonModificar = new Button("Modificar");
        AnchorPane.setBottomAnchor(buttonModificar, (double) 10);
        AnchorPane.setLeftAnchor(buttonModificar, (double) 10);
        AnchorPane.setRightAnchor(buttonAlta, (double) 5);
        AnchorPane.setTopAnchor(buttonAlta, (double) 10);

        AnchorPane anchorPane = new AnchorPane(tablaPartidos, buttonAlta, buttonBorrar, buttonModificar);
        Scene escenaPrincipal = new Scene(anchorPane,700,700);
        stage.setScene(escenaPrincipal);
        stage.show();

        buttonAlta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DialogoPartido d = null;
                try {
                    d = new DialogoPartido();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                d.show();
            }
        });

        buttonBorrar.setOnAction(new EventHandler<ActionEvent>(){
            @Override                                                   //Modificar a partir de una variable de control de tipo int: a cada partido
            //se le asigna un número
            public void handle(ActionEvent actionEvent) {
                System.out.println("Introduzca el número de control del partido a registrar. ");
                int control = tablaPartidos.getSelectionModel().getSelectedIndex();
                if (control>=0)
                    logica.getInstance().eliminarPartido(control);

                //Alerta de confirmación

                Label label= new Label();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Borrado");
                alert.setHeaderText("Borrar");
                alert.setContentText("¿Seguro que quiere borrar el registro?");

                alert.showAndWait();
                if ((alert.getResult() == ButtonType.OK))
                    label.setText("Borrado realizado correctamente.");
                else
                    label.setText("Modificación fallida.");
            }
        });

        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {          //Mismo planteamiento

                int i = tablaPartidos.getSelectionModel().getSelectedIndex();
                PartidoRugby elegirPartido = logica.getInstance().getListaPartidos().get(i);
                DialogoPartido d = null;
                d = new DialogoPartido(elegirPartido,i);
                d.show();
                //Alerta de confirmación
                Label label= new Label();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("Modificar");
                alert.setContentText("¿Seguro que quiere modificar el registro?");

                alert.showAndWait();
                if ((alert.getResult() == ButtonType.OK))
                    label.setText("Modificación realizada correctamente.");
                else
                    label.setText("Modificación fallida.");
            }

        });
    }
}