
import org.bcit.com2522.project.QuoteGenerator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuoteGeneratorTest {

    /**
     * Tests the getQuote method
     */
    @Test
    void testGetQuote() {
        // Verify that all the quotes can be retrieved
        String[] quotes = {
                "This game was pretty hard to make",
                "Paul is very cool!",
                "Do you even read these?",
                "We've been trying to reach \nyou about your car's extended warranty",
                "I used to be an adventurer \nlike you, until I took CST",
                "\\(o_o)/",
                "We've been trying to reach \nyou about your car's extended warranty",
                "God is dead and we have \nkilled him",
                "The Mitochondria is the \npowerhouse of the cell",
                "Are you finished with \nthose errands?",
                "I think you left your \noven on",
                "never gonna give you up\nnever gonna let you down",
                "Life is short, don't \nwaste it playing this game",
                "(a+b)^2 = a^2 + b^2"
        };

        for (String quote : quotes) {
            assertTrue(quoteExists(quote), "Quote not found: " + quote);
        }
    }

    /**
     * Helper method to make getQuote testable
     * @param quote
     * @return
     */
    private boolean quoteExists(String quote) {
        for (int i = 0; i < 100; i++) {
            if (QuoteGenerator.getQuote().equals(quote)) {
                return true;
            }
        }
        return false;
    }

}