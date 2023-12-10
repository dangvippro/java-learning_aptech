package itrequests;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    
    public static void run() {
        Connection con = MainConnection.getConnection();
        Scanner sc = new Scanner(System.in);
        int select;
        
        do {
            System.out.println("****************************************"+
            "\n\t1. Show all Requests." + 
            "\n\t2. Adding new Requests." + 
            "\n\t3. Editing Requests." + 
            "\n\t4. Deleteting Requests." + 
            "\n\t5. Finding Requests." + 
            "\n\t6. Exit." + 
            "\n****************************************");
            do {
                System.out.print("Enter your select: ");
                select = sc.nextInt();
                if (select<1 || select>6) {
                    System.err.println("Select must be from 1 to 6!!");
                }
            } while(select<1 || select>6);
            
            sc.nextLine();
            
            switch (select) {
                case 1:
                    System.out.println("\tReqID\tReqName\t\tReqDate\t\tReqEmail\tReqType\t\tReqDetails");
                    try {
                        String sql = "SELECT ReqID, ReqName, ReqDate, ReqEmail, ReqType,ReqDetails FROM ITRequests";
                        PreparedStatement pstm = con.prepareStatement(sql);
                        ResultSet rs1 = pstm.executeQuery();
                        
                        while (rs1.next()) {
                            System.out.println("\t" + rs1.getInt("ReqID") + "\t" + rs1.getString("ReqName") + "\t" + rs1.getString("ReqDate") + "\t" + rs1.getString("ReqEmail") + "\t" + rs1.getString("ReqType") + "\t" + rs1.getString("ReqDetails"));
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                    break;
                case 2:
                    String reqName, reqDate, reqEmail, reqType, reqDetails;
                    Date date = new Date();
                    reqDate = ((int) date.getYear()+1900) + "-" + ((int) date.getMonth()+1) + "-" + date.getDate();
                    
                    do {
                        System.out.print("Enter ReqName: "); reqName = sc.nextLine();
                        if (reqName.equals("")) {
                            System.err.println("ReqName cannot be empty!");
                        }
                    } while (reqName.equals(""));
                    
                    do {
                        System.out.print("Enter ReqEmail: "); reqEmail = sc.nextLine();
                        if (reqEmail.equals("")) {
                            System.err.println("reqEmail cannot be empty!");
                        } else if (!(reqEmail.matches("^(.+)@(\\S+)$"))) {
                            System.err.println("reqEmail is incorrect!");
                        }                                
                    } while (reqEmail.equals("") || !(reqEmail.matches("^(.+)@(\\S+)$")));
                    
                    do {
                        System.out.print("Enter ReqType: "); reqType = sc.nextLine();
                        if (!reqType.toLowerCase().equals("hardware") && !reqType.toLowerCase().equals("networking") && !reqType.toLowerCase().equals("email") && !reqType.toLowerCase().equals("server access")) {
                            System.err.println("Reqtype only accepts 4 values such as Hardware, Networking, Email, Server access");
                        }
                    } while (!reqType.toLowerCase().equals("hardware") && !reqType.toLowerCase().equals("networking") && !reqType.toLowerCase().equals("email") && !reqType.toLowerCase().equals("server access"));
                    
                    System.out.print("Enter ReqDetails:"); reqDetails = sc.nextLine();
                    
                    try {
                        String sql = "INSERT INTO ITRequests(ReqName, ReqDate, ReqEmail, ReqType, ReqDetails) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement pstm = con.prepareStatement(sql);
                        pstm.setString(1, reqName);
                        pstm.setString(2, reqDate);
                        pstm.setString(3, reqEmail);
                        pstm.setString(4, reqType);
                        pstm.setString(5, reqDetails);
                        pstm.executeUpdate();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                    
                    System.out.println("Add new requests successfully!");
                    break;
                case 3:
                    System.out.print("Enter ReqID you want to Edit: "); int id3 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("\tEnter a new ReqDetails: "); String ReqDetails = sc.nextLine();
                    
                    try {
                        String sql = "UPDATE ITRequests SET ReqDetails = ? WHERE ReqID = ?";
                        PreparedStatement pstm = con.prepareStatement(sql);
                        pstm.setString(1, ReqDetails);
                        pstm.setInt(2, id3);
                        pstm.executeUpdate();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                    
                    System.out.println("Edit successfully!");
                    break;
                case 4:
                    System.out.print("Enter ReqID you want to delete: "); int id4 = sc.nextInt();
                    sc.nextLine();
                    
                    try {
                        String sql = "DELETE FROM ITRequests WHERE ReqID = ?";
                        PreparedStatement pstm = con.prepareStatement(sql);
                        pstm.setInt(1, id4);
                        pstm.executeUpdate();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                    
                    System.out.println("Delete successfully!");
                    break;
                case 5:
                    System.out.print("Enter ReqEmail you want to find: "); String ReqEmail = sc.nextLine();
                    try {
                        String sql = "SELECT ReqDetails FROM ITRequests WHERE ReqEmail = ?";
                        PreparedStatement pstm = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        pstm.setString(1, ReqEmail);
                        ResultSet rs = pstm.executeQuery();
                        
                        if (!rs.next()) {
                            System.err.println("Cannot found!");
                        }
                        
                        rs.beforeFirst();
                        
                        while (rs.next()) {
                            System.out.println("\t" + rs.getString("ReqDetails"));
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                    break;
            }
            
        } while (select!=6);
    }
    
    public static void main(String[] args) {
        run();
    }

}
