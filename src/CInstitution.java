import java.util.ArrayList;

/**
 * Created by ronwa on 27.01.2017.
 */
public class CInstitution {

    //attributes
    private ArrayList<CCourse> m_course;
    private ArrayList<CMember> m_member;
    private ArrayList<CMember> m_admins;
    private int m_ID;

    //constructor
    public CInstitution(int _ID){
        this.m_course = new ArrayList<CCourse>();
        this.m_member = new ArrayList<CMember>();
        this.m_admins = new ArrayList<CMember>();
        this.m_ID = _ID;
    }

    //methods

    //UNFINISHED
    /*public CCourse addCourse() {
        CCourse course = new CCourse();
        this.m_course.add(course);
        return course;
    }*/

    public boolean deleteCourse(CCourse course) {
        boolean success = false;
        int i = 0;
        do{
            if(this.m_course.get(i) == course){
                this.m_course.remove(i);
                success = true;
            }
            i++;
        }while(success == false && i < this.m_course.size());
        return success;
    }

    public void addMember(CMember member){
        this.m_member.add(member);
        return;
    }

    public boolean deleteMember(CMember member){
        boolean success = false;
        int i = 0;
        do{
            if(this.m_member.get(i) == member){
                this.m_member.remove(i);
                success = true;
            }
            i++;
        }while(success == false && i < this.m_member.size());
        return success;
    }

    /*public void addAdmin(CAdmin admin){
        this.m_admins.add(admin);
        return;
    }

    public boolean deleteAdmin(CAdmin admin){
        boolean success = false;
        int i = 0;
        do {
            if (this.m_admins.get(i) == admin){
                this.m_admins.remove(i);
                success = true;
            }
        }while(success == false && i < this.m_admins.size());
        return success;
    }*/

    public void setM_ID(int ID){
        this.m_ID = ID;
        return;
    }
    public int getM_ID(){
        return this.m_ID;
    }
}

