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
    public CPlatform() {
        this.m_institutions = new ArrayList<CInstitution>();
        this.m_member = new ArrayList<CMember>();
        this.m_admins = new ArrayList<CMember>();
    }

    //methods
   public CMember register(String firstname, String lastname, int ID, String mail){
        CMember member = new CMember(firstname, lastname, ID, mail);
        addMember(member);
        return member;
    }

    public boolean login(CMember member){
        boolean success = false;
        int i = 0;
        do{
            if(this.m_member.get(i).getM_ID() == member.getM_ID()){
                success = true;
            }
        }while(!success && i < this.m_member.size());
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
            if(this.m_member.get(i).getM_ID() == member.getM_ID()){
                this.m_member.remove(i);
                success = true;
                member = null;
            }
            i++;
        }while(!success && i < this.m_member.size());
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
}
