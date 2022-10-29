# 리액터 개발자 도구 

### SimpleExample
- 명령형으로 작성된 코드 
- ExecutorService를 이용해 다른 스레드에서 특정 업무를 처리하게 함
- 스택 트레이스가 스레드를 넘어 출력될 수 없음을 보여줌

### ReactorSimpleExample
- 리액터로 작성된 코드
- Flux 형태로 생성된 데이터의 다섯 번째 원소를 가져오고자 함 (랜덤으로 인덱스 오류 발생)
- 스택 트레이스가 스레드를 넘어 출력될 수 없음을 보여줌

### ReactorDebuggingExample
- Hooks.onOperatorDebug()를 호출하여 의미있는 스택 트레이스 정보 생성

[정리내용](https://www.notion.so/2-bb0edb0e18d14fd09b205b199fafe731)