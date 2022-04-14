package com.pepole.tesusaku.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pepole.tesusaku.model.MUser;
import com.pepole.tesusaku.repository.UserMapper;
import com.pepole.tesusaku.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserMapper mapper;

	private final PasswordEncoder passwordEncoder;
	
	/** ユーザー登録 */
	@Override
	public void signup(MUser user) {
		// パスワード暗号化
		String rawPassword = user.getPassword();
		user.setPassword(passwordEncoder.encode(rawPassword));

		mapper.insertOne(user);
	}

	/** 他ユーザー全件取得 */
	@Override
	public List<MUser> getOthers(String userId) {
		return mapper.findByUserNot(userId);
	}

	/** ユーザー全件取得 */
	@Override
	public List<MUser> getAllUsers() {
		return mapper.findAll();
	}

	/** ユーザー取得 */
	@Override
	public List<MUser> getUsers(MUser user) {
		return mapper.findMany(user);
	}

	/** ユーザー取得(1件) */
	@Override
	public MUser getUserOne(String userId) {
		return mapper.findOne(userId);
	}

	/** ユーザー更新(1件) */
	@Override
	public void updateUserOne(String userId,
			String password,
			String userName) {

		// パスワード暗号化
		String encryptPassword = passwordEncoder.encode(password);

		mapper.updateOne(userId, encryptPassword, userName);
	}

	/** ユーザー削除(1件) */
	@Override
	public void deleteUserOne(String userId) {
		mapper.deleteOne(userId);
	}

	/** ログインユーザー情報取得 */
	@Override
	public MUser getLoginUser(String userId) {
		return mapper.findLoginUser(userId);
	}
}
