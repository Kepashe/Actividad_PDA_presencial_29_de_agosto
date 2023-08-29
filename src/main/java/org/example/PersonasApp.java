package org.example;

import org.example.DAOPersonas;
import org.example.Persona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonasApp extends JFrame {

    private JTextField cedulaField, nombreField, edadField, direccionField;
    private JButton insertButton, updateButton, deleteButton, findButton;

    public PersonasApp() {
        setTitle("Gestión de Personas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        cedulaField = new JTextField(10);
        nombreField = new JTextField(20);
        edadField = new JTextField(5);
        direccionField = new JTextField(30);

        insertButton = new JButton("Insertar");
        updateButton = new JButton("Modificar");
        deleteButton = new JButton("Eliminar");
        findButton = new JButton("Buscar");

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Cédula:"), constraints);
        constraints.gridx = 1;
        panel.add(cedulaField, constraints);

        constraints.gridy = 1;
        panel.add(new JLabel("Nombre:"), constraints);
        constraints.gridx = 1;
        panel.add(nombreField, constraints);

        constraints.gridy = 2;
        panel.add(new JLabel("Edad:"), constraints);
        constraints.gridx = 1;
        panel.add(edadField, constraints);

        constraints.gridy = 3;
        panel.add(new JLabel("Dirección:"), constraints);
        constraints.gridx = 1;
        panel.add(direccionField, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        panel.add(insertButton, constraints);
        constraints.gridx = 1;
        panel.add(updateButton, constraints);

        constraints.gridy = 5;
        constraints.gridx = 0;
        panel.add(deleteButton, constraints);
        constraints.gridx = 1;
        panel.add(findButton, constraints);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar inserción de persona utilizando DAOPersonas.insert()
                int cedula = Integer.parseInt(cedulaField.getText());
                String nombre = nombreField.getText();
                int edad = Integer.parseInt(edadField.getText());
                String direccion = direccionField.getText();
                Persona persona = new Persona(cedula, nombre, edad, direccion);
                DAOPersonas.insert(persona);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar modificación de persona utilizando DAOPersonas.update()
                int cedula = Integer.parseInt(cedulaField.getText());
                String nombre = nombreField.getText();
                int edad = Integer.parseInt(edadField.getText());
                String direccion = direccionField.getText();
                Persona persona = new Persona(cedula, nombre, edad, direccion);
                DAOPersonas.update(persona);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar eliminación de persona utilizando DAOPersonas.delete()
                int cedula = Integer.parseInt(cedulaField.getText());
                Persona persona = new Persona(cedula, "", 0, "");
                DAOPersonas.delete(persona);
            }
        });

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar búsqueda de persona utilizando DAOPersonas.find()
                int cedula = Integer.parseInt(cedulaField.getText());
                Persona persona = DAOPersonas.find(cedula);
                if (persona != null) {
                    nombreField.setText(persona.getNombre());
                    edadField.setText(String.valueOf(persona.getEdad()));
                    direccionField.setText(persona.getDireccion());
                } else {
                    // Manejar el caso en el que no se encuentra la persona
                    JOptionPane.showMessageDialog(null, "Persona no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new PersonasApp().setVisible(true);
            }
        });
    }
}
