import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ronwa on 03.03.2017.
 */
public class CInstitutionTest {

    private CInstitution fherfurt = new CInstitution(1);
    private CCourse informatik;
    private CCourse angeln;
    private int key;

    @Before
    public void setUp() throws Exception{
        fherfurt.addAdmin(CDataBase.testMemberAdmin);
        informatik = fherfurt.addCourse("Informatik" , CDataBase.testMemberAdmin);
        key = informatik.getM_license().getM_key();
        informatik.addMember(CDataBase.testMemberAdmin);
        fherfurt.addAdmin(CDataBase.testMemberAdmin);
    }

    @Test
    public void addCourse() throws Exception {
        assertNotNull(fherfurt.addCourse("Mathe", CDataBase.testMemberAdmin));
        assertNull(fherfurt.addCourse("Mathe", CDataBase.testMemberNoAdmin));
    }

    @Test
    public void deleteCourse() throws Exception {
        assertTrue(fherfurt.deleteCourse(informatik, CDataBase.testMemberAdmin));
        assertFalse(fherfurt.deleteCourse(informatik, CDataBase.testMemberNoAdmin));
        assertFalse(fherfurt.deleteCourse(angeln, CDataBase.testMemberAdmin));
    }

    @Test
    public void loginCourse() throws Exception {
        assertTrue(fherfurt.loginCourse(key, informatik, CDataBase.testMemberNoAdmin));
        assertFalse(fherfurt.loginCourse(CDataBase.wrongKey, informatik, CDataBase.testMemberNoAdmin));
        assertFalse(fherfurt.loginCourse(key, angeln, CDataBase.testMemberNoAdmin));
        assertFalse(fherfurt.loginCourse(key, informatik, CDataBase.testMemberAdmin));
    }

    @Test
    public void logoutCourse() throws Exception {
        assertTrue(fherfurt.logoutCourse(CDataBase.testMemberAdmin, informatik));
        assertFalse(fherfurt.logoutCourse(CDataBase.testMemberNoAdmin, informatik));
        assertFalse(fherfurt.logoutCourse(CDataBase.testMemberAdmin, angeln));
    }

    @Test
    public void deleteAdmin() throws Exception {
        assertTrue(fherfurt.deleteAdmin(CDataBase.testMemberAdmin));
        assertFalse(fherfurt.deleteAdmin(CDataBase.testMemberNoAdmin));
    }
}