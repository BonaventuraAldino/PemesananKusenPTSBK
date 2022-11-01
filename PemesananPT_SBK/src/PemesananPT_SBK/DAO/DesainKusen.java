/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PemesananPT_SBK.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS ROG
 */
public class DesainKusen {
    
    private Koneksi db;
    private Statement st;
    private String query;
    public ResultSet rs;
    private int jumlah_baris;

    public String[][] Tampil_Semua_desainKusen() {
        rs = null;
        String[][] data = null;
        db = new Koneksi();
        db.KoneksiDatabase();
        jumlah_baris = 0;
        try {
            st = db.con.createStatement();
            query = "select count(KD_DesainKusen) as jum from desainkusen";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlah_baris = rs.getInt("jum");
            }
            query = "select * from desainkusen";
            rs = st.executeQuery(query);
            data = new String[jumlah_baris][5];
            //5 adalah banyaknya field yang akan ditampilkan
            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("KD_DesainKusen");
                data[r][1] = rs.getString("Nama_Kusen");
                data[r][2] = rs.getString("Ukuran");
                data[r][3] = rs.getString("Bahan");
                data[r][4] = rs.getString("Harga");
                
                r++;

            }
            st.close();
            db.con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }
    
    public boolean cek_DesainKusen(String KD_DesainKusen_dt){
        rs= null;
        db = new Koneksi();
        db.KoneksiDatabase();
        boolean data = false;
        try {
            st = db.con.createStatement();
            query = "select KD_DesainKusen from desainkusen where KD_DesainKusen'"+KD_DesainKusen_dt+"'";
            rs = st.executeQuery(query);
            if(rs.next()){
                return false;
            }
            else{
                JOptionPane.showMessageDialog(null, "KD_DesainKusen"+KD_DesainKusen_dt+ "Tidak Ada Dalam"
                        + " Database !!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }
    
    
    public void insert (int KD_DesainKusen_dt, 
                        String Nama_Kusen_dt, 
                        String Ukuran_dt, 
                        String Bahan_dt,
                        int Harga_dt )
    {
        db = new Koneksi();
        db.KoneksiDatabase();
        try {
            st = db.con.createStatement();
            query = "insert into desainkusen values ('"+KD_DesainKusen_dt+"','"+Nama_Kusen_dt+"', '"+Ukuran_dt+"', '"+Bahan_dt+"', '"+Harga_dt+"')";
            st.executeUpdate(query);
            st.close();
            db.con.close();
            JOptionPane.showMessageDialog(null, "Simpan Data Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
     public void ubah (int KD_DesainKusen_dt, 
                        String Nama_Kusen_dt, 
                        String Ukuran_dt, 
                        String Bahan_dt,
                        int Harga_dt)
     {
         db = new Koneksi();
         db.KoneksiDatabase();
         try {
             query = "UPDATE desainkusen SET Nama_Kusen=? ,Ukuran=? ,Bahan=? ,Harga=? WHERE KD_DesainKusen=?";
             PreparedStatement update =db.con.prepareStatement(query);
             update.setInt(5, KD_DesainKusen_dt);
             update.setString(1, Nama_Kusen_dt);
             update.setString(2, Ukuran_dt);
             update.setString(3, Bahan_dt);
             update.setInt(4, Harga_dt);
             update.executeUpdate();
             update.close();
             db.con.close();
             JOptionPane.showMessageDialog(null, "Berhasil Merubah Data : "
                                            + KD_DesainKusen_dt);
             
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
     }
     
     public void hapus(int KD_DesainKusen){
         db = new Koneksi();
         db.KoneksiDatabase();
         try {
             query = "delete from desainkusen where KD_DesainKusen=?";
             PreparedStatement hapus_data = db.con.prepareStatement(query);
             hapus_data.setInt(1, KD_DesainKusen);
             hapus_data.executeUpdate();
             hapus_data.close();
             db.con.close();
             JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data : "
                                            + KD_DesainKusen);
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
     }
     
    /* public String [][] cari_pegawai (String kata_kunci){
         rs = null;
         String [][] data = null;
         db = new Koneksi();
         db.KoneksiDatabase();
         int jumlah_baris = 0;
         try {
             st = db.con.createStatement();
             query = "select count(KD_Pelanggan) as jum from pelanggan";
             rs = st.executeQuery(query);
             if (rs.next()){
                 jumlah_baris = rs.getInt("jum");
                 
             }
             query = "SELECT * FROM pelanggan WHERE KD_Pelanggan='"+kata_kunci+"'";
             rs = st.executeQuery(query);
             data = new String[jumlah_baris][4];
             //5 adalah banyak field yang ditampilkan
             int r =0;
             while(rs.next()){
                data[r][0] = rs.getString("KD_Pelanggan");
                data[r][1] = rs.getString("Nama_Pelanggan");
                data[r][2] = rs.getString("No_Telp");
                data[r][3] = rs.getString("Alamat");
                
                    r++;
             }
            st.close();
            db.con.close();
            
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
         return data;
     }*/
    
}
