package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundDetailPanel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Group;

public class GroupTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 7526838185508691740L;

	private List<Group> groupList;
	private String[][] columnList = {{"Name", "getName"}, {"Date", "getVerboseStartTime"}, {"Actions", null}};

	private static int COLUMN_HEADER = 0;
	private static int COLUMN_METHOD = 1;

	public GroupTableModel() {
		this(null);
	}
	
	public GroupTableModel(final List<Group> _groupList) {
		// Initialize data
		if(_groupList != null){
			groupList = _groupList;
		}else{
			groupList = new ArrayList<Group>();
		}
	}
	
	public void setElements(final List<Group> _groupList) {
		groupList.clear();
		addElements(_groupList);
	}
	
	public void addElement(final Group _group){
		// XXX amaury - Delete print
		System.out.println("GroupTableModel.addElement() - " + _group);
		groupList.add(_group);
		fireTableDataChanged();
	}

	public void addElements(final List<Group> _groupList){
		groupList.addAll(_groupList);
	}
	
	public void clearElements(){
		groupList.clear();
		fireTableDataChanged();
	}
	
	public void updateElement(final Group _group){
		// XXX amaury - Delete print
		System.out.println("GroupTableModel.updateElement() - " + _group);
		final int index = groupList.indexOf(_group);
		if(index != -1){
			groupList.remove(index);
			groupList.add(index, _group);
			fireTableDataChanged();
		}
	}
	
	public void removeElement(Group _group) {
		// XXX amaury - Delete print
		System.out.println("GroupTableModel.removeElement() - " + _group);
		final int index = groupList.indexOf(_group);
		// XXX amaury - Delete print
		System.out.println("INDEX = " + index);
		if(index != -1){
			groupList.remove(index);
			fireTableDataChanged();
		}
	}
	
	@Override
	public int getColumnCount() {
		return columnList.length;
	}

	@Override
	public int getRowCount() {
		return groupList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		if(row >= 0 && row < getRowCount() && column >=0 && column < getColumnCount()){
			final Group group = groupList.get(row);
			try {
				if(columnList.length >= column &&
						columnList[column][COLUMN_METHOD] != null &&
						!columnList[column][COLUMN_METHOD].isEmpty()) {
					Method method = Group.class.getMethod(this.columnList[column][COLUMN_METHOD]);
					return method.invoke(group);
				}
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	@Override
	public String getColumnName(int column) {
		if(column >=0 && column < getColumnCount()){
			return this.columnList[column][COLUMN_HEADER];
		}
		return "";
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public Group getItem(final int index){
		if(index >=0 && index < getRowCount()){
			return groupList.get(index);
		}
		return null;
	}

	
	
	// TODO AP - To remove
	@Override
	public void fireTableChanged(TableModelEvent e) {
		// XXX AP - Delete print
		System.out.println("TournamentTableModel.fireTableChanged()");
		super.fireTableChanged(e);
	}

}
