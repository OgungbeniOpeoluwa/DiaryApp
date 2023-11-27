package diary;

import diary.exception.InvalidPinException;
import diary.exception.InvalidUserNameException;

import java.util.ArrayList;

public class Diaries {
    private int numberOfDiary;
    ArrayList <Diary> diaries = new ArrayList<>();

    public void add(String userName, String password) {
        numberOfDiary++;
        Diary diary = new Diary(userName,password);
        diaries.add(diary);
    }

    public int getNumberOfDiaries() {
        return numberOfDiary;
    }

    public  Diary findDiary(String userName) {
        for(Diary diary:diaries){
            if(diary.getUsername().equals(userName)){
                return diary;
            }
        }
        throw new InvalidUserNameException("userName does not exist");
    }

    public void delete(String username, String password) {
        Diary diary = findDiary(username);
        if(diary.getPassword().equals(password)) {
            diaries.remove(diary);
            numberOfDiary--;
        }
        else{
            throw new InvalidPinException("INVALID PASSWORD");
        }

    }
}
