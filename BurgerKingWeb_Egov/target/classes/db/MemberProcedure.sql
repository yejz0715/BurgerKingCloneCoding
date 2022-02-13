create or replace PROCEDURE b_insertMember
(   p_id IN MEMBER.ID%TYPE,
    p_pwd IN MEMBER.PWD%TYPE, 
    p_phone IN MEMBER.PHONE%TYPE,
    p_name IN MEMBER.NAME%TYPE
)
IS

BEGIN
    insert into member( mseq, id, pwd, phone, name)
    values( mseq.nextVal, p_id, p_pwd, p_phone, p_name);
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

-- 회원 삭제 프로시져
create or replace procedure b_updateMember(
	p_mseq in member.mseq%type
)
is
    v_id member.id%type;
    v_oseq orders.oseq%type;
    list1 sys_refcursor;
begin
    -- mseq에 해당하는 id
	select id into v_id from member where mseq=p_mseq;
    
    --id에 해당하는 oseq 목록 찾기
    open list1 for 
        select oseq from orders where id=v_id;
        
    -- oseq값에 해당하는 주문 상세 삭제
    loop
        fetch list1 into v_oseq;
        exit when list1%notfound;
        delete from order_detail where oseq = v_oseq;
    end loop;
    
    -- 해당 id를 가진 cart order qna myaddress 지우기
    delete from cart where id=v_id;
    delete from orders where id=v_id;
    delete from qna where id=v_id;
    delete from myaddress where mseq=p_mseq;
    -- 해당 member를 지우기
    delete from member where mseq=p_mseq;
    commit;
end;

--findId
create or replace procedure b_findMember(
	p_name in member.name%type,
    p_phone in member.phone%type,
	p_rc out SYS_REFCURSOR  
)
is
begin
	open p_rc for
		select * from member where name=p_name and phone=p_phone;
end;

--findPwd
create or replace procedure b_findPwd(
	p_name in member.name%type,
    p_id in member.id%type,
	p_rc out SYS_REFCURSOR  
)
is
begin
	open p_rc for
		select * from member where name=p_name and id=p_id;
end;

--updatePwd
create or replace procedure b_updatePwd(
	p_pwd in member.pwd%type,
    p_mseq in member.mseq%type
)
is

begin
	update member set pwd=p_pwd where mseq=p_mseq;
end;

-- mesq 이용, getMember
create or replace PROCEDURE b_getMember2
(   p_mseq IN member.id%TYPE,
    p_rc    OUT SYS_REFCURSOR)
IS

BEGIN
    OPEN p_rc FOR
        SELECT * FROM MEMBER WHERE mseq=p_mseq;
END;

-- admin에서 memberUpdate
create or replace procedure b_adminUpdateMember(
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



-- 멤버 삭제 전 mseq 이용하여 myaddress도 삭제
create or replace procedure b_deleteAddress(
	p_mseq in myaddress.mseq%type
)
is
begin
	delete from myaddress where mseq = p_mseq;
    commit;
end;