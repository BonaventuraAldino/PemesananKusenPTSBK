/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PemesananPT_SBK.DAO;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author riski
 */
public class ModelTabel {
       public void SetTable(JTable tabel,
                          String [][] data,
                          String [] namaKolom,
                          int jmlKolom,
                          int [] Lebar)
    {
        tabel.setModel(new DefaultTableModel(data, namaKolom));
        for (int i=0;i<jmlKolom;i++)
            tabel.getColumnModel().getColumn(i).setPreferredWidth(i);
        
    }
}
