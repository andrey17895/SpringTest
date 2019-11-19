create table request_params (
    id bigserial,
    request_id bigint references request (id),
    name varchar,
    value varchar,
    primary key (id)
)