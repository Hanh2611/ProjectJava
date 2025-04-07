package org.projects.GUI.Panel;

import org.projects.Action.NhaCungCapAction;
import org.projects.BUS.NhaCungCapBUS;
import org.projects.DAO.NhaCungCapDAO;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.Components.header.headerSearch;
import org.projects.GUI.Components.layoutCompoment;
import org.projects.GUI.utils.UIUtils;
import org.projects.entity.NhaCungCapEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    private NhaCungCapAction nccAction = new NhaCungCapAction(this,null);
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS(this);
    private NhaCungCapEntity nhaCungCapEntity;

    public NhaCungCap() {
        String[][] listItemHeader = {
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"}
        };
        header = new headerBar(listItemHeader,new ArrayList<>(Arrays.asList("add", "update", "delete", "detail")),new String[]{"---","mã","tên","địa chỉ"});
        this.add(header);
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(940,1000));
//        layoutCompoment.addHeader(this, listItemHeader,new String[]{"add","update","delete","detail"});
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

        //su kien cac nut
        for(String name : header.getHeaderFunc().getHm().keySet()) {
            header.getHeaderFunc().getHm().get(name).addMouseListener(nccAction);
        }

        //su kien tim kiem
        header.getSearch().getSearchComboBox().addItemListener(nccAction);
        header.getSearch().getSearchField().getDocument().addDocumentListener(nccAction);
        header.getSearch().getSearchButton().addActionListener(nccAction);

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
    public NhaCungCapEntity getRow() {
        int row = nccTabel.getSelectedRow();
        if(row == -1) return null;
        int ma = (int) nccTabel.getValueAt(row,0);
        String ten = nccTabel.getValueAt(row,1).toString();
        String sdt = nccTabel.getValueAt(row,2).toString();
        String email = nccTabel.getValueAt(row,3).toString();
        String diaChi = nccTabel.getValueAt(row,4).toString();

        return new NhaCungCapEntity(ma,ten,sdt,email,diaChi);
    }

    public void searchfunction(String keyword,String textfield) {
        keyword = this.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        textfield = this.getHeader().getSearch().getSearchField().getText();
        if(!keyword.equals("---") && !textfield.trim().isEmpty()) {
            List<NhaCungCapEntity> lst = NhaCungCapBUS.search(keyword,textfield);
            loadList(lst);
        } else reloadDAO();
    }
    //getter
    public headerBar getHeader() {
        return header;
    }
    public NhaCungCapBUS getNccBUS() {return nccBUS;}
}
