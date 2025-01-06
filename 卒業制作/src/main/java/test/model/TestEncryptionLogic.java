package test.model;

import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;

import model.EncryptionLogic;
import model.KeyStorage;

class TestEncryptionLogic {

	@Test
	void test() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
		
		String pass = "Takatori5082";
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		FileReader fr = new FileReader("key.dat");
		FileReader fr2 = new FileReader("iv.dat");
		for(int i = fr.read();i != -1;i = fr.read()) {
			sb.append((char)i);
		}
		for(int i = fr2.read();i != -1;i = fr2.read()) {
			sb2.append((char)i);
		}
		KeyStorage.init(sb.toString(), sb2.toString());
		System.out.println(pass);
		System.out.println(new String(EncryptionLogic.dec(EncryptionLogic.enc(pass))));
		
	}

}
