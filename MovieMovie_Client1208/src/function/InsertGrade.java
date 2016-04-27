package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertGrade {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public InsertGrade() {
		conn = ConnectResource.makeConnection();
	}

	public void grade(String id, String movie_name, double grade, String reple) {
		try {

			String query = " insert into grade(u_id, m_id, grade,comments ) " + "values ('" + id + "',"
					+ "(select m_id from movies where m_name = '" + movie_name + "') ," + grade + ", '" + reple
					+ "')";
			/*
			 * query += "values('" + member.getU_ID() + "', '" +
			 * member.getPASSWORD() + "', '" + member.getU_NAME() + "', " +
			 * member.getAGE() + " , " + member.getSEX() +")";
			 */
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery(query);

			
			// 없음

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

/*public class ButtonEditor extends DefaultCellEditor {
	
	private static final long serialVersionUID = 1L;
	protected JButton button;
	private JTable table;
	private String label;
	int row, column;
	private boolean isPushed;
	String id = "";

	public ButtonEditor(JCheckBox checkBox, String id) {
		super(checkBox);
		this.id = id;
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	
		this.table = table;
		this.row = row;
		this.column = column;
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	// 해당 행을 삭제하는 메소드
	public String getCellEditorValue() {

		String movie_name = "";

		if (isPushed) {

			movie_name = table.getValueAt(row, 0).toString();
			JOptionPane.showMessageDialog(button, label + ": 이거를 지운다!");

			int cnt = new DeleteGradedMovie().delete(movie_name, id);
			System.out.println(cnt + "개 삭제하였음");

		}

		return "삭제";

	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}*/
	
}
