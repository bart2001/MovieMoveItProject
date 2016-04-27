package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.MembersDTO;
import function.DeleteMember;
import function.SelectAllMembers;

public class AdminFrame extends JFrame {
   
   private static final long serialVersionUID = 1L;
   String U_ID = "";
   MembersDTO member = null;
   JTable table;
   DefaultTableModel defaultTableModel;
   JScrollPane scrollPane;

            
   // 생성자
   public AdminFrame() {
   
      // JFrame
      setTitle("관리자 페이지");
      setBounds(200, 200, 600, 400);
      init();   
      setVisible(true);
   }   
      
   public void init() {   
         
      JLabel label = new JLabel("사용자 목록");
      label.setHorizontalAlignment(JLabel.CENTER);
      
      table = new JTable();
      
      defaultTableModel = new DefaultTableModel();
      
      table.setFocusable(false);
      table.setCellSelectionEnabled(false);
      
      SelectAllMembers selectAdminPage = new SelectAllMembers();      
      List<MembersDTO> list = selectAdminPage.getMemberList();
      System.out.println("list size = " + list.size());
            
      String[] columnNames = {"U_ID", "U_NAME", "AGE", "SEX"};
      Object[][] rowData = new Object[list.size()][4];
      
      for (int i = 0; i < list.size(); i++) {
                  
         MembersDTO mmdto = list.get(i);
         
         rowData[i][0] = mmdto.getU_ID();
         rowData[i][1] = mmdto.getU_NAME();
         rowData[i][2] = mmdto.getBIRTHDATE();
         rowData[i][3] = mmdto.getSEX();            
      }      
      
      defaultTableModel.setDataVector(rowData, columnNames);
      table.setModel(defaultTableModel);
            
      table.addMouseListener(new MouseHandler());
      
      scrollPane = new JScrollPane(table);            
      
      add(label, "North");
      
      add(scrollPane, "Center");   
            
      defaultTableModel.fireTableDataChanged();
      table.revalidate();
      this.revalidate();   
               
   }   
      
   class MouseHandler extends MouseAdapter {

      @Override
      public void mouseClicked(MouseEvent e) {
         
         super.mouseClicked(e);
         
         System.out.println("클릭");
         
          table = (JTable) e.getSource();
          int row = table.getSelectedRow();          
             
          System.out.println("회원 삭제 버튼 클릭");
          U_ID = (String) table.getValueAt(row, 0);
          System.out.println(U_ID);
                          
          new DeleteMemberPopup();                
      }
   }
      
   class DeleteMemberPopup extends JFrame implements ActionListener {
         
      private static final long serialVersionUID = 1L;               

      public DeleteMemberPopup() {

         setBounds(200, 200, 340, 200);
         this.setLayout(new BorderLayout(0, 10));
         setLayout(null);

         JLabel label = new JLabel("정말 삭제 하시겠습니까?");
         JButton btn1 = new JButton("확인"); // 삭제를 실행해주는 버튼
         JButton btn2 = new JButton("취소"); // 창을 끄는 버튼

         btn1.setBounds(50, 100, 100, 30);
         btn2.setBounds(180, 100, 100, 30);

         label.setBounds(50, 50, 200, 30);

         label.setHorizontalAlignment(JLabel.CENTER);

         add(label, "North");
         add(btn1);
         add(btn2);

         btn1.addActionListener(this);
         btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
         });

         setVisible(true);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         int cnt = new DeleteMember().delete(U_ID);
         System.out.println(cnt + "개 삭제하였음");
         dispose();
         init();
      }
   }      
}