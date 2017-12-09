/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionvehiculosinterfaz;

import gestionvehiculos.Coche;
import gestionvehiculos.ExcepcionGestionVehiculos;
import gestionvehiculos.GestionVehiculos;
import gestionvehiculos.Parte;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author usuario
 */
public class Menu {
    Scanner teclado = new Scanner(System.in);
    GestionVehiculos gs;

    public Menu() throws ExcepcionGestionVehiculos {
        this.gs = new GestionVehiculos();
    }
    
    
    public void menuPrincipal()
    {
        int opcion;
        boolean salida= true;
        do
        {
            System.out.println("1. Gestion de Coches");
            System.out.println("2. Gestion de Partes");
            System.out.println("3. Salir");
            opcion = teclado.nextInt();
            
            switch(opcion)
            {
                case 1:
                    menuCoches();
                    break;
                case 2:
                    menuPartes();
                    break;
                case 3:
                    System.out.println("Fin");
                    salida=false;
                    break;
            }
        }while(salida);
    }
    private void menuCoches()
    {
        int opcion;
        boolean salida= true;
        do
        {
            System.out.println("1. Crear");
            System.out.println("2. Eliminar");
            System.out.println("3. Modificar");
            System.out.println("4. Buscar");
            System.out.println("5. Mostrar todos los coches");
            System.out.println("6. Volver");
            opcion = teclado.nextInt();
            
            switch(opcion)
            {
                case 1:
                    crearCoche();
                    break;
                case 2:
                    eliminarCoche();
                    break;
                case 3:
                    modificarCoche();                    
                    break;
                case 4:
                    buscarCoche();
                    break;
                case 5: 
                    mostrarCoches();
                    break;
                case 6:
                    salida=false;
            }
        }while(salida);
    }
    private void crearCoche()
    {
        PropertyConfigurator.configure("maniobra\\log4j.properties");
        org.apache.log4j.Logger loggerTRACE = org.apache.log4j.LogManager.getLogger("TRACE");
        loggerTRACE.trace("crear coche");
        Coche c;
        int repeticion=1;
        while(repeticion==1)
        {
            System.out.print("Introduzca la matricula del coche: ");
            teclado.next();
            String matricula= teclado.nextLine();
            System.out.print("Introduzca la marca del coche: ");
            String marca= teclado.nextLine();
            System.out.print("Introduzca el modelo del coche: ");
            String modelo= teclado.nextLine();
            System.out.print("Introduzca los extras del coche: ");
            String extras= teclado.nextLine();
            System.out.print("Introduzca la cilindrada del coche: ");
            String cilindrada= teclado.nextLine();
            while (!esEntero(cilindrada)) {
                System.out.print("Dato erróneo. Introduce de nuevo la cilindrada del coche: ");
                cilindrada = teclado.nextLine();
            }
            int cc = Integer.parseInt(cilindrada);
            System.out.print("introduzca el año del coche: ");
            String año = teclado.nextLine();
            while (!esEntero(año)) {
                System.out.print("Dato erróneo. Introduce de nuevo el ID del coche: ");
                año = teclado.nextLine();
            }
            int añoc = Integer.parseInt(año);
            System.out.print("Introduzca el numero de bastidor del coche: ");
            String numBastidor= teclado.nextLine();
            System.out.print("introduzca el precio de mercado del coche: ");
            String precio = teclado.nextLine();
            while (!esEntero(precio)) {
                System.out.print("Dato erróneo. Introduce de nuevo el ID del coche: ");
                precio = teclado.nextLine();
            }
            int precioMercado = Integer.parseInt(precio);
            c = new Coche(0, matricula, marca, modelo, extras, cc, añoc, numBastidor, precioMercado);
            try {
                System.out.println("Coches introducidos "+gs.insertarCoche(c));
            } catch (ExcepcionGestionVehiculos ex) {
                System.out.println(ex.getMensajeErrorUsuario());
                PropertyConfigurator.configure("maniobra\\log4j.properties");
                org.apache.log4j.Logger loggerERROR = org.apache.log4j.LogManager.getLogger("ERROR");
                loggerERROR.error(" Mensaje " + ex.getMensajeErrorSistema()+ " - " + ex.getSentenciaSQL()); 
            }
            System.out.println("¿Quieres introducir mas coches?(s/n): ");
            String respuesta = teclado.nextLine();
            respuesta= respuesta.toLowerCase();
            while (!esRepuesta(respuesta)) {
                System.out.print("Dato erróneo. Introduce una 's' si es si o una 'n' si es no: ");
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
    }
    
    private void eliminarCoche()
    {
        PropertyConfigurator.configure("maniobra\\log4j.properties");
        org.apache.log4j.Logger loggerTRACE = org.apache.log4j.LogManager.getLogger("TRACE");
        loggerTRACE.trace("eliminar coche");
        int repeticion=1;
        while(repeticion==1)
        {
            System.out.print("introduzca el ID del coche a eliminar: ");
            String id= teclado.nextLine();
            while (!esEntero(id)) {
                System.out.print("Dato erróneo. Introduce de nuevo el ID del coche a eliminar: ");
                id = teclado.nextLine();
            }
            int idCoche = Integer.parseInt(id);
            try {
                gs.eliminarCoche(idCoche);
            } catch (ExcepcionGestionVehiculos ex) {
                System.out.println(ex.getMensajeErrorUsuario());
                PropertyConfigurator.configure("maniobra\\log4j.properties");
                org.apache.log4j.Logger loggerERROR = org.apache.log4j.LogManager.getLogger("ERROR");
                loggerERROR.error(" Mensaje " + ex.getMensajeErrorSistema()+ " - " + ex.getSentenciaSQL()); 
            }
            System.out.println("¿Quieres eliminar mas coches?(s/n): ");
            String respuesta = teclado.nextLine();
            respuesta= respuesta.toLowerCase();
            while (!esRepuesta(respuesta)) {
                System.out.print("Dato erróneo. Introduce una 's' si es si o una 'n' si es no: ");
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
    }
    
    private void modificarCoche()
    {
        PropertyConfigurator.configure("maniobra\\log4j.properties");
        org.apache.log4j.Logger loggerTRACE = org.apache.log4j.LogManager.getLogger("TRACE");
        loggerTRACE.trace("modificar coche");
        Coche c;
        int repeticion=1;
        while(repeticion==1)
        {
            System.out.print("introduzca el ID del coche a modificar: ");
            String id= teclado.nextLine();
            while (!esEntero(id)) {
                System.out.print("Dato erróneo. Introduce de nuevo el ID del coche a modificar: ");
                id = teclado.nextLine();
            }
            int idCoche = Integer.parseInt(id);
            System.out.print("Introduzca la matricula del coche: ");
            String matricula= teclado.nextLine();
            System.out.print("Introduzca la marca del coche: ");
            String marca= teclado.nextLine();
            System.out.print("Introduzca el modelo del coche: ");
            String modelo= teclado.nextLine();
            System.out.print("Introduzca los extras del coche: ");
            String extras= teclado.nextLine();
            System.out.print("Introduzca la cilindrada del coche: ");
            String cilindrada= teclado.nextLine();
            while (!esEntero(cilindrada)) {
                System.out.print("Dato erróneo. Introduce de nuevo la cilindrada del coche: ");
                cilindrada = teclado.nextLine();
            }
            int cc = Integer.parseInt(cilindrada);
            System.out.print("introduzca el año del coche: ");
            String año = teclado.nextLine();
            while (!esEntero(año)) {
                System.out.print("Dato erróneo. Introduce de nuevo el año del coche: ");
                año = teclado.nextLine();
            }
            int añoc = Integer.parseInt(año);
            System.out.print("Introduzca el numero de bastidor del coche: ");
            String numBastidor= teclado.nextLine();
            System.out.print("introduzca el precio de mercado del coche: ");
            String precio = teclado.nextLine();
            while (!esEntero(precio)) {
                System.out.print("Dato erróneo. Introduce de nuevo el precio de mercado del coche: ");
                precio = teclado.nextLine();
            }
            int precioMercado = Integer.parseInt(precio);
            c = new Coche(idCoche, matricula, marca, modelo, extras, cc, añoc, numBastidor, precioMercado);
            try {
                gs.modificarCoche(idCoche,c);
            } catch (ExcepcionGestionVehiculos ex) {
                System.out.println(ex.getMensajeErrorUsuario());
                PropertyConfigurator.configure("maniobra\\log4j.properties");
                org.apache.log4j.Logger loggerERROR = org.apache.log4j.LogManager.getLogger("ERROR");
                loggerERROR.error(" Mensaje " + ex.getMensajeErrorSistema()+ " - " + ex.getSentenciaSQL()); 
            }
            System.out.println("¿Quieres modificar mas coches?(s/n): ");
            String respuesta = teclado.nextLine();
            respuesta= respuesta.toLowerCase();
            while (!esRepuesta(respuesta)) {
                System.out.print("Dato erróneo. Introduce una 's' si es si o una 'n' si es no: ");
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
    }
    private void buscarCoche()
    {
        PropertyConfigurator.configure("maniobra\\log4j.properties");
        org.apache.log4j.Logger loggerTRACE = org.apache.log4j.LogManager.getLogger("TRACE");
        loggerTRACE.trace("buscar coche");
        int repeticion=1;
        while(repeticion==1)
        {
            System.out.print("introduzca el ID del coche a buscar: ");
            String id= teclado.nextLine();
            while (!esEntero(id)) {
                System.out.print("Dato erróneo. Introduce de nuevo el ID del coche a buscar: ");
                id = teclado.nextLine();
            }
            int idCoche = Integer.parseInt(id);
            
            try {
                Coche c =gs.leerCoche(idCoche);
                System.out.println(String.format("%9s %9s %9s %9s %9s %9s %9s %9s %9s ", 
                    "ID", "Matricula", "marca", "modelo", "extras", "cilindrada", "año" ,"numero_Bastidor", "Precio_mercado")); 
                System.out.println(String.format("%9s %9s %9s %9s %9s %9s %9s %9s %9s ",
                        c.getCocheId(),c.getMatricula(),c.getMarca(),c.getModelo(),c.getExtras(),c.getCilintrada(),c.getAño(),c.getNumBastidor(),c.getPrecioMercado()));
            } catch (ExcepcionGestionVehiculos ex) {
                System.out.println(ex.getMensajeErrorUsuario());
                PropertyConfigurator.configure("maniobra\\log4j.properties");
                org.apache.log4j.Logger loggerERROR = org.apache.log4j.LogManager.getLogger("ERROR");
                loggerERROR.error(" Mensaje " + ex.getMensajeErrorSistema()+ " - " + ex.getSentenciaSQL()); 
            }
            System.out.println("¿Quieres buscar mas coches?(s/n): ");
            String respuesta = teclado.nextLine();
            respuesta= respuesta.toLowerCase();
            while (!esRepuesta(respuesta)) {
                System.out.print("Dato erróneo. Introduce una 's' si es si o una 'n' si es no: ");
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
    }
    private void mostrarCoches()
    {
        PropertyConfigurator.configure("maniobra\\log4j.properties");
        org.apache.log4j.Logger loggerTRACE = org.apache.log4j.LogManager.getLogger("TRACE");
        loggerTRACE.trace("mostrar coches");
        ArrayList<Coche> listacoches = new ArrayList();
        try {
            listacoches.addAll(gs.leerCoches());
            System.out.println(String.format("%9s %9s %9s %9s %9s %9s %9s %9s %9s ", 
                    "ID", "Matricula", "marca", "modelo", "extras", "cilindrada", "año" ,"numero_Bastidor", "Precio_mercado")); 
            for (Coche c : listacoches) {
                System.out.println(String.format("%9s %9s %9s %9s %9s %9s %9s %9s %9s ",
                        c.getCocheId(),c.getMatricula(),c.getMarca(),c.getModelo(),c.getExtras(),c.getCilintrada(),c.getAño(),c.getNumBastidor(),c.getPrecioMercado()));
            }
            
            //sou print con los datos de la lista
            
        } catch (ExcepcionGestionVehiculos ex) {
            System.out.println(ex.getMensajeErrorUsuario());
            PropertyConfigurator.configure("maniobra\\log4j.properties");
            org.apache.log4j.Logger loggerERROR = org.apache.log4j.LogManager.getLogger("ERROR");
            loggerERROR.error(" Mensaje " + ex.getMensajeErrorSistema()+ " - " + ex.getSentenciaSQL()); 
        }
        
    }
    private void menuPartes()
    {
        int opcion;
        boolean salida= true;
        do
        {
            System.out.println("1. Crear");
            System.out.println("2. Eliminar");
            System.out.println("3. Modificar");
            System.out.println("4. Buscar");
            System.out.println("5. Mostrar todos los partes");
            System.out.println("6. Volver");
            opcion = teclado.nextInt();
            
            switch(opcion)
            {
                case 1:
                    crearParte();
                    break;
                case 2:
                    eliminarParte();
                    break;
                case 3:
                    modificarParte();                    
                    break;
                case 4:
                    buscarParte();
                    break;
                case 5: 
                    mostrarParte();
                    break;
                case 6:
                    salida=false;
            }
        }while(salida);
    }
    private void crearParte()
    {
        PropertyConfigurator.configure("maniobra\\log4j.properties");
        org.apache.log4j.Logger loggerTRACE = org.apache.log4j.LogManager.getLogger("TRACE");
        loggerTRACE.trace("crear parte");
        Parte p;
        Coche c;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
        int repeticion=1;
        while(repeticion==1)
        {  
            System.out.print("Introduzca el codigo del parte: ");
            String codigo= teclado.nextLine();
            System.out.print("Introduzca la fecha del parte(yyyy-MM-dd): ");
            String fParte= teclado.nextLine();
            while (!esFecha(fParte)) {
                System.out.print("Dato erróneo. Introduce de nuevo la fecha del parte: ");
                fParte = teclado.nextLine();
            }
            try {
                fecha = sdf.parse(fParte);
            } catch (ParseException e) {
            }
            System.out.print("introduzca ID del coche: ");
            String cId = teclado.nextLine();
            while (!esEntero(cId)) {
                System.out.print("Dato erróneo. Introduce de nuevo el ID del coche: ");
                cId = teclado.nextLine();
            }
            int cocheId = Integer.parseInt(cId);
            c = new Coche(cocheId,null,null, null, null, 0, 0, null,0);
            p = new Parte(0, codigo, fecha, c);
            try {
                gs.insertarParte(p);
            } catch (ExcepcionGestionVehiculos ex) {
                System.out.println(ex.getMensajeErrorUsuario());
                PropertyConfigurator.configure("maniobra\\log4j.properties");
                org.apache.log4j.Logger loggerERROR = org.apache.log4j.LogManager.getLogger("ERROR");
                loggerERROR.error(" Mensaje " + ex.getMensajeErrorSistema()+ " - " + ex.getSentenciaSQL()); 
            }
            System.out.println("¿Quieres introducir mas partes?(s/n): ");
            String respuesta = teclado.nextLine();
            respuesta= respuesta.toLowerCase();
            while (!esRepuesta(respuesta)) {
                System.out.print("Dato erróneo. Introduce una 's' si es si o una 'n' si es no: ");
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
        
    }
    private void eliminarParte()
    {
        PropertyConfigurator.configure("maniobra\\log4j.properties");
        org.apache.log4j.Logger loggerTRACE = org.apache.log4j.LogManager.getLogger("TRACE");
        loggerTRACE.trace("eliminar parte");
        int repeticion=1;
        while(repeticion==1)
        {
            System.out.print("introduzca el ID del parte: ");
            String id= teclado.nextLine();
            while (!esEntero(id)) {
                System.out.print("Dato erróneo. Introduce de nuevo el ID del parte: ");
                id = teclado.nextLine();
            }
            int idParte = Integer.parseInt(id);
            
            try {
                gs.eliminarParte(idParte);
            } catch (ExcepcionGestionVehiculos ex) {
                System.out.println(ex.getMensajeErrorUsuario());
                PropertyConfigurator.configure("maniobra\\log4j.properties");
                org.apache.log4j.Logger loggerERROR = org.apache.log4j.LogManager.getLogger("ERROR");
                loggerERROR.error(" Mensaje " + ex.getMensajeErrorSistema()+ " - " + ex.getSentenciaSQL()); 
            }
            System.out.println("¿Quieres introducir mas partes?(s/n): ");
            String respuesta = teclado.nextLine();
            respuesta= respuesta.toLowerCase();
            while (!esRepuesta(respuesta)) {
                System.out.print("Dato erróneo. Introduce una 's' si es si o una 'n' si es no: ");
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
    }
    private void modificarParte()
    {
        PropertyConfigurator.configure("maniobra\\log4j.properties");
        org.apache.log4j.Logger loggerTRACE = org.apache.log4j.LogManager.getLogger("TRACE");
        loggerTRACE.trace("modificar parte");
        Parte p;
        Coche c;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
        int repeticion=1;
        while(repeticion==1)
        {
            System.out.print("introduzca el ID del parte: ");
            String id= teclado.nextLine();
            while (!esEntero(id)) {
                System.out.print("Dato erróneo. Introduce de nuevo el ID del parte: ");
                id = teclado.nextLine();
            }
            int idParte = Integer.parseInt(id);
            System.out.print("Introduzca el codigo del parte: ");
            String codigo= teclado.nextLine();
            System.out.print("Introduzca la fecha del parte(yyyy-MM-dd): ");
            String fMatri= teclado.nextLine();
            while (!esFecha(fMatri)) {
                System.out.print("Dato erróneo. Introduce de nuevo la fecha del parte: ");
                fMatri = teclado.nextLine();
            }
            try {
                fecha = sdf.parse(fMatri);
            } catch (ParseException e) {
            }
            System.out.print("introduzca ID del coche: ");
            String cId = teclado.nextLine();
            while (!esEntero(cId)) {
                System.out.print("Dato erróneo. Introduce de nuevo el ID del coche: ");
                cId = teclado.nextLine();
            }
            int cocheId = Integer.parseInt(cId);
            c = new Coche(cocheId,null,null, null, null, 0, 0, null,0);
            p = new Parte(0, codigo, fecha, c);
            try {
                gs.modificarParte(idParte, p);
            } catch (ExcepcionGestionVehiculos ex) {
                System.out.println(ex.getMensajeErrorUsuario());
                PropertyConfigurator.configure("maniobra\\log4j.properties");
                org.apache.log4j.Logger loggerERROR = org.apache.log4j.LogManager.getLogger("ERROR");
                loggerERROR.error(" Mensaje " + ex.getMensajeErrorSistema()+ " - " + ex.getSentenciaSQL()); 
            }
            System.out.println("¿Quieres introducir mas partes?(s/n): ");
            String respuesta = teclado.nextLine();
            respuesta= respuesta.toLowerCase();
            while (!esRepuesta(respuesta)) {
                System.out.print("Dato erróneo. Introduce una 's' si es si o una 'n' si es no: ");
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
    }
    private void buscarParte()
    {
        PropertyConfigurator.configure("maniobra\\log4j.properties");
        org.apache.log4j.Logger loggerTRACE = org.apache.log4j.LogManager.getLogger("TRACE");
        loggerTRACE.trace("buscar parte");
        int repeticion=1;
        while(repeticion==1)
        {
            System.out.print("introduzca el ID del parte: ");
            String id= teclado.nextLine();
            while (!esEntero(id)) {
                System.out.print("Dato erróneo. Introduce de nuevo el ID del parte: ");
                id = teclado.nextLine();
            }
            int idParte = Integer.parseInt(id);
            try {
                Parte p =gs.leerParte(idParte);
                Coche c=p.getCocheId();
                System.out.println(String.format("%9s %9s %9s %9s %9s %9s %9s %9s %9s %9s %9s %9s ", 
                    "ID_parte","codigo","fecha","ID_coche", "Matricula", "marca", "modelo", "extras", "cilindrada", "año" ,"numero_Bastidor", "Precio_mercado")); 
                System.out.println(String.format("%9s %9s %9s %9s %9s %9s %9s %9s %9s %9s %9s %9s ",
                        p.getParteId(),p.getCodigo(),p.getFecha(),c.getCocheId(),c.getMatricula(),c.getMarca(),c.getModelo(),c.getExtras(),c.getCilintrada(),c.getAño(),c.getNumBastidor(),c.getPrecioMercado()));
            } catch (ExcepcionGestionVehiculos ex) {
                System.out.println(ex.getMensajeErrorUsuario());
                PropertyConfigurator.configure("maniobra\\log4j.properties");
                org.apache.log4j.Logger loggerERROR = org.apache.log4j.LogManager.getLogger("ERROR");
                loggerERROR.error(" Mensaje " + ex.getMensajeErrorSistema()+ " - " + ex.getSentenciaSQL()); 
            }
            System.out.println("¿Quieres introducir mas partes?(s/n): ");
            String respuesta = teclado.nextLine();
            respuesta= respuesta.toLowerCase();
            while (!esRepuesta(respuesta)) {
            System.out.print("Dato erróneo. Introduce una 's' si es si o una 'n' si es no: ");
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
    }
    private void mostrarParte()
    {
        PropertyConfigurator.configure("maniobra\\log4j.properties");
        org.apache.log4j.Logger loggerTRACE = org.apache.log4j.LogManager.getLogger("TRACE");
        loggerTRACE.trace("mostrar parte");
        ArrayList<Parte> listaPartes = new ArrayList();
        Coche c= null;
        try {
            listaPartes.addAll(gs.leerPartes());
            System.out.println(String.format("%9s %9s %9s %9s %9s %9s %9s %9s %9s %9s %9s %9s ", 
                    "ID_parte","codigo","fecha","ID_coche", "Matricula", "marca", "modelo", "extras", "cilindrada", "año" ,"numero_Bastidor", "Precio_mercado")); 
            for (Parte p : listaPartes) {
                c=p.getCocheId();
                System.out.println(String.format("%9s %9s %9s %9s %9s %9s %9s %9s %9s %9s %9s %9s ",
                        p.getParteId(),p.getCodigo(),p.getFecha(),c.getCocheId(),c.getMatricula(),c.getMarca(),c.getModelo(),c.getExtras(),c.getCilintrada(),c.getAño(),c.getNumBastidor(),c.getPrecioMercado()));
            }
        } catch (ExcepcionGestionVehiculos ex) {
            System.out.println(ex.getMensajeErrorUsuario());
            PropertyConfigurator.configure("maniobra\\log4j.properties");
            org.apache.log4j.Logger loggerERROR = org.apache.log4j.LogManager.getLogger("ERROR");
            loggerERROR.error(" Mensaje " + ex.getMensajeErrorSistema()+ " - " + ex.getSentenciaSQL()); 
        }
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
