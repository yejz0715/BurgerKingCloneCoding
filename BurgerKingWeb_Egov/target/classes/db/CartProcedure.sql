create or replace procedure b_selectCart(
	p_id in member.id%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from cart_view where id=p_id and result='1';
end;