CREATE TABLE S_RUNNING_SESSION 
(
  S_ID VARCHAR2(20 BYTE) NOT NULL 
, CLASS_CODE VARCHAR2(20 BYTE) NOT NULL 
, SECTION_CODE VARCHAR2(20 BYTE) NOT NULL 
, SESSION_CODE VARCHAR2(20 BYTE) NOT NULL 
, ROLL_NO NUMBER 
, PROMOTION_FLAG VARCHAR2(1 BYTE) 
, CONSTRAINT S_RUNNING_SESSION_PK PRIMARY KEY 
  (
    S_ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX S_RUNNING_SESSION_PK ON S_RUNNING_SESSION (S_ID ASC) 
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
