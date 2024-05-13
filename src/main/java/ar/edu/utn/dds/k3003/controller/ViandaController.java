package ar.edu.utn.dds.k3003.controller;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.dtos.ViandaDTO;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.NoSuchElementException;

public class ViandaController {

  private final Fachada fachada;

  public ViandaController(Fachada fachada) {
    this.fachada = fachada;
  }

  public void agregarVianda(Context context) {
    var viandaDTO = context.bodyAsClass(ViandaDTO.class);
    var viandaDTORta = this.fachada.agregar(viandaDTO);
    context.json(viandaDTORta);
    context.status(HttpStatus.CREATED);
  }

  public void findByColaboradorIdAndAnioAndMes(Context context) {
    var id = context.pathParamAsClass("id", Long.class)
        .get();
    var anio = context.pathParamAsClass("anio", Integer.class)
        .get();
    var mes = context.pathParamAsClass("mes", Integer.class)
        .get();
    try {
      var viandaDTOS = this.fachada.viandasDeColaborador(id, mes, anio);
      context.json(viandaDTOS);
    } catch (NoSuchElementException ex) {
      context.result(ex.getLocalizedMessage());
      context.status(HttpStatus.NOT_FOUND);
    }
  }

  public void buscarPorQR(Context context) {
    var qr = context.pathParamAsClass("qr", String.class)
        .get();

    try {
      var viandaDTO = this.fachada.buscarXQR(qr);
      context.json(viandaDTO);
    } catch (NoSuchElementException ex) {
      context.result(ex.getLocalizedMessage());
      context.status(HttpStatus.NOT_FOUND);
    }
  }

  public void evaluarVencimiento(Context context) {
    var qr = context.pathParamAsClass("qr", String.class)
        .get();

    try {
      var viandaDTO = this.fachada.evaluarVencimiento(qr);
      context.json(viandaDTO);
    } catch (NoSuchElementException ex) {
      context.result(ex.getLocalizedMessage());
      context.status(HttpStatus.NOT_FOUND);
    }
  }

  public void modificarHeladera(Context context) {
    var qr = context.pathParamAsClass("qr", String.class)
        .get();
    var idHeladera = context.bodyAsClass(Integer.class);

    var viandaDTO = this.fachada.modificarHeladera(qr, idHeladera);
    context.json(viandaDTO);

  }
}
