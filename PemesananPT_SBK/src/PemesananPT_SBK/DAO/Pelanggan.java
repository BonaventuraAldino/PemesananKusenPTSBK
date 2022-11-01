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
 * @author user
 */
public class Pelanggan {
    private Koneksi db;
    private Statement st;
    private String query;
    public ResultSet rs;
    private int jumlah_baris;

    public String[][] Tampil_Semua_pelanggan() {
        rs = null;
        String[][] data = null;
        db = new Koneksi();
        db.KoneksiDatabase();
        jumlah_baris = 0;
        try {
            st = db.con.createStatement();
            query = "select count(KD_Pelanggan) as jum from pelanggan";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlah_baris = rs.getInt("jum");
            }
            query = "select * from pelanggan";
            rs = st.executeQuery(query);
            data = new String[jumlah_baris][3];
            //5 adalah banyaknya field yang akan ditampilkan
            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("KD_Pelanggan");
                data[r][1] = rs.getString("Nama_Pelanggan");
                data[r][2] = rs.getString("Alamat");
                
                r++;

            }
            st.close();
            db.con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }
    
    public boolean cek_Pelanggan(String KD_Pelanggan_dt){
        rs= null;
        db = new Koneksi();
        db.KoneksiDatabase();
        boolean data = false;
        try {
            st = db.con.createStatement();
            query = "select KD_Pelanggan from pelanggan where KD_Pelanggan'"+KD_Pelanggan_dt+"'";
            rs = st.executeQuery(query);
            if(rs.next()){
                return false;
            }
            else{
                JOptionPane.showMessageDialog(null, "KD_Pelanggan"+KD_Pelanggan_dt+ "Tidak Ada Dalam"
                        + " Database !!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }
    
    
    public void insert (int KD_Pelanggan_dt, 
                        String Nama_Pelanggan_dt, 
                        String No_Telp_dt, 
                        String Alamat_dt)
    {
        db = new Koneksi();
        db.KoneksiDatabase();
        try {
            st = db.con.createStatement();
            query = "insert into pelanggan values ('"+KD_Pelanggan_dt+"','"+Nama_Pelanggan_dt+"', '"+No_Telp_dt+"', '"+Alamat_dt+"')";
            st.executeUpdate(query);
            st.close();
            db.con.close();
            JOptionPane.showMessageDialog(null, "Simpan Data Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
     public void ubah (int KD_Pelanggan_dt, 
                        String Nama_Pelanggan_dt, 
                        String No_Telp_dt, 
                        String Alamat_dt)
     {
         db = new Koneksi();
         db.KoneksiDatabase();
         try {
             query = "UPDATE pelanggan SET Nama_Pelanggan=? ,No_Telp=? ,Alamat=? WHERE KD_Pelanggan=?";
             PreparedStatement update =db.con.prepareStatement(query);
             update.setInt(4, KD_Pelanggan_dt);
             update.setString(1, Nama_Pelanggan_dt);
             update.setString(2, No_Telp_dt);
             update.setString(3, Alamat_dt);
             update.executeUpdate();
             update.close();
             db.con.close();
             JOptionPane.showMessageDialog(null, "Berhasil Merubah Data : "
                                            + KD_Pelanggan_dt);
             
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
     }
     
     public void hapus(int KD_Pelanggan_dt){
         db = new Koneksi();
         db.KoneksiDatabase();
         try {
             query = "delete from pelanggan where KD_Pelanggan=?";
             PreparedStatement hapus_data = db.con.prepareStatement(query);
             hapus_data.setInt(1, KD_Pelanggan_dt);
             hapus_data.executeUpdate();
             hapus_data.close();
             db.con.close();
             JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data : "
                                            + KD_Pelanggan_dt);
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
     }
     
     public String [][] cari_pegawai (String kata_kunci){
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
     }
    
}
