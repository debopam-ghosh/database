package database;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Scanner;

public class Table {
    Scanner sc = new Scanner(System.in);
    String name;
    Column col;
    int n;//no. of columns in table
    LinkedHashSet<Row> rows;

    public Table() {
        name = "";
        col = null;
        rows = new LinkedHashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return name.equals(table.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void createTable() {
        int i, j, n_rows;
        System.out.print("Enter the table name : ");
        name = sc.next();
        System.out.println("How many columns do you want in your table?");
        n = sc.nextInt();
        col = new Column();
        for (i = 0; i < n; i++) {
            col.addColumn();
        }

        System.out.println("How many rows do you want in your table?");
        n_rows = sc.nextInt();
        System.out.println("\nThe table format  is " + this);
        for (i = 0; i < n_rows; i++) {
            Row row = new Row(n);
            System.out.println("\nEnter for row no. " + (i + 1));
            for (j = 0; j < n; j++) {
                Object a = null;
                Scanner scan = new Scanner(System.in);
                String type = col.attr_type.get(j).getClass().getName();
                System.out.print("Add element : ");

                switch (type) {
                    case "java.lang.Integer":
                        if (scan.hasNextInt())
                            a = scan.nextInt();
                        else {
                            System.out.print("Wrong type. Re-enter!!\n");
                            j--;
                            continue;
                        }
                        break;
                    case "java.lang.Double":
                        if (scan.hasNextDouble())
                            a = scan.nextDouble();
                        else {
                            System.out.print("Wrong type. Re-enter!!");
                            j--;
                            continue;
                        }
                        break;
                    case "java.lang.String":
                        a = scan.nextLine();
                        break;
                }
                row.addElementToRow(a);
            }
            rows.add(row);
        }
    }

    public String toString() {
        return name + col.toString();
    }

    public void displayTable() {
        System.out.println("Table name : " + name);
        if (rows.size() == 0) {
            System.out.println(name + " has no rows");
            return;
        }
        Utility.printArray(col.attr_name.toArray());
        for (Row temp : rows)
            Utility.printArray(temp.elements.toArray());

    }

    public void addRow() {
        Row row = new Row(n);

        System.out.println("\nEnter elements for this row  ");
        for (int j = 0; j < n; j++) {
            Object a = null;
            Scanner scan = new Scanner(System.in);
            String type = col.attr_type.get(j).getClass().getName();
            System.out.print("Add element : ");

            switch (type) {
                case "java.lang.Integer":
                    if (scan.hasNextInt())
                        a = scan.nextInt();
                    else {
                        System.out.print("Wrong type. Re-enter!!\n");
                        j--;
                        continue;
                    }
                    break;
                case "java.lang.Double":
                    if (scan.hasNextDouble())
                        a = scan.nextDouble();
                    else {
                        System.out.print("Wrong type. Re-enter!!");
                        j--;
                        continue;
                    }
                    break;
                case "java.lang.String":
                    a = scan.nextLine();
                    break;
            }
            row.addElementToRow(a);
        }
        rows.add(row);
    }

    public boolean removeRow(int index) {
        if (index < 0 || index >= rows.size()) {
            return false;
        }
        //Converting LinkedHashSet to array to help index the element
        Row[] LHSArray = new Row[rows.size()];
        LHSArray = rows.toArray(LHSArray);
        Row toBeRemoved = LHSArray[index];//Acquiring the element
        rows.remove(toBeRemoved);
        return true;
    }
}