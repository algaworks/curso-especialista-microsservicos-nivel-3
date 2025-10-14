create table public.credit_card (
    id uuid not null,
    brand varchar(255),
    created_at timestamp with time zone,
    customer_id uuid,
    exp_month integer,
    exp_year integer,
    gateway_code varchar(255),
    last_numbers varchar(255),
    primary key (id)
);

create index idx_credit_card_customer_id on public.credit_card (customer_id);

create table public.payment_settings (
    id uuid not null,
    credit_card_id uuid,
    gateway_code varchar(255),
    "method" varchar(255),
    primary key (id)
);

create index idx_payment_settings_credit_card_id on public.payment_settings (credit_card_id);
alter table public.payment_settings add constraint fk_payment_settings_credit_card_id foreign key (credit_card_id) references public.credit_card(id);

create table public.invoice (
    id uuid not null,
    created_at timestamp with time zone,
    created_by_user_id uuid,
    last_modified_by_user_id uuid,
    last_modified_date timestamp with time zone,
    version bigint not null,
    cancel_reason varchar(255),
    canceled_at timestamp with time zone,
    customer_id uuid,
    expires_at timestamp with time zone,
    issued_at timestamp with time zone,
    order_id varchar(255),
    paid_at timestamp with time zone,
    payer_address_city varchar(255),
    payer_address_complement varchar(255),
    payer_address_neighborhood varchar(255),
    payer_address_number varchar(255),
    payer_address_state varchar(255),
    payer_address_street varchar(255),
    payer_address_zip_code varchar(255),
    payer_document varchar(255),
    payer_email varchar(255),
    payer_full_name varchar(255),
    payer_phone varchar(255),
    status varchar(255),
    total_amount numeric(38,2),
    payment_settings_id uuid,
    primary key (id),
    constraint uk_invoice_payment_settings_id unique (payment_settings_id)
);

create index idx_invoice_customer_id on public.invoice (customer_id);
create index idx_invoice_order_id on public.invoice (order_id);
create unique index idx_invoice_payment_settings_id on public.invoice (payment_settings_id);

alter table public.invoice add constraint fk_invoice_payment_settings_id foreign key (payment_settings_id) references public.payment_settings(id);

create table public.invoice_line_item (
    invoice_id uuid not null,
    items_amount numeric(38,2),
    items_name varchar(255),
    items_number integer,
    primary key (invoice_id, items_number)
);

create index idx_invoice_line_item_invoice_id on public.invoice_line_item (invoice_id);
alter table public.invoice_line_item add constraint fk_invoice_line_item_invoice_id foreign key (invoice_id) references public.invoice(id);