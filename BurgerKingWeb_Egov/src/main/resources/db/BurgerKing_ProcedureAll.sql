-- ADMIN
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
    select count(*) as cnt into vs_count from product where pname like '%'||p_key||'%' and kind3='4';
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
        select rownum as rn, p. * from ((select*from product where pname like '%'||p_key||'%' and kind3 in ('1','2','3') order by pseq desc) p)
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

-----------------------------------------------------------------------------------------------------------------------------------------
-- QNA
-- qnaList 조회
create or replace procedure b_listQna(
    p_id in QNA.ID%type,
    p_rc out SYS_REFCURSOR    
)
is
begin
    open p_rc for
      select * FROM QNA where id=p_id order by qseq desc;
end;


-- qna작성
create or replace PROCEDURE b_insertQna
(   p_id IN QNA.ID%TYPE,
    p_subject IN QNA.subject%TYPE, 
    p_content IN QNA.content%TYPE,
    p_pass IN QNA.pass%TYPE
    
)
IS

BEGIN
    insert into QNA( qseq, id, subject, content, pass)
    values( qseq.nextVal, p_id, p_subject, p_content, p_pass);
    commit;
END;


--qna passcheck
create or replace PROCEDURE b_getpassChk
(   p_qseq in QNA.qseq%type,
    p_rc    OUT SYS_REFCURSOR)
IS

BEGIN
    OPEN p_rc FOR
        select * from Qna where qseq=p_qseq;
END;


-- qna view
create or replace procedure b_getQna(
    p_qseq in QNA.QSEQ%type,
    p_rc out SYS_REFCURSOR    
)
is
begin
    open p_rc for
      select * FROM QNA where qseq=p_qseq;
end;


--  qna 게시글삭제
create or replace procedure b_deleteQna(
    p_qseq in QNA.QSEQ%type  
)
is
begin
    delete from QNA where qseq=p_qseq;
end;



--  qna 댓글업데이트
create or replace procedure b_deleteQna(
    p_qseq in QNA.QSEQ%type,  
    p_reply in QNA.reply%type
)
is
begin
    update qna set reply=p_reply, rep='2' where qseq=p_qseq;
    commit;
end;


--  qna 댓글업데이트
create or replace procedure b_updateQna(
    p_qseq in QNA.QSEQ%type,  
    p_reply in QNA.reply%type
)
is
begin
    update qna set reply=p_reply, rep='2' where qseq=p_qseq;
    commit;
end;

-----------------------------------------------------------------------------------------------------------------------------------------
-- ADDRESS
-- 특정 유저의 내 주소지를 검색하는 프로시져
create or replace procedure b_getMyAddress(
	p_mseq in member.mseq%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from myaddress where mseq=p_mseq;
end;

-- member의 주소값 저장하는 프로시져
create or replace procedure b_setUserAddress(
	p_mseq in myaddress.mseq%type,
    p_zip_num in myaddress.zip_num%type,
    p_address in myaddress.address%type
)
is

begin
	insert into myaddress(mseq, zip_num, address) 
    values(p_mseq, p_zip_num, p_address);
    commit;
end;

-- 게스트 주소 설정 프로시져
create or replace procedure b_setGuestAddress(
	p_gseq in guest.gseq%type,
    p_zip_num in guest.zip_num%type,
    p_address in guest.address%type
)
is

begin
	update guest set zip_num=p_zip_num, address=p_address where gseq=p_gseq;
    commit;
end;

-- 해당 dong의 주소 검색 프로시져
create or replace procedure b_selectAddressByDong(
	p_dong in address.dong%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from address where dong like '%'||p_dong||'%';
end;

-- member 주소지 변경 프로시져
create or replace procedure b_updateUserAddress(
	p_mseq in myaddress.mseq%type,
    p_zip_num in myaddress.zip_num%type,
    p_address in myaddress.address%type
)
is

begin
	update myaddress set zip_num=p_zip_num, address=p_address where mseq=p_mseq;
    commit;
end;
-----------------------------------------------------------------------------------------------------------------------------------------
-- PRODDUCT
create or replace procedure b_getProductList(
	p_kind1 in product.kind1%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from product where kind1 = p_kind1;
end;

-- 해당 mseq의 추가 재료의 정보를 조회
create or replace PROCEDURE b_selectSubProductOrder(        
    p_mseq IN member.mseq%TYPE,    
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from subproduct_order where mseq = p_mseq and oseq = 0 order by cseq;
end; 

-- 해당 gseq의 추가 재료의 정보를 조회
create or replace PROCEDURE b_selectSubProductOrder2(        
    p_gseq IN guest.gseq%TYPE,    
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from subproduct_order where gseq = p_gseq and oseq = 0 order by cseq;
end; 

-- 해당 pseq 상품의 썸네일 메뉴를 가져오는 프로시져
create or replace PROCEDURE b_getDeliverydetail(        
    p_pseq IN product.pseq%TYPE,    
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from product where pseq = p_pseq and kind3=4;
end;

-- 해당 kind1,2값과 kind3(1~3)값을 가진 상품을 조회하는 프로시져
create or replace PROCEDURE b_getProductkind(        
    p_kind1 IN product.kind1%TYPE,    
    p_kind2 IN product.kind2%TYPE,    
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from product where kind1 = p_kind1 and kind2 = p_kind2 and kind3<4;
end;

-- 해당 pseq 값에 해당하는 메뉴 정보를 불러올 것.
create or replace PROCEDURE b_getProducts(        
    p_pseq IN product.pseq%TYPE,
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
       select * from product where pseq = p_pseq;
end;

-- sub_product의 추가 메뉴들을 조회하는 프로시져
create or replace PROCEDURE b_getSubProduct(        
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from sub_product order by kind2 desc;
end;

-- 해당 spseq값을 가진 추가 메뉴 목록을 가져오는 프로시져
create or replace PROCEDURE b_getSubProduct2(        
    p_spseq in sub_product.spseq%type,
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from sub_product where spseq = p_spseq;
end;

-- 회원 추가메뉴 주문을 생성하는 프로시져
create or replace PROCEDURE b_insertSubProductOrder(        
    p_cseq in cart.cseq%type,
    p_mseq in member.mseq%type,
    p_spseq in subproduct_order.spseq%type,
    p_sname in subproduct_order.sname%type,
    p_addprice in subproduct_order.addprice%type
)  
IS
BEGIN
    insert into subproduct_order(sposeq, cseq, spseq, sname, addprice, mseq)
		values(sposeq.nextVal ,p_cseq, p_spseq, p_sname,
		 p_addprice, p_mseq);
end;    

-- 비회원 추가메뉴 주문을 생성하는 프로시져
create or replace PROCEDURE b_insertSubProductOrderByGseq(        
    p_cseq in cart.cseq%type,
    p_gseq in guest.gseq%type,
    p_spseq in subproduct_order.spseq%type,
    p_sname in subproduct_order.sname%type,
    p_addprice in subproduct_order.addprice%type
)  
IS
BEGIN
    insert into subproduct_order(sposeq, cseq, spseq, sname, addprice, gseq)
		values(sposeq.nextVal ,p_cseq, p_spseq, p_sname,
		 p_addprice, p_gseq);
end; 

-- header product
create or replace procedure b_getProduct(
	p_kind1 in product.kind1%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from product where kind1 = p_kind1;
end;

-- 회원 주문 목록의 추가 재료만 가져오는 프로시져
create or replace PROCEDURE b_selectSubProductOrder3(        
    p_mseq IN member.mseq%TYPE,    
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from subproduct_order where mseq = p_mseq and oseq != 0 order by oseq;
end; 

-- 비회원 주문 목록의 추가 재료만 가져오는 프로시져
create or replace PROCEDURE b_selectSubProductOrder4(        
    p_gseq IN guest.gseq%TYPE,    
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from subproduct_order where gseq = p_gseq and oseq != 0 order by cseq;
end;

-- 아직 배달 종료되지 않은 주문들의 상태만 가져오는 프로시져 
create or replace PROCEDURE b_getResult(        
    p_odseq IN subproduct_order.odseq%TYPE,    
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    open p_rc for
        select result from order_detail where odseq = p_odseq;
end;

-- admin_pro 상품 추가시 맞는 종류가 선택되었는지 확인하는 프로시저
create or replace PROCEDURE b_selectProduct1(        
    p_kind1 IN product.kind1%TYPE,    
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from product where kind1 = p_kind1;
end;

-- admin_pro 상품 추가시 썸네일이 있는 상품인지 확인하는 프로시저
create or replace PROCEDURE b_selectProduct2(        
    p_kind1 IN product.kind1%TYPE,    
    p_kind2 IN product.kind2%TYPE,    
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from product where kind1 = p_kind1 and kind2 = p_kind2;
end;

--admin_pro  상품 추가 프로시저
create or replace PROCEDURE b_insertProduct(        
    p_kind1 in product.kind1%type,
    p_kind2 in product.kind2%type,
    p_kind3 in product.kind3%type,
    p_pname in product.pname%type,
    p_price1 in product.price1%type,
    p_price2 in product.price2%type,
    p_price3 in product.price3%type,
    p_content in product.content%type,
    p_image in product.image%type,
    p_useyn in product.useyn%type
)  
IS
BEGIN
    insert into product (pseq, kind1, kind2, kind3, pname, price1, price2, price3, content,
		image, useyn) values(pseq.nextVal, p_kind1, p_kind2, p_kind3, p_pname, p_price1, p_price2, p_price3, 
		p_content, p_image, p_useyn);
    commit;
end; 

--admin_pro 수정
create or replace PROCEDURE b_updateProduct
(     p_pseq IN product.pseq%TYPE,
      p_kind1 IN product.kind1%TYPE,
     p_kind2 IN product.kind2%TYPE,
     p_kind3 IN product.kind3%TYPE,
     p_pname IN product.pname%TYPE,
     p_price1 IN product.price1%TYPE,
     p_price2 IN product.price2%TYPE,
     p_price3 IN product.price3%TYPE,
     p_content IN product.content%TYPE,
     p_useyn IN product.useyn%TYPE,
     p_image IN product.image%TYPE
    
)
IS
BEGIN
    update product set kind1=p_kind1, kind2=p_kind2, kind3=p_kind3, pname=p_pname, price1=p_price1, 
    price2=p_price2, price3=p_price3, content=p_content, useyn=p_useyn, image=p_image where pseq=p_pseq;
    commit;
END;

-- oseq를 기준으로 추가메뉴주문 목록을 불러오는 프로시져
create or replace PROCEDURE b_selectSubProductOrder5(        
    p_oseq IN orders.oseq%TYPE,    
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from subproduct_order where oseq = p_oseq order by odseq;
end; 

-- subproduct odseq로 검색하는 프로시저
create or replace procedure b_selectSubProductOrder6(
   p_odseq in subproduct_order.odseq%type,
   p_rc out SYS_REFCURSOR  
)
is
begin
   open p_rc for
      select * from subproduct_order where odseq = p_odseq order by odseq;
end;

-- sposeq 이용 관리자 페이지에서 추가재료 삭제하는 프로시저
create or replace procedure b_deleteSpo(
   p_sposeq in subproduct_order.sposeq%type 
)
is
begin
   delete from subproduct_order where sposeq = p_sposeq;
   commit;
end;

-----------------------------------------------------------------------------------------------------------------------------------------
-- CART
create or replace procedure b_selectCart(
	p_id in member.id%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from cart_view where id=p_id and result='1';
end;

-- member cart 추가 프로시져
create or replace PROCEDURE b_insertCart(        
    p_id in member.id%type,
    p_pseq IN product.pseq%TYPE
)  
IS
BEGIN
    insert into cart( cseq , id, pseq) values( cseq.nextVal, p_id, p_pseq); 
    commit;
end;

-- 게스트 cart 추가를 위한 cseq.nextVal 조회 프로시져
create or replace PROCEDURE b_getCseq(        
    p_rc OUT number
)  
IS
BEGIN
       select cseq.nextVal into p_rc from dual;
end;

-- 해당 pseq값을 가지고 있는 카트를 최근 등록순으로 조회하는 프로시져 
create or replace PROCEDURE b_getPseqCart(        
    p_pseq in product.pseq%type,
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from cart_view where pseq = p_pseq order by indate desc;
end;

-- 특정 카트의 수량을 가져오는 프로시져
create or replace PROCEDURE b_getQuantity(        
    p_cseq in cart.cseq%type,
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select quantity from cart where cseq = p_cseq;
end;

-- 수량을 1개 감소시키는 프로시져
create or replace PROCEDURE b_minusQuantity(        
    p_cseq in cart.cseq%type
)  
IS
BEGIN
    update cart set quantity=quantity-1 where cseq = p_cseq;
    commit;
end;

-- 수량을 1개 증가시키는 프로시져
create or replace PROCEDURE b_plusQuantity(        
    p_cseq in cart.cseq%type
)  
IS
BEGIN
    update cart set quantity=quantity+1 where cseq = p_cseq;
    commit;
end;

-- 카트 하나를 제거하는 프로시져
create or replace PROCEDURE b_deleteCart(        
    p_cseq in cart.cseq%type
)  
IS
BEGIN
    delete from cart where cseq = p_cseq;
    delete from subproduct_order where cseq = p_cseq;
    commit;
end;

-----------------------------------------------------------------------------------------------------------------------------------------
-- ORDER
-- 회원의 주문목록을 가져오는 프로시져
create or replace procedure b_getOrderList(
	p_id in member.id%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from order_view where id=p_id and result in ('1', '2', '3');
end;

-- 비회원의 주문목록을 가져오는 프로시져
create or replace PROCEDURE b_getOrderListByGuest(        
    p_id IN guest.id%TYPE,    
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    open p_rc for
        select * from order_view2 where id=p_id and result in ('1', '2', '3');
end;

-- orders 테이블에 주문을 삽입하는 프로시져 이후 oseq 값을 반환한다.
create or replace PROCEDURE b_insertOrder(        
    p_id IN member.id%TYPE,    
    p_memberkind IN member.memberkind%TYPE,
    p_oseq OUT number
)  
IS
BEGIN
    insert into orders(oseq, id, memberkind)
    values(oseq.nextVal, p_id, p_memberkind);
    p_oseq := oseq.currVal;
    commit;
end;

-- order_detail 테이블에 주문 상세를 삽입하는 프로시져
-- 해당 카트번호를 가진 추가 메뉴의 oseq과 odseq 값도 변경해주고 카트를 비운다.
create or replace PROCEDURE b_insertOrderDetail(        
    p_oseq in orders.oseq%type,
    p_pseq in product.pseq%type,
    p_quantity in cart_view.quantity%type,
    p_cseq in cart_view.cseq%type
)  
IS
BEGIN
    insert into order_detail(odseq, oseq, pseq, quantity, result)
    values(odseq.nextVal, p_oseq, p_pseq, p_quantity, 1);
    update subproduct_order set oseq=p_oseq, odseq=odseq.currVal
    where cseq = p_cseq;
    delete from cart where cseq=p_cseq;
    commit;
end;

-- 해당 odseq값을 가진 주문상세의 진행상황을 가져오는 프로시져
create or replace PROCEDURE b_getOrderDetail(        
    p_odseq IN order_detail.odseq%TYPE,    
    p_rc OUT number
)  
IS
BEGIN
    select result into p_rc from order_detail where odseq = p_odseq;
end;

-- 선택한 주문을 삭제하는 프로시져
create or replace PROCEDURE b_deleteOrder2(        
    p_odseq IN order_detail.odseq%TYPE
)  
IS
    v_oseq number;
    v_cnt number;
BEGIN
    -- 해당 odseq값을 가진 주문상세의 oseq를 구함
    select oseq into v_oseq from order_detail where odseq=p_odseq;
    
    -- 해당 odseq의 주문상세와 추가메뉴를 삭제
    delete from order_detail where odseq=p_odseq;
    delete from subproduct_order where odseq=p_odseq;
    
    -- 구했던 oseq의 값으로 해당 oseq값을 가진 odseq가 남았는지 구함
    select count(*) into v_cnt from order_detail where oseq=v_oseq;
    
    -- 구한 갯수가 0이면 해당 주문은 주문 상세가 더 없으므로 주문을 삭제한다.
    if v_cnt = 0 then
        delete from orders where oseq=v_oseq;
    end if;
end;

-- 비회원의 처리단계에 있는 주문을 가져오는 프로시져
create or replace procedure b_getOrderByOseq(
   p_oseq in orders.oseq%type,
   p_rc out SYS_REFCURSOR  
)
is
begin
   open p_rc for
      select * from order_view2 where oseq = p_oseq and result in ('1', '2', '3');
end;

-- orderDetail의 삭제
create or replace procedure b_deleteOrderDetail(
   p_odseq in order_detail.odseq%type 
)
is
begin
   delete from order_detail where odseq = p_odseq;
   commit;
end;

-- 추가재료의 삭제
create or replace procedure b_deleteSpo(
   p_odseq in order_detail.odseq%type 
)
is
begin
   delete from order_detail where odseq = p_odseq;
   commit;
end;

-- oseq 이용, order detail이 남아있는지 확인
create or replace procedure b_getOrderDetailByOseq(
   p_oseq in order_detail.oseq%type,
   p_rc out SYS_REFCURSOR  
)
is
begin
   open p_rc for
      select * from order_detail where oseq = p_oseq;
end;

-- orders 테이블의 내용 삭제
create or replace procedure b_deleteOrders(
   p_oseq in orders.oseq%type 
)
is
begin
   delete from orders where oseq = p_oseq;
   commit;
end;


-- orderView 검색 프로시저(회원)
create or replace procedure b_getOrder_view(
   p_odseq in order_view.odseq%type,
   p_rc out SYS_REFCURSOR  
)
is
begin
   open p_rc for
      select * from order_view where odseq = p_odseq;
end;

-- orderView2 검색 프로시저(비회원)
create or replace procedure b_getOrder_view2(
   p_odseq in order_view2.odseq%type,
   p_rc out SYS_REFCURSOR  
)
is
begin
   open p_rc for
      select * from order_view2 where odseq = p_odseq;
end;

-----------------------------------------------------------------------------------------------------------------------------------------
-- EVENT
--eventList
create or replace PROCEDURE b_getAllEvents(        
    p_rc OUT   SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
    SELECT * FROM event order by eseq asc;
end; 
--eventTab2 
create or replace PROCEDURE b_getOngoingEvents(        
    p_rc OUT   SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
    SELECT * FROM event where state = 1 order by eseq asc;
end; 
  
--eventTab3 
create or replace PROCEDURE b_getPastEvents(        
    p_rc OUT   SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
    SELECT * FROM event where state = 0 order by eseq asc;
end; 
--eventDetail
create or replace PROCEDURE b_getDetailEvent(        
    p_eseq IN EVENT.eseq%TYPE,    
    p_rc OUT   SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
    SELECT * FROM event where eseq=p_eseq;
end; 
-----------------------------------------------------------------------------------------------------------------------------------------
-- MEMBER
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
create or replace procedure b_deleteMember(
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