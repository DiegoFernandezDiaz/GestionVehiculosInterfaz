/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionvehiculosinterfaz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class GestionVehiculosInterfaz {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Coche> listaCoches = new ArrayList();
        Coche c;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Date fMatricula=null;
        int repeticion=1;
        while(repeticion==1)
        {
            System.out.print("introduzca el ID del coche: ");
            String id= teclado.nextLine();
            while (!esEntero(id)) {
            System.out.print("Dato erróneo. Introduce de nuevo el ID del coche: ");
            id = teclado.nextLine();
            }
            int idCoche = Integer.parseInt(id);
            System.out.print("Introduzca la matricula del coche: ");
            String matricula= teclado.nextLine();
            System.out.print("Introduzca la marca del coche: ");
            String marca= teclado.nextLine();
            System.out.print("Introduzca el modelo del coche: ");
            String modelo= teclado.nextLine();
            System.out.print("Introduzca la fecha de matriculacion del coche(yyyy-MM-dd): ");
            String fMatri= teclado.nextLine();
            while (!esFecha(fMatri)) {
            System.out.print("Dato erróneo. Introduce de nuevo la fecha de matriculacion: ");
            fMatri = teclado.nextLine();
            }
            try {
                fMatricula = sdf.parse(fMatri);
            } catch (ParseException e) {
            }
            System.out.print("Introduzca la cilindrada del coche: ");
            String cilindrada= teclado.nextLine();
            while (!esEntero(cilindrada)) {
            System.out.print("Dato erróneo. Introduce de nuevo la cilindrada del coche: ");
            cilindrada = teclado.nextLine();
            }
            int cc = Integer.parseInt(cilindrada);
            
            c = new Coche(idCoche, cc, matricula, marca, modelo, fMatricula);
            listaCoches.add(c);
            System.out.println("¿Quieres introducir mas coches?(s/n): ");
            String respuesta = teclado.nextLine();
            respuesta= respuesta.toLowerCase();
            while (!esRepuesta(respuesta)) {
            System.out.print("Dato erróneo. Introduce de nuevo cuántos coches tienes: ");
            respuesta = teclado.nextLine();
            }
            if(respuesta.charAt(0) == 's')
            {
                repeticion=1;
            }else
            {
                repeticion=0;
            }
        }
        //Escribir los datos de todos los coches
        
    }
    public static boolean esRepuesta(String s)
    {
        return s.charAt(0)=='s' || s.charAt(0)=='n';
    }
    public static boolean esEntero(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        }
        return true;
    }
    public static boolean esFecha(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try { 
            sdf.parse(s);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
}
