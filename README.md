### :hotel:소개
<img src="https://github.com/Song0-0/yeah-nolja/assets/102652457/d277a95f-499d-4251-9d6c-8a414afb6f46" width="400" height="600"/>

```
국내 호텔 예약을 보다 편하게 이용해보세요.
가능한 날짜와 원하는 위치의 호텔을 알려드립니다.
```

### :star2:프로젝트 상세
```
- 국내 호텔 예약 사이트입니다.
- 호텔, 예약, 회원으로 크게 3가지 도메인으로 프로젝트를 구성하였습니다.
```

### :white_circle:ERD
<img src="https://github.com/Song0-0/yeah-nolja/assets/102652457/f1633af9-4d41-490d-a6a0-ec090d2521a0" width="900" height="500"/>

### :computer:기술스택
```
Java
Spring Boot
MySQL
MariaDB
Spring Data JPA
Gradle
Docker
```

### :triangular_flag_on_post:핵심 구현 내용
```
- HotelMapRepository 클래스 사용하여 Map DB로 구현. HotelJpaRepositoryClass 클래스 사용하여 순수JPA로 구현하였습니다.
- Controller에 클라이언트 응답용으로 RepositoryEntity사용하였습니다.
- PATCH를 적용하여 부분 수정이 된다는 것을 전제로하였고, 
부분 수정이 되기 위해서 서비스로직에서 분기문을 통해 요청값이 있는 컬럼만 업데이트 되게 구현하였습니다.
- Swagger 적용하였습니다.
- getHotel() 단건조회 시, Optional 사용하여 NullPointerException 방지, 
getAllHotels() 전체조회, 특정조건조회 시 stream()사용하여 List타입의 Entity를 List타입의 DTO로 변환하였습니다.
- LOG LEVEL 설정하였고, @Slf4j 사용과 Logger 직접 생성하여 사용하였습니다.
- 상품(호텔) 단위테스트 적용하였습니다.
- 특정 호텔지역 기준 조회 API는 제거, 
호텔 전체 조회에서 매개변수로 지역명, 체크인 날짜, 체크아웃 required = false로 받아서 처리하였습니다.
- JWT 적용하였습니다.
```


### :trophy:프로젝트를 하면서
```
개인적으로 처음부터 끝까지 만들어 본 첫 프로젝트입니다. 
주어진 요구사항을 바탕으로 만드는 것이 아니라 설계, 기획부터 스스로 해보았습니다. 
DB에서 테이블 간의 관계에 대해 이해하는 것에 어려움이 있었지만, 
정답은 없고 구현이 가능하도록 만고 고도화를 해 나가며 효율도가 항샹된다는 것을 알게 되었습니다.
```
