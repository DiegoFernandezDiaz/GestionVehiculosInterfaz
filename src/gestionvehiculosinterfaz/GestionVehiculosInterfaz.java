/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionvehiculosinterfaz;

import gestionvehiculos.ExcepcionGestionVehiculos;
import java.util.logging.Level;
import java.util.logging.Logger;


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
            Logger.getLogger(GestionVehiculosInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}
