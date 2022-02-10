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