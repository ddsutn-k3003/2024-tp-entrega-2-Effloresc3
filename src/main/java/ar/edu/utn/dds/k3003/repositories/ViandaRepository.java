package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Vianda;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class ViandaRepository {

  private static AtomicLong seqId = new AtomicLong();
  private Collection<Vianda> viandas;

  public ViandaRepository() {
    this.viandas = new ArrayList<>();
  }

  public Vianda save(Vianda vianda) {
    if (Objects.isNull(vianda.getId())) {
      vianda.setId(seqId.getAndIncrement());
      this.viandas.add(vianda);
    }
    return vianda;
  }

  public Vianda findByQr(String qr) {
    Optional<Vianda> first =
        this.viandas.stream().filter(vianda -> vianda.getQr().equals(qr)).findFirst();
    return first.orElseThrow(
        () -> new NoSuchElementException(String.format("No hay una ruta de " + "qr: %s", qr)));
  }

  public List<Vianda> findByCollaboratorIdAndYerAndMonth(
      Long colaboradorId, Integer mes, Integer anio) {
    YearMonth yearMonth = YearMonth.of(anio, mes);
    return viandas.stream()
        .filter(
            vianda ->
                vianda.getColaboradorId().equals(colaboradorId)
                    && YearMonth.from(vianda.getFechaElaboracion()).equals(yearMonth))
        .toList();
  }
}
