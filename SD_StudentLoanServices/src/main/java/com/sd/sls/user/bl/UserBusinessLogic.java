package com.sd.sls.user.bl;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.user.constants.UserConstants;
import com.sd.sls.user.dao.IUserDAO;
import com.sd.sls.user.model.User;

@Service
public class UserBusinessLogic implements IUserBusinessLogic {

	@Autowired
	private IUserDAO userDAO;

//	@Autowired
//    private PasswordEncoder passwordEncoder;

	@Override
	public Map<String, Boolean> registerUser(Map<String, Object> userValues) {
		Map<String, Boolean> returnMap = new HashMap<>();
		User user = createUser(userValues);
		if (checkIfUserAlreadyExists(user)) {
			returnMap.put(UserConstants.USER_ALREADY_REGISTERED, true);
			return returnMap;
		}
		if (userDAO.registerUser(user) == 1) {
			returnMap.put(UserConstants.USER_REGISTERED, true);
		}
		return returnMap;
	}

	private User createUser(Map<String, Object> userValues) {
		User user = new User();
		user.setUserName(Objects.toString(userValues.get("userName")));
		user.setEmail(Objects.toString(userValues.get("email")));
		try {
			user.setPassword(encrypt(Objects.toString(userValues.get("password")), "mysecretkey12345"));
			System.out.println(encrypt(Objects.toString(userValues.get("password")), "mysecretkey12345"));
			System.out.println(decrypt(Objects.toString(userValues.get("password")), "mysecretkey12345"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setPhoneNumber(Long.valueOf(Objects.toString(userValues.get("phoneNumber"))));
		return user;
	}

	private boolean checkIfUserAlreadyExists(User user) {
		return userDAO.checkIfUserAlreadyExists(user);
	}

	public static String encrypt(String data, String secret) throws Exception {
		SecretKeySpec key = new SecretKeySpec(secret.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encrypted = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
	}

	public static String decrypt(String encryptedData, String secret) throws Exception {
		SecretKeySpec key = new SecretKeySpec(secret.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
		return new String(original);
	}
}
