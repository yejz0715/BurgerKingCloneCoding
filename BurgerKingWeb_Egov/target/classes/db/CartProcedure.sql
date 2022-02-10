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
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
       select cseq.nextVal from dual;
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