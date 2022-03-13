/* ユーザーマスタ */
INSERT INTO m_user (
user_id
, password
, user_name
, role
) VALUES
('admin', '$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy', 'システ
ム管理者', 'ROLE_ADMIN')
, ('user', '$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy', 'ユーザー
1', 'ROLE_TESTER')
;