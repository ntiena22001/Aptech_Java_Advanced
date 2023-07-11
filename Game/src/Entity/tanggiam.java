/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Entity;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class tanggiam {
    private int vitriID;
    private int sotanggiam;

    public tanggiam(int vitriID, int sotanggiam) {
        this.vitriID = vitriID;
        this.sotanggiam = sotanggiam;
    }

    public tanggiam(int sotanggiam) {
        this.sotanggiam = sotanggiam;
    }

  

    public tanggiam() {
    }

    public int getVitriID() {
        return vitriID;
    }

    public void setVitriID(int vitriID) {
        this.vitriID = vitriID;
    }

    public int getSotanggiam() {
        return sotanggiam;
    }

    public void setSotanggiam(int sotanggiam) {
        this.sotanggiam = sotanggiam;
    }
    

}
