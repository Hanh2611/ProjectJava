package org.projects.BUS;

import org.projects.DAO.PhanQuyenDAO;
import org.projects.entity.NhomQuyen;
import java.util.*;

public class PhanQuyenBUS {
    public PhanQuyenBUS() {
    }
    public List<NhomQuyen> getNhomQuyen() {
        return new PhanQuyenDAO().showlist();
    }
}
