insert into test_profile (id) values (1) on conflict do nothing;

insert into request (id, test_profile_id, url, body, "method", perc)
values (1, 1, 'ya.ru', '', 0, null) on conflict do nothing;

insert into request_headers (id, request_id, "name", "value")
values (1, 1, 'header1', 'header_value1') on conflict do nothing;

insert into request_params (id, request_id, "name", "value")
values (1, 1, 'param1', 'param_value1') on conflict do nothing;

insert into test_profile (id) values (2) on conflict do nothing;

insert into request (id,test_profile_id, url, body, "method", perc)
values (2, 2, 'ya.ru', '', 0, null) on conflict do nothing;

insert into request_headers (id, request_id, "name", "value")
values (2, 2, 'header2', 'header_value2') on conflict do nothing;

insert into request_params (id, request_id, "name", "value")
values (2, 2, 'param2', 'param_value2') on conflict do nothing;
