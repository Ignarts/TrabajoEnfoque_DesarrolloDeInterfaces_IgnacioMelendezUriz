package dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.dao.AlquilerDAO;
import com.example.model.Alquiler;

public class AlquilerDaoTest {
  private AlquilerDAO dao;

  @BeforeEach
  void setUp() {
    dao = new AlquilerDAO();
  }

  @Test
  @DisplayName("Caso 1: Búsqueda con resultados (Happy Path)")
  void testBuscarPorFechasExitoso() {
    // Rango amplio donde sabemos que hay datos (según tus inserts)
    LocalDate inicio = LocalDate.of(2020, 1, 1);
    LocalDate fin = LocalDate.of(2030, 12, 31);

    List<Alquiler> resultados = dao.buscarPorFechas(inicio, fin);

    assertNotNull(resultados, "La lista nunca debe ser null");
    assertFalse(resultados.isEmpty(), "Debería haber resultados en la BD de prueba");

    // Verificamos que el JOIN funcionó (que trae datos de la vivienda)
    Alquiler primerResultado = resultados.get(0);
    assertNotNull(primerResultado.getVivienda(), "El objeto Vivienda dentro de Alquiler no debe ser null");
    assertNotNull(primerResultado.getVivienda().getCodigoReferencia(), "La referencia de la vivienda debe existir");
  }

  @Test
  @DisplayName("Caso 2: Búsqueda sin resultados (Lista vacía)")
  void testBuscarSinResultados() {
    // Rango antiguo donde NO debería haber datos
    LocalDate inicio = LocalDate.of(1900, 1, 1);
    LocalDate fin = LocalDate.of(1905, 12, 31);

    List<Alquiler> resultados = dao.buscarPorFechas(inicio, fin);

    assertNotNull(resultados, "La lista no debe ser null incluso si no hay datos");
    assertTrue(resultados.isEmpty(), "La lista debería estar vacía para fechas de 1900");
  }

  @Test
  @DisplayName("Caso 3: Fechas invertidas (Inicio > Fin)")
  void testFechasInvertidas() {
    // Si el usuario pone Inicio: 2024 y Fin: 2023
    LocalDate inicio = LocalDate.of(2024, 1, 1);
    LocalDate fin = LocalDate.of(2023, 1, 1);

    List<Alquiler> resultados = dao.buscarPorFechas(inicio, fin);

    // SQL simplemente no encontrará nada, pero no debe fallar
    assertNotNull(resultados);
    assertTrue(resultados.isEmpty(), "No debería encontrar rangos imposibles");
  }
}
