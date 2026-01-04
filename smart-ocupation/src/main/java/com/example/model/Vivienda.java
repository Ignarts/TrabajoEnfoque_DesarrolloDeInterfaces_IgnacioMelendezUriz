package com.example.model;

public class Vivienda {
  private String codigoReferencia;
  private String ubicacion;
  private double precioMensual;

  public Vivienda() {
  }

  public String getCodigoReferencia() {
    return codigoReferencia;
  }

  public void setCodigoReferencia(String codigoReferencia) {
    this.codigoReferencia = codigoReferencia;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

  public double getPrecioMensual() {
    return precioMensual;
  }

  public void setPrecioMensual(double precioMensual) {
    this.precioMensual = precioMensual;
  }
}
