package diary;


import diary.exception.InvalidIdException;
import java.util.Scanner;

public class diaryMain {
    public static void main(String[] args) {
        display();
    }
    
    
    static Diary diary;
    public static void display(){
        print("""
                WELCOME TO YOUR MY SECRETE DIARY, KINDLY SETUP YOUR ACCOUNT!!!!!
                =============================================================""");
        createDiary();

    }
    private static void displayMethodInDiary(){
        System.out.println("""
                ==============================
                 1. unlock Diary
                 2. lock Diary
                 3. Find Entry By Id
                 4. Add Entry
                 5  Delete Entry 
                 6. Update Entry 
                 ==============================""");
    }

    public static void mainMenu(){
        displayMethodInDiary();
        String inputs = input("Enter a number");
        print("");
        switch(inputs){
            case "1" -> unlockDiary();
            case "2" -> lockDiary();
            case "3" -> findEntryById();
            case "4" -> createEntry();
            case "5" -> deleteEntry();
            case "6" -> updateEntry();
            default -> exit();
        }

    }
    private static  void exit(){
        print("You have successfully logged out!!!");
        System.exit(0);
    }

    private static void updateEntry() {
        int id = 0;
        id = getEntryId(id);
        String title = input("Enter ya Title : ");
        String body = input("Enter ya body update : ");
        diary.updateEntry(id,title,body);
        Entry entry = diary.findEntry(id);
        print(entry.toString());
        print("");
        mainMenu();
    }

    private static void deleteEntry() {
        int id = 0;
        id = getEntryId(id);
        try {
            diary.deleteEntry(id);
            print("");
            print("Entry has been deleted successfully!!");
        }catch (Exception ex){
            print(ex.getMessage());
            createEntry();
        }

    }

    private static void findEntryById() {
        int id = 0;
        id = getEntryId(id);
        try {
            print(diary.findEntry(id).toString());
            print("");
            mainMenu();

        }catch(InvalidIdException ex){
            print(ex.getMessage());
            print("Id does not Exit,Create Entry");
            createEntry();
        }

    }

    private static void createEntry() {
        String title = input("Enter Title: ");
        String body = input("Enter Entry: ");
        try {
            diary.createEntry(title, body);
            print("Entry Saved");
            print("");
            mainMenu();
        }catch(Exception ex){
            print(ex.getMessage());
            unlockDiary();
        }
    }

    private static void lockDiary() {
        diary.lockDiary();
        print("Diary Has successfully been Locked");
        print("");
        mainMenu();
    }

    private static void unlockDiary() {
        String password = input("Enter your password: ");
       try{
            diary.unLocked(password);
           System.out.println("Diary has been successfully unlocked!!");
           print("");
           mainMenu();

        }
       catch(Exception ex){
           print(ex.getMessage());
           mainMenu();

       }


    }



    private static void createDiary() {
        String userName = input("Enter your username: ");
        String pin = input("Enter your password : ");
        diary = new Diary(userName,pin);
        System.out.println("Diary successfully created!!");
        print("");
        mainMenu();


    }



    private static  String input(String message){
        Scanner scanner = new Scanner(System.in);
        print(message);
        return scanner.nextLine();
    }
    private static void  print(String message){
       System.out.println(message);
    }
    private static int getEntryId(int id) {
        try {
            id = Integer.parseInt(input("Enter entry number: "));
        } catch (Exception ex) {
            print("Wrong id,input words instead of number!!!");
            mainMenu();
        }
        return id;
    }



}
