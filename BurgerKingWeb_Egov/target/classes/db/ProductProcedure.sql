create or replace procedure b_getProductList(
	p_kind1 in product.kind1%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from product where kind1 = p_kind1;
end;