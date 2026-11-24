import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Contact {
    private final String name;
    private final String phone;

    Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    String getName() {
        return name;
    }

    String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone;
    }
}

public class PhoneDirectory {
    private final ArrayList<Contact> contacts = new ArrayList<>();
    private final HashMap<String, Contact> contactsByPhone = new HashMap<>();

    private void addContact(String name, String phone) {
        if (contactsByPhone.containsKey(phone)) {
            System.out.println("That phone number is already in the directory.");
            return;
        }

        Contact contact = new Contact(name, phone);
        contacts.add(contact);
        contactsByPhone.put(phone, contact);
        System.out.println("Contact added.");
    }

    // Searches names in the ArrayList; partial matches are allowed.
    private List<Contact> searchByName(String query) {
        List<Contact> matches = new ArrayList<>();

        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase())) {
                matches.add(contact);
            }
        }

        return matches;
    }

    // Uses the HashMap for a quick exact phone-number lookup.
    private Contact searchByPhone(String phone) {
        return contactsByPhone.get(phone);
    }

    private void showAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("The directory is empty.");
            return;
        }

        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public static void main(String[] args) {
        PhoneDirectory directory = new PhoneDirectory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Phone Directory ---");
            System.out.println("1. Add contact");
            System.out.println("2. Search by name");
            System.out.println("3. Search by phone number");
            System.out.println("4. Show all contacts");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine().trim();

                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine().trim();

                    directory.addContact(name, phone);
                    break;

                case "2":
                    System.out.print("Enter a name to search: ");
                    String query = scanner.nextLine().trim();
                    List<Contact> matches = directory.searchByName(query);

                    if (matches.isEmpty()) {
                        System.out.println("No matching contacts found.");
                    } else {
                        for (Contact contact : matches) {
                            System.out.println(contact);
                        }
                    }
                    break;

                case "3":
                    System.out.print("Enter phone number: ");
                    String searchPhone = scanner.nextLine().trim();
                    Contact found = directory.searchByPhone(searchPhone);

                    if (found == null) {
                        System.out.println("Contact not found.");
                    } else {
                        System.out.println(found);
                    }
                    break;

                case "4":
                    directory.showAllContacts();
                    break;

                case "5":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Choose 1–5.");
            }
        }
    }
}
