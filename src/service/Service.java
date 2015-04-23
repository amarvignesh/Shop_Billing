package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import bean.User;

public class Service {

	
	private PreparedStatement stmt;
	private int count = 0;
	private	ResultSet rs;

	
	public boolean validateLogin(User user, Connection conn, DataSource ds) {
//		System.out.println(user.getUsername());
//		System.out.println(user.getPssword());
		
		
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) as count from userinfo where username=? and password=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,user.getUsername());
			stmt.setString(2, user.getPssword());

			rs = stmt.executeQuery();
			if(rs.next()){
				count=rs.getInt("count");
			}
			rs.close();
			
		} catch (SQLException e) {
			
		}

				
		if(count==1){
			System.out.println("user found");
			return true;
			
		}
		else{
			return false;
		}
	}
	
}
