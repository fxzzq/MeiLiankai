package com.minlength.platform.core.utils.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * <p>用于RSA非对称加密算法的工具类,该类加解密都实现了分段加解密功能</p>
 * <br/>
 * <p>证书生成</p>
 * <br/>
 * 使用openssl如下：
 * openssl genrsa -out private-rsa.key 1024
 * openssl req -new -x509 -key private-rsa.key -days 750 -out public-rsa.cer
 * 
 * @Title: RSAUtil.java 
 * @Package com.meloclan.platform.core.utils.security 
 * @author shugang
 * @date 2017 2017-5-19 下午4:39:00 
 * @version v1.0
 */
public class RSAUtil {
	
	public static final String JKS = "JKS";
	
	public static final String X509 = "x509";
	
	public static String readKeyFile(InputStream in) throws Exception {  
        BufferedReader br = new BufferedReader(new InputStreamReader(in));  
        String readLine = null;  
        StringBuilder sb = new StringBuilder();  
        while ((readLine = br.readLine()) != null) {  
            if (readLine.charAt(0) == '-') {  
                continue;  
            } else {  
                sb.append(readLine);  
                sb.append('\r');  
            }  
        }  
        return sb.toString();   
    }
	
	public static PrivateKey loadPrivateKey(String privateKey,String algorithm) throws Exception{  
        // 解密由base64编码的私钥     
        byte[] keyBytes = base64Decoder(privateKey);     
        // 构造PKCS8EncodedKeySpec对象     
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);     
        // KEY_ALGORITHM 指定的加密算法     
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);     
        // 取私钥匙对象     
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);     
        return priKey;  
    }
	
	public static byte[] base64Decoder(String data) throws IOException{  
        BASE64Decoder base64Decoder = new BASE64Decoder();  
        return base64Decoder.decodeBuffer(data);  
    }  
      
    public static String base64Encoder(byte[] data){  
        BASE64Encoder base64Encoder=new BASE64Encoder();  
        return base64Encoder.encodeBuffer(data);  
    } 
    
	public static String safeUrlBase64Encode(byte[] data) {
		String encodeBase64 = new BASE64Encoder().encode(data);
		String safeBase64Str = encodeBase64.replace('+', '-');
		safeBase64Str = safeBase64Str.replace('/', '_');
		safeBase64Str = safeBase64Str.replaceAll("=", "");
		return safeBase64Str;
	}

	public static byte[] safeUrlBase64Decode(final String safeBase64Str)
			throws IOException {
		String base64Str = safeBase64Str.replace('-', '+');
		base64Str = base64Str.replace('_', '/');
		int mod4 = base64Str.length() % 4;
		if (mod4 > 0) {
			base64Str = base64Str + "====".substring(mod4);
		}
		return new BASE64Decoder().decodeBuffer(base64Str);

	}
	
	/**
	 * 从cert文件中获取公钥
	 * @param stream	公钥的输入流
	 * @return
	 * @throws CertificateException 
	 */
	public static RSAPublicKey getPublicKeyFromCert(InputStream stream) throws CertificateException{
		if(stream == null){return null;}
        CertificateFactory cf = CertificateFactory.getInstance(X509);  
        Certificate cerCert = cf.generateCertificate(stream);  
        if(cerCert != null){
        	PublicKey _key = cerCert.getPublicKey();
        	if(_key instanceof RSAPublicKey){
        		return (RSAPublicKey)_key;
        	}
        }  
		return null;
	}
	
	/**
	 * 从本地的keystore中获取私钥
	 * @param stream
	 * @param storePwd
	 * @param recordAlias
	 * @param recordPwd
	 * @return
	 * @throws Exception
	 */
	public static RSAPrivateKey getPrivateKeyFromStore(InputStream stream, String storePwd, String recordAlias, String recordPwd) throws Exception{
		if(stream == null){return null;}
		KeyStore ks = KeyStore.getInstance(JKS);		
		ks.load(stream, storePwd == null? null : storePwd.toCharArray());
		RSAPrivateKey privateKey = (RSAPrivateKey) ks.getKey(recordAlias, recordPwd == null ? null : recordPwd.toCharArray());
		return privateKey;
		
	}
	
	/**
	 * 先进行rsa加密，并且将加密的byte转换为base64编码
	 * @param publicKey		公钥
	 * @param message		需要加密的明文信息
	 * @return
	 * @throws Exception 
	 */
	public static String encryptRsaToBase64(RSAPublicKey publicKey, String message) throws Exception{
		//首先进行公钥加密
		byte[] _message_en = encrypt(publicKey, message.getBytes());
		
		//将加密后的字节码进行base64编码
		String _message_base64_en = safeUrlBase64Encode(_message_en);
		
		return _message_base64_en;
	}
	
	/**
	 * 先进行rsa加密，并且将加密的byte转换为base64编码
	 * @param privateKey	私钥
	 * @param message		需要加密的明文信息
	 * @return
	 * @throws Exception
	 */
	public static String encryptRsaToBase64(RSAPrivateKey privateKey, String message) throws Exception{
		//首先进行公钥加密
		byte[] _message_en = encrypt(privateKey, message.getBytes());
		
		//将加密后的字节码进行base64编码
		String _message_base64_en = safeUrlBase64Encode(_message_en);
		
		return _message_base64_en;
	}
	
	/** 
     * 公钥加密过程 
     *  
     * @param publicKey 
     *            公钥 
     * @param plainTextData 
     *            明文数据 
     * @return 
     * @throws Exception 
     *             加密过程中的异常信息 
     */  
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)  
            throws Exception {  
        if (publicKey == null) {  
            throw new Exception("加密公钥为空, 请设置");  
        }
        //计算可支持的明文最大长度
        int message_max_size = publicKey.getModulus().bitLength()/8 - 11; 
        int result_size = publicKey.getModulus().bitLength()/8;
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
          //分段
            byte[][] split = splitByteArray(plainTextData, message_max_size);
            byte[] output = new byte[result_size * split.length];
            for(int i = 0; i < split.length ; i++){
            	byte[] oneSplit = split[i];
            	byte[] temp = cipher.doFinal(oneSplit);
            	System.arraycopy(temp, 0, output, i * result_size, result_size);
            }
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此加密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("加密公钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("明文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("明文数据已损坏");  
        }  
    } 
	
	/**
	 * <p>私钥加密过程</p>
	 * <p>加密采用分段加密的方式，加密时支持的最大字节数：证书位数/8 -11（比如：2048位的证书，支持的最大加密字节数：2048/8 - 11 = 245）</p>
	 * <p>而加密后得到密文的字节数，正好是密钥的长度值除以 8（比如：2048位的证书，加密后的密文位数为2048/8=256），依据该特性进行分段加密</p>
	 * @param privateKey		私钥
	 * @param plainTextData		待加密数据
	 * @return
	 * @throws Exception
	 */
    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData)  
            throws Exception {  
        if (privateKey == null) {  
            throw new Exception("加密私钥为空, 请设置");  
        }
        //计算可支持的明文最大长度
        int message_max_size = privateKey.getModulus().bitLength()/8 - 11; 
        int result_size = privateKey.getModulus().bitLength()/8;
        Cipher cipher = null;
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);            
            //分段
            byte[][] split = splitByteArray(plainTextData, message_max_size);
            byte[] output = new byte[result_size * split.length];
            for(int i = 0; i < split.length ; i++){
            	byte[] oneSplit = split[i];
            	byte[] temp = cipher.doFinal(oneSplit);
            	System.arraycopy(temp, 0, output, i * result_size, result_size);
            }            
        	return output;       
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此加密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("加密私钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("明文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("明文数据已损坏");  
        }  
    } 
    
    /**
     * 解密首先通过rsa加密，再对加密后的byte进行base64编码的密文
     * @param privateKey	私钥
     * @param base64		公钥
     * @return
     * @throws IOException 
     */
    public static String decryptRsaFromBase64(RSAPrivateKey privateKey, String base64) throws Exception{
    	//首先进行base64的解码
    	byte[] _base64_de = safeUrlBase64Decode(base64);
    	
    	//再进行rsa解密
    	byte[] _message_de = decrypt(privateKey, _base64_de);
    	
    	//返回解密字符串
    	return new String(_message_de);
    }
    
    /**
     * 解密首先通过rsa加密，再对加密后的byte进行base64编码的密文
     * @param publicKey		公钥
     * @param base64		需要解密的密文信息
     * @return
     * @throws Exception
     */
    public static String decryptRsaFromBase64(RSAPublicKey publicKey, String base64) throws Exception{
    	
    	//首先进行base64的解码
    	byte[] _base64_de = safeUrlBase64Decode(base64);
    	
    	//再进行rsa解密
    	byte[] _message_de = decrypt(publicKey, _base64_de);
    	
    	//返回解密字符串
    	return new String(_message_de);
    }
    
    /** 
     * 私钥解密过程 
     *  
     * @param privateKey 
     *            私钥 
     * @param cipherData 
     *            密文数据 
     * @return 明文 
     * @throws Exception 
     *             解密过程中的异常信息 
     */  
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)  
            throws Exception {  
        if (privateKey == null) {  
            throw new Exception("解密私钥为空, 请设置");  
        }  
        int result_size = privateKey.getModulus().bitLength()/8; 
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            cipher.init(Cipher.DECRYPT_MODE, privateKey);  
            byte[] output = null;
            //分段数据
            byte[][] split = splitByteArray(cipherData, result_size);
            for(int i = 0; i < split.length; i++){
            	byte[] temp = cipher.doFinal(split[i]);
            	if(output == null){
            		output = temp;
            	}else{
            		output = concat(output, temp);
            	}
            }    
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此解密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("解密私钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("密文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("密文数据已损坏");  
        }  
    }  
  
    /**
     * 公钥解密，支持分段解密  
     * @param publicKey
     * @param cipherData
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData)  
            throws Exception {  
        if (publicKey == null) {  
            throw new Exception("解密公钥为空, 请设置");  
        }  
        //计算可支持的明文最大长度
        int result_size = publicKey.getModulus().bitLength()/8;        
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            cipher.init(Cipher.DECRYPT_MODE, publicKey);  
            byte[] output = null;
            //分段数据
            byte[][] split = splitByteArray(cipherData, result_size);
            for(int i = 0; i < split.length; i++){
            	byte[] temp = cipher.doFinal(split[i]);
            	if(output == null){
            		output = temp;
            	}else{
            		output = concat(output, temp);
            	}
            }            
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此解密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("解密公钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("密文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("密文数据已损坏");  
        }  
    } 
    
    /**
     * 将数组分成几段
     * @param data		需要分段的数据
     * @param size		每段的最大大小
     * @return
     */
    private static final byte[][] splitByteArray(byte[] data, int size){
    	if(data == null && size <= 0){return null;}
    	if(data.length <= size){return new byte[][]{data};}
    	byte[][] result = new byte[new Double(Math.ceil(new Double(data.length)/size)).intValue()][];    	
    	byte[] temp = null;
    	int index = 0;
    	for(int i = 0; i < data.length; i++){
    		if(i % size == 0){
    			if(temp == null){
    				temp = new byte[size];
    			}else{
    				result[index++] = temp;
    				if(data.length - index * size > size){
    					temp = new byte[size];
    				}else{
    					temp = new byte[data.length - index * size];
    				}
    			}
    		}
    		temp[i%size]=data[i];
    	}
    	result[index] = temp;
    	return result;
    }
    
    /**
     * 合并两个数组
     * @param a
     * @param b
     * @return
     */
    static byte[] concat(byte[] a, byte[] b) {  
    	byte[] c= new byte[a.length+b.length];  
        System.arraycopy(a, 0, c, 0, a.length);  
        System.arraycopy(b, 0, c, a.length, b.length);  
        return c;  
     }  
    
    public static String bytesToHexString(byte[] src){   
        StringBuilder stringBuilder = new StringBuilder("");   
        if (src == null || src.length <= 0) {   
            return null;   
        }   
        for (int i = 0; i < src.length; i++) {   
            int v = src[i] & 0xFF;   
            String hv = Integer.toHexString(v);   
            if (hv.length() < 2) {   
                stringBuilder.append(0);   
            }   
            stringBuilder.append(hv);   
        }   
        return stringBuilder.toString();   
    }   
    /**  
     * Convert hex string to byte[]  
     * @param hexString the hex string  
     * @return byte[]  
     */  
    public static byte[] hexStringToBytes(String hexString) {   
        if (hexString == null || hexString.equals("")) {   
            return null;   
        }   
        hexString = hexString.toUpperCase();   
        int length = hexString.length() / 2;   
        char[] hexChars = hexString.toCharArray();   
        byte[] d = new byte[length];   
        for (int i = 0; i < length; i++) {   
            int pos = i * 2;   
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
        }   
        return d;   
    } 
    private static byte charToByte(char c) {   
        return (byte) "0123456789ABCDEF".indexOf(c);   
    }

}
