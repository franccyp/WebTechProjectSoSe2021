create table if not exists shopping_list
(
    id
    bigserial
    not
    null
    constraint
    shopping_list_pk
    primary
    key,
    list_name
    varchar
(
    255
) not null,
    author varchar
(
    255
) not null
    );

--alter table shopping_list owner to liedzsmlzxucla;