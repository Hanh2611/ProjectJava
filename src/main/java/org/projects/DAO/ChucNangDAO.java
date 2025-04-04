package org.projects.DAO;

import java.util.List;

public interface ChucNangDAO<T> {
    List<T> showlist();
    int them(T add);
    int sua(T fix);
    int xoa(T delete);
    int chitiet(T detail);
}
