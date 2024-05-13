package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.facades.dtos.EstadoViandaEnum;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Vianda {
  private Long id;
  private String qr;
  private Long colaboradorId;
  private Integer heladeraId;
  private EstadoViandaEnum estado;
  private LocalDateTime fechaElaboracion;

  public Vianda(
      String qr,
      Long colaboradorId,
      Integer heladeraId,
      EstadoViandaEnum estado,
      LocalDateTime fechaElaboracion) {
    this.qr = qr;
    this.colaboradorId = colaboradorId;
    this.heladeraId = heladeraId;
    this.estado = estado;
    this.fechaElaboracion = fechaElaboracion;
  }
}
