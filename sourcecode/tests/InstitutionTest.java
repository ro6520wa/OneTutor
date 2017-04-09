/**
 * This is the test class for {@link Institution}.
 * It includes tests regarding the adding, deleting , login, logout of courses and deleting admins.
 *
 * @author   Ron Wagner
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InstitutionTest {

    private Institution fherfurt = new Institution(1);
    private Course informatik;
    private Course angeln;
    private int key;

    @Before
    public void setUp() throws Exception{
        fherfurt.addAdmin(DataBase.testMemberAdmin);
        informatik = fherfurt.addCourse("Informatik" , DataBase.testMemberAdmin);
        key = informatik.getM_license().getM_key();
        informatik.addMember(DataBase.testMemberAdmin);
        fherfurt.addAdmin(DataBase.testMemberAdmin);
    }

    @Test
    public void addCourse() throws Exception {
        assertNotNull(fherfurt.addCourse("Mathe", DataBase.testMemberAdmin));
        assertNull(fherfurt.addCourse("Mathe", DataBase.testMemberNoAdmin));
    }

    @Test
    public void deleteCourse() throws Exception {
        assertTrue(fherfurt.deleteCourse(informatik, DataBase.testMemberAdmin));
        assertFalse(fherfurt.deleteCourse(informatik, DataBase.testMemberNoAdmin));
        assertFalse(fherfurt.deleteCourse(angeln, DataBase.testMemberAdmin));
    }

    @Test
    public void loginCourse() throws Exception {
        assertTrue(fherfurt.loginCourse(key, informatik, DataBase.testMemberNoAdmin));
        assertFalse(fherfurt.loginCourse(DataBase.wrongKey, informatik, DataBase.testMemberNoAdmin));
        assertFalse(fherfurt.loginCourse(key, angeln, DataBase.testMemberNoAdmin));
        assertFalse(fherfurt.loginCourse(key, informatik, DataBase.testMemberAdmin));
    }

    @Test
    public void logoutCourse() throws Exception {
        assertTrue(fherfurt.logoutCourse(DataBase.testMemberAdmin, informatik));
        assertFalse(fherfurt.logoutCourse(DataBase.testMemberNoAdmin, informatik));
        assertFalse(fherfurt.logoutCourse(DataBase.testMemberAdmin, angeln));
    }

    @Test
    public void deleteAdmin() throws Exception {
        assertTrue(fherfurt.deleteAdmin(DataBase.testMemberAdmin));
        assertFalse(fherfurt.deleteAdmin(DataBase.testMemberNoAdmin));
    }
}