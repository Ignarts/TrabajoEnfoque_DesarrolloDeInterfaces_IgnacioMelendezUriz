package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.model.Alquiler;
import com.example.model.Vivienda;

public class ModeloTest {
  @Test
  @DisplayName("Test de integridad de Vivienda")
  void testVivienda() {
    Vivienda v = new Vivienda();
    v.setCodigoReferencia("TEST-001");
    v.setPrecioMensual(500.0);
    v.setUbicacion("Centro");

    assertEquals("TEST-001", v.getCodigoReferencia());
    assertEquals(500.0, v.getPrecioMensual());
    assertEquals("Centro", v.getUbicacion());
  }

  @Test
  @DisplayName("Test de relación Alquiler-Vivienda")
  void testAlquilerRelacion() {
    // Probamos que un Alquiler pueda contener una Vivienda correctamente
    Alquiler alquiler = new Alquiler();
    alquiler.setNombreCliente("Juan Test");
    alquiler.setFechaEntrada(LocalDate.now());

    Vivienda casa = new Vivienda();
    casa.setCodigoReferencia("CASA-TEST");

    // Asignamos la vivienda al alquiler
    alquiler.setVivienda(casa);

    // Verificamos que no sea null y que los datos coincidan
    assertNotNull(alquiler.getVivienda(), "La vivienda no debería ser nula");
    assertEquals("CASA-TEST", alquiler.getVivienda().getCodigoReferencia(), "El código de referencia debe coincidir");
  }
}
