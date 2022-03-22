/* ユーザーマスタ */
INSERT INTO m_user (
	user_id
	, password
	, user_name
	, role
) VALUES
	('admin', '$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy', '管理者A', 'ROLE_ADMIN')
	, ('tester', '$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy', '実行者A', 'ROLE_TESTER')
;