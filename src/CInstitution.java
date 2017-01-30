import java.util.ArrayList;

/**
 * Created by ronwa on 27.01.2017.
 */
public class CInstitution {

    //attributes
    private ArrayList<CCourse> m_course;
    private ArrayList<CMember> m_admins;
    private int m_ID;

    //constructor
    public CInstitution(int _ID){
        this.m_course = new ArrayList<CCourse>();
        this.m_admins = new ArrayList<CMember>();
        this.m_ID = _ID;
    }

    //methods

    public CCourse addCourse(String _title, CMember _admin) {
        CCourse course = new CCourse(_title, _admin);
        this.m_course.add(course);
        return course;
    }

    public boolean deleteCourse(CCourse course) {
        boolean success = false;
        int i = 0;
        do{
            if(this.m_course.get(i).getM_title() == course.getM_title()){
                this.m_course.remove(i);
                success = true;
            }
            i++;
        }while(!success && i < this.m_course.size());
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
}

