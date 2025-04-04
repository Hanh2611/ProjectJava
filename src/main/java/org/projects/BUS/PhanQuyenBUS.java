package org.projects.BUS;

import org.projects.DAO.DanhMucQuanLyDAO;
import org.projects.DAO.NhomQuyenDAO;
import org.projects.entity.DanhMucQuanLy;
import org.projects.entity.NhomQuyen;
import java.util.*;

public class PhanQuyenBUS {
    public PhanQuyenBUS() {
    }
    public List<NhomQuyen> getNhomQuyen() {
        return new NhomQuyenDAO().showlist();
    }
    public List<DanhMucQuanLy> getDanhMucQuanLy() {return new DanhMucQuanLyDAO().showlist();}

}
