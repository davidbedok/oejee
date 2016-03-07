CREATE TABLE productcategory (
	productcategory_id INTEGER NOT NULL,
	productcategory_name CHARACTER VARYING(100) NOT NULL,
	CONSTRAINT PK_PRODUCTCATEGORY_ID PRIMARY KEY (productcategory_id)
);

ALTER TABLE productcategory OWNER TO postgres;

CREATE TABLE product (
	product_id SERIAL NOT NULL,
	product_name CHARACTER VARYING(100) NOT NULL,
	product_productcategory_id INTEGER NOT NULL,
	product_price REAL NOT NULL,
	CONSTRAINT PK_PRODUCT_ID PRIMARY KEY (product_id),
	CONSTRAINT FK_PRODUCT_PRODUCTCATEGORY FOREIGN KEY (product_productcategory_id)
		REFERENCES productcategory (productcategory_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE product OWNER TO postgres;

CREATE UNIQUE INDEX UI_BOOK_ISBN ON product USING btree (product_name);

CREATE TABLE transaction (
	transaction_id SERIAL NOT NULL,
	transaction_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	transaction_comment CHARACTER VARYING(200),
	CONSTRAINT PK_TRANSACTION_ID PRIMARY KEY (transaction_id)
);

ALTER TABLE transaction OWNER TO postgres;

CREATE TABLE item (
	item_id SERIAL NOT NULL,
	item_transaction_id INTEGER NOT NULL,
	item_product_id INTEGER NOT NULL,
	item_quantity INTEGER NOT NULL,
	CONSTRAINT PK_ITEM_ID PRIMARY KEY (item_id),
	CONSTRAINT FK_ITEM_TRANSACTION FOREIGN KEY (item_transaction_id)
		REFERENCES transaction (transaction_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT FK_ITEM_PRODUCT FOREIGN KEY (item_product_id)
		REFERENCES product (product_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE item OWNER TO postgres;
