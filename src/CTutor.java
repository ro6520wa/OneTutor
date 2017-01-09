/**
 * Created by ronwagner on 09.01.17.
 */
public class CTutor extends CMember
{
    //attributes
    CPermission m_permission = new CPermission(false, true, true);

    public CTutor(String _firstname,String _lastname,int _ID, String _mail, CPermission _permission)
    {
        super(_firstname, _lastname, _ID, _mail);
        this.m_permission = _permission;
    }
}
