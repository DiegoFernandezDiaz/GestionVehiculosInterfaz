/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionvehiculosinterfaz;

import gestionvehiculos.ExcepcionGestionVehiculos;


/**
 *
 * @author usuario
 */
public class GestionVehiculosInterfaz {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Menu menu;
        try {
            menu = new Menu();
            menu.menuPrincipal();
        } catch (ExcepcionGestionVehiculos ex) {
            System.out.println(ex.getMensajeErrorUsuario());
        }
      
    }
}
