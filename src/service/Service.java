package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import bean.User;

public class Service {

	public boolean validateLogin(User user, Connection conn) throws SQLException, NamingException{
//		System.out.println(user.getUsername());
//		System.out.println(user.getPssword());
		
		

		String sql = "select count(*) as count from userinfo where username=? and password=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,user.getUsername());
		stmt.setString(2, user.getPssword());
		ResultSet rs= stmt.executeQuery();
		int count=0;
		if(rs.next()){
			count=rs.getInt("count");
		}
		rs.close();
		
		if(count==1){
			return true;
		}
		else{
			return false;
		}
	}
	
}
