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


create table product
(
    id    SERIAL,
    name  varchar unique not null,
    price double precision check ( price > 0 )
) insert into  product(name, price) values ('Молоко', 10);
insert into product(name, price)
values ('Минеральная вода', 5);
insert into product(name, price)
values ('Клубника', 30);
insert into product(name, price)
values ('Шоколад', 20);
insert into product(name, price)
values ('Колбаса', 40);


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
VALUES (1, 12, '2023-08-17'),
       (2, 2, '2022-11-15'),
       (3, 2, '2023-03-30'),
       (4, 10, '2022-10-14'),
       (7, 11, '2023-09-17'),
       (8, 5, '2022-10-23'),
       (9, 11, '2023-03-02'),
       (10, 12, '2023-02-21'),
       (11, 9, '2022-10-27'),
       (12, 12, '2023-02-25'),
       (13, 8, '2022-10-29'),
       (14, 6, '2023-08-07'),
       (15, 10, '2023-08-30'),
       (16, 5, '2023-08-22'),
       (17, 3, '2023-03-21'),
       (19, 9, '2023-09-01'),
       (20, 2, '2023-06-08'),
       (5, 6, '2023-01-03'),
       (6, 6, '2023-03-04'),
       (18, 6, '2023-09-20');