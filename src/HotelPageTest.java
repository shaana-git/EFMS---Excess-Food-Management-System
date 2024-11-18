package src.src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HotelPageTest {
    private HotelPage hotelPage;

    @BeforeEach
    void setUp() {
        hotelPage = new HotelPage(); // Initialize the HotelPage before each test
    }

    @Test
    void testInsertFoodWithInvalidQuantityAndCost() throws Exception {
        // Insert food item with invalid quantity and cost (negative values)
        Method insertMethod = HotelPage.class.getDeclaredMethod("insertFoodToDatabase", String.class, int.class, double.class);
        insertMethod.setAccessible(true);
        insertMethod.invoke(hotelPage, "Invalid Food", -5, -3.5); // Invalid quantity and cost

        // Load foods from the database
        Method loadMethod = HotelPage.class.getDeclaredMethod("loadFoodsFromDatabase");
        loadMethod.setAccessible(true);
        loadMethod.invoke(hotelPage);

        // Access addedFoods field and verify that invalid food item is not added
        Field addedFoodsField = HotelPage.class.getDeclaredField("addedFoods");
        addedFoodsField.setAccessible(true);
        List<String> addedFoods = (List<String>) addedFoodsField.get(hotelPage);

        // Assert that the invalid food item is not added
        boolean hasInvalidFood = addedFoods.stream().anyMatch(f -> f.contains("Invalid Food"));
        assertFalse(hasInvalidFood, "Food item with invalid quantity or cost should not be added to the list");
    }


    @Test
    void testInsertFoodWithEmptyName() throws Exception {
        // Insert food item with an empty name
        Method insertMethod = HotelPage.class.getDeclaredMethod("insertFoodToDatabase", String.class, int.class, double.class);
        insertMethod.setAccessible(true);
        insertMethod.invoke(hotelPage, "", 5, 10.0); // Invalid name

        // Load foods from the database
        Method loadMethod = HotelPage.class.getDeclaredMethod("loadFoodsFromDatabase");
        loadMethod.setAccessible(true);
        loadMethod.invoke(hotelPage);

        // Access addedFoods field and verify that the empty name food item is not added
        Field addedFoodsField = HotelPage.class.getDeclaredField("addedFoods");
        addedFoodsField.setAccessible(true);
        List<String> addedFoods = (List<String>) addedFoodsField.get(hotelPage);

        // Assert that no food with an empty name is added
        boolean hasEmptyNameFood = addedFoods.stream().anyMatch(f -> f.isEmpty());
        assertFalse(hasEmptyNameFood, "Food item with an empty name should not be added to the list");
    }
}
