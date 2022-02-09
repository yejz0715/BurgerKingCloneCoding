-- qnaList 조회
create or replace procedure b_listQna(
    p_id in QNA.ID%type,
    p_rc out SYS_REFCURSOR    
)
is
begin
    open p_rc for
      select * FROM QNA where id=p_id order by qseq desc;
end;


-- qna작성
create or replace PROCEDURE b_insertQna
(   p_id IN QNA.ID%TYPE,
    p_subject IN QNA.subject%TYPE, 
    p_content IN QNA.content%TYPE,
    p_pass IN QNA.pass%TYPE
    
)
IS

BEGIN
    insert into QNA( qseq, id, subject, content, pass)
    values( qseq.nextVal, p_id, p_subject, p_content, p_pass);
    commit;
END;