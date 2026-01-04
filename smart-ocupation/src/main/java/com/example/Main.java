package com.example;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.example.view.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            System.out.println("No se pudo cargar el diseño del sistema, usando el de Java por defecto.");
        }

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);

            System.out.println("Aplicación SmartOcupation iniciada correctamente.");
        });
    }
}