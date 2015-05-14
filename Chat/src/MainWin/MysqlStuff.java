package MainWin;

import java.sql.*;

public class MysqlStuff {
	
	public static String conn()
	{
		String connection = "jdbc:mysql://localhost/mfurtado_chat?";
		return connection;
	}
	
	public static String loginAero()
	{
		String login = "user=aerothosis&password=halorvb1";
		return login;
	}
}
