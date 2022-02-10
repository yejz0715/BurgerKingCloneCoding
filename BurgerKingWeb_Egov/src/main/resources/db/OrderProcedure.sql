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