package com.pepole.tesusaku.service;

import java.util.List;

import com.pepole.tesusaku.model.MUser;

public interface UserService {

	/** ユーザー登録 */
	public void signup(MUser user);

	/** ユーザー全件取得 */
	public List<MUser> getAllUsers();

	/** 他ユーザー全件取得 */
	public List<MUser> getOthers(String userId);

	/** ユーザー取得 */
	public List<MUser> getUsers(MUser user);

	/** ユーザー取得(1件) */
	public MUser getUserOne(String userId);

	/** ユーザー更新(1件) */
	public void updateUserOne(String userId,
			String password,
			String userName);

	/** ユーザー削除(1件) */
	public void deleteUserOne(String userId);

	/** ログインユーザー情報取得 */
	public MUser getLoginUser(String userId);
}
