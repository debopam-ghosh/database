package database;

import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    static ArrayList<Schema> schemas = new ArrayList<>();

    public static void shutdown() {
        System.out.println("----Database Shutdown----");
        System.exit(0);
    }

    public static String displayMenu(int i) {
        StringBuilder grandMenu = new StringBuilder();
        grandMenu.append("\n1. Enlist all schema\n")
                .append("2. Create schema\n")
                .append("3. Delete Schema\n")
                .append("4. Shutdown database\n");

        StringBuilder tableMenu = new StringBuilder();
        tableMenu.append("\n1. Enlist all tables\n")
                .append("2. Create table\n")
                .append("3. Delete table\n")
                .append("4. Shutdown database\n")
                .append("Press any other key to go back to previous menu...\n");

        StringBuilder tableOptions = new StringBuilder();
        tableOptions.append("\n1. Display table\n")
                .append("2. Delete table\n")
                .append("3. Add Row\n")
                .append("4. Delete Row\n")
                .append("5. Shutdown database\n")
                .append("Press any other key to go back to previous menu...\n");
        StringBuilder[] list = {grandMenu, tableMenu, tableOptions};
        return list[i].append("Enter your choice : ").toString();
    }

    public static void main(String args[]) {
        System.out.println("-----Starting Database-----");
        int choice;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(displayMenu(0));
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    if (schemas.size() == 0) {
                        System.out.println("There are no schemas in this database.");
                        break;
                    }
                    for (Schema temp : schemas) {
                        System.out.print((schemas.indexOf(temp) + 1) + ". --> ");
                        temp.printSchema();
                    }
                    System.out.print("Enter the schema no. to interact with it : ");
                    Scanner scan = new Scanner(System.in);
                    int c = scan.nextInt();
                    c--;
                    if (c < 0 || c >= schemas.size()) {
                        System.out.println("Invalid option");
                        break;
                    }
                    boolean flag1 = true;
                    while (flag1) {
                        System.out.print(displayMenu(1));
                        int tablechoice;
                        tablechoice = sc.nextInt();


                        switch (tablechoice) {
                            case 1:
                                int tableno = 1;
                                for (Table temp : schemas.get(c).tables) {
                                    System.out.print(tableno++ + ". --> ");
                                    temp.displayTable();
                                }
                                if (tableno == 1) {
                                    System.out.println("This schema has no tables.");
                                    flag1 = false;
                                    break;
                                }

                                System.out.print("Enter the table no. to interact with it : ");
                                int d = sc.nextInt();
                                d--;
                                if (d < 0 || d >= schemas.get(c).tables.size()) {
                                    System.out.println("Invalid option");
                                    break;
                                }
                                boolean flag2 = true;
                                while (flag2) {
                                    System.out.print(displayMenu(2));
                                    Table[] array = new Table[schemas.get(c).tables.size()];//To help index tables
                                    array = schemas.get(c).tables.toArray(array);
                                    switch (sc.nextInt()) {//Menu to deal with individual tables
                                        case 1:
                                            array[d].displayTable();
                                            break;
                                        case 2:
                                            if (schemas.get(c).removeTable(d) == false)
                                                System.out.println("Invalid index");
                                            break;
                                        case 3:
                                            array[d].addRow();
                                            break;
                                        case 4:
                                            System.out.print("Enter the row no : ");
                                            if (array[d].removeRow(sc.nextInt() - 1) == false)
                                                System.out.println("Invalid index");
                                            break;
                                        case 5:
                                            shutdown();
                                        default:
                                            flag2 = false;
                                    }

                                }
                                break;
                            case 2:
                                Table tempTable = new Table();
                                tempTable.createTable();
                                schemas.get(c).tables.add(tempTable);
                                break;
                            case 3:
                                System.out.print("Enter the table no. : ");
                                schemas.get(c).removeTable(sc.nextInt() - 1);
                                break;
                            case 4:
                                shutdown();
                            default:
                                flag1 = false;
                        }
                    }
                    break;
                case 2:
                    Schema obj = new Schema();
                    obj.createSchema();
                    schemas.add(obj);
                    break;
                case 3:
                    System.out.print("Enter the schema no. : ");
                    int num = sc.nextInt();
                    num--;
                    if (num < 0 || num >= schemas.size()) {
                        System.out.println("Invalid choice");
                        break;
                    }
                    schemas.remove(num);
                    System.out.println("Schema removed.");
                    break;
                case 4:
                    shutdown();
            }
        }
    }
}
