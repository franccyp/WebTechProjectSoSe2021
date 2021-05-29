create table if not exists shopping_list
(
    list_id
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
),
    author varchar
(
    255
)
    );

--alter table shopping_list owner to liedzsmlzxucla;