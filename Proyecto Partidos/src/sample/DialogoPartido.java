package sample;

import javafx.scene.control.*;
import logica.logica;
import models.PartidoRugby;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DialogoPartido extends Stage {

    private TextField eLocal;                                       //Declaración de variables
    private TextField eVisitante;
    private TextField div;
    private TextField resul;
    private DatePicker FechaPartido;
    private Button botonAceptar;

    public DialogoPartido() throws ParseException {
        Vista();
        botonAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                añadirPartido();
            }
        });
    }

    public static final LocalDate fechaLocal(String dateString) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaLocal = LocalDate.parse(dateString, formato);
        return fechaLocal;
    }

    public DialogoPartido(PartidoRugby partido, int i) {

        Vista();
        eLocal.setText(partido.getLocal());
        eVisitante.setText(partido.getVisitante());
        div.setText(partido.getDivision());
        resul.setText(partido.getResultado());
        FechaPartido.setValue(fechaLocal(partido.getFecha()));

        botonAceptar.setOnAction(new EventHandler<ActionEvent>() {

            @Override

            public void handle(ActionEvent event) {

                PartidoRugby m = new PartidoRugby(eLocal.getText(), eVisitante.getText(), div.getText(), resul.getText(), FechaPartido.getValue().toString());
                logica.getInstance().modificarPartido(m, i);
                close();

            }
        });
    }

    private void añadirPartido() {

        PartidoRugby partido = new PartidoRugby(eLocal.getText(), eVisitante.getText(), div.getText(), resul.getText(), FechaPartido.getValue().toString());
        logica.getInstance().introducirPartido(partido);
        close();

    }

    public void Vista() {


        initModality(Modality.APPLICATION_MODAL);  //Establezco el tipo de la ventana(Revisar más adelante, no estoy seguro de que
        setTitle("Registro de partidos");          //esta sentencia sea la correcta)

        Label local = new Label("Equipo local");
        eLocal = new TextField();

        Label visitante = new Label("Equipo visitante");
        eVisitante = new TextField();

        Label division = new Label("Division");
        div = new TextField();

        Label resultado = new Label("Resultado");
        resul = new TextField();

        Label fecha = new Label("Fecha");
        FechaPartido = new DatePicker();

        botonAceptar = new Button("Aceptar");

        VBox ventana = new VBox(local, eLocal, visitante, eVisitante, division, div, resultado, resul, fecha, FechaPartido, botonAceptar);

        Scene escenaPartido = new Scene(ventana, 700, 550);  //Mensaje para que el que me compruebe la aplicación
        setScene(escenaPartido);                                    //Ajústame bien el tamaño de los componentes, porfa
    }
}




