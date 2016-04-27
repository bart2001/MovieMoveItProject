package gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.MoviesDTO;
import dto.MyPageDTO;
import function.SelectGradesByM_NAME;

public class SelectGradesByM_NAME_Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	
	String m_name;
	MoviesDTO movieInfo;
	List<MyPageDTO> mlist;

	// DB����
	public SelectGradesByM_NAME_Frame getInstance() {
		return this;
	}

	public SelectGradesByM_NAME_Frame(String m_name) {
		
		movieInfo = new SelectGradesByM_NAME().movieInfo(m_name);
		mlist = new SelectGradesByM_NAME().mlist(m_name); // id ���� ���� ����Ʈ ��������
		if (mlist.isEmpty()) {
			JOptionPane.showMessageDialog(null, "���� ����� �����ϴ�.");

		} else {
			this.m_name = m_name;
			
			// JFrame �Ҽ� �޼ҵ�
			setTitle("���񹫺�");
			setBounds(100, 100, 800, 400);

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(8,2));
			
			JLabel jLabel = new JLabel(m_name + "�� ������ ���Դϴ�.");		
		//	add(jLabel, "North");
			JLabel jLabel_GENRE = new JLabel("�帣  :  " + movieInfo.getGENRE());
			JLabel jLabel_DIRECTOR = new JLabel("����  :  " + movieInfo.getDIRECTOR());
			JLabel jLabel_ACTOR = new JLabel("���  :  " + movieInfo.getACTOR());
			JLabel jLabel_RELEASE_DATE = new JLabel("������  :  " + movieInfo.getRELEASE_DATE());
			JLabel jLabel_V_TIME = new JLabel("�󿵽ð�  :  " + movieInfo.getV_TIME());
			JLabel jLabel_NATION = new JLabel("����  :  " + movieInfo.getNATION());
			JLabel jLabel_PRODUCTION = new JLabel("���ۻ�  :  " + movieInfo.getPRODUCTION());
			
			
			//M_NAME   GENRE   DIRECTOR   ACTOR   RELEASE_DATE   V_TIME   NATION   PRODUCTION
			
			panel.add(jLabel);
			panel.add(jLabel_GENRE);
			panel.add(jLabel_DIRECTOR);
			panel.add(jLabel_ACTOR);
			panel.add(jLabel_RELEASE_DATE);
			panel.add(jLabel_V_TIME);
			panel.add(jLabel_NATION);
			panel.add(jLabel_PRODUCTION);
		
			add(panel, "North");		
			

			// jScrollpane (ǥ�� ���� ���� ��ũ�� �ǳ�)
			
			String columnNames[] = { "ȸ����", "����", "���" };
			if(mlist.isEmpty()){
				this.setVisible(false);
			}
			
			Object[][] rowData = new Object[mlist.size()][3];

			for (int i = 0; i < mlist.size(); i++) {
				MyPageDTO dto = mlist.get(i);
				rowData[i][0] = dto.getMovie_name();
				rowData[i][1] = dto.getGrade();
				rowData[i][2] = dto.getComments();
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

		//	this.add(jLabel, "North");
			this.add(jScrollPane, "Center");
			this.setVisible(true);
		}

	}

}