import org.bcit.com2522.project.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private Menu menu;

    /**
     * Instantiates a new menu before each test
     */
    @BeforeEach
    public void setUp() {
        menu = new Menu();
    }

    /**
     * Checks if menu is initialized with expected value
     */
    @Test
    public void testClickableInitialValue() {
        assertFalse(menu.getClickable());
    }

    /**
     * Tests the loadMenu function
     */
    @Test
    public void testLoadMenu() {
       menu.loadMenu();

       assertTrue(menu.getClickable());
    }

    /**
     * Tests the hideMenu function
     */
    @Test
    public void testHideMenu() {
        menu.hideMenu();

        assertFalse(menu.getClickable());

    }



}