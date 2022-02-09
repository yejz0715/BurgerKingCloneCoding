create or replace procedure b_getOrderList(
	p_id in member.id%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from order_view where id=p_id and result in ('1', '2', '3');
end;