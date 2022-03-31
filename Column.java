package database;

import java.util.ArrayList;
import java.util.Scanner;

public class Column {
    ArrayList<String> attr_name = new ArrayList<>();
    ArrayList<Object> attr_type = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    //These variables help to contain the types of each column
    final int default_integer = 0;
    final double default_double = 0.0;
    final String default_String = "";

    public void addColumn() {
        System.out.print("\nWhat is this column's name : ");
        attr_name.add(sc.next());
        System.out.print("What is this column's type : ");
        String type = sc.next().toLowerCase();
        switch (type) {
            case "int":
                attr_type.add(default_integer);
                break;
            case "double":
                attr_type.add(default_double);
                break;
            case "varchar":
                attr_type.add(default_String);
                break;
            default:
                System.out.println("Invalid type given!!!\nPlease Re-enter ");
                addColumn();
        }
    }

    public String toString() {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < attr_name.size(); i++) {
            temp.add(attr_name.get(i) + " " + typeToString(attr_type.get(i)));
        }
        return temp.toString();

    }

    public String typeToString(Object type) {
        Object integer = 0;
        Object doubletype = 0.0;
        if (type.toString().equals(integer.toString()))
            return "int";
        else if (type.toString().equals(doubletype.toString()))
            return "double";
        else
            return "varchar";
    }
}