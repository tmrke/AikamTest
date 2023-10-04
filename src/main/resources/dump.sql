create table customers
(
    id        SERIAL primary key,
    name      varchar not null,
    last_name varchar not null
);

insert into customers(last_name, name)
values ('Петров', 'Петр');
insert into customers(last_name, name)
values ('Сидоров', 'Сидр');
insert into customers(last_name, name)
values ('Андреев', 'Андрей');
insert into customers(last_name, name)
values ('Маринина', 'Марина');
insert into customers(last_name, name)
values ('Ленина', 'Елена');
insert into customers(last_name, name)
values ('Романов', 'Роман');
insert into customers(last_name, name)
values ('Игорев', 'Игорь');
insert into customers(last_name, name)
values ('Федотов', 'Федот');
insert into customers(last_name, name)
values ('Никитин', 'Никита');
insert into customers(last_name, name)
values ('Сергеев', 'Сергей');
insert into customers(last_name, name)
values ('Марьина', 'Мария');
insert into customers(last_name, name)
values ('Светланова', 'Светлана');


create table products
(
    id    SERIAL primary key,
    name  varchar unique not null,
    price double precision check ( price > 0 )
);

insert into products(name, price)
values ('Молоко', 15);
insert into products(name, price)
values ('Минеральная вода', 5);
insert into products(name, price)
values ('хлеб', 10);
insert into products(name, price)
values ('Клубника', 30);
insert into products(name, price)
values ('Шоколад', 20);
insert into products(name, price)
values ('Колбаса', 40);
insert into products(name, price)
values ('мясо', 60);
insert into products(name, price)
values ('икра', 100);
insert into products(name, price)
values ('лапша', 20);
insert into products(name, price)
values ('кефир', 25);



create table orders
(
    id          SERIAL primary key,
    customer_id INT,
    product_id  INT,
    order_date  DATE not null,
    FOREIGN KEY (customer_id) REFERENCES customers (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

INSERT INTO orders (customer_id, product_id, order_date)
VALUES (1, 2, '2023-08-17'),
       (2, 2, '2022-11-15'),
       (3, 2, '2023-03-30'),
       (4, 10, '2022-10-14'),
       (7, 1, '2023-09-17'),
       (8, 5, '2022-10-23'),
       (9, 4, '2023-03-02'),
       (10, 2, '2023-02-21'),
       (11, 9, '2022-10-27'),
       (11, 2, '2023-02-25'),
       (1, 8, '2022-10-29'),
       (4, 6, '2023-08-07'),
       (5, 10, '2023-08-30'),
       (6, 5, '2023-08-22'),
       (7, 3, '2023-03-21'),
       (9, 9, '2023-09-01'),
       (2, 2, '2023-06-08'),
       (5, 6, '2023-01-03'),
       (6, 6, '2023-03-04'),
       (8, 6, '2023-09-20');