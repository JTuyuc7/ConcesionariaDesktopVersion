CREATE TABLE `users` (`user_id` INT NOT NULL AUTO_INCREMENT , `user_name` VARCHAR(90) NOT NULL , `last_name` VARCHAR(90) NOT NULL , `email` VARCHAR(90) NOT NULL , `password` VARCHAR(90) NOT NULL , `isSuperAdmin` BOOLEAN NOT NULL DEFAULT FALSE , `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP , PRIMARY KEY (`user_id`)) ENGINE = InnoDB;

INSERT INTO `users` (`user_name`, `last_name`, `email`, `password`, `isSuperAdmin`, `created_at`)
VALUES ('Jaime', 'Tuyuc', 'admin@admin.com', '123456', '1', CURRENT_TIMESTAMP), ('user2', 'lastNmae', 'hola@hola.com', '1234567', '0', CURRENT_TIMESTAMP);

# Productos
CREATE TABLE `producto` (`codigoproducto` INT NOT NULL AUTO_INCREMENT, `nombreproducto` VARCHAR(100) NOT NULL , `cantidadproducto` INT NOT NULL , `preciounitario` FLOAT NOT NULL , `codigo_user_id` INT NOT NULL, `fecha` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP , PRIMARY KEY (`codigoproducto`)) ENGINE = InnoDB COMMENT = 'Creacion de la tabla producto';

ALTER TABLE `producto` ADD CONSTRAINT `FK_producto` FOREIGN KEY (`codigo_user_id`) REFERENCES `users`(`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

INSERT INTO `producto` (`nombreproducto`, `cantidadproducto`, `preciounitario`, `codigo_user_id`, `fecha`)
VALUES ('Tesla', '0', '125000', '1', CURRENT_TIMESTAMP), ('Moto', '0', '125000', '2', CURRENT_TIMESTAMP), ('Patineta', '0', '125000', '1', CURRENT_TIMESTAMP);
