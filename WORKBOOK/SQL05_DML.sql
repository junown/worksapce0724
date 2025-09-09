-- DML

-- 1
INSERT ALL
    INTO TB_CLASS (CLASS_NO, DEPARTMENT_NO, CLASS_TYPE) VALUES (1, 1, '전공필수')
    INTO TB_CLASS (CLASS_NO, DEPARTMENT_NO, CLASS_TYPE) VALUES (2, 2, '전공선택')
    INTO TB_CLASS (CLASS_NO, DEPARTMENT_NO, CLASS_TYPE) VALUES (3, 3, '교양필수')
    INTO TB_CLASS (CLASS_NO, DEPARTMENT_NO, CLASS_TYPE) VALUES (4, 4, '교양선택')
    INTO TB_CLASS (CLASS_NO, DEPARTMENT_NO, CLASS_TYPE) VALUES (5, 5, '논문지도')
SELECT * FROM DUAL;

-- 2
CREATE TABLE TB_학생일반정보 AS
(
    SELECT STUDENT_NO AS 학번,
           STUDENT_NAME AS 학생이름,
           STUDENT_ADDRESS AS 주소
    FROM TB_STUDENT
);
                                        
-- 3
CREATE TABLE TB_국어국문학과 AS
(
    SELECT STUDENT_NO AS 학번,
           STUDENT_NAME AS 학생이름,
             DECODE(SUBSTR(STUDENT_SSN, 8, 1),
                  '1', '19' || SUBSTR(STUDENT_SSN, 1, 2),
                  '2', '19' || SUBSTR(STUDENT_SSN, 1, 2),
                  '3', '20' || SUBSTR(STUDENT_SSN, 1, 2),
                  '4', '20' || SUBSTR(STUDENT_SSN, 1, 2)
           ) AS 출생년도,
           COACH_PROFESSOR_NO AS 교수이름
    FROM TB_STUDENT
    JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
    WHERE DEPARTMENT_NAME = '국어국문학과');
                                        
-- 4
UPDATE TB_DEPARTMENT
SET CAPACITY = ROUND(CAPACITY * 1.1);

-- 5
UPDATE TB_STUDENT
SET STUDENT_ADDRESS = '서울시 종로구 숭인동 181-21'
WHERE STUDENT_NO = 'A413042';

-- 6
UPDATE TB_STUDENT
SET STUDENT_SSN = SUBSTR(STUDENT_SSN, 1, 6);

-- 7
UPDATE TB_GRADE
SET POINT = 3.5
WHERE STUDENT_NO IN (SELECT STUDENT_NO
                                FROM TB_STUDENT
                                JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
                                WHERE STUDENT_NAME = '김명훈'
                                            AND DEPARTMENT_NAME = '의학과')
            AND CLASS_NO IN (SELECT CLASS_NO
                                FROM TB_STUDENT
                                JOIN TB_CLASS USING(DEPARTMENT_NO)
                                WHERE CLASS_NAME = '피부생리학')
            AND TERM_NO = 200501;
            
-- 8
UPDATE TB_GRADE
SET POINT = NULL
WHERE STUDENT_NO IN (
            SELECT STUDENT_NO
            FROM TB_STUDENT
            WHERE ABSENCE_YN = 'Y');
                                