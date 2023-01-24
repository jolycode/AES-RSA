import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.DataInputStream;
import java.io.FileInputStream;
class AES{
	
	private static final String SECRET_KEY= "J@NcRfUjXn2QKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04gjXn2r5u7McQfTjWnZr4uNcRfUjXn2r5u7xx";
	private static final String SALT = "F)J@McQfTjWnZr4qhkiG9w0BAQEFAASCAl8wggJMcQfTjWnZr4uNcRfUjXn2r5u7xx";

	public static String encrypt(String strToEncrypt){
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec
				= new IvParameterSpec(iv);
			SecretKeyFactory factory
				= SecretKeyFactory.getInstance(
					"PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(
				SECRET_KEY.toCharArray(), SALT.getBytes(),65536, 1024);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(
				tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance(
				"AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey,
						ivspec);
			return Base64.getEncoder().encodeToString(
				cipher.doFinal(strToEncrypt.getBytes(
					StandardCharsets.UTF_8)));
		}
		catch (Exception e) {
			System.out.println("Error while encrypting: "
							+ e.toString());
		}
		return null;
	}
	public static String decrypt(String strToDecrypt){
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec
				= new IvParameterSpec(iv);
			SecretKeyFactory factory
				= SecretKeyFactory.getInstance(
					"PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(
				SECRET_KEY.toCharArray(), SALT.getBytes(),65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(
				tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance(
				"AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey,
						ivspec);
			return new String(cipher.doFinal(
				Base64.getDecoder().decode(strToDecrypt)));
		}
		catch (Exception e) {
			System.out.println("Error while decrypting: "
							+ e.toString());
		}
		return null;
	}
}
public class AES_E_D {
	public static void main(String[] args){	
		KB();
		MB();
		GB();
		System.out.println("Done!");
	}
	static void  KB(){
		try{
		     DataInputStream dis = 
			 new DataInputStream (
			 new FileInputStream ("C:\\Users\\danil\\OneDrive\\Рабочий стол\\500kb.txt"));   
			 byte[] datainBytes = new byte[dis.available()];
			 dis.readFully(datainBytes);
			 dis.close();  
			 String originalString_kb = new String(datainBytes, 0, datainBytes.length);
			 System.out.println("AES 500 kb");
			long startTime_E = System.currentTimeMillis();
			long total_E = 0;
			String encryptedString= AES.encrypt(originalString_kb);
			for (int i = 0; i < 10000000; i++) {
			    total_E += i;}
			long stopTime_E = System.currentTimeMillis();
			long elapsedTime_E = stopTime_E - startTime_E;
			System.out.println("Encrypt 500KB TIME:"+elapsedTime_E+" miliseconds");
			
			long startTime_D = System.currentTimeMillis();
			long total_D  = 0;
			String decryptedString= AES.decrypt(encryptedString);
			for (int i = 0; i < 10000000; i++) {
			    total_D  += i;}
			long stopTime_D  = System.currentTimeMillis();
			long elapsedTime_D  = stopTime_D  - startTime_D ;
			System.out.println("Decrypt 500KB TIME:"+elapsedTime_D +" miliseconds");
			
		}catch(Exception ex){
			ex.printStackTrace();}

	}
	
	static void  MB(){
		try{
		     DataInputStream dis = 
			 new DataInputStream (
			 new FileInputStream ("C:\\Users\\danil\\OneDrive\\Рабочий стол\\500MB.txt"));   
			 byte[] datainBytes = new byte[dis.available()];
			 dis.readFully(datainBytes);
			 dis.close();  
			 String originalString_kb = new String(datainBytes, 0, datainBytes.length);
			System.out.println("AES 500 MB");
			long startTime_E = System.currentTimeMillis();
			long total_E = 0;
			String encryptedString = AES.encrypt(originalString_kb);
			for (int i = 0; i < 10000000; i++) {
			    total_E += i;}
			long stopTime_E = System.currentTimeMillis();
			long elapsedTime_E = stopTime_E - startTime_E;
			System.out.println("Encrypt 500MB TIME:"+elapsedTime_E+" miliseconds");
			long startTime_D = System.currentTimeMillis();
			long total_D  = 0;
			String decryptedString=AES.decrypt(encryptedString);
			for (int i = 0; i < 10000000; i++) {
			    total_D  += i;}
			long stopTime_D  = System.currentTimeMillis();
			long elapsedTime_D  = stopTime_D  - startTime_D ;
			System.out.println("Decrypt 500MB TIME:"+elapsedTime_D +" miliseconds");
			
		}catch(Exception ex){
			ex.printStackTrace();}

	}
	static void  GB(){
		try{
		     DataInputStream dis = 
			 new DataInputStream (
			 new FileInputStream ("C:\\Users\\danil\\OneDrive\\Рабочий стол\\1GB.txt"));   
			 byte[] datainBytes = new byte[dis.available()];
			 dis.readFully(datainBytes);
			 dis.close();  
			 String originalString_kb = new String(datainBytes, 0, datainBytes.length);
			 System.out.println("AES 1 GB");
			
			long startTime_E = System.currentTimeMillis();
			long total_E = 0;
			String encryptedString = AES.encrypt(originalString_kb);
			for (int i = 0; i < 10000000; i++) {
			    total_E += i;}
			long stopTime_E = System.currentTimeMillis();
			long elapsedTime_E = stopTime_E - startTime_E;
			System.out.println("Encrypt 1 GB TIME:"+elapsedTime_E+" miliseconds");
			long startTime_D = System.currentTimeMillis();
			long total_D  = 0;
			String decryptedString=AES.decrypt(encryptedString);
			for (int i = 0; i < 10000000; i++) {
			    total_D  += i;}
			long stopTime_D  = System.currentTimeMillis();
			long elapsedTime_D  = stopTime_D  - startTime_D ;
			System.out.println("Decrypt 1 GB TIME:"+elapsedTime_D +" miliseconds");
			
		}catch(Exception ex){
			ex.printStackTrace();}

	}
}
