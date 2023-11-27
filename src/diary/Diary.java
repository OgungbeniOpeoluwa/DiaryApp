package diary;

import diary.exception.DiaryLockException;
import diary.exception.InvalidIdException;
import diary.exception.InvalidPinException;

import java.util.ArrayList;

public class Diary {
    private final String username;
    private  boolean isLocked = true;
    private String password;
    private ArrayList<Entry> entries =  new ArrayList<>();

    private int numbersOfEntry;
    public Diary(String username, String password) {
       this.username = username;
        this.password = password;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void unLocked(String password) {
        validate(password);
            this.isLocked = false;
    }

    public void lockDiary() {
        this.isLocked = true;
    }
    private void validate(String password) {
        if(!(password.equals(this.password)))throw new InvalidPinException("InValid Pin");
    }

    public void createEntry(String title, String body) {
        if(isLocked) throw new DiaryLockException("Diary is Locked ");
        else{
            numbersOfEntry++;
            int idNumber = generateIdNumber();
            Entry entry = new Entry(idNumber, title, body);
            entries.add(entry);
        }
    }
    public int getNumbersOfEntry(){
        return numbersOfEntry;
    }

    private int generateIdNumber() {
        return numbersOfEntry;
    }

    public Entry findEntry(int id) {
        for(Entry entry:entries ){
            if(entry.getId() == id){
                return entry;
            }
        }
        throw new InvalidIdException("Entry Id does Not exist") ;
    }

    public void deleteEntry(int id) {
        Entry entry = findEntry(id);
        entries.remove(entry);
        numbersOfEntry--;
    }

    public String getUsername() {
        return username;
    }

    public void updateEntry(int id, String title, String body) {
        Entry entry = findEntry(id);
        entry.setTitle(title);
        String update  = entry.getBody() + "\n" + body;
        entry.setBody(update);

    }

    public String getPassword() {
        return  password;
    }
}
