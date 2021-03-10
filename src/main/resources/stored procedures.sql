SELECT * FROM productdb_bt.product;

DELIMITER //
CREATE PROCEDURE GetAllProducts()
BEGIN
	SELECT * FROM product;
END //

DELIMITER //
CREATE PROCEDURE GetAllproductsByPrice(IN price_in decimal)
BEGIN
	SELECT * FROM product where price>price_in;
END //

DELIMITER //
CREATE PROCEDURE GetAllProductsCountByPrice(IN price_in decimal)
BEGIN
	SELECT count(*) FROM product where price>price_in;
END //
