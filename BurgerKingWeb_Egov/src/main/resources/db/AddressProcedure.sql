-- 특정 유저의 내 주소지를 검색하는 프로시져
create or replace procedure b_getMyAddress(
	p_mseq in member.mseq%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from myaddress where mseq=p_mseq;
end;

-- member의 주소값 저장하는 프로시져
create or replace procedure b_setUserAddress(
	p_mseq in myaddress.mseq%type,
    p_zip_num in myaddress.zip_num%type,
    p_address in myaddress.address%type
)
is

begin
	insert into myaddress(mseq, zip_num, address) 
    values(p_mseq, p_zip_num, p_address);
    commit;
end;

-- 게스트 주소 설정 프로시져
create or replace procedure b_setGuestAddress(
	p_gseq in guest.gseq%type,
    p_zip_num in guest.zip_num%type,
    p_address in guest.address%type
)
is

begin
	update guest set zip_num=p_zip_num, address=p_address where gseq=p_gseq;
    commit;
end;

-- 해당 dong의 주소 검색 프로시져
create or replace procedure b_selectAddressByDong(
	p_dong in address.dong%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from address where dong like '%'||p_dong||'%';
end;