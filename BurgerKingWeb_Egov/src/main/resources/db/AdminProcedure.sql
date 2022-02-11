-- admin 로그인 체크 프로시저
create or replace procedure b_adminCheck(
   p_id in member.id%type,
   p_rc out SYS_REFCURSOR  
)
is

begin
   open p_rc for
      select * from admin where id=p_id;
end;


-- 페이징을 위한 getAllCount 프로시저(****미사용****)
create or replace PROCEDURE b_getAllCount(
    p_count out number,  -- 매개변수
    p_tablename in varchar2,
    p_fieldname in varchar2,
    p_key in varchar2
)
IS
    vs_count number;    -- 프로시저 내에 선언안 지역 변수
    v_query varchar2(300);
begin
    v_query := 'select count(*) as cnt into vs_count from ' ||p_tablename|| ' where p_fieldname like %'||p_key||'%';
    execute immediate v_query;
    p_count := vs_count;
end;


-- member용 getAllCount 프로시저
create or replace PROCEDURE b_getAllCountMem(
    p_count out number  -- 매개변수
)
IS
    vs_count number;    -- 프로시저 내에 선언안 지역 변수
begin
    select count(*) as cnt into vs_count from member;
    p_count := vs_count;
end;


-- mseq 이용, member불러오는 프로시저
create or replace PROCEDURE b_listMember(
    p_startNum NUMBER,
    p_endNum NUMBER,
    p_key member.name%TYPE,
    p_rc OUT SYS_REFCURSOR)
IS
begin
    OPEN p_rc For
        select * from (
        select * from (
        select rownum as rn, m. * from ((select*from member where name  like '%'||p_key||'%' order by mseq desc) m)
        ) where rn >= p_startNum
        ) where rn <= p_endNum;
end;



-- 배달이 진행중인 상품이 있을 경우 삭제가 되지 않게 하는 프로시저
create or replace procedure b_getOrderListResult2(
   p_id in orders.id%type,
   p_result in number,
   p_rc out SYS_REFCURSOR  
)
is

begin
   open p_rc for
      select * from order_view where id=p_id and p_result in ('2','3');
end;


-- 멤버삭제 프로시저
create or replace procedure b_deleteMember(
   p_mseq in member.mseq%type 
)
is

begin
   delete from member where mseq=p_mseq;
   commit;
end;

-- event항목의 페이징을 위한 getAllCount
create or replace PROCEDURE b_getAllCountEvent(
    p_count out number  -- 매개변수
)
IS
    vs_count number;    -- 프로시저 내에 선언안 지역 변수
begin
    select count(*) as cnt into vs_count from event;
    p_count := vs_count;
end;

-- eseq이용, 이벤트 리스트를 불러오는 프로시저
create or replace PROCEDURE b_listEvent(
    p_startNum NUMBER,
    p_endNum NUMBER,
    p_key event.subject%TYPE,
    p_rc OUT SYS_REFCURSOR)
IS
begin
    OPEN p_rc For
        select * from (
        select * from (
        select rownum as rn, e. * from ((select*from event where subject like '%'||p_key||'%' order by eseq desc) e)
        ) where rn >= p_startNum
        ) where rn <= p_endNum;
end;

-- 이벤트 상세보기 프로시저
create or replace PROCEDURE b_getEvent(
    p_eseq event.eseq%TYPE,
    p_rc OUT SYS_REFCURSOR)
IS
begin
    OPEN p_rc For
        select * from event where eseq=p_eseq;
end;


-- 이벤트 입력 프로시저
create or replace procedure b_insertEvent(
   p_subject in event.subject%type,
   p_content in event.content%type,
   p_enddate in event.enddate%type,
   p_image in event.image%type,
   p_state in event.state%type,
   p_thumbnail in event.thumbnail%type  
)
is

begin
   insert into event(eseq, subject, content, startdate, enddate, image, state, thumbnail)
		values(eseq.nextVal, p_subject, p_content, sysdate, to_Date(p_enddate,'yyyy-MM-dd'), p_image, p_state , p_thumbnail);
   commit;
end;

--admin_shortPro_페이징
create or replace PROCEDURE b_getShortProductAllCount(
    p_key in number,
	p_count out number  
)
IS
    vs_count number;    
begin
    select count(*) as cnt into vs_count from product where pname like '%'||p_key||'%' and kind='4';
    p_count := vs_count;
end;

--admin_shortPro_리스트
create or replace PROCEDURE b_listShortProduct(
    p_startNum NUMBER,
    p_endNum NUMBER,
    p_key product.pname%TYPE,
    p_rc OUT SYS_REFCURSOR)
IS
begin
    OPEN p_rc For
        select * from (
        select * from (
        select rownum as rn, p. * from ((select*from product where pname like '%'||p_key||'%' and kind3='4' order by pseq desc) p)
        ) where rn >= p_startNum
        ) where rn <= p_endNum;
end;

--adminPro_페이징
create or replace PROCEDURE b_getProductAllCount(
   p_key in number, 
	p_count out number  
)
IS
    vs_count number;    
begin
    select count(*) as cnt into vs_count from product where  pname like '%'||p_key||'%' and kind3 in ('1','2','3');
    p_count := vs_count;
end;

--adminPro_리스트
create or replace PROCEDURE b_listProduct(
    p_startNum NUMBER,
    p_endNum NUMBER,
    p_key product.pname%TYPE,
    p_rc OUT SYS_REFCURSOR)
IS
begin
    OPEN p_rc For
        select * from (
        select * from (
        select rownum as rn, p. * from ((select*from product where pname like '%'||p_key||'%' and kind3<'4' order by pseq desc) p)
        ) where rn >= p_startNum
        ) where rn <= p_endNum;
end;

-- 이벤트 삭제 프로시저
create or replace procedure b_deleteEvent(
   p_eseq in event.eseq%type 
)
is
begin
   delete from event where eseq = p_eseq;
   commit;
end;

--이벤트 업데이트 프로시저
create or replace procedure b_updateEvent(
   p_eseq in event.eseq%type,
   p_subject in event.subject%type,
   p_content in event.content%type,
   p_enddate in event.enddate%type,
   p_image in event.image%type,
   p_state in event.state%type,
   p_thumbnail in event.thumbnail%type  
)
is

begin
    update event set subject=p_subject, content=p_content, image=p_image, enddate=to_Date(p_enddate,'yyyy-MM-dd'), state=p_state, 
    thumbnail=p_thumbnail where eseq=p_eseq;
    commit;
end;

--admin_pro_삭제
create or replace procedure b_deleteProduct(
   p_pseq in product.pseq%type 
)
is

begin
   delete from product where pseq=p_pseq;
   commit;
end;

--admin_pro_상세보기
create or replace procedure b_productDetail(
   p_pseq in product.PSEQ%type,
   p_rc out SYS_REFCURSOR  
)
is
begin
   open p_rc for
      select * from product where PSEQ=p_pseq;
  end;  

  
  -- qna 페이징을 위한 프로시저
create or replace PROCEDURE b_getAllCountQna(
    p_count out number  -- 매개변수
)
IS
    vs_count number;    -- 프로시저 내에 선언안 지역 변수
begin
    select count(*) as cnt into vs_count from qna;
    p_count := vs_count;
end;

-- 페이징이 적용된 listQna
create or replace PROCEDURE b_adminListQna(
    p_startNum NUMBER,
    p_endNum NUMBER,
    p_key qna.subject%TYPE,
    p_rc OUT SYS_REFCURSOR)
IS
begin
    OPEN p_rc For
        select * from (
        select * from (
        select rownum as rn, q. * from ((select*from qna where id like '%'||p_key||'%' order by qseq desc) q)
        ) where rn >= p_startNum
        ) where rn <= p_endNum;
end;

-- 회원 orderList 페이징을 위한 프로시저
create or replace PROCEDURE b_getAllCountOrderMem(
    p_count out number  -- 매개변수
)
IS
    vs_count number;    -- 프로시저 내에 선언안 지역 변수
begin
    select count(*) as cnt into vs_count from order_view;
    p_count := vs_count;
end;

-- 비회원 orderList 페이징을 위한 프로시저
create or replace PROCEDURE b_getAllCountOrderNonmem(
    p_count out number  -- 매개변수
)
IS
    vs_count number;    -- 프로시저 내에 선언안 지역 변수
begin
    select count(*) as cnt into vs_count from order_view2;
    p_count := vs_count;
end;

-- 회원 orderlist를 위한 프로시저
create or replace PROCEDURE b_adminListOrder(
    p_startNum NUMBER,
    p_endNum NUMBER,
    p_key order_view.mname%TYPE,
    p_rc OUT SYS_REFCURSOR)
IS
begin
    OPEN p_rc For
        select * from (
        select * from (
        select rownum as rn, o. * from ((select*from order_view where mname like '%'||p_key||'%' order by result, odseq desc) o)
        ) where rn >= p_startNum
        ) where rn <= p_endNum;
end;


-- 비회원 orderlist를 위한 프로시저
create or replace PROCEDURE b_adminListOrder2(
    p_startNum NUMBER,
    p_endNum NUMBER,
    p_key order_view2.mname%TYPE,
    p_rc OUT SYS_REFCURSOR)
IS
begin
    OPEN p_rc For
        select * from (
        select * from (
        select rownum as rn, o. * from ((select*from order_view2 where mname like '%'||p_key||'%' order by result, odseq desc) o)
        ) where rn >= p_startNum
        ) where rn <= p_endNum;
end;


-- odseq 값으로 result값 가져오는 프로시저
create or replace PROCEDURE b_adminGetResult(
    p_odseq order_detail.odseq%TYPE,
    p_rc OUT SYS_REFCURSOR
    )
IS
begin
    OPEN p_rc For
        select result from order_detail where odseq = p_odseq;
end;

-- 불러온 odseq값으로 result값에 +1을 추가하는 프로시저
create or replace PROCEDURE b_updateOrderResult(
    p_result order_detail.result%TYPE,
    p_odseq order_detail.odseq%TYPE
    )
IS
begin
    update order_detail set result = p_result where odseq = p_odseq;
end;