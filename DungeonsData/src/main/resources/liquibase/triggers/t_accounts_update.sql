create or replace trigger t_accounts_update
after insert on users
for each row
execute function f_t_accounts_insert();