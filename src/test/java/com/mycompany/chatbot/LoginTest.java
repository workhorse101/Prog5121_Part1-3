/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.chatbot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RC_Student_Lab
 */
public class LoginTest {
          
    private Login login;

    @Before
    public void setUp() {
        login = new Login("Kyle", "Smith");
        login.setUsername("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
        login.setCellphone("+27838968976");
    }

    @Test
    public void testCheckUserNameValid() {
        assertTrue("Valid username should return true", login.checkUserName());
    }

    @Test
    public void testCheckUserNameNoUnderscore() {
        login.setUsername("kyle1");
        assertFalse("Username without underscore should return false", login.checkUserName());
    }

    @Test
    public void testCheckUserNameTooLong() {
        login.setUsername("kyle_long_name");
        assertFalse("Username longer than 5 chars should return false", login.checkUserName());
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        login.setPassword("Ch&&sec@ke99!");
        assertTrue("Valid password should return true", login.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexityTooShort() {
        login.setPassword("Ch&&sec@ke99!");
        assertFalse("Password shorter than 8 chars should return false", login.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexityNoCapital() {
        login.setPassword("Ch&&sec@ke99!");
        assertFalse("Password without capital letter should return false", login.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexityNoNumber() {
        login.setPassword("Ch&&sec@ke99!");
        assertFalse("Password without number should return false", login.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexityNoSpecialChar() {
        login.setPassword("Ch&&sec@ke99!");
        assertFalse("Password without special character should return false", login.checkPasswordComplexity());
    }

    @Test
    public void testCheckCellPhoneNumberValid() {
        assertTrue("Valid cellphone should return true", login.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumberNoInternationalCode() {
        login.setCellphone("0838968976");
        assertFalse("Cellphone without international code should return false", login.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumberWrongLength() {
        login.setCellphone("+278389689");
        assertFalse("Cellphone with wrong length should return false", login.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumberWrongCountryCode() {
        login.setCellphone("+25838968976");
        assertFalse("Cellphone with wrong country code should return false", login.checkCellPhoneNumber());
    }

    @Test
    public void testRegisterUserSuccess() {
        String expected = "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nUser registered successfully.";
        assertEquals("Successful registration should return correct message", expected, login.registerUser());
    }

    @Test
    public void testRegisterUserUsernameFailure() {
        login.setUsername("invalidusername");
        String expected = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        assertEquals("Username failure should return correct message", expected, login.registerUser());
    }

    @Test
    public void testRegisterUserPasswordFailure() {
        login.setPassword("weak");
        String expected = "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        assertEquals("Password failure should return correct message", expected, login.registerUser());
    }

    @Test
    public void testRegisterUserCellphoneFailure() {
        login.setCellphone("invalid");
        String expected = "Cell phone number is incorrectly formatted or does not contain international code, please correct the number and try again.";
        assertEquals("Cellphone failure should return correct message", expected, login.registerUser());
    }

    @Test
    public void testLoginUserSuccess() {
        assertTrue("Valid login should return true", login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUserWrongUsername() {
        assertFalse("Wrong username should return false", login.loginUser("wronguser", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUserWrongPassword() {
        assertFalse("Wrong password should return false", login.loginUser("kyl_1", "wrongpassword"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        String expected = "Welcome Kyle, Smith it is great to see you again.";
        assertEquals("Successful login status should return welcome message", expected, login.returnLoginStatus(true));
    }

    @Test
    public void testReturnLoginStatusFailure() {
        String expected = "Username or password incorrect, please try again.";
        assertEquals("Failed login status should return error message", expected, login.returnLoginStatus(false));
    }

    @Test
    public void testGetUsername() {
        assertEquals("getUsername should return the set username", "kyl_1", login.getUsername());
    }

    @Test
    public void testSetUsername() {
        login.setUsername("new_1");
        assertEquals("setUsername should update the username", "new_1", login.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("getPassword should return the set password", "Ch&&sec@ke99!", login.getPassword());
    }

    @Test
    public void testSetPassword() {
        login.setPassword("NewPass123!");
        assertEquals("setPassword should update the password", "NewPass123!", login.getPassword());
    }

    @Test
    public void testGetCellphone() {
        assertEquals("getCellphone should return the set cellphone", "+27838968976", login.getCellphone());
    }

    @Test
    public void testSetCellphone() {
        login.setCellphone("+27831234567");
        assertEquals("setCellphone should update the cellphone", "+27831234567", login.getCellphone());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("getFirstName should return the set first name", "Kyle", login.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        login.setFirstName("John");
        assertEquals("setFirstName should update the first name", "John", login.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("getLastName should return the set last name", "Smith", login.getLastName());
    }

    @Test
    public void testSetLastName() {
        login.setLastName("Doe");
        assertEquals("setLastName should update the last name", "Doe", login.getLastName());
    }
}
