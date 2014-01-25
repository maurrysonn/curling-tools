package com.maurrysonn.curling_tools.modules.clubModule.gui.panels.clubListPanel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;

public class ClubTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2732841014944246550L;

	private List<Club> clubList;
	private String[][] columnList = {{"Name", "getName"}, {"Short Name", "getShortName"}};
	
	private static int COLUMN_HEADER = 0;
	private static int COLUMN_METHOD = 1;
	
	public ClubTableModel(){
		this(null);
	}
	
	public ClubTableModel(final List<Club> _clubList) {
		// Initialize data
		if(_clubList != null){
		this.clubList = _clubList;
		}else{
			this.clubList = new ArrayList<Club>();
		}
	}
	
	public void addElement(final Club _club){
		// XXX amaury - Delete print
		System.out.println("ClubTableModel.addElement() - Club="+_club);
		this.clubList.add(_club);
		// XXX amaury - Delete print
		System.out.println("ClubTableModel.removeElement() - fireTableDataChanged()");
		fireTableDataChanged();
	}
	
	public void addElements(final List<Club> _clubList){
		this.clubList.addAll(_clubList);
	}
	
	public void clearElements(){
		this.clubList.clear();
	}
	
	public void updateElement(final Club _club){
		// XXX amaury - Delete print
		System.out.println("ClubTableModel.updateElement() - Club="+_club);
		final int index = this.clubList.indexOf(_club);
		// XXX amaury - Delete print
		System.out.println("INDEX = " + index);
		if(index != -1){
			this.clubList.remove(index);
			this.clubList.add(index, _club);
			// XXX amaury - Delete print
			System.out.println("ClubTableModel.updateElement() - fireTableDataChanged()");
			fireTableDataChanged();
		}
	}
	
	public void removeElement(Club _club) {
		// XXX amaury - Delete print
		System.out.println("ClubTableModel.removeElement() - Club="+_club);
		final int index = this.clubList.indexOf(_club);
		// XXX amaury - Delete print
		System.out.println("INDEX = " + index);
		if(index != -1){
			this.clubList.remove(index);
			// XXX amaury - Delete print
			System.out.println("ClubTableModel.removeElement() - fireTableDataChanged()");
			fireTableDataChanged();
		}
	}
	
	@Override
	public int getColumnCount() {
		return this.columnList.length;
	}

	@Override
	public int getRowCount() {
		return this.clubList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		if(row >= 0 && row < getRowCount() && column >=0 && column < getColumnCount()){
			final Club club = this.clubList.get(row);
			try {
				// TODO AP - Stocker la méthode dans le tableau
				Method method = Club.class.getMethod(this.columnList[column][COLUMN_METHOD]);
				return method.invoke(club);
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
	
	public Club getItem(final int index){
		if(index >=0 && index < getRowCount()){
			return this.clubList.get(index);
		}
		return null;
	}

}
