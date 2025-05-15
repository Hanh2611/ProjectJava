    package org.projects.BUS;

    import org.projects.DAO.ThongKeDoanhThuDAO;
    import org.projects.entity.ThongkeDoanhThuEntity;

    import java.util.HashMap;
    import java.util.List;

    public class ThongKeDoanhThuBUS {
        private final ThongKeDoanhThuDAO tkdtDAO;

        public ThongKeDoanhThuBUS() {
            tkdtDAO = new ThongKeDoanhThuDAO();
        }

        public HashMap<String,Double> getngayvatongtien(String from, String to) {
                return tkdtDAO.laytongtiencuahoadontheongay(from, to);
        }

        public List<ThongkeDoanhThuEntity> getdanhsach(String from, String to) {
            return tkdtDAO.laydanhsachtheongay(from, to);
        }

        public HashMap<String,Double> getthangvatongtien(String thang,String nam) {
            return tkdtDAO.laytongtienhoadontheothang(thang,nam);
        }

        public List<ThongkeDoanhThuEntity> laydanhsach(String thang,String nam) {
            return tkdtDAO.laydanhsachtheothangnam(thang,nam);
        }

        public HashMap<String,Double> getnamvatongtien(String nam) {
            return tkdtDAO.laytonghoadontheonam(nam);
        }

        public List<ThongkeDoanhThuEntity> laydanhsachtheonam(String nam) {
            return tkdtDAO.laydanhsachtheonam(nam);
        }
    }
