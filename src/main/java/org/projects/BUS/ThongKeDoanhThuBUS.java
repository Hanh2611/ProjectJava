    package org.projects.BUS;

    import org.projects.DAO.ThongKeDoanhThuDAO;
    import org.projects.entity.ThongkeDoanhThuEntity;

    import java.util.HashMap;
    import java.util.List;

    public class ThongKeDoanhThuBUS {
        private static ThongKeDoanhThuDAO tkdtDAO = new ThongKeDoanhThuDAO();

        public HashMap<String,Double> getngayvatongtien(String from, String to) {
                return tkdtDAO.laytongtiencuahoadontheongay(from, to);
        }

        public List<ThongkeDoanhThuEntity> getdanhsach(String from, String to) {
            return tkdtDAO.laydanhsachtheongay(from, to);
        }
    }
