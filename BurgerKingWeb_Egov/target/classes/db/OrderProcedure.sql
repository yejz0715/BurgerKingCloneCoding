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