import java.util.ArrayList;

/**
 * Created by ronwa on 27.01.2017.
 */
public class CInstitution {

    //attributes
    private ArrayList<CCourse> m_courses;
    private ArrayList<CMember> m_admins;
    private int m_ID;

    //constructor
    public CInstitution(int _ID){
        this.m_courses = new ArrayList<CCourse>();
        this.m_admins = new ArrayList<CMember>();
        this.m_ID = _ID;
    }

    //methods

    public CCourse addCourse(String _title, CMember _admin) {
        boolean success = false;
        int i = 0;
        //check if the user calling the function is actually an admin
        if (this.m_admins.contains(_admin)) {
            this.m_admins.remove(i);
            success = true;
        }
        CCourse course;
        //if so create a new course with the given title, else return null
        if(success){
            course = new CCourse(_title, _admin, this.getM_ID());
            this.m_courses.add(course);
        }
        else{
            course = null;
        }
        return course;
    }

    public boolean deleteCourse(CCourse _course, CMember _admin) {
        boolean is_admin = false;
        if(this.getM_admins().contains(_admin)) {
            is_admin = true;
        }
        boolean success = false;
        if (is_admin) {
            if(this.m_courses.contains(_course)){
                this.m_courses.remove(_course);
                success = true;
                }
        }
        return success;
    }

    public boolean loginCourse(int _key, CCourse _course, CMember _member){
        boolean success = false;
        if(this.m_courses.contains(_course) && _course.getM_license().validateKey(_key) && !_course.getM_members().contains(_member)){
            _course.addMember(_member);
            success = true;
        }
        return success;
    }

    public boolean logoutCourse(CMember _member, CCourse _course){
        boolean success = false;
        if(this.m_courses.contains(_course) && _course.getM_members().contains(_member)){
            _course.removeMember(_member);
            success = true;
        }
        return success;
    }

    public void addAdmin(CMember _admin){
        this.m_admins.add(_admin);
        return;
    }

    public boolean deleteAdmin(CMember _admin){
        boolean success = false;
        int i = 0;
        if (this.m_admins.contains(_admin)) {
            this.m_admins.remove(i);
            success = true;
        }
        return success;
    }

    public void setM_ID(int ID){
        this.m_ID = ID;
        return;
    }
    public int getM_ID(){
        return this.m_ID;
    }

    public ArrayList<CMember> getM_admins(){return m_admins;}
}

