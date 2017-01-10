/**
 * Created by ronwagner on 09.01.17
 */
public abstract class CMember
{

    //attributes
    private String m_firstname;
    private String m_lastname;
    private int m_ID;
    private String m_mail;

    //constructor
    protected CMember(String _firstname,String _lastname,int _ID, String _mail)
    {
        this.m_firstname = _firstname;
        this.m_lastname = _lastname;
        this.m_ID = _ID;
        this.m_mail = _mail;
    }

    //methods

    public String getM_firstname()
    {
        return m_firstname;
    }

    public void setM_firstname(String m_firstname)
    {
        this.m_firstname = m_firstname;
    }

    public String getM_lastname()
    {
        return m_lastname;
    }

    public void setM_lastname(String m_lastname)
    {
        this.m_lastname = m_lastname;
    }

    public int getM_ID()
    {
        return m_ID;
    }

    public void setM_ID(int m_ID)
    {
        this.m_ID = m_ID;
    }

    public String getM_mail()
    {
        return m_mail;
    }

    public void setM_mail(String m_mail)
    {
        this.m_mail = m_mail;
    }
}
