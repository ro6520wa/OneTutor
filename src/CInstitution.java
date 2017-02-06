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
        do {
            if (this.m_admins.get(i).getM_ID() == _admin.getM_ID()){
                this.m_admins.remove(i);
                success = true;
            }
        }while(!success && i < this.m_admins.size());
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
        int i = 0;
        do{
            if(this.getM_admins().get(i).getM_ID() == _admin.getM_ID()){
                is_admin = true;
            }
        }while(i < this.getM_admins().size() && !is_admin);
        boolean success = false;
        if (is_admin) {
            int j = 0;
            do{
                if(this.m_courses.get(j).getM_title() == _course.getM_title()){
                    this.m_courses.remove(j);
                    success = true;
                }
                j++;
            }while(!success && j < this.m_courses.size());
        }
        return success;
    }

    public boolean loginCourse(int _key, CCourse _course, CMember _member){
        boolean success = false;
        if(_course.getM_license().validateKey(_key)){
            _course.addMember(_member);
            success = true;
        }
        return success;
    }

    public boolean logoutCourse(CMember _member, CCourse _course){
        boolean success = false;
        if(_course.getM_members().contains(_member)){
            _course.removeMember(_member);
            success = true;
        }
        return success;
    }

    public void addAdmin(CMember admin){
        this.m_admins.add(admin);
        return;
    }

    public boolean deleteAdmin(CMember admin){
        boolean success = false;
        int i = 0;
        do {
            if (this.m_admins.get(i).getM_ID() == admin.getM_ID()){
                this.m_admins.remove(i);
                success = true;
            }
        }while(!success && i < this.m_admins.size());
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

