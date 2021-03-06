package MainWin;

import java.math.*;
import java.security.*;
import java.util.*;
import javax.swing.*;


public class CmnCode 
{
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static final String CD = "0123456789";
	static Random rnd = new Random();

	public static String HashBash(String convert)
	{
		try
		{
			String plaintext = convert;
			MessageDigest s = MessageDigest.getInstance("SHA1");
			s.reset();
			s.update(plaintext.getBytes());
			byte[] digest = s.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			return hashtext;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}
	
	public static String RandomGen(int len)
	{
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
	
	public static String UserIDRandom(int len)
	{
		StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( CD.charAt( rnd.nextInt(CD.length()) ) );
		   return sb.toString();
	}
	
}
