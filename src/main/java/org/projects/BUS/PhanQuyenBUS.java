package org.projects.BUS;

import org.projects.DAO.PhanQuyenDAO;
import org.projects.entity.NhomQuyenEntity;
import java.util.*;

public class PhanQuyenBUS {
    public PhanQuyenBUS() {
    }
    public List<NhomQuyenEntity> getNhomQuyen() {
        return new PhanQuyenDAO().showlist();
    }
}
