/**
 * This class represents the member i.e. the person using OneTutor. They consist of a firstname, a lastname, an ID to uniquely identify them
 * and determine whether they are an administrator or not, an E-Mail used for their login and a list of courses they are entered in.
 *
 * @author Ron Wagner
 * @see CCourse
 */


import java.util.ArrayList;

public class CMember
{

    /**
     * Class constructor that creates an instance of this class with the given attributes.
     *
     * @param _firstname        Character string that represents the firstname of the member
     * @param _lastname         Character string that represents the latsname of the member
     * @param _mail             Character String that represents the E-Mail of the member
     * @param   _ID             Integer value that represents a unique identifier for every instance
     * @return                  An instance of this class with the specific attributes
     */
    public CMember(String _firstname,String _lastname,int _ID, String _mail)
    {
        this.m_firstname = _firstname;
        this.m_lastname = _lastname;
        this.m_ID = _ID;
        this.m_mail = _mail;
        m_courses = new ArrayList<CCourse>();
    }

    /**************
     *ATTRIBUTES
     *************/
    private String m_firstname;
    private String m_lastname;
    private int m_ID;
    private String m_mail;
    private ArrayList<CCourse> m_courses;

    /**************
     *METHODS
     *************/
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

    public ArrayList<CCourse> getCourses(){return m_courses;}

    public void addCourse(CCourse _course){
        this.m_courses.add(_course);
        return;
    }

    public boolean removeCourse(CCourse _course){
        boolean success = false;
        if(this.m_courses.contains(_course)){
            this.m_courses.remove(_course);
            success = true;
        }
        return success;
    }
}
