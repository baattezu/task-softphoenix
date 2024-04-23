CREATE TABLE requests (
      id serial PRIMARY KEY,
      product VARCHAR(255) NOT NULL,
      quantity INT NOT NULL,
      delivery_address VARCHAR(255) NOT NULL,
      phone_number VARCHAR(20) NOT NULL
);
CREATE TABLE news (
      id serial PRIMARY KEY,
      title VARCHAR(255) NOT NULL,
      content TEXT NOT NULL
);