create table request_headers (
    id bigserial,
    request_id bigint references request (id),
    name varchar,
    value varchar,
    primary key (id)
)