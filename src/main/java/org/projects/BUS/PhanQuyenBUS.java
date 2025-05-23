package org.projects.BUS;

import org.projects.DAO.CapQuyenDAO;
import org.projects.DAO.DanhMucQuanLyDAO;
import org.projects.DAO.NhomQuyenDAO;
import org.projects.DAO.QuyenNguoiDungDAO;
import org.projects.GUI.utils.Session;
import org.projects.entity.*;

import java.util.*;

public class PhanQuyenBUS {
    public PhanQuyenBUS() {
    }
    public static List<NhomQuyen> getNhomQuyen() {
        return new NhomQuyenDAO().showlist();
    }
    public static List<DanhMucQuanLy> getDanhMucQuanLy() {return new DanhMucQuanLyDAO().showlist();}
    public static int addNhomQuyen(String nameNhomQuyen) {
        NhomQuyen q = new NhomQuyen(nameNhomQuyen);
        int maNhomQuyen = new NhomQuyenDAO().them(q);
        return maNhomQuyen;
    }

    public static void addCapQuyen(HashMap<String, List<Boolean>> danhMucData, int maNhomQuyen) {
        String[] hanhDong = {"them", "sua", "xoa", "xem", "excel"};
        for (String key : danhMucData.keySet()) {
            int maDanhMuc = DanhMucQuanLyDAO.getMaDanhMuc(key);
            int pos = 0;
            for (Boolean value : danhMucData.get(key)) {
                CapQuyen capQuyen = new CapQuyen();
                if (value == true) {
                    capQuyen.setHanh_dong(hanhDong[pos]);
                    capQuyen.setMa_nhom_quyen(maNhomQuyen);
                    capQuyen.setMa_danh_muc_quan_ly(maDanhMuc);
                    new CapQuyenDAO().them(capQuyen);
                }
                pos++;
            }
        }
    }

    public static void deleteCapQuyen(int maNhomQuyen) {
        CapQuyen capQuyen = new CapQuyen();
        capQuyen.setMa_nhom_quyen(maNhomQuyen);
        new CapQuyenDAO().xoa(capQuyen);
    }

    public static void deleteNhomQuyen(int maNhomQuyen) {
        NhomQuyen nhomQuyen = new NhomQuyen();
        QuyenNguoiDung quyenNguoiDung = new QuyenNguoiDung();
        quyenNguoiDung.setMa_nhom_quyen(maNhomQuyen);
        nhomQuyen.setMaNhomQuyen(maNhomQuyen);
        new QuyenNguoiDungDAO().xoa(quyenNguoiDung);
        deleteCapQuyen(maNhomQuyen);
        new NhomQuyenDAO().xoa(nhomQuyen);
    }

    public static void updateNhomQuyen(HashMap<String, List<Boolean>> danhMucData, int maNhomQuyen) {
        deleteCapQuyen(maNhomQuyen);
        addCapQuyen(danhMucData, maNhomQuyen);
    }

    public static boolean checkCapQuyen(CapQuyen capQuyen) {
        return new CapQuyenDAO().check(capQuyen);
    }

    public static void updateNameNhomQuyen(int maNhomQuyen, String nameNhomQuyen) {
        new NhomQuyenDAO().sua(new NhomQuyen(maNhomQuyen, nameNhomQuyen));
    }

    public static List<Integer> getQuyenDanhMuc(TaiKhoanEntity user) {
        return new DanhMucQuanLyDAO().getDanhMucQuanLyByMaNhomQuyen(QuyenNguoiDungBUS.getMaNhomQuyenByMaNguoiDung(user.getMaNguoiDung()));
    }

    public static int getMaDanhMuc(String nameDanhMuc) {
        return DanhMucQuanLyDAO.getMaDanhMuc(nameDanhMuc);
    }

    public static List<Integer> getListNhomQuyen(int maNguoiDung) {
        return new NhomQuyenDAO().getNhomQuyenOfUser(maNguoiDung);
    }
    public static void getListAction() {
        Session.quyenTaiKhoan = new DanhMucQuanLyDAO().quyenHanhDong(Session.maNhomQuyen);
    }

    public static boolean checkExitsNameNhomQuyen(String nameNhomQuyen) {
        return new NhomQuyenDAO().checkExist(nameNhomQuyen);
    }

    public static List<String> getTenNhomQuyen() {
        return new NhomQuyenDAO().getDanhsachtennhomquyen();
    }

    public static int getMaNhomQuyenByName(String name) {
        return new NhomQuyenDAO().getMaNhomQuyen(name);
    }

    public static List<NhomQuyen> search(String key,String word) {
        getNhomQuyen();
        List<NhomQuyen> lst = new ArrayList<>();
        word = word.toLowerCase();
        switch (key) {
            case "---":
                lst.addAll(getNhomQuyen());
                break;
            case "Tên nhóm quyền":
                for(NhomQuyen nq : getNhomQuyen()) {
                    if(nq.getTenNomQuyen().contains(word)) {
                        lst.add(nq);
                    }
                }
                break;
            case "Mã nhóm quyền":
                for(NhomQuyen tk : getNhomQuyen()) {
                    if(tk.getMaNhomQuyen() == (Integer.parseInt(word))) {
                        lst.add(tk);
                    }
                }
                break;
            default:
                break;
        }
        return lst;
    }
 }
