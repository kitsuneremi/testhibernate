/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitories;

import DomainModels.DanhMuc;
import DomainModels.SanPham;
import Utilities.DBConnection;
import ViewModels.SanPhamViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nguyenvv
 */
public class SanPhamRepository {

    private DBConnection dBConnection;

    public ArrayList<SanPhamViewModel> getListSanPhamViewModel() {
        ArrayList<SanPhamViewModel> list = new ArrayList<>();
        String sql = "SELECT danh_muc.ten_danh_muc, san_pham.ten_san_pham,\n"
                + "    san_pham.so_luong, san_pham.gia_nhap, san_pham.gia_ban\n"
                + " from danh_muc INNER JOIN san_pham ON danh_muc.id = san_pham.danh_muc_id";
        try (Connection con = dBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamViewModel sanPhamViewModel = new SanPhamViewModel();
                sanPhamViewModel.setTenDanhMuc(rs.getString(1));
                sanPhamViewModel.setTenSanPham(rs.getString(2));
                sanPhamViewModel.setSoLuong(rs.getInt(3));
                sanPhamViewModel.setGiaNhap(rs.getFloat(4));
                sanPhamViewModel.setGiaBan(rs.getFloat(5));

                list.add(sanPhamViewModel);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return list;
    }

    public ArrayList<SanPham> getListSanPham() {
        String sql = "select ten_san_pham, so_luong,gia_nhap, gia_ban, mieu_ta "
                + "from san_pham";
        ArrayList<SanPham> list = new ArrayList<>();
        try (Connection con = dBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setTenSanPhamString(rs.getString(1));
                sanPham.setSoLuong(rs.getInt(2));
                sanPham.setGiaBan(rs.getDouble(4));
                sanPham.setGiaNhap(rs.getDouble(3));
                sanPham.setMieuTa(rs.getString(5));
                list.add(sanPham);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    }

}
