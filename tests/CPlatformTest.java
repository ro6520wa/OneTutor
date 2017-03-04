/**
 * This is the test class for {@link CPlatform}.
 * It includes tests regarding the adding institutions, login and register of member.
 *
 * @author   Ron Wagner
 * @see      CMember
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CPlatformTest {

    private CPlatform onetutor = new CPlatform();

    @Before
    public void setUp()
    {
        CMember testMember = onetutor.register(CDataBase.firstName, CDataBase.lastName, CDataBase.idNoAdmin, CDataBase.mail);
    }

    @Test
    public void testAddInstitution() throws Exception {
        assertNotNull(onetutor.addInstitution());
    }

    @Test
    public void testRegister() throws Exception {
        assertNull(onetutor.register(CDataBase.firstName, CDataBase.lastName, CDataBase.idNoAdmin, CDataBase.mail));
        assertNotNull(onetutor.register(CDataBase.firstName, CDataBase.lastName, CDataBase.idNoAdmin, CDataBase.mail_wrong));
    }

    @Test
    public void testLogin() throws Exception {
        assertTrue("Login successful" ,onetutor.login(CDataBase.mail));
        assertFalse("Mail is not registered" ,onetutor.login(CDataBase.mail_wrong));
    }

}