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