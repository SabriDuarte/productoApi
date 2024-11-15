-- data.sql
-- Insert data into the users table
INSERT IGNORE INTO crud_product(id,reference,nombre, precio, stock, is_active, created_at, updated_at)
Values('1','111','Teclado','1500','5',NOW(),NOW()),('2','222','Mouse','900','3',NOW(),NOW()),('3','333','Monitor','2000','4',NOW(),NOW());
