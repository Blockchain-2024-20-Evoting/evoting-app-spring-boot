create table if not exists elections
(
    id
    bigint
    auto_increment
    primary
    key,
    end_date
    date
    not
    null,
    name
    varchar
(
    50
) not null,
    start_date date not null,
    state enum
(
    'ACTIVE',
    'INACTIVE',
    'PENDING'
) null,
    constraint UKtl302qogqgywa0i4cykb88mim
    unique
(
    name
)
    );

create table if not exists parties
(
    id
    bigint
    auto_increment
    primary
    key,
    description
    varchar
(
    500
) not null,
    img varchar
(
    255
) not null,
    name varchar
(
    50
) not null
    );

create table if not exists candidates
(
    id
    bigint
    auto_increment
    primary
    key,
    first_name
    varchar
(
    50
) not null,
    image varchar
(
    255
) not null,
    last_name varchar
(
    50
) not null,
    election_id bigint not null,
    party_id bigint not null,
    constraint UKdccip4dqg4r5g591sp06884wc
    unique
(
    party_id
),
    constraint FKegefwnwjhhircbicuvk8fjdhp
    foreign key
(
    party_id
) references parties
(
    id
),
    constraint FKmdu3yssghrl36wth97l9b9a9d
    foreign key
(
    election_id
) references elections
(
    id
)
    );

create table if not exists roles
(
    id
    bigint
    auto_increment
    primary
    key,
    name
    enum
(
    'ADMIN',
    'STUDENT'
) not null,
    constraint UKofx66keruapi6vyqpv6f2or37
    unique
(
    name
)
    );

create table if not exists transactions
(
    id
    bigint
    auto_increment
    primary
    key,
    block_hash
    varchar
(
    66
) not null,
    block_number bigint not null,
    created_at datetime
(
    6
) not null,
    from_account varchar
(
    42
) not null,
    gas_used bigint not null,
    status varchar
(
    255
) not null,
    to_account varchar
(
    42
) not null,
    transaction_hash varchar
(
    66
) not null,
    transaction_status enum
(
    'FAILED',
    'SUCCESS'
) null,
    constraint UK1yf7q7v3d1u7xyb1aevyw69dn
    unique
(
    block_number,
    block_hash
),
    constraint UKanexpx8d9qc08smxuvlstutgy
    unique
(
    block_hash
),
    constraint UKcf04ugllmah959hrjlq2hsgnx
    unique
(
    transaction_hash
)
    );

create table if not exists users
(
    id
    bigint
    auto_increment
    primary
    key,
    email
    varchar
(
    30
) not null,
    password varchar
(
    255
) not null,
    role_id bigint null,
    constraint UK6dotkott2kjsp8vw4d0m25fb7
    unique
(
    email
),
    constraint FKp56c1712k691lhsyewcssf40f
    foreign key
(
    role_id
) references roles
(
    id
)
    );

create table if not exists students
(
    id
    bigint
    auto_increment
    primary
    key,
    created_at
    datetime
(
    6
) null,
    first_name varchar
(
    255
) not null,
    last_name varchar
(
    255
) not null,
    update_at datetime
(
    6
) null,
    user_id bigint null,
    constraint UKg4fwvutq09fjdlb4bb0byp7t
    unique
(
    user_id
),
    constraint FKdt1cjx5ve5bdabmuuf3ibrwaq
    foreign key
(
    user_id
) references users
(
    id
)
    );

create table if not exists votes
(
    id
    bigint
    auto_increment
    primary
    key,
    created_at
    datetime
(
    6
) not null,
    student_id bigint not null,
    transaction_id bigint not null,
    constraint UKivq94uba5292n8ta32hqtsosm
    unique
(
    transaction_id
),
    constraint FKb2g754kj5squbgu158wosd8a5
    foreign key
(
    transaction_id
) references transactions
(
    id
),
    constraint FKedp02ocau0clg1eoax2xbmd4d
    foreign key
(
    student_id
) references students
(
    id
)
    );


