import java.util.ArrayList;
/**
 * Created by ronwa on 27.01.2017.
 */
public class CPlatform {

    //attributes
    private ArrayList<CInstitution> m_institutions;
    private ArrayList<CMember> m_member;
    private ArrayList<CMember> m_admins;

    //constructor
    public CPlatform(){
        this.m_institutions = new ArrayList<CInstitution>();
        this.m_member = new ArrayList<CMember>();
        this.m_admins = new ArrayList<CMember>();
    }

    //methods
    public CPupil register(String firstname, String lastname, int ID, String mail){
        CPermission permission_pupil = new CPermission(false, true, false);
        CPupil pupil = new CPupil(firstname, lastname, ID, mail, permission_pupil);
        addMember(pupil);
        return pupil;
    }

    public boolean login(CMember member){
        boolean success = false;
        int i = 0;
        do{
            if(this.m_member.get(i) == member){
                success = true;
            }
        }while(success == false && i < this.m_member.size());
        return success;
    }
    //LOGOUT NEEDED!?

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
                member = null;
            }
            i++;
        }while(success == false && i < this.m_member.size());
        return success;
    }

    public void addAdmin(CAdmin admin){
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
    }

}
