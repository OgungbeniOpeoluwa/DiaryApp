package diary;

import diary.exception.DiaryLockException;
import diary.exception.InvalidPinException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryTest {
    Diary diary;
    @BeforeEach
    public void setUpDiary(){
        diary = new Diary("username","password");
    }
    @Test
    public void testThatDiaryIsLocked(){
        assertTrue(diary.isLocked());
    }
    @Test
    public void testThatDiaryIsUnLocked(){
        diary.unLocked("password");
        assertFalse(diary.isLocked());

    }
    @Test
    public void testDiaryCanBeLocked(){
        diary.unLocked("password");
        diary.lockDiary();
        assertTrue(diary.isLocked());
    }
    @Test
    public void testThatDiaryThrowsAnExceptionIfPasswordIsWrong(){
       assertThrows(InvalidPinException.class, () -> diary.unLocked("ope"));
    }
    @Test
    public void testThat1EntryIsCreated(){
        diary.unLocked("password");
        diary.createEntry("title","body");
        assertEquals(1,diary.getNumbersOfEntry());
    }
    @Test
    public void testThatEntriesCantBeCreatedWithOutUnlocking(){
       assertThrows(DiaryLockException.class,() -> diary.createEntry("title","body"));
    }

    @Test
    public void testThatWeCanFindAnEntryWithAnId(){
        diary.unLocked("password");
        diary.createEntry("title","body");
        Entry entry = diary.findEntry(1);
        assertEquals(1,entry.getId());
    }
    @Test
    public void testThatWeCanFind2EntryCreatedWithISId(){
        diary.unLocked("password");
        diary.createEntry("title","body");
        diary.createEntry("title","body");
        Entry entry = diary.findEntry(1);
        Entry entry2 = diary.findEntry(2);
        assertEquals(1,entry.getId());
        assertEquals(2,entry2.getId());
    }
    @Test
    public void testThatAnEntryCanBeDeleted(){
        diary.unLocked("password");
        diary.createEntry("title","body");
        diary.deleteEntry(1);
        assertEquals(0,diary.getNumbersOfEntry());
    }
    @Test
    public void testThatFirstEntryIsDeletedFromTwoEntriesAndTheSecondEntryIdStillRemain(){
        diary.unLocked("password");
        diary.createEntry("title","body");
        diary.createEntry("bolu","body");
        Entry entry2 = diary.findEntry(2);
        diary.deleteEntry(1);
        assertEquals(1,diary.getNumbersOfEntry());
        assertEquals("bolu",entry2.getTitle());
    }
    @Test
    public void testThatDiaryCanBeUpdate(){
        diary.unLocked("password");
        diary.createEntry("title","Hello guys");
        Entry entry = diary.findEntry(1);
        assertEquals("title",entry.getTitle());
        assertEquals("Hello guys",entry.getBody());
        diary.updateEntry(1,"HeartBreak","i was heartbroken");
        assertEquals("HeartBreak",entry.getTitle());
        assertEquals("Hello guys i was heartbroken",entry.getBody());
    }


}