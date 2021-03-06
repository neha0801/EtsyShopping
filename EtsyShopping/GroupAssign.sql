---user table

  CREATE TABLE "TESTDB"."ETSYUSER" 
   (	"USER_ID" NUMBER(10,0) NOT NULL ENABLE, 
	"NAME" VARCHAR2(255 BYTE) NOT NULL ENABLE, 
	"EMAIL" VARCHAR2(255 BYTE) NOT NULL ENABLE, 
	"PASSWORD" VARCHAR2(255 BYTE) NOT NULL ENABLE, 
	"CREDIT" NUMBER(10,2), 
	 CONSTRAINT "ETSYUSER_PK" PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE OR REPLACE TRIGGER "TESTDB"."ETSYUSER_TRG" 
BEFORE INSERT ON ETSYUSER 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING THEN
      SELECT ETSYUSER_SEQ.NEXTVAL INTO :NEW.USER_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."ETSYUSER_TRG" ENABLE;

-- Items Table


 
  CREATE TABLE "TESTDB"."ETSYITEM" 
   (	"ITEM_ID" NUMBER(20,0) NOT NULL ENABLE, 
	"ITEM_NAME" VARCHAR2(255 BYTE) NOT NULL ENABLE, 
	"ITEM_DESCRIPTION" VARCHAR2(1000 BYTE), 
	"ITEM_PRICE" NUMBER(10,2) NOT NULL ENABLE, 
	"ITEM_SHIPPINGCOST" NUMBER(10,2), 
	"ITEM_INSTOCK" NUMBER(1,0) NOT NULL ENABLE, 
	"USER_ID" NUMBER(10,0), 
	"ITEN_PICTURE" VARCHAR2(1000 BYTE), 
	 CONSTRAINT "ETSYITEM_PK" PRIMARY KEY ("ITEM_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "FK_USER" FOREIGN KEY ("USER_ID")
	  REFERENCES "TESTDB"."ETSYUSER" ("USER_ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE OR REPLACE TRIGGER "TESTDB"."ETSYITEM_TRG" 
BEFORE INSERT ON ETSYITEM 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING THEN
      SELECT ETSYITEM_SEQ.NEXTVAL INTO :NEW.ITEM_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."ETSYITEM_TRG" ENABLE;

--- Cart Table

  CREATE TABLE "TESTDB"."ETSYCART" 
   (	"CART_ID" NUMBER(10,0) NOT NULL ENABLE, 
	"ITEM_ID" NUMBER(10,0) NOT NULL ENABLE, 
	"CART_STATUS" NUMBER(1,0) NOT NULL ENABLE, 
	"ORDER_DATE" DATE, 
	"QUANTITY" NUMBER(10,0), 
	"TOTALPRICE" NUMBER(10,2), 
	"USER_ID" NUMBER(10,0), 
	 CONSTRAINT "ETSYCART_PK" PRIMARY KEY ("CART_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "FK_ITEM" FOREIGN KEY ("ITEM_ID")
	  REFERENCES "TESTDB"."ETSYITEM" ("ITEM_ID") ENABLE, 
	 CONSTRAINT "FK_USER11" FOREIGN KEY ("USER_ID")
	  REFERENCES "TESTDB"."ETSYUSER" ("USER_ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
  
  
  CREATE OR REPLACE TRIGGER "TESTDB"."ETSYCART_TRG" 
BEFORE INSERT ON ETSYCART 
FOR EACH ROW 
  BEGIN
:new.cart_id := ETSYCART_SEQ.nextVal();
:new.order_date := sysdate;
END;
/
ALTER TRIGGER "TESTDB"."ETSYCART_TRG" ENABLE;

