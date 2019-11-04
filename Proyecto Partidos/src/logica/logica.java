package logica;

import models.PartidoRugby;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class logica {

    //Inicializamos una variable lógica llamada instancia, y creamos un ArrayList observable

    private static logica instancia = null;
    private ObservableList<PartidoRugby> PartidosLista;

    private logica(){


        PartidosLista = FXCollections.observableArrayList();
    }

    public static logica getInstance(){



        if (instancia == null){
            instancia = new logica();
        }
        return instancia;
    }

    public ObservableList<PartidoRugby> getListaPartidos() {

        return PartidosLista;
    }

    public void introducirPartido(PartidoRugby partido){

        PartidosLista.add(partido);
    }

    public void eliminarPartido(int pos){


        PartidosLista.remove(pos);
    }

    public void modificarPartido(PartidoRugby m, int posicion){

        PartidosLista.set(posicion,m);
    }

}