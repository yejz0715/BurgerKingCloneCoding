create or replace PROCEDURE b_insertMember
(   p_id IN MEMBER.ID%TYPE,
    p_mseq in MEMBER.MSEQ%type,
    p_pwd IN MEMBER.PWD%TYPE, 
    p_phone IN MEMBER.PHONE%TYPE,
    p_name IN MEMBER.NAME%TYPE
)
IS

BEGIN
    insert into member( mseq, id, pwd, phone, name)
    values( member_seq.nextVal, p_id, p_pwd, p_phone, p_name);
    commit;
END;

--

create or replace PROCEDURE b_getMember
(   p_id IN member.id%TYPE,
    p_rc    OUT SYS_REFCURSOR)
IS

BEGIN
    OPEN p_rc FOR
        SELECT * FROM MEMBER WHERE ID=p_id;
END;