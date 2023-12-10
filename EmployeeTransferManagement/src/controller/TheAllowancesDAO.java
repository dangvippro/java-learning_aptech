package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TheAllowancesDAO {
    List<TheAllowances> listTheAllowances = new ArrayList<>();
    server.DBHelper db = new server.DBHelper();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public TheAllowancesDAO() {
        try {
            con = db.getCon();
            ps = con.prepareStatement("select theAllowances, transferPositionNumber from TransferPosition where transferPositionName = ?");
            ps.setString(1, rs.getString("transferPositionNumber"));
            rs = ps.executeQuery();
            while (rs.next()){
                listTheAllowances.add(new TheAllowances("1", rs.getString("theAllowances")));
//                listTheAllowances.add(new TheAllowances("2", "Position allowance"));
//                listTheAllowances.add(new TheAllowances("3", "Regional allowance"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TheAllowancesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int add(TheAllowances ta){
        listTheAllowances.add(ta);
        return 1;
    }
    
    public List<TheAllowances> getAllTheAllowances(){
        return listTheAllowances;
    }
    
}
