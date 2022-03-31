package database;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Scanner;

public class Schema {
    LinkedHashSet<Table> tables;
    String name;
    Scanner sc;

    public Schema() {
        tables = new LinkedHashSet<>();
        name = "";
        sc = new Scanner(System.in);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schema schema = (Schema) o;
        return tables.equals(schema.tables) && name.equals(schema.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tables, name);
    }

    public void setSchemaName(String name) {
        this.name = name;
    }

    public String getSchemaName() {
        return name;
    }

    public void printSchema() {
        System.out.println("Schema name : " + getSchemaName());
        if (tables.size() == 0) {
            System.out.println(getSchemaName() + " has no tables");
            return;
        }
        for (Table temp : tables) {
            System.out.println(temp);
        }
    }

    public void createSchema() {
        System.out.print("Enter the schema name  : ");
        setSchemaName(sc.next());
        System.out.println("Schema created");
    }

    public boolean removeTable(int index) {
        if (index < 0 || index >= tables.size()) {
            return false;
        }
        //Coverting LinkedHashSet to array to help index the element
        Table[] LHSArray = new Table[tables.size()];
        LHSArray = tables.toArray(LHSArray);
        Table toBeRemoved = LHSArray[index];//Aquiring the element
        tables.remove(toBeRemoved);
        return true;
    }
}