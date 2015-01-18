package backend;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptionDecryption {
	//private byte[] iv;
	
	
	IvParameterSpec ivParameterSpec;
	
	public byte[] AESEncryption(SecretKey aesKey, String message){
		Cipher cipher;
		byte[]  data = null;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ivParameterSpec = new IvParameterSpec(aesKey.getEncoded());
			cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParameterSpec);
	        data = cipher.doFinal(message.getBytes());
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return data;
	}
	
	public byte[] AESDecryption(byte[] ciphertext, SecretKey aesKey){
		byte[] decryptedText = null;
		Cipher c;
		try {
			c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ivParameterSpec = new IvParameterSpec(aesKey.getEncoded());
			c.init(Cipher.DECRYPT_MODE, aesKey, ivParameterSpec);
			decryptedText = c.doFinal(ciphertext);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return decryptedText;
	}
	
	public SecretKey GenerateSecretKeyClient(String cliefilepath, String ConsultantID){
		SecretKey secret = null;
		GenerateKey genKey = new GenerateKey();
		//getClient Key
		PrivateKey clientPrivKey = genKey.ByteToPrivate(genKey.readKeyFile(cliefilepath));
		//get Consultant PubKey
		PublicKey consPubKey = genKey.ByteToPublicKey(genKey.readKeyFile(genKey.consOnServerPath+ConsultantID+genKey.pubKeyName));
		
		secret = genKey.DHSecretKeyAgreeement(consPubKey, clientPrivKey);
		return secret;
	}
	
	
	public SecretKey GenerateSecretKeyConsultant(String consfilepath, String clientID){
		SecretKey secret = null;
		GenerateKey genKey = new GenerateKey();
		//get Consultant Key
		PrivateKey consPrivKey = genKey.ByteToPrivate(genKey.readKeyFile(consfilepath));
		//get Client PubKey
		PublicKey clientPubKey = genKey.ByteToPublicKey(genKey.readKeyFile(genKey.clientOnServerPath+clientID+genKey.pubKeyName));
		
		secret = genKey.DHSecretKeyAgreeement(clientPubKey, consPrivKey);
		return secret;
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AESEncryptionDecryption aed = new AESEncryptionDecryption();
		AuthenticateUser au = new AuthenticateUser();
		UserParam cli = new UserParam();
		UserParam cons = new UserParam();
		
		String msg = "coba";
		String path = "./Client/CF000014PRIVKEY";
		cli = au.KeyAuthentication(path);
		path = "./Consultant/CF000011PRIVKEY";
		cons = au.KeyAuthentication(path);
		
		//SecretKey aesKey = new SecretKeySpec(new byte[16], "AES");
	/*	byte[] ciphertext = aed.AESEncryption(cli.GetSecretKey(), msg);
		
		
		byte[] decrypted = aed.AESDecryption(ciphertext, cons.GetSecretKey());
		System.out.println(ciphertext);
		System.out.println(decrypted + " "+ new String(decrypted));
		*/
		
		
	}

}
