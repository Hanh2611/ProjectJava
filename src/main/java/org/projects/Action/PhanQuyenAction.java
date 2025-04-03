package org.projects.Action;

import org.projects.DAO.PhanQuyenDAO;
import org.projects.entity.NhomQuyen;
import java.util.*;

public class PhanQuyenAction {
    public PhanQuyenAction() {
    }
    public List<NhomQuyen> getNhomQuyen() {
        return new PhanQuyenDAO().showlist();
    }
}
