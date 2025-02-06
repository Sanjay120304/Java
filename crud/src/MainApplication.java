import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Keep a single scanner for all methods

        while (true) {
            System.out.println("\nCRUD Operations Menu:");
            System.out.println("1. Create Record");
            System.out.println("2. Read Records");
            System.out.println("3. Update Record");
            System.out.println("4. Delete Record");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("No input found. Please enter a valid choice.");
                continue;
            }

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    CreateRecord.createRecord(scanner);  
                    break;
                case 2:
                    ReadRecords.readRecords();
                    break;
                case 3:
                    UpdateRecord.updateRecord(scanner);  
                    break;
                case 4:
                    DeleteRecord.deleteRecord(scanner);  
                    break;
                case 5:
                    System.out.println("Exiting the application.");
                    scanner.close(); 
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
