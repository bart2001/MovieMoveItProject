package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.MyPageDTO;
import function.SelectGradesByU_ID;

public class SelectGradesByU_ID_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	List<MyPageDTO> list;

	public SelectGradesByU_ID_Frame getInstance() {
		return this;
	}

	public SelectGradesByU_ID_Frame(String id) {

		// JFrame �Ҽ� �޼ҵ�
		this.setTitle("���񹫺�");
		this.setBounds(100, 100, 800, 400);
		// �����ư�� ������ ���� �� �ֵ��� ��
		this.setLayout(new BorderLayout(0, 10));

		JLabel jLabel = new JLabel(id + "���� �� ��ȭ�Դϴ�.");
		jLabel.setHorizontalAlignment(JLabel.CENTER);

		// jScrollpane (ǥ�� ���� ���� ��ũ�� �ǳ�)
		String columnNames[] = { "��ȭ��", "�帣", "����", "��ȭ���", "������", "�󿵽ð�", "���۱���", "���ۻ�", "����", "�ı�" };

		list = new SelectGradesByU_ID().list(id); // id ���� ���� ����Ʈ ��������
		if(list.isEmpty()){
			this.setVisible(false);
		}
	    
		Object[][] rowData = new Object[list.size()][10];

		for (int i = 0; i < list.size(); i++) {
			
			MyPageDTO dto = list.get(i);
			
	         rowData[i][0] = dto.getMovie_name();
	         rowData[i][1] = dto.getGENRE();
	         rowData[i][2] = dto.getDIRECTOR();
	         rowData[i][3] = dto.getACTOR();
	         rowData[i][4] = dto.getRELEASE_DATE();
	         rowData[i][5] = dto.getV_TIME();
	         rowData[i][6] = dto.getNATION();
	         rowData[i][7] = dto.getPRODUCTION();
	         rowData[i][8] = dto.getGrade();
	         rowData[i][9] = dto.getComments();         
		}

		// DefaultTableModel�� �����ϰ� ������ ���
		DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnNames);

		// JTable�� DefaultTableModel�� ���
		JTable jTable = new JTable(defaultTableModel) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}			
		};
		
		// ���̺� �� ���� �Ұ����ϰ� �ϱ�
		jTable.setFocusable(false);
		jTable.setCellSelectionEnabled(false);

		// JScrollPane�� JTable�� ���
		JScrollPane jScrollPane = new JScrollPane(jTable);

		this.add(jLabel, "North");
		this.add(jScrollPane, "Center");
		this.setVisible(true);
	}

}