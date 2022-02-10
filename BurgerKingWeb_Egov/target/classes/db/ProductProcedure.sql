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

-- 상품 추가시 맞는 종류가 선택되었는지 확인하는 프로시저
create or replace PROCEDURE b_selectProduct1(        
    p_kind1 IN product.kind1%TYPE,    
    p_rc OUT SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
        select * from product where kind1 = p_kind1;
end;

-- 상품 추가시 썸네일이 있는 상품인지 확인하는 프로시저
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

-- 상품 추가 프로시저
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
