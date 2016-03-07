SELECT
	product_name,
	product_price,
	productcategory_name,
	item_quantity,
	( product_price * item_quantity) AS total_price   
FROM item
	INNER JOIN product ON ( product_id = item_product_id )
	INNER JOIN productcategory ON ( product_productcategory_id = productcategory_id )
WHERE ( 1 = 1 )
	AND ( item_transaction_id = 17 );