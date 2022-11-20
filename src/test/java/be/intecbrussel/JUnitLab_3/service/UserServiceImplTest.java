package be.intecbrussel.JUnitLab_3.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.Null;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private final UserServiceImpl userServiceImpl = new UserServiceImpl();

    @DisplayName("Testing MessageService.retrieveUserByEmail() if email is not null")
    @Test
    void shouldFailWhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.retrieveUserByEmail(null, "1234");
        });
    }

    @DisplayName("Testing MessageService.retrieveUserByEmail() if email is not empty")
    @Test
    void shouldFailWhenEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.retrieveUserByEmail("", "1234");
        });
    }

    @DisplayName("Testing MessageService.retrieveUserByEmail() if contains '.")
    @Test
    void shouldFailWhenEmailNotContainsPoint() {
        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.retrieveUserByEmail("bob@gmailcom", "1234");
        });
    }

    @DisplayName("Testing MessageService.retrieveUserByEmail() if contains '@")
    @Test
    void shouldFailWhenEmailNotContainsAtSign() {
        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.retrieveUserByEmail("bob.gmailcom", "1234");
        });
    }

    @DisplayName("Testing MessageService.retrieveUserByEmail() if User is empty")
    @Test
    void shouldFailWhenEmailNotContainsValidUser() {
        assertThrows(NullPointerException.class, () -> {
            userServiceImpl.retrieveUserByEmail("bob@gmail.com", "1234");
        });
    }

    //--------------------------------------------------------------------------------

    @DisplayName("Testing MessageService.retrieveUserByPhone() is correct phone number")
    @Test
    void shouldFailWhenPhoneNumberIsWrong() {
        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.retrieveUserByPhone("+123", "password");
        });
    }

    @DisplayName("Testing MessageService.retrieveUserByPhone() if User is not null")
    @Test
    void shouldFailWhenUserIsNotFound() {
        assertThrows(NullPointerException.class, () -> {
            userServiceImpl.retrieveUserByPhone("+32400000000", "password");
        });
    }

    //--------------------------------------------------------------------------------

    @DisplayName("Testing MessageService.retrieveAllUsersInPages() if pageNo is less then 0")
    @Test
    void shouldFailWhenPagesNoIsLessThenZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.retrieveAllUsersInPages(-1, 5);
        });
    }

    @DisplayName("Testing MessageService.retrieveAllUsersInPages() if pageSize is less then 0")
    @Test
    void shouldFailWhenPagesSizeIsLessThenZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.retrieveAllUsersInPages(1, -1);
        });
    }

    @DisplayName("Testing MessageService.retrieveAllUsersInPages() if page is empty")
    @Test
    void shouldFailWhenPageIsEmpty() {
        assertThrows(NullPointerException.class, () -> {
            userServiceImpl.retrieveAllUsersInPages(1, 1);
        });
    }

    //--------------------------------------------------------------------------------

    @Test
    void createUser() {
    }

    //--------------------------------------------------------------------------------

    @DisplayName("Testing MessageService.updateUserByEmail() if email is not null")
    @Test
    void shouldFailWhenUpdateEmailIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.updateUserEmailById(1L, null);
        });
    }

    @DisplayName("Testing MessageService.updateUserByEmail() if email is not empty")
    @Test
    void shouldFailWhenUpdateEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.updateUserEmailById(1L, "");
        });
    }

    @DisplayName("Testing MessageService.updateUserByEmail() if contains '.")
    @Test
    void shouldFailWhenUpdateEmailNotContainsPoint() {
        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.updateUserEmailById(1L, "bob@gmailcom");
        });
    }

    @DisplayName("Testing MessageService.updateUserByEmail() if contains '@")
    @Test
    void shouldFailWhenUpdateEmailNotContainsAtSign() {
        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.updateUserEmailById(1L, "bob.com");
        });
    }

    @DisplayName("Testing MessageService.updateUserByEmail() if User is empty")
    @Test
    void shouldFailWhenUpdateUserNotFound() {
        assertThrows(NullPointerException.class, () -> {
            userServiceImpl.updateUserEmailById(1L, "bob@gmail.com");
        });
    }

    //--------------------------------------------------------------------------------

    @DisplayName("Testing MessageService.updateUserByPhone() is correct phone number")
    @Test
    void shouldFailWhenUpdatePhoneNumberIsWrong() {
        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.updateUserPhoneById(1L, "123");
        });
    }

    @DisplayName("Testing MessageService.updateUserByPhone() if User is not null")
    @Test
    void shouldFailWhenUpdateUserIsNotFound() {
        assertThrows(NullPointerException.class, () -> {
            userServiceImpl.updateUserPhoneById(1L, "+32400000000");
        });
    }

}