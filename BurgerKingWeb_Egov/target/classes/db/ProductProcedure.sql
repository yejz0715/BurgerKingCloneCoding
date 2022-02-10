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