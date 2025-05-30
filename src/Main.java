
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import conexion.ConexionBD;
import modelo.Autor;
import modelo.Libro;

public class Main {

    public static void main(String[] args)  {
        try (Connection conn = ConexionBD.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Conexión exitosa a la base de datos 'taller'.");

            }
            String opcion = "";
            Scanner scanner = new Scanner(System.in);
            List<Autor> listaAutores = new ArrayList<>();
            List<Libro> listaLibros = new ArrayList<>();
            int error = 0;

            //Inserta autor a la base de datos
            do {
                System.out.println("Ingresa nombre del autor");
                String nombre = scanner.nextLine();
                System.out.println("Ingresa la nacionalidad del autor");
                String nacionalidad = scanner.nextLine();
                System.out.println("Ingresa año de nacimiento del autor");
                String nacimiento = scanner.nextLine();

                PreparedStatement insertar = conn.prepareStatement("insert into autores (nombre,nacionalidad,año_nacimiento) values (?,?,?)");
                insertar.setString(1,nombre);
                insertar.setString(2,nacionalidad);
                insertar.setString(3,nacimiento);
                insertar.executeUpdate();

                System.out.println("Quiere insertar otro autor (s/n)");
                opcion = scanner.nextLine();
            } while (opcion.equalsIgnoreCase("s"));

            //trae los datos de autores de la base de datos y lo guarda en una lista
            PreparedStatement select = conn.prepareStatement("select * from autores");
            ResultSet resultado = select.executeQuery();
            while (resultado.next()) {
                listaAutores.add(new Autor(resultado.getString("nombre"), resultado.getString("nacionalidad"), resultado.getString("año_nacimiento")));
            }
            for (Autor a : listaAutores){
                System.out.println(a);
            }

            //Ingresa libros a la base de datos
            do {
                System.out.println("Ingresa nombre del libro");
                String nombre = scanner.nextLine();
                System.out.println("Ingresa el año de publicacion del libro");
                String publicacion = scanner.nextLine();
                System.out.println("Ingresa el genero del libro");
                String genero = scanner.nextLine();
                System.out.println("Ingresa el nombre del autor del libro");
                String autor = scanner.nextLine();
            //Comprueba que el autor exite en la base de datos
                PreparedStatement select2 = conn.prepareStatement("select nombre from autores where nombre = ?");
                select2.setString(1,autor);
                ResultSet resultado2 = select2.executeQuery();
                error = 1;
                while (resultado2.next()){
                    PreparedStatement insertar = conn.prepareStatement("insert into libros (nombre,año_publicacion,genero,autor) values (?,?,?,?)");
                    insertar.setString(1,nombre);
                    insertar.setString(2,publicacion);
                    insertar.setString(3,genero);
                    insertar.setString(4,resultado2.getString("nombre"));
                    insertar.executeUpdate();
                    error = 0;

                }
                if (error == 1){
                    System.out.println("No se inserto el libro porque el autor es desconocido");
                }




                System.out.println("Quiere insertar otro libro (s/n)");
                opcion = scanner.nextLine();
            } while (opcion.equalsIgnoreCase("s"));






        //Trae llos datos de todos los libros de la base de datos y lo guarda en una lista
            PreparedStatement selectLibros = conn.prepareStatement("select nombre,año_publicacion,genero,autor from libros");
            ResultSet resultadoLibros = selectLibros.executeQuery();
            while (resultadoLibros.next()) {

                for (Autor a : listaAutores){
                    if (a.getNombre().equalsIgnoreCase(resultadoLibros.getString("autor"))){
                        listaLibros.add(new Libro(resultadoLibros.getString("nombre"),resultadoLibros.getString("año_publicacion"),resultadoLibros.getString("genero"),a));
                    }
                }

            }
            for (Libro l: listaLibros){
                System.out.println(l);
            }



        } catch (SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
        }



    }
}