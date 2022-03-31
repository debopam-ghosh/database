package database;

import java.util.ArrayList;
public class Row
{
    ArrayList<Object> elements;
    public Row(int n)
    {
        elements= new ArrayList<>(n);
    }
    public void addElementToRow(Object element)
    {
        elements.add(element);
    }
    public String toString(){
        return elements.toString();
    }
}