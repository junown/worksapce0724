/*
1. 강의별 수강생 수 구하기

각 강의 제목별로 수강한 수강생 수를 조회하시오.

단, 수강생이 1명 이상인 강의만 조회하고, 수강생 수가 많은 순으로 정렬하시오.
*/
SELECT L.TITLE AS "강의명", COUNT(E.STUDENT_ID) AS "수강생 수"
FROM TB_ENROLLMENT E
JOIN TB_LECTURE L ON (E.LECTURE_ID = L.LECTURE_ID)
GROUP BY L.TITLE
ORDER BY COUNT(E.STUDENT_ID) DESC;



--2. 특정 학생의 수강 내역 조회

--STUDENT_ID = 1001인 학생이 수강 중인 강의 제목, 강사 이름, 수강 상태를 조회하시오.

--수강 상태가 'Y'인 항목만 출력합니다.


SELECT L.TITLE AS "강의 제목", T.NAME AS "강사 이름", E.STATUS AS "수강 상태"
FROM TB_STUDENT S
LEFT JOIN TB_ENROLLMENT E ON (S.STUDENT_ID = E.STUDENT_ID)
LEFT JOIN TB_LECTURE L ON (L.LECTURE_ID = E.LECTURE_ID)
LEFT JOIN TB_TEACHER T ON (T.TEACHER_ID = L.TEACHER_ID)
WHERE S.STUDENT_ID = 1001;


/*
3. 전공별 강의 개수 집계

TEACHER 테이블의 전공(SUBJECT)별로 담당 중인 강의 개수를 구하시오.

강의가 없는 전공은 제외하고, 결과를 전공명 기준으로 오름차순 정렬하세요.
*/
SELECT SUBJECT AS "전공", COUNT(TITLE) AS "강의 개수"
FROM TB_TEACHER
JOIN TB_LECTURE USING (TEACHER_ID)
WHERE LECTURE_ID IS NOT NULL
GROUP BY SUBJECT
ORDER BY COUNT(TITLE) DESC;

/*
4. 평균 점수가 80점 이상인 강의만 조회

각 강의별 수강생들의 GRADE_SCORE 평균을 계산한 후,

평균 점수가 80점 이상인 강의의 제목과 평균 점수를 조회하시오.

점수는 소수점 첫째 자리까지 출력하고, 평균 점수 내림차순으로 정렬하세요.
*/
SELECT TITLE AS "강의 제목", ROUND(AVG(GRADE_SCORE), 1) AS "평균 점수"
FROM TB_ENROLLMENT
JOIN TB_LECTURE USING (LECTURE_ID)
GROUP BY TITLE
HAVING AVG(GRADE_SCORE) > 80
ORDER BY AVG(GRADE_SCORE);


/*
5. 수강기록 등록 시, 강의별 수강 인원 수 자동 집계 트리거

새로운 수강기록(ENROLLMENT)이 등록될 때마다,

해당 강의(LECTURE)의 수강인원(INDEX_COUNT) 컬럼 값을 자동으로 1 증가시키는 트리거를 작성하시오.

또한 수강기록이 삭제될 경우, 해당 값은 1 감소해야 한다.
*/
SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER TRG_01
AFTER INSERT
ON TB_ENROLLMENT
FOR EACH ROW
BEGIN
    IF(:NEW.STATUS = 'Y')
         THEN UPDATE TB_LECTURE
         SET INDEX_COUNT = INDEX_COUNT + :NEW.LECTURE_ID
          WHERE LECTURE_ID = :NEW.LECTURE_ID;
        ELSE
            UPDATE TB_PRODUCT
            SET INDEX_COUNT = INDEX_COUNT - :NEW.LECTURE_ID
            WHERE LECTURE_ID = :NEW.LECTURE_ID;
        END IF;
END;
/