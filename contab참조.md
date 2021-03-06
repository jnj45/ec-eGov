# Crontab 참고

## 필드
| 필드 이름 | 허용 값 | 
| --- | --- |
| 초(seconds) |  0 ~ 59 |
| 분(minutes) |  0 ~ 59 |
| 시(hours) |  0 ~ 23 |
| 날(day-of-month) | 1 ~ 31 |
| 달(month) | 1 ~ 12 or JAN ~ DEC |
| 요일(day-of-week) | 1 ~ 7 or SUN-SAT |
| 년도 | 빈값, 1970 ~ 2099 |

## cron 표현식 특수문자
| 표현식 | 설명 | 예시 |
| ------ | ----- | ------ |
| * | 모든 수를 나타냄 | |
| - | 값의 사이를 의미 | * 10-13 * * * * 10,11,12,13분에 동작함 |
| , | 특정값 지칭 | * 10,11,13 * * * * 10,11,13분에 동작함 |
| / | 값의 증가를 표현 |  * 0/5 * * * * 0분부터 시작해서 5분마다 동작 |
| ? | 특별한 값이 없음을 나타냄(day-of-month, day-of-week 필드만 사용) | |
| L | 마지막 날을 나타냄(day-of-month, day-of-week 필드만 사용) | | 

## 예제
*초 분 시 일 월 주(년)
- "0 0 02 * * ?" : 매일 새벽 두시
- "0 0 12 * * ?" : 아무 요일, 매월, 매일 12:00:00
- "0 15 10 ? * *" : 모든 요일, 매월, 아무 날이나 10:15:00 
- "0 15 10 * * ?" : 아무 요일, 매월, 매일 10:15:00 
- "0 15 10 * * ? *" : 모든 연도, 아무 요일, 매월, 매일 10:15 
- "0 15 10 * * ? : 2005" 2005년 아무 요일이나 매월, 매일 10:15 
- "0 * 14 * * ?" : 아무 요일, 매월, 매일, 14시 매분 0초 
- "0 0/5 14 * * ?" : 아무 요일, 매월, 매일, 14시 매 5분마다 0초 
- "0 0/5 14,18 * * ?" : 아무 요일, 매월, 매일, 14시, 18시 매 5분마다 0초 
- "0 0-5 14 * * ?" : 아무 요일, 매월, 매일, 14:00 부터 매 14:05까지 매 분 0초 
- "0 10,44 14 ? 3 WED" : 3월의 매 주 수요일, 아무 날짜나 14:10:00, 14:44:00 
- "0 15 10 ? * MON-FRI" : 월~금, 매월, 아무 날이나 10:15:00 
- "0 15 10 15 * ?" : 아무 요일, 매월 15일 10:15:00 
- "0 15 10 L * ?" : 아무 요일, 매월 마지막 날 10:15:00 
- "0 15 10 ? * 6L" : 매월 마지막 금요일 아무 날이나 10:15:00 
- "0 15 10 ? * 6L 2002-2005" : 2002년부터 2005년까지 매월 마지막 금요일 아무 날이나 10:15:00 
- "0 15 10 ? * 6#3" : 매월 3번째 금요일 아무 날이나 10:15:00
