CREATE TABLE chicken_lineage (
	id INT NOT NULL AUTO_INCREMENT,
	lineage VARCHAR(15) NOT NULL,
    chicken_color VARCHAR(15) NOT NULL,
    provider VARCHAR(15) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE shed_manufacturer (
	id INT NOT NULL AUTO_INCREMENT,
	manufacturer VARCHAR(15) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE shed (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(15) NOT NULL,
  type VARCHAR(15) NOT NULL,
  capacity INT NOT NULL,
  model VARCHAR(15) NOT NULL,
  shed_manufacturer_id  INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (shed_manufacturer_id) REFERENCES shed_manufacturer(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


CREATE TABLE chicken_lot (
  id INT NOT NULL AUTO_INCREMENT,
  birth_date DATE NOT NULL,
  accommodation_date DATE NOT NULL,
  initial_quantity INT NOT NULL,
  current_quantity INT NOT NULL,
  debicking VARCHAR(15) NOT NULL,
  chicken_lineage_id  INT NOT NULL,
  shed_id  INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (chicken_lineage_id) REFERENCES chicken_lineage(id),
  FOREIGN KEY (shed_id) REFERENCES shed(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


CREATE TABLE creation_monitoring (
  id INT NOT NULL AUTO_INCREMENT,
  age_week INT NOT NULL,
  age_day INT NOT NULL,
  date_week DATE NOT NULL,
  body_weight INT NOT NULL,
  food INT NOT NULL,
  water INT NOT NULL,
  discard INT NOT NULL,
  mortality INT NOT NULL,
  chicken_lot_id  INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (chicken_lot_id) REFERENCES chicken_lot(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


CREATE TABLE production_monitoring (
  id INT NOT NULL AUTO_INCREMENT,
  age_week INT NOT NULL,
  age_day INT NOT NULL,
  date_week DATE NOT NULL,
  body_weight INT NOT NULL,
  food INT NOT NULL,
  water INT NOT NULL,
  discard INT NOT NULL,
  mortality INT NOT NULL,
  total_production INT NOT NULL,
  first_eggs INT NOT NULL,
  second_eggs INT NOT NULL,
  egg_weight INT NOT NULL,
  chicken_lot_id  INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (chicken_lot_id) REFERENCES chicken_lot(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO shed_manufacturer (manufacturer) values ('Artabas');
INSERT INTO shed_manufacturer (manufacturer) values ('Kilbra');
INSERT INTO shed_manufacturer (manufacturer) values ('Plasson');
INSERT INTO shed_manufacturer (manufacturer) values ('Próprio');

INSERT INTO chicken_lineage (lineage, chicken_color, provider) values ('Nick-Chick', 'Branca', 'H e N');
INSERT INTO chicken_lineage (lineage, chicken_color, provider) values ('Nick-Brown', 'Vermelha', 'H e N');
INSERT INTO chicken_lineage (lineage, chicken_color, provider) values ('W-80',  'Branca', 'Hy-Line');
INSERT INTO chicken_lineage (lineage, chicken_color, provider) values ('Hy-Line Brown',  'Vermelha', 'Hy-Line');

INSERT INTO shed (name, type, capacity, model, shed_manufacturer_id ) values ('A1', 'Automatizado', 10080, 'P45-10', 1);
INSERT INTO shed (name, type, capacity, model, shed_manufacturer_id ) values ('A2', 'Automatizado', 10080, 'P45-10', 1);
INSERT INTO shed (name, type, capacity, model, shed_manufacturer_id ) values ('C1', 'Convencional', 3072, 'Madeira', 4);
INSERT INTO shed (name, type, capacity, model, shed_manufacturer_id ) values ('C2', 'Convencional', 3072, 'Madeira', 4);





