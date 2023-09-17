
create table accounts
(
    id         bigint not null
        constraint accounts_id_pk
            primary key,
    balance decimal,
    overdraft_limit decimal,
    customer_num  varchar,
    type     varchar
);

alter table accounts
    owner to lchit;

create unique index accounts_id_uindex
    on accounts (id);

create sequence "accounts_id_seq";

alter sequence "accounts_id_seq" owned by accounts.id;

alter table accounts
    alter column id set default nextval('accounts_id_seq'::regclass);

insert into accounts (balance, customer_num, type)  values (2000.00,1,'SAVINGS');
insert into accounts (balance, customer_num, type)  values (5000.00,2,'SAVINGS');
insert into accounts (balance,overdraft_limit, customer_num, type)  values (1000.00,10000.00,3,'CURRENT');
insert into accounts (balance,overdraft_limit, customer_num, type)  values (-5000.00,20000.00,4,'CURRENT');



create table account_defaults
(
    id         bigint not null
            primary key,
    min_balance decimal,
    max_balance  decimal,
    type     varchar
);

alter table account_defaults
    owner to lchit;

insert into account_defaults(id, min_balance, max_balance, type) values (1,1000.00,null,'SAVINGS');

insert into account_defaults(id, min_balance, max_balance, type) values (2,-100000.00,null,'CURRENT')



