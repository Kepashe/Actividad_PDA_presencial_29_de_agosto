package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DAOPersonas {

    private static final String ALL_PERSONAS = "SELECT * FROM PERSONA";
    private static final String INSERT_PERSONA = "INSERT INTO PERSONA (CEDULA, NOMBRE, EDAD, DIRECCION) VALUES  (?,?,?,?)";
    private static final String UPDATE_PERSONA = "UPDATE PERSONA SET  NOMBRE=?, EDAD=?, DIRECCION=? WHERE CEDULA =?";
    private static final String DELETE_PERSONA = "DELETE FROM PERSONA WHERE CEDULA=?";
    private static final String PERSONA_CI = "SELECT * FROM PERSONA WHERE CEDULA=?";


    public static boolean insert (Persona persona){
        try {
            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_PERSONA);

            statement.setInt(1, persona.getCedula());
            statement.setString(2, persona.getNombre());
            statement.setInt(3, persona.getEdad());
            statement.setString(4, persona.getDireccion());


            int retorno = statement.executeUpdate();

            return retorno>0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean update(Persona persona) {
        try {
            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(UPDATE_PERSONA);

            statement.setString(1, persona.getNombre());
            statement.setInt(2, persona.getEdad());
            statement.setString(3, persona.getDireccion());
            statement.setInt(4, persona.getCedula());

            int retorno = statement.executeUpdate();
            return retorno > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean delete(Persona persona) {
        try {
            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(DELETE_PERSONA);
            statement.setInt(1, persona.getCedula());

            int retorno = statement.executeUpdate();
            return retorno > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static LinkedList<Persona> findAll(){
        LinkedList<Persona> personas = new LinkedList<Persona>();
        try {
            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_PERSONAS);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                Persona persona = getPersonaFromResultSet(resultado);
                personas.add(persona);
            }

            return personas;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Persona find(int ciPersona){
        try {
            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(PERSONA_CI);
            statement.setInt(1, ciPersona);

            ResultSet resultado = statement.executeQuery();
            Persona persona = null;
            if(resultado.next()){
                persona = getPersonaFromResultSet(resultado);
            }
            return persona;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Persona getPersonaFromResultSet(ResultSet resultado) throws SQLException {

        int cedula = resultado.getInt("CEDULA");
        String nombre = resultado.getString("NOMBRE");
        int edad = resultado.getInt("EDAD");
        String direccion = resultado.getString("DIRECCION");

        Persona persona = new Persona(cedula,nombre,edad,direccion);

        return persona;

    }



}