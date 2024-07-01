import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void checkUserName() {
        Login user1 = new Login();
        assertTrue(user1.checkUserName("Didi_"));
    }


    @org.junit.jupiter.api.Test
    void checkPasswordComplexity() {
        Login user1 = new Login();
        assertTrue(user1.checkPasswordComplexity("Luthando@1"));
    }

    @org.junit.jupiter.api.Test
    void registerUser() {
        Login user1 = new Login();
        assertEquals("User Successfully Registered",user1.registerUser("Didi_","Luthando@1"));
    }


    @org.junit.jupiter.api.Test
    void loginUser() {
        Login user1 = new Login();
        user1.username = "Didi_";
        user1.password = "Luthando@1";
        assertTrue(user1.loginUser("Didi_", "Luthando@1", user1.username, user1.password));
    }

    @org.junit.jupiter.api.Test
    void returnLoginStatus() {
        Login user1 = new Login();
        user1.firstName = "Luthando";
        user1.lastName = "Didiza";
        assertEquals("Welcome " + user1.firstName + " " + user1.lastName + " it is great to see you again",user1.returnLoginStatus(true, user1.firstName, user1.lastName));
    }

    @org.junit.jupiter.api.Test
    void checkTaskDescription() {
        Task task1 = new Task();
        assertTrue(task1.checkTaskDescription("Assignment For The 21st Century"));
    }


    @org.junit.jupiter.api.Test
    void createTaskID() {
        Task task1 = new Task();
        assertEquals("TASK: " + 1,task1.createTaskID(1));
    }


    @org.junit.jupiter.api.Test
    void returnTotalHours() {
        Task task1 = new Task();
        assertEquals(5,task1.returnTotalHours(5));
    }

}