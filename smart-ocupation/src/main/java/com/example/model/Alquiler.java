package com.example.model;

import java.time.LocalDate;

public class Alquiler {
  private int numExpediente;
  private LocalDate fechaEntrada;
  private String nombreCliente;
  private Vivienda vivienda;

  public Alquiler() {
    this.vivienda = new Vivienda();
  }

  public int getNumExpediente() {
    return numExpediente;
  }

  public void setNumExpediente(int numExpediente) {
    this.numExpediente = numExpediente;
  }

  public LocalDate getFechaEntrada() {
    return fechaEntrada;
  }

  public void setFechaEntrada(LocalDate fechaEntrada) {
    this.fechaEntrada = fechaEntrada;
  }

  public String getNombreCliente() {
    return nombreCliente;
  }

  public void setNombreCliente(String nombreCliente) {
    this.nombreCliente = nombreCliente;
  }

  public Vivienda getVivienda() {
    return vivienda;
  }

  public void setVivienda(Vivienda vivienda) {
    this.vivienda = vivienda;
  }
}
