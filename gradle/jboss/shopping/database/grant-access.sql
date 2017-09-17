GRANT SELECT, INSERT, UPDATE, DELETE ON 
	productcategory, 
	product, 
	transaction, 
	item 
TO shopping_role;

GRANT USAGE, SELECT, UPDATE ON 
	product_product_id_seq,
	transaction_transaction_id_seq,
	item_item_id_seq 
TO shopping_role;