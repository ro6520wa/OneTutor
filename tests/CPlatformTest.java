import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ronwa on 12.02.2017.
 */

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