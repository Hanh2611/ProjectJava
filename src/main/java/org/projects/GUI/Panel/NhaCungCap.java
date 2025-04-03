package org.projects.GUI.Panel;

import org.projects.Action.NhaCungCapAction;
import org.projects.DAO.NhaCungCapDAO;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.utils.UIUtils;
import org.projects.entity.NhaCungCapEntity;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class NhaCungCap extends JPanel{
    private JPanel centerPanel;
    private JTable nccTabel;
    private DefaultTableModel nameTableModel;
    private DefaultTableCellRenderer listRenderTable;
    private JScrollPane scrollData;
    private headerBar header;
    private NhaCungCapAction nccBus = new NhaCungCapAction(this,null);

    public NhaCungCap() {
        String[][] listItemHeader = {
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"}
        };
        header = new headerBar(listItemHeader,new String[]{"add","update","delete","detail"});
        this.add(header);
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(940,1000));
//        layoutCompoment.addHeader(this, listItemHeader);
        this.init();
        reloadDAO();
    }

    private void init() {
        nameTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        nameTableModel.setColumnIdentifiers(new String[]{"Mã nhà cung cấp","tên nhà cung cấp","số điện thoại","email","địa chỉ"});
        nccTabel = new JTable();
        nccTabel.setForeground(Color.decode("#7ed6df"));
        nccTabel.setShowGrid(true);
        nccTabel.setGridColor(new Color(220, 220, 220));
        nccTabel.setSelectionBackground(new Color(204, 229, 255));
        nccTabel.setRowHeight(40);
        nccTabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        nccTabel.setModel(nameTableModel);
        listRenderTable = new DefaultTableCellRenderer();
        listRenderTable.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0 ; i < nccTabel.getColumnCount();++i) {
            nccTabel.getColumnModel().getColumn(i).setCellRenderer(listRenderTable);
        }
        scrollData = new JScrollPane(nccTabel);
        centerPanel.add(scrollData, BorderLayout.CENTER);
        this.add(centerPanel);

        for(String name : header.getHeaderFunc().getHm().keySet()) {
            header.getHeaderFunc().getHm().get(name).addActionListener(nccBus);
        }
        UIUtils.refreshComponent(this);
    }
    public void loadList(List<NhaCungCapEntity> list) {
        nameTableModel.setRowCount(0);
        if(list != null) {
            for(NhaCungCapEntity nc : list) {
                nameTableModel.addRow(new Object[]{
                   nc.getMaNCC(),
                   nc.getTenNCC(),
                   nc.getSoDienThoaiNCC(),
                   nc.getEmailNCC(),
                   nc.getDiaCHiNCC()
                });
            }

        }
    }
    public void reloadDAO() {
        List<NhaCungCapEntity> lst = new NhaCungCapDAO().showlist();
        loadList(lst);
    }
    //getter
    public headerBar getHeader() {
        return header;
    }
}
