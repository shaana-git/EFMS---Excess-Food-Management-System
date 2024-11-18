package src.src;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

class RegistrationPageTest {

    @Test
    void testValidatePassword_ValidPassword() {
        String validPassword = "Password123!";
        assertTrue(RegistrationPage.validatePassword(validPassword), "Password should be valid.");
    }

    @Test
    void testValidatePassword_ShortPassword() {
        String shortPassword = "Pass1!";
        assertFalse(RegistrationPage.validatePassword(shortPassword), "Password should be at least 10 characters.");
    }

    @Test
    void testValidatePassword_MissingUppercase() {
        String passwordWithoutUppercase = "password123!";
        assertFalse(RegistrationPage.validatePassword(passwordWithoutUppercase), "Password must contain at least one uppercase letter.");
    }

    @Test
    void testValidatePassword_MissingDigit() {
        // Test case for a password without a digit
        String passwordWithoutDigit = "Password!@";
        assertFalse(RegistrationPage.validatePassword(passwordWithoutDigit), "Password must contain at least one digit.");
    }

    @Test
    void testValidatePassword_MissingSpecialCharacter() {
        // Test case for a password without a special character
        String passwordWithoutSpecialChar = "Password123";
        assertFalse(RegistrationPage.validatePassword(passwordWithoutSpecialChar), "Password must contain at least one special character.");
    }

    @Test
    void testValidatePassword_EmptyPassword() {
        // Test case for an empty password
        String emptyPassword = "";
        assertFalse(RegistrationPage.validatePassword(emptyPassword), "Password should not be empty.");
    }

    @Test
    void testEmailValid_ValidEmail() {
        // Test case for a valid email format
        String validEmail = "user@example.com";
        assertTrue(RegistrationPage.emailValid(validEmail), "Email should be valid.");
    }

    @Test
    void testEmailValid_InvalidEmail() {
        // Test case for an invalid email format (missing '@')
        String invalidEmail = "userexample.com";
        assertFalse(RegistrationPage.emailValid(invalidEmail), "Email should be invalid.");
    }

    @Test
    void testEmailValid_EmptyEmail() {
        // Test case for an empty email field
        String emptyEmail = "";
        assertFalse(RegistrationPage.emailValid(emptyEmail), "Email should not be empty.");
    }


    @Test
    void testRadioButtonSelection() {
        // Test the selection of radio buttons (type of user)
        RegistrationPage page = new RegistrationPage();
        assertFalse(page.radioH.isSelected(), "Hotel radio button should not be selected initially.");
        assertFalse(page.radioI.isSelected(), "Individual radio button should not be selected initially.");
        assertFalse(page.radioO.isSelected(), "Organization radio button should not be selected initially.");

        page.radioH.setSelected(true);
        assertTrue(page.radioH.isSelected(), "Hotel radio button should be selected after setting.");
    }
    
}
