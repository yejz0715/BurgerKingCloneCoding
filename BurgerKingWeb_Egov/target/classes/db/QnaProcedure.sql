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


--qna passcheck
create or replace PROCEDURE b_getpassChk
(   p_qseq in QNA.qseq%type,
    p_rc    OUT SYS_REFCURSOR)
IS

BEGIN
    OPEN p_rc FOR
        select * from Qna where qseq=p_qseq;
END;


-- qna view
create or replace procedure b_getQna(
    p_qseq in QNA.QSEQ%type,
    p_rc out SYS_REFCURSOR    
)
is
begin
    open p_rc for
      select * FROM QNA where qseq=p_qseq;
end;


--  qna 게시글삭제
create or replace procedure b_deleteQna(
    p_qseq in QNA.QSEQ%type  
)
is
begin
    delete from QNA where qseq=p_qseq;
end;



--  qna 댓글업데이트
create or replace procedure b_deleteQna(
    p_qseq in QNA.QSEQ%type,  
    p_reply in QNA.reply%type
)
is
begin
    update qna set reply=p_reply, rep='2' where qseq=p_qseq;
    commit;
end;


--  qna 댓글업데이트
create or replace procedure b_updateQna(
    p_qseq in QNA.QSEQ%type,  
    p_reply in QNA.reply%type
)
is
begin
    update qna set reply=p_reply, rep='2' where qseq=p_qseq;
    commit;
end;