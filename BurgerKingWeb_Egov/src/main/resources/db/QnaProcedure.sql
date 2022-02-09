create or replace procedure listQna(
    p_id in QNA.ID%type,
    p_rc out SYS_REFCURSOR    
)
is
begin
    open p_rc for
      select * FROM QNA where id=p_id order by qseq desc;
end;