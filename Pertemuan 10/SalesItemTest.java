import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SalesItemTest {

    @Test
    public void testConstructorAndGetters() {
        SalesItem instrument = new SalesItem("Electric Guitar", 85000);

        assertEquals("Electric Guitar", instrument.getName());
        assertEquals(85000, instrument.getPrice());
        assertEquals(0, instrument.getNumberOfComments());
    }

    @Test
    public void testAddCommentValid() {
        SalesItem gear = new SalesItem("Studio Monitor", 25000);

        assertTrue(gear.addComment("Sultan", "Amazing frequency response!", 5));
        assertEquals(1, gear.getNumberOfComments());
    }

    @Test
    public void testAddCommentDuplicateAuthor() {
        SalesItem gear = new SalesItem("Audio Interface", 19900);

        assertTrue(gear.addComment("Barizy", "Low latency, great preamps.", 5));
        assertFalse(gear.addComment("Barizy", "Forgot to say it looks cool too.", 4)); 
    }

    @Test
    public void testAddCommentInvalidRating() {
        SalesItem gear = new SalesItem("Condenser Mic", 30000);

        assertFalse(gear.addComment("UserX", "Broken box", -2)); 
        assertFalse(gear.addComment("UserY", "Best mic ever!", 10)); 
    }

    @Test
    public void testRemoveComment() {
        SalesItem gear = new SalesItem("Guitar Amp", 45000);

        gear.addComment("Abdullah", "Too loud for my apartment.", 3);
        gear.addComment("Sultan", "Pure tube tone, love it!", 5);

        assertEquals(2, gear.getNumberOfComments());
        gear.removeComment(0);
        assertEquals(1, gear.getNumberOfComments());
    }

    @Test
    public void testUpvoteDownvoteComment() {
        SalesItem gear = new SalesItem("Digital Piano", 120000);

        gear.addComment("Barizy", "Key action feels very realistic.", 5);
        gear.addComment("Guest", "Too heavy to move around.", 2);

        gear.upvoteComment(0);
        gear.upvoteComment(0);
        gear.downvoteComment(1);

        Comment topComment = gear.findMostHelpfulComment();
        assertEquals("Barizy", topComment.getAuthor());
        assertEquals(2, topComment.getVoteCount());

        Comment downvoted = gear.findCommentByAuthor("Guest");
        assertEquals(-1, downvoted.getVoteCount());
    }

    @Test
    public void testFindMostHelpfulComment() {
        SalesItem gear = new SalesItem("Drum Kit", 95000);

        gear.addComment("User1", "Sturdy hardware.", 4);
        gear.addComment("User2", "Cymbals sound a bit cheap.", 3);
        gear.upvoteComment(0);

        Comment mostHelpful = gear.findMostHelpfulComment();
        assertEquals("User1", mostHelpful.getAuthor());
    }

    @Test
    public void testShowInfo() {
        SalesItem gear = new SalesItem("Analog Synth", 59900);

        gear.addComment("Sultan", "The oscillators are very warm.", 5);
        gear.addComment("Barizy", "Steep learning curve but worth it.", 4);

        gear.showInfo();
    }
}