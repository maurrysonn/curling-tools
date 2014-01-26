package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentListPanel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public class TournamentTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 7526838185508691740L;

	private List<Tournament> tournamentList;
	private String[][] columnList = {{"Name", "getName"}, {"Date", "getVerboseStartDate"}, {"Club", "getClub"}};

	private static int COLUMN_HEADER = 0;
	private static int COLUMN_METHOD = 1;

	public TournamentTableModel() {
		this(null);
	}
	
	public TournamentTableModel(final List<Tournament> _tournamentList) {
		// Initialize data
		if(_tournamentList != null){
			tournamentList = _tournamentList;
		}else{
			tournamentList = new ArrayList<Tournament>();
		}
	}
	
	public void addElement(final Tournament _tournament){
		// XXX AP - Delete print
		System.out.println("TournamentTableModel.addElement() - Tournament=" + _tournament);
		tournamentList.add(_tournament);
		fireTableDataChanged();
	}

	public void addElements(final List<Tournament> _tournamentList){
		tournamentList.addAll(_tournamentList);
	}
	
	public void clearElements(){
		tournamentList.clear();
	}
	
	public void updateElement(final Tournament _tournament){
		// XXX AP - Delete print
		System.out.println("TournamentTableModel.updateElement() - Tournament=" + _tournament);
		final int index = tournamentList.indexOf(_tournament);
		// XXX amaury - Delete print
		System.out.println("INDEX = " + index);
		if(index != -1){
			tournamentList.remove(index);
			tournamentList.add(index, _tournament);
			fireTableDataChanged();
		}
	}
	
	public void removeElement(Tournament _tournament) {
		// XXX AP - Delete print
		System.out.println("TournamentTableModel.removeElement() - Tournament=" + _tournament);
		final int index = tournamentList.indexOf(_tournament);
		// XXX amaury - Delete print
		System.out.println("INDEX = " + index);
		if(index != -1){
			tournamentList.remove(index);
			fireTableDataChanged();
		}
	}
	
	@Override
	public int getColumnCount() {
		return columnList.length;
	}

	@Override
	public int getRowCount() {
		return tournamentList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		if(row >= 0 && row < getRowCount() && column >=0 && column < getColumnCount()){
			final Tournament tournament = tournamentList.get(row);
			try {
				Method method = Tournament.class.getMethod(this.columnList[column][COLUMN_METHOD]);
				return method.invoke(tournament);
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
	
	public Tournament getItem(final int index){
		if(index >=0 && index < getRowCount()){
			return tournamentList.get(index);
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
