package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Alquiler;
import com.example.model.Vivienda;

public class AlquilerDAO {
  private static final String URL = "jdbc:mysql://localhost:3306/SmartOcupationDB";
  private static final String USER = "root";
  private static final String PASS = "";

  public List<Alquiler> buscarPorFechas(LocalDate inicio, LocalDate fin) {
    List<Alquiler> lista = new ArrayList<>();

    String sql = "SELECT a.num_expediente, a.fecha_entrada, a.nombre_cliente, " +
        "v.codigo_referencia, v.ubicacion, v.precio_mensual " +
        "FROM Alquileres a " +
        "JOIN Viviendas v ON a.id_vivienda = v.id_vivienda " +
        "WHERE a.fecha_entrada BETWEEN ? AND ?";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setDate(1, java.sql.Date.valueOf(inicio));
      ps.setDate(2, java.sql.Date.valueOf(fin));

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Alquiler a = new Alquiler();
        a.setNumExpediente(rs.getInt("num_expediente"));

        a.setFechaEntrada(rs.getDate("fecha_entrada").toLocalDate());
        a.setNombreCliente(rs.getString("nombre_cliente"));

        Vivienda v = new Vivienda();
        v.setCodigoReferencia(rs.getString("codigo_referencia"));
        v.setUbicacion(rs.getString("ubicacion"));
        v.setPrecioMensual(rs.getDouble("precio_mensual"));

        a.setVivienda(v);
        lista.add(a);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return lista;
  }
}
