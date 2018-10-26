# Group4_SlackBotService

Fridend Class
친구 이름 나이 성별

CommandExecuteService Class : 파싱된 문자열을 전달 받아 명령어에 알맞는 문자열 반환

successMessage(이름, 명령어) : 명령어가 올바르게 실행되었을 경우, 메시지 출력
add(데이터베이스, 이름, 나이, 성별) : 전달 받은 이름, 나이, 성별을 통해 Friend 인스턴스 생성 후 데이터베이스에 저장 후 sucessMessage 호출
remove(데이터베이스, 이름) : 전달 받은 데이터베이스에서 이름 검색 후, 삭제 후 successMessage 호출
find(데이터베이스, 이름) : 전달 받은 데이터베이스에서 이름 검색 후, 결과 반환
list(데이터베이스) : 전달 받은 데이터베이스에 저장되어 있는 목록 반환
