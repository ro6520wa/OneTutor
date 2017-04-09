/**
 * This is the test class for {@link Platform}.
 * It includes tests regarding the adding institutions, login and register of member.
 *
 * @author   Ron Wagner
 * @see      Member
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlatformTest {

    private Platform onetutor = new Platform();

    @Before
    public void setUp()
    {
        Member testMember = onetutor.register(DataBase.firstName, DataBase.lastName, DataBase.idNoAdmin, DataBase.mail);
    }

    @Test
    public void testAddInstitution() throws Exception {
        assertNotNull(onetutor.addInstitution());
    }

    @Test
    public void testRegister() throws Exception {
        assertNull(onetutor.register(DataBase.firstName, DataBase.lastName, DataBase.idNoAdmin, DataBase.mail));
        assertNotNull(onetutor.register(DataBase.firstName, DataBase.lastName, DataBase.idNoAdmin, DataBase.mail_wrong));
    }

    @Test
    public void testLogin() throws Exception {
        assertTrue("Login successful" ,onetutor.login(DataBase.mail));
        assertFalse("Mail is not registered" ,onetutor.login(DataBase.mail_wrong));
    }

}