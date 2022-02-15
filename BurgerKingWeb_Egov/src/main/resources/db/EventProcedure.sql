--eventList
create or replace PROCEDURE b_getAllEvents(        
    p_rc OUT   SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
    SELECT * FROM event order by eseq asc;
end; 
--eventTab2 
create or replace PROCEDURE b_getOngoingEvents(        
    p_rc OUT   SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
    SELECT * FROM event where state = 1 order by eseq asc;
end; 
  
--eventTab3 
create or replace PROCEDURE b_getPastEvents(        
    p_rc OUT   SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
    SELECT * FROM event where state = 0 order by eseq asc;
end; 
--eventDetail
create or replace PROCEDURE b_getDetailEvent(        
    p_eseq IN EVENT.eseq%TYPE,    
    p_rc OUT   SYS_REFCURSOR
)  
IS
BEGIN
    OPEN p_rc FOR
    SELECT * FROM event where eseq=p_eseq;
end; 