CREATE TABLE `order` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_id` int(11) NOT NULL ,
  `Create_date` datetime,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `order_item` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Count` int(11) NOT NULL,
  `Product_id` int(11) NOT NULL ,
  `Order_id` int(11) NOT NULL ,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
