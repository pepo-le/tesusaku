/* ユーザーマスタ */
CREATE TABLE IF NOT EXISTS m_user (
    user_id VARCHAR(50) PRIMARY KEY ,
    password VARCHAR(100) ,
    user_name VARCHAR(50) ,
    role VARCHAR(50)
);

/* テストスイートテーブル */
CREATE TABLE IF NOT EXISTS t_testsuite (
    suite_id INT AUTO_INCREMENT,
    suite_name VARCHAR(200) ,
    PRIMARY KEY(suite_id)
);

/* テストケーステーブル */
CREATE TABLE IF NOT EXISTS t_testcase (
    case_id INT AUTO_INCREMENT,
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
    testsuite_id INT ,
    PRIMARY KEY(case_id) ,
    FOREIGN KEY(testsuite_id) REFERENCES t_testsuite(suite_id)
);