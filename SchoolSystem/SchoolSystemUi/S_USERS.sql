CREATE TABLE S_USERS 
(
  USER_ID VARCHAR2(20 BYTE) NOT NULL 
, USER_EMAIL VARCHAR2(50 BYTE) NOT NULL 
, PASSWORD VARCHAR2(20 BYTE) 
, ROLE VARCHAR2(20 BYTE) 
, NICK_NAME VARCHAR2(40 BYTE) 
, CONSTRAINT S_USERS_PK PRIMARY KEY 
  (
    USER_ID 
  , USER_EMAIL 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX S_USERS_PK ON S_USERS (USER_ID ASC, USER_EMAIL ASC) 
      LOGGING 
      TABLESPACE SYSTEM 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        INITIAL 65536 
        NEXT 1048576 
        MINEXTENTS 1 
        MAXEXTENTS UNLIMITED 
        FREELISTS 1 
        FREELIST GROUPS 1 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE SYSTEM 
PCTFREE 10 
PCTUSED 40 
INITRANS 1 
STORAGE 
( 
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1 
  MAXEXTENTS UNLIMITED 
  FREELISTS 1 
  FREELIST GROUPS 1 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS;
