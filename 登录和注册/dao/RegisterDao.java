package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtils;

public class RegisterDao {

	public boolean insert(String usn, String pwd, String ag, String ema, String gender) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from user;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("username").equals(usn)) {
					return false;
				}
			}
			sql = "insert into user(username,password,age,email,gender)values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, usn);
			ps.setString(2, pwd);
			ps.setString(3, ag);
			ps.setString(4, ema);
			ps.setString(5, gender);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return true;
	}

}
