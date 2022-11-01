/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PemesananPT_SBK;


import PemesananPT_SBK.VIEW.frameUtama;

import pt_sbk.VIEW.screen;

/**
 *
 * @author ASUS ROG
 */
public class PemesananPT_SBK {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        frameUtama fu = new frameUtama();
        
        screen sc = new screen();
        sc.setVisible(true);
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(35);
                sc.jLabel2.setText(Integer.toString(i)+"%");
                sc.jProgressBar1.setValue(i);

            }
            fu.setVisible(true);
            sc.setVisible(false);
        }catch (Exception e) {
        }

    }   
}
