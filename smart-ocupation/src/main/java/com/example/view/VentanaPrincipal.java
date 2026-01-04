package com.example.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

// Importaciones estándar
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.dao.AlquilerDAO;
import com.example.model.Alquiler;
import com.github.lgooddatepicker.components.DatePicker;
// Importaciones para PDF (iText)
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class VentanaPrincipal extends JFrame {

  private DatePicker datePickerInicio;
  private DatePicker datePickerFin;
  private JTable tabla;
  private DefaultTableModel modeloTabla;
  private AlquilerDAO dao;

  public VentanaPrincipal() {
    dao = new AlquilerDAO();
    configurarVentana();
    inicializarComponentes();
  }

  private void configurarVentana() {
    setTitle("SmartOcupation - Gestión de Alquileres");
    setSize(950, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());
  }

  private void inicializarComponentes() {
    // --- PANEL SUPERIOR (Filtros) ---
    JPanel panelFiltros = new JPanel();
    panelFiltros.setBorder(BorderFactory.createTitledBorder("Búsqueda de Alquileres"));
    panelFiltros.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

    datePickerInicio = new DatePicker();
    datePickerFin = new DatePicker();
    JButton btnBuscar = new JButton("Consultar Histórico");

    panelFiltros.add(new JLabel("Desde:"));
    panelFiltros.add(datePickerInicio);
    panelFiltros.add(new JLabel("Hasta:"));
    panelFiltros.add(datePickerFin);
    panelFiltros.add(btnBuscar);

    add(panelFiltros, BorderLayout.NORTH);

    // --- PANEL CENTRAL (Tabla) ---
    String[] columnas = { "Expediente", "Cliente", "Fecha Entrada", "Ref. Vivienda", "Ubicación", "Precio/Mes" };

    modeloTabla = new DefaultTableModel(columnas, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    tabla = new JTable(modeloTabla);
    tabla.setRowHeight(25);
    add(new JScrollPane(tabla), BorderLayout.CENTER);

    // --- PANEL INFERIOR (Botones Acción) ---
    JPanel panelBotones = new JPanel();
    JButton btnInforme = new JButton("Generar Informe PDF");
    panelBotones.add(btnInforme);
    add(panelBotones, BorderLayout.SOUTH);

    // --- EVENTOS ---
    btnBuscar.addActionListener(e -> buscar());
    btnInforme.addActionListener(e -> generarInforme());
  }

  private void buscar() {
    modeloTabla.setRowCount(0); // Limpiar tabla

    LocalDate inicio = datePickerInicio.getDate();
    LocalDate fin = datePickerFin.getDate();

    if (inicio == null || fin == null) {
      JOptionPane.showMessageDialog(this, "Por favor seleccione ambas fechas.", "Aviso", JOptionPane.WARNING_MESSAGE);
      return;
    }

    if (inicio.isAfter(fin)) {
      JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser posterior a la fecha final.", "Error",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    List<Alquiler> resultados = dao.buscarPorFechas(inicio, fin);

    for (Alquiler a : resultados) {
      Object[] fila = {
          a.getNumExpediente(),
          a.getNombreCliente(),
          a.getFechaEntrada(),
          a.getVivienda().getCodigoReferencia(),
          a.getVivienda().getUbicacion(),
          String.format("%.2f €", a.getVivienda().getPrecioMensual())
      };
      modeloTabla.addRow(fila);
    }

    if (resultados.isEmpty()) {
      JOptionPane.showMessageDialog(this, "No se encontraron alquileres en ese rango.");
    }
  }

  private void generarInforme() {
    if (modeloTabla.getRowCount() == 0) {
      JOptionPane.showMessageDialog(this, "No hay datos en la tabla para generar el informe.", "Aviso",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    String nombreArchivo = "Informe_Alquileres.pdf";
    Document documento = new Document();

    try {
      PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
      documento.open();

      // 1. Título
      Paragraph titulo = new Paragraph("Informe de Alquileres - SmartOcupation");
      titulo.setAlignment(Paragraph.ALIGN_CENTER);
      titulo.setSpacingAfter(20);
      documento.add(titulo);

      // 2. Tabla PDF
      PdfPTable pdfTabla = new PdfPTable(6); // 6 Columnas
      pdfTabla.setWidthPercentage(100);

      // Encabezados
      String[] encabezados = { "Expediente", "Cliente", "Fecha", "Ref. Vivienda", "Ubicación", "Precio" };
      for (String header : encabezados) {
        pdfTabla.addCell(header);
      }

      // Datos
      for (int i = 0; i < modeloTabla.getRowCount(); i++) {
        for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
          Object valor = modeloTabla.getValueAt(i, j);
          pdfTabla.addCell(valor != null ? valor.toString() : "");
        }
      }

      documento.add(pdfTabla);

      // 3. Conclusión / Pie
      documento.add(new Paragraph("\nInforme generado el: " + LocalDate.now()));

      documento.close();

      JOptionPane.showMessageDialog(this,
          "Informe PDF creado correctamente en la carpeta del proyecto:\n" + nombreArchivo, "Éxito",
          JOptionPane.INFORMATION_MESSAGE);

    } catch (DocumentException | FileNotFoundException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(this, "Error al crear el PDF: " + e.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}