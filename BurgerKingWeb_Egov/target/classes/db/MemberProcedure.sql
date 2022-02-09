create or replace PROCEDURE b_insertMember
(   p_id IN MEMBER.ID%TYPE,
    p_mseq in MEMBER.MSEQ%type,
    p_pwd IN MEMBER.PWD%TYPE, 
    p_phone IN MEMBER.PHONE%TYPE,
    p_name IN MEMBER.NAME%TYPE
)
IS

BEGIN
    insert into member( mseq, id, pwd, phone, name)
    values( member_seq.nextVal, p_id, p_pwd, p_phone, p_name);
    commit;
END;

--

create or replace PROCEDURE b_getMember
(   p_id IN member.id%TYPE,
    p_rc    OUT SYS_REFCURSOR)
IS

BEGIN
    OPEN p_rc FOR
        SELECT * FROM MEMBER WHERE ID=p_id;
END;

-- 로그인 시 마지막 접속일(lastdate) 변경 프로시져
create or replace procedure b_lastDateUpdate(
	p_id in member.id%type
)
is

begin
	update member set lastdate=sysdate where id=p_id;
end;

-- gseq 다음 값 호출
create or replace procedure b_selectGseq(
	p_rc out guest.gseq%type
)
is
    v_gseq guest.gseq%type;
begin
    select GSEQ.nextval into v_gseq from dual;
    p_rc := v_gseq;
end;

-- 게스트 정보 테이블 삽입
create or replace procedure b_insertGuest(
	p_gseq in guest.gseq%type,
    p_id in guest.id%type,
    p_pwd in guest.pwd%type,
    p_name in guest.name%type,
    p_phone in guest.phone%type
)
is

begin
	insert into guest(gseq, id, pwd, name, phone)
    values(p_gseq, p_id, p_pwd, p_name, p_phone);
    commit;
end;

-- gseq값에 따른 게스트 정보 조회
create or replace procedure b_getGuest(
	p_gseq in guest.gseq%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from guest where gseq=p_gseq;
end;

-- 회원정보 변경 프로시져
create or replace procedure b_updateMember(
	p_id in member.id%type,
    p_pwd in member.pwd%type,
    p_name in member.name%type,
    p_phone in member.phone%type
)
is

begin
	update member set pwd=p_pwd, name=p_name, phone=p_phone where id=p_id;
    commit;
end;