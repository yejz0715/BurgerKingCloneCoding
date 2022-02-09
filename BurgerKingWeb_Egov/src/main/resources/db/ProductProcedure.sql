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