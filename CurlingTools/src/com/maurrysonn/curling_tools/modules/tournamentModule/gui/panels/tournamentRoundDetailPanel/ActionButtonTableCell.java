package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundDetailPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ActionButtonTableCell extends AbstractCellEditor implements
		TableCellEditor, TableCellRenderer {

	private static final long serialVersionUID = -841155364721498112L;

	private JTable table;
	private TableColumn column;
	private Action action;
	
	private JButton actionBtn;
	private JPanel pane;
	
	public ActionButtonTableCell(final JTable _table, final int _column, final Action _action) {
		// Initialize data
		table = _table;
		column = table.getColumnModel().getColumn(_column);
		action = _action;
		// Initialize renderer/editor
		initializeGUI();
		initializeListeners();
		initializeTable();
	}

	private void initializeTable() {
		// Initialize table
		column.setCellRenderer(this);
		column.setCellEditor(this);
	}

	private void initializeListeners() {
		// Init action button listener
		actionBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// XXX amaury - Delete print
				System.out
						.println("ActionButtonTableCell.initializeListeners().new ActionListener() {...}.actionPerformed()");
				int row = table.convertRowIndexToModel(table.getEditingRow());
				fireEditingStopped();
				ActionEvent evt = new ActionEvent(actionBtn, ActionEvent.ACTION_PERFORMED, String.valueOf(row));
				action.actionPerformed(evt);
			}
		});
		
		addActionButtonTableCellListener(new ActionButtonTableCellListener() {
			@Override
			public void nameActionChanged() {
				column.setMaxWidth(getPreferredSize().width);
				table.setRowHeight(getPreferredSize().height);
			}
		});
	}

	private void initializeGUI() {
		// Initialize GUI renderer/editor
		actionBtn = new JButton("");
		pane = new JPanel();
		pane.add(actionBtn);
	}
	
	private void updateData(JTable table, Object value, boolean isSelected) {
		if(value instanceof String) {
			// Update title btn
			setNameAction((String)value);
		}
		if (isSelected) {
			pane.setBackground(table.getSelectionBackground());
		}else{
			pane.setBackground(table.getBackground());
		}
	}
	
	private void setNameAction(String value) {
		if(!actionBtn.getText().equals(value)) {
			// XXX amaury - Delete print
			System.out.println("===> Size changed !");
			actionBtn.setText(value);
			fireNameActionChanged();
		}
	}

	@Override
	public Object getCellEditorValue() {
		// Return nothing
		return null;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// XXX amaury - Delete print
		System.out
				.println("ActionButtonTableCell.getTableCellRendererComponent() - " + row + "|" + column);
		updateData(table, value, isSelected);
		return pane;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// XXX amaury - Delete print
		System.out
				.println("ActionButtonTableCell.getTableCellEditorComponent() - " + row + "|" + column);
		updateData(table, value, isSelected);
		return pane;
	}

	public Dimension getPreferredSize() {
		return pane.getPreferredSize();
	}
	
	private void fireNameActionChanged() {
		for (final ActionButtonTableCellListener l : listenerList.getListeners(ActionButtonTableCellListener.class)) {
			l.nameActionChanged();
		}
	}
	
	public void addActionButtonTableCellListener(final ActionButtonTableCellListener l) {
		listenerList.add(ActionButtonTableCellListener.class, l);
	}
}
