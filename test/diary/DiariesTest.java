package diary;

import diary.exception.InvalidPinException;
import diary.exception.InvalidUserNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiariesTest {
    Diaries diaries;
    @BeforeEach
    public void setUpDiary(){
        diaries = new Diaries();
    }
    @Test
    public void testThatDiaryCanBeAdded(){
        diaries.add("ope","1998");
        assertEquals(1,diaries.getNumberOfDiaries());

    }

    @Test
    public void testThatTwoDiaryCanBeAddedAndFindOne(){
        diaries.add("ope","1998");
        diaries.add("delighted","1999");
        Diary diary = diaries.findDiary("ope");
        assertEquals("ope",diary.getUsername());

    }
    @Test
    public void testICantFindDiaryWithWrongUserName(){
        diaries.add("ope","1998");
        assertThrows(InvalidUserNameException.class,() -> diaries.findDiary("tolu"));

    }
    @Test
    public void testThatDiaryIsDeletedFromDiariesList() {
        diaries.add("ope", "1998");
        diaries.add("delighted", "1999");
        diaries.delete("ope", "1998");
        assertEquals(1, diaries.getNumberOfDiaries());
    }
    @Test
    public void enteringWrongPasswordToDeleteDiaryThrowsExceptions() {
        diaries.add("ope", "1998");
        assertEquals(1, diaries.getNumberOfDiaries());
        assertThrows(InvalidPinException.class,()->diaries.delete("ope", "1999"));
    }


}