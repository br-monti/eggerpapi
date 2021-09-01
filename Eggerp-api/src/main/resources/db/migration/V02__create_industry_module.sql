CREATE TABLE egg_lot (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(15) NOT NULL,
    box_color VARCHAR(15),
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO egg_lot (name, box_color) values ('L1', 'Laranja');
INSERT INTO egg_lot (name, box_color) values ('L2', 'Verde');
INSERT INTO egg_lot (name, box_color) values ('L3',  'Vermelha');

ALTER TABLE chicken_lot ADD egg_lot_id INT;
ALTER TABLE chicken_lot ADD FOREIGN KEY (egg_lot_id) REFERENCES egg_lot(id);

INSERT INTO chicken_lot (birth_date, accommodation_date, initial_quantity, current_quantity, debicking, chicken_lineage_id, shed_id, egg_lot_id) 
values ('2020-05-23', '2020-05-23', 10000, 10000, 'Convencional', 1, 1, 1);
INSERT INTO chicken_lot (birth_date, accommodation_date, initial_quantity, current_quantity, debicking, chicken_lineage_id, shed_id, egg_lot_id) 
values ('2020-05-18', '2020-05-18', 10000, 10000, 'Convencional', 1, 2, 1);
INSERT INTO chicken_lot (birth_date, accommodation_date, initial_quantity, current_quantity, debicking, chicken_lineage_id, shed_id, egg_lot_id) 
values ('2020-03-24', '2020-03-25', 3000, 3000, 'Convencional', 1, 3, 2);
INSERT INTO chicken_lot (birth_date, accommodation_date, initial_quantity, current_quantity, debicking, chicken_lineage_id, shed_id, egg_lot_id) 
values ('2020-03-26', '2020-03-27', 3000, 3000, 'Convencional', 1, 4, 3);

CREATE TABLE egg_base (
	id INT NOT NULL AUTO_INCREMENT,
	quantity INT NOT NULL,
	production_date DATE NOT NULL,
	validity_date DATE NOT NULL,
	category_A INT,
	category_B INT,
	discard INT,
	industry_status VARCHAR(15),
    egg_lot_id INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (egg_lot_id) REFERENCES egg_lot(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

-- INSERT INTO egg_base (quantity, production_date, validity_date, category_A, category_B, discard, industry_status, egg_lot_id) 
-- values (15200, '2021-02-17', '2021-03-17', 15000, 150, 50, 'EggBase', 1);
-- INSERT INTO egg_base (quantity, production_date, validity_date, category_A, category_B, discard, industry_status, egg_lot_id) 
-- values (10000, '2021-02-17', '2021-03-17', 9800, 100, 100, 'EggBase', 2);
-- INSERT INTO egg_base (quantity, production_date, validity_date, category_A, category_B, discard, industry_status, egg_lot_id) 
-- values (12000, '2021-02-17', '2021-03-17', 11950, 30, 20, 'EggBase', 3);

CREATE TABLE egg_type (
	id INT NOT NULL AUTO_INCREMENT,
	type VARCHAR(15) NOT NULL,
	category VARCHAR(2),
    min_weight INT,
    max_weight INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO egg_type (type, category, min_weight, max_weight) 
values ('Jumbo', 'A', 66, 70);
INSERT INTO egg_type (type, category, min_weight, max_weight) 
values ('Extra', 'A', 60, 65);
INSERT INTO egg_type (type, category, min_weight, max_weight) 
values ('Grande', 'A', 55, 59);
INSERT INTO egg_type (type, category, min_weight, max_weight) 
values ('Médio', 'A', 50, 54);
INSERT INTO egg_type (type, category, min_weight, max_weight) 
values ('Pequeno', 'A', 45, 49);

CREATE TABLE classification (
	id INT NOT NULL AUTO_INCREMENT,
	quantity INT,
	egg_base_id INT NOT NULL,
	egg_type_id INT NOT NULL,
	PRIMARY KEY (id),	
	FOREIGN KEY (egg_base_id) REFERENCES egg_base(id),
	FOREIGN KEY (egg_type_id) REFERENCES egg_type(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

-- INSERT INTO classification (quantity, egg_base_id, egg_type_id) values (200, 1, 1);
-- INSERT INTO classification (quantity, egg_base_id, egg_type_id) values (2000, 1, 2);
-- INSERT INTO classification (quantity, egg_base_id, egg_type_id) values (6000, 1, 3);
-- INSERT INTO classification (quantity, egg_base_id, egg_type_id) values (6000, 1, 4);
-- INSERT INTO classification (quantity, egg_base_id, egg_type_id) values (800, 1, 5);

CREATE TABLE packing (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(45) NOT NULL,
	packing_type VARCHAR(15) NOT NULL,
    quantity_by_packing INT NOT NULL,
    packing_by_box INT NOT NULL,
    quantity_by_box INT NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO packing (name, packing_type, quantity_by_packing, packing_by_box, quantity_by_box) 
values ('Cartela 20 unidades', 'Cartela', 20, 12, 240);
INSERT INTO packing (name, packing_type, quantity_by_packing, packing_by_box, quantity_by_box) 
values ('Cartela 30 unidades', 'Cartela', 30, 12, 360);
INSERT INTO packing (name, packing_type, quantity_by_packing, packing_by_box, quantity_by_box) 
values ('Filme 12 unidades', 'Filme', 12, 20, 240);
INSERT INTO packing (name, packing_type, quantity_by_packing, packing_by_box, quantity_by_box) 
values ('Filme 20 unidades', 'Filme', 20, 12, 240);
INSERT INTO packing (name, packing_type, quantity_by_packing, packing_by_box, quantity_by_box) 
values ('Filme 30 unidades', 'Filme', 30, 10, 300);
INSERT INTO packing (name, packing_type, quantity_by_packing, packing_by_box, quantity_by_box) 
values ('Estojo Polpa 6 unidades', 'Estojo Polpa', 6, 60, 360);
INSERT INTO packing (name, packing_type, quantity_by_packing, packing_by_box, quantity_by_box) 
values ('Estojo Polpa 12 unidades', 'Estojo Polpa	', 12, 30, 360);

CREATE TABLE product (
	id INT NOT NULL AUTO_INCREMENT,
	quantity INT,
	classification_id INT NOT NULL,
    packing_id INT NOT NULL,   	
	PRIMARY KEY (id),
	FOREIGN KEY (classification_id) REFERENCES classification(id),
	FOREIGN KEY (packing_id) REFERENCES packing(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

-- INSERT INTO product (quantity, classification_id, packing_id) values (100, 1, 1);
-- 
-- INSERT INTO product (quantity, classification_id, packing_id) values (200, 2, 2);
-- 
-- INSERT INTO product (quantity, classification_id, packing_id) values (300, 3, 3);
-- INSERT INTO product (quantity, classification_id, packing_id) values (400, 3, 4);
-- INSERT INTO product (quantity, classification_id, packing_id) values (500, 3, 5);
-- INSERT INTO product (quantity, classification_id, packing_id) values (600, 3, 6);
-- INSERT INTO product (quantity, classification_id, packing_id) values (700, 3, 7);
-- 
-- INSERT INTO product (quantity, classification_id, packing_id) values (50, 4, 2);
-- 
-- INSERT INTO product (quantity, classification_id, packing_id) values (200, 5, 2);



-- 
-- CREATE TABLE expedition (
-- 	id INT NOT NULL AUTO_INCREMENT,
-- 	date DATE NOT NULL,
-- 	quantity INT NOT NULL,
-- 	ret INT NOT NULL,
-- 	PRIMARY KEY (id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
-- 
-- 
-- CREATE TABLE line (
-- 	id INT NOT NULL AUTO_INCREMENT,
-- 	name VARCHAR(15) NOT NULL,
--     expedition_id INT NOT NULL,
-- 	PRIMARY KEY (id),
-- 	FOREIGN KEY (expedition_id) REFERENCES expedition(id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
-- 
-- CREATE TABLE city (
-- 	id INT NOT NULL AUTO_INCREMENT,
-- 	city VARCHAR(45) NOT NULL,
--     state VARCHAR(15) NOT NULL,
--     line_id INT NOT NULL,
-- 	PRIMARY KEY (id),
-- 	FOREIGN KEY (line_id) REFERENCES line(id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
-- 


