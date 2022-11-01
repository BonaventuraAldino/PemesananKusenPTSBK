/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PemesananPT_SBK.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author riski
 */
public class Pesanan {
    private Koneksi db;
    private Statement st;
    private String query;
    public ResultSet rs;
    private int jumlah_baris;

    public String[][] Tampil_Semua_pesanan() {
        rs = null;
        String[][] data = null;
        db = new Koneksi();
        db.KoneksiDatabase();
        jumlah_baris = 0;
        try {
            st = db.con.createStatement();
            query = "select count(KD_Pemesanan) as jum from pesanan ";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlah_baris = rs.getInt("jum");
            }
            query = "SELECT KD_Pemesanan,KD_Pelanggan, Nama_Pelanggan, Nama_Kusen, Jumlah_Kusen, Ukuran, Harga_Total, Alamat, Tgl_Pengambilan FROM pesanan join pelanggan using (KD_Pelanggan) join desainkusen USING (KD_DesainKusen)";
            rs = st.executeQuery(query);
            data = new String[jumlah_baris][9];
            //7 adalah banyaknya field yang akan ditampilkan
            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("KD_Pemesanan");
                data[r][1] = rs.getString("KD_Pelanggan");
                data[r][2] = rs.getString("Nama_Pelanggan");
                data[r][3] = rs.getString("Nama_Kusen");
                data[r][4] = rs.getString("Jumlah_Kusen");
                data[r][5] = rs.getString("Ukuran");
                data[r][6] = rs.getString("Harga_Total");
                data[r][7] = rs.getString("Alamat");
                data[r][8] = rs.getString("Tgl_Pengambilan");
                r++;

            }
            
            st.close();
            db.con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }
    
    
    public boolean cek_Pesanan(String KD_Pemesanan_dt){
        rs= null;
        db = new Koneksi();
        db.KoneksiDatabase();
        boolean data = false;
        try {
            st = db.con.createStatement();
            query = "select KD_Pemesanan from pesanan where KD_Pemesanan'"+KD_Pemesanan_dt+"'";
            rs = st.executeQuery(query);
            if(rs.next()){
                return false;
            }
            else{
                JOptionPane.showMessageDialog(null, "id_pesanan"+KD_Pemesanan_dt+ "Tidak Ada Dalam"
                        + " Database !!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }
    
    public void insert (int KD_Pemesanan_dt, 
                        int KD_Pelanggan_dt, 
                        int KD_DesainKusen_dt, 
                        int Jumlah_Kusen_dt, 
                        int Harga_Total_dt, 
                        String Tgl_Pemesanan_dt, 
                        String Tgl_Pengambilan_dt)
    {
        db = new Koneksi();
        db.KoneksiDatabase();
        try {
            st = db.con.createStatement();
            query = "insert into pesanan values ('"+KD_Pemesanan_dt+"','"+KD_Pelanggan_dt+"', '"+KD_DesainKusen_dt+"', '"+Jumlah_Kusen_dt+"', '"+Harga_Total_dt+"','"+Tgl_Pemesanan_dt+"','"+Tgl_Pengambilan_dt+"')";
            st.executeUpdate(query);
            st.close();
            db.con.close();
            JOptionPane.showMessageDialog(null, "Simpan Data Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
     public void ubah (int KD_Pemesanan_dt,
                        int KD_Pelanggan_dt,
                        String Nama_Pelanggan_dt, 
                        String Tgl_Pengambilan_dt, 
                        String Alamat_dt)
     {
         db = new Koneksi();
         db.KoneksiDatabase();
         try {
             query = "UPDATE pesanan, pelanggan SET pelanggan.Nama_Pelanggan=? ,pesanan.Tgl_Pengambilan=? ,pelanggan.Alamat=? WHERE pesanan.KD_Pemesanan=? and pelanggan.KD_Pelanggan=?";
             PreparedStatement update =db.con.prepareStatement(query);
             update.setInt(4, KD_Pemesanan_dt);
             update.setInt(5, KD_Pelanggan_dt);
             update.setString(1, Nama_Pelanggan_dt);
             update.setString(2, Tgl_Pengambilan_dt);
             update.setString(3, Alamat_dt);
             update.executeUpdate();
             update.close();
             db.con.close();
             JOptionPane.showMessageDialog(null, "Berhasil Merubah Data : "
                                            + KD_Pemesanan_dt);
             
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
     }
     
     public void hapus(int KD_pemesanan_dt){
         db = new Koneksi();
         db.KoneksiDatabase();
         try {
             query = "delete from pesanan where KD_Pemesanan=?";
             PreparedStatement hapus_data = db.con.prepareStatement(query);
             hapus_data.setInt(1, KD_pemesanan_dt);
             hapus_data.executeUpdate();
             hapus_data.close();
             db.con.close();
             JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data : "
                                            + KD_pemesanan_dt);
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
     }
     
    /* public String [][] cari_pesanan (String kata_kunci){
         rs = null;
         String [][] data = null;
         db = new Koneksi();
         db.KoneksiDatabase();
         int jumlah_baris = 0;
         try {
             st = db.con.createStatement();
             query = "select count(idpesanan) as jum from pesanan";
             rs = st.executeQuery(query);
             if (rs.next()){
                 jumlah_baris = rs.getInt("jum");
                 
             }
             query = "SELECT * FROM pesanan join stok_kusen using(idkusen) WHERE idpesanan='"+kata_kunci+"'";
             rs = st.executeQuery(query);
             data = new String[jumlah_baris][7];
             //7 adalah banyak field yang ditampilkan
             int r =0;
             while(rs.next()){
                data[r][0] = rs.getString("idpesanan");
                data[r][1] = rs.getString("nama_pemesan");
                data[r][2] = rs.getString("tgl_pesanan");
                data[r][3] = rs.getString("nama_kusen");
                data[r][4] = rs.getString("jumlah_pesanan");
                data[r][5] = rs.getString("jumlah_bayar");
                data[r][6] = rs.getString("alamat_pemesan");
                    r++;
             }
            st.close();
            db.con.close();
            
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
         return data;
     } */
}
