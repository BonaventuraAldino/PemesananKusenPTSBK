/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PemesananPT_SBK.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import PemesananPT_SBK.VIEW.framePemilik;

/**
 *
 * @author user
 */
public class Deadline {
    private Koneksi db;
    private Statement st;
    private String query;
    public ResultSet rs;
    private int jumlah_baris;

    public String[][] Tampil_Tanggal_Pengambilan() {
        rs = null;
        String[][] data = null;
        db = new Koneksi();
        db.KoneksiDatabase();
        jumlah_baris = 0;
        try {
            st = db.con.createStatement();
            query = "select count(KD_Pemesanan) as jum from pesanan";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlah_baris = rs.getInt("jum");
            }
            query = "SELECT Nama_Pelanggan, Tgl_Pemesanan, Tgl_Pengambilan, (Tgl_Pengambilan) - (CURRENT_DATE) as Deadline from pesanan "
                    + "JOIN pelanggan USING (KD_Pelanggan) where Tgl_Pengambilan BETWEEN CURRENT_DATE and Tgl_Pengambilan";
            rs = st.executeQuery(query);
            data = new String[jumlah_baris][4];
            //5 adalah banyaknya field yang akan ditampilkan
            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("Nama_Pelanggan");
                data[r][1] = rs.getString("Tgl_Pemesanan");
                data[r][2] = rs.getString("Tgl_Pengambilan");
                data[r][3] = rs.getString("Deadline");
                r++;

            }
            st.close();
            db.con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }
    
}
