create or replace function f_t_accounts_insert()
returns trigger
language  plpgsql as
$func$
begin
	insert into accounts (id,user_id,gold_amount) values (nextval('main_sequence'),new.id, 0);
	return null;
end
$func$;