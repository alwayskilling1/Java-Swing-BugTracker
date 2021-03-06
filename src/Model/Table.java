package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Misc.Logger;

public class Table implements Serializable {

    private String name; // name of the table itself
    private ArrayList<ArrayList<String>> table; // table itself (Columns - Rows)
    private ArrayList<String> tableHeaders; // stores the name of the columns

    public Table() {
        table = new ArrayList<ArrayList<String>>();
        tableHeaders = new ArrayList<String>();

        name = "Unnamed Table";
    }

    public Table(String name) {
        this();
        this.name = name;

        Logger.Log("Table " + this.name + " has been created with hashcode: " + hashCode());
    }

    public Table(String name, ArrayList<String> columns) {
        this();

        if (columns != null)
            for (String string : columns) {
                tableHeaders.add(string);
            }
        this.name = name;

        Logger.Log("Table " + this.name + "has been created with hashcode: " + hashCode());
    }

    public Table(String name, String[] columns) {
        this();

        if (columns != null)
            for (String string : columns) {
                tableHeaders.add(string);
            }
        this.name = name;

        Logger.Log("Table " + this.name + "has been created with hashcode: " + hashCode());
    }

    public void addColumn(String header) throws Exception { // adds a column to the table

        for (String name : tableHeaders) {
            if (name == null)
                continue;
            if (name.equals(header)) {
                Logger.Error("Name already exists in " + getClass().getSimpleName() + " " + name + " @" + hashCode());
                throw new Exception("Name already exists");
            }
        }

        tableHeaders.add(header);
        table.add(new ArrayList<String>());

        Logger.Log("Column added to " + name + " {" + this.getClass().getSimpleName() + " @" + hashCode() + "}");
    }

    public void addRow(ArrayList<String> data) { // adds a row to the table

        for (ArrayList<String> column : table) {
            if (!(data.size() == 0))
                column.add(data.remove(0));
            else
                column.add("null");
        }

        Logger.Log("Row added to " + name + " {" + this.getClass().getSimpleName() + " @" + hashCode() + "}");
    }

    public void display() {
        System.out.println(toString());
    }

    public String toString() {

        String string = "\n" + getClass().getSimpleName() + " " + name + ":\n";

        for (int i = 0; i < tableHeaders.size(); i++) {

            string += i == 0 ? "|" : "";
            string += "\t" + (tableHeaders.get(i) == null ? "-" : tableHeaders.get(i)) + "\t | ";

        }
        string += "\n";

        for (int i = 0; i < rowCount(); i++) {

            for (int j = 0; j < table.size(); j++) {

                string += j == 0 ? "|" : "";
                string += "\t" + (table.get(j).get(i) == null ? "-" : table.get(j).get(i)) + "\t | ";

            }
            string += "\n";
        }

        return string;
    }

    private int rowCount() { // Just to ease readability

        return table.get(0).size();

    }

    public ArrayList<String> getRow(int index) throws Exception { // returns the requested row

        if (rowCount() < index) {

            Logger.Error("Row does not exist in " + getClass().getSimpleName() + " " + name + " @" + hashCode());
            throw new Exception("Row does not exist");

        }

        ArrayList<String> row = new ArrayList<String>();

        for (ArrayList<String> column : table) {

            row.add(column.get(index));

        }
        return row;
    }

    public ArrayList<String> getColumn(int index) throws Exception { // simple

        if (index > tableHeaders.size()) {
            Logger.Error("Colummn " + index + " does not exist in table" + name + " @" + hashCode());
            throw new Exception();
        }

        return table.get(index);
    }

    public void changeHeader(String header, String newHeader) throws Exception {

        if (!tableHeaders.contains(header)) {

            Logger.Error("Header {" + header + "} does not exist in table: " + name);
            throw new Exception("Header {" + header + "} does not exist in table: " + name);

        }

        if (tableHeaders.contains(newHeader)) {
            int i = tableHeaders.indexOf(header);
            tableHeaders.set(tableHeaders.indexOf(newHeader), header);
            tableHeaders.set(i, newHeader);
        } else
            tableHeaders.set(tableHeaders.indexOf(header), newHeader);

        Logger.Warn(
                "Header " + header + " in table " + name + " @" + hashCode() + " has been changed to: " + newHeader);
    }

    public void changeValue(int row, int Column, String newValue) throws Exception { // check build

        if (row > rowCount() || Column > tableHeaders.size()) {

            Logger.Error("Invalid index in table: " + name + " @" + hashCode());
            throw new Exception("Invalid index in table: " + name + " @" + hashCode());

        }

        table.get(Column).set(row, newValue);
        Logger.Warn("Value of index : [" + Column + ',' + row + "] in table " + name + " @" + hashCode()
                + " has been changed to: " + newValue);
    }

    public void changeValue(int row, String Column, String newValue) throws Exception { // check build

        if (row > rowCount() || table.indexOf(Column) > tableHeaders.size()) {

            Logger.Error("Invalid index in table: " + name + " @" + hashCode());
            throw new Exception("Invalid index in table: " + name + " @" + hashCode());

        }

        table.get(table.indexOf(Column)).set(row, newValue);
        Logger.Warn("Value of index : [" + Column + ',' + row + "] in table " + name + " @" + hashCode()
                + " has been changed to: " + newValue);
    }

    public boolean clearTable() { // erases all data on the table

        table = new ArrayList<ArrayList<String>>();
        tableHeaders = new ArrayList<String>();

        Logger.Warn("Table: " + name + " @" + hashCode() + " has been cleared");
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
        Logger.Log("Tabel " + this.name + " has been renamed to: " + name == null ? "" : name);

    }

    public ArrayList<String> getTableHeaders() {
        return tableHeaders;
    }

}