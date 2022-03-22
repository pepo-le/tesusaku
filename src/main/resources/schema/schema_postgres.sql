/* ユーザーマスタ */
CREATE TABLE IF NOT EXISTS m_user (
    user_id VARCHAR(50) PRIMARY KEY ,
    password VARCHAR(100) ,
    user_name VARCHAR(50) ,
    role VARCHAR(50)
)
;

/* テストスイートテーブル */
CREATE TABLE IF NOT EXISTS t_testsuite (
    suite_id SERIAL ,
    suite_name VARCHAR(200) ,
    admin_id VARCHAR(50) ,
    PRIMARY KEY(suite_id) ,
    FOREIGN KEY(admin_id) REFERENCES m_user(user_id)
)
;

/* テストケーステーブル */
CREATE TABLE IF NOT EXISTS t_testcase (
    suite_id INT ,
    case_id INT ,
    case_name VARCHAR(50) ,
    condition VARCHAR(1000) ,
    process VARCHAR(2000) ,
    expect VARCHAR(1000) ,
    result SMALLINT ,
    check_date DATE ,
    check_ver VARCHAR(100) ,
    defect_no VARCHAR(100) ,
    tester VARCHAR(100) ,
    comment VARCHAR(2000) ,
    PRIMARY KEY(suite_id, case_id) ,
    FOREIGN KEY(suite_id) REFERENCES t_testsuite(suite_id)
)
;
ALTER TABLE
	t_testcase
ADD UNIQUE
	(suite_id, case_id)
	;

/* アサインテーブル */
CREATE TABLE IF NOT EXISTS t_assign (
    suite_id INT ,
    user_id VARCHAR(50) ,
    PRIMARY KEY(suite_id, user_id) ,
    FOREIGN KEY(suite_id) REFERENCES t_testsuite(suite_id) ,
    FOREIGN KEY(user_id) REFERENCES m_user(user_id)
)
;