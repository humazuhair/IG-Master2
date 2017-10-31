CREATE USER 'pizzaiolo'@'localhost' IDENTIFIED BY 'PizzaioloDePizzaHut';

GRANT ALL PRIVILEGES ON `pizzeria`.* TO 'pizzaiolo'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;