-- ============================
--       CATEGORIES
-- ============================
insert into category (name) values
('plantes d''intérieur'),
('succulentes'),
('plantes fleuries'),
('aromatiques'),
('tropicales');

-- ============================
--  BOTANICAL INFO (20)
-- ============================
insert into product_botanical_info (difficulty, family, lifespan, origin, toxicity) values
('facile','araceae','5-10 ans','amérique centrale','non'),
('moyenne','moraceae','5-15 ans','afrique','non'),
('facile','asphodelaceae','10-20 ans','afrique','non'),
('facile','crassulaceae','5-10 ans','mexique','non'),
('moyenne','orchidaceae','3-7 ans','asie','non'),
('facile','lamiaceae','1-2 ans','méditerranée','non'),
('moyenne','rutaceae','5-10 ans','asie','oui'),
('moyenne','araliaceae','5-15 ans','asie','non'),
('difficile','piperaceae','5 ans','brésil','non'),
('facile','arecaceae','5-15 ans','asie','non'),
('facile','araceae','5-10 ans','colombie','non'),
('moyenne','araceae','8-12 ans','pérou','non'),
('facile','crassulaceae','3-5 ans','afrique','non'),
('moyenne','cactaceae','20-50 ans','pérou','non'),
('facile','pteridaceae','3-5 ans','asie','non'),
('difficile','araceae','10 ans','thailande','oui'),
('facile','araceae','5-10 ans','costa rica','non'),
('moyenne','asparagaceae','8-15 ans','afrique','non'),
('moyenne','araceae','7-12 ans','brésil','oui'),
('facile','crassulaceae','5-10 ans','mexique','non');

-- ============================
--       CARE INFO (20)
-- ============================
insert into product_care_info (fertilizer, soil_type, sunlight, watering) values
('engrais vert','terreau universel','lumière indirecte','modéré'),
('engrais liquide','terreau drainant','lumineux','modéré'),
('aucun','substrat très drainant','soleil','faible'),
('faible engrais','gravier','soleil','faible'),
('engrais orchidée','écorce','lumière indirecte','modéré'),
('engrais léger','terre légère','mi-ombre','modéré'),
('engrais agrumes','terreau riche','plein soleil','modéré'),
('engrais universel','terreau standard','lumière indirecte','modéré'),
('engrais faible','mélange tropical','mi-ombre','modéré'),
('engrais palmier','terreau riche','lumière indirecte','modéré'),
('engrais faible','terreau mixte','mi-ombre','modéré'),
('engrais tropical','terreau humide','lumière indirecte','modéré'),
('aucun','substrat minéral','soleil','faible'),
('engrais cactus','substrat cactus','plein soleil','faible'),
('engrais faible','terreau humide','ombre','modéré'),
('engrais tropical','terreau aéré','lumière indirecte','faible'),
('engrais faible','terreau universel','mi-ombre','modéré'),
('engrais liquide','terreau riche','lumière indirecte','modéré'),
('engrais tropical','terre humide','mi-ombre','modéré'),
('aucun','terreau drainant','soleil','faible');

-- ============================
--       PRODUCTS (20)
-- ============================
INSERT INTO product (
    name, scientific_name, description, long_description, image_url, expert_advice,
    price, rating, stock_quantity, created_at, updated_at,
    botanical_info_id, care_info_id, category_id,
    active, discount
) VALUES
('Monstera Deliciosa','Monstera deliciosa','Plante tropicale à larges feuilles perforées.','Idéale pour les intérieurs lumineux.','img/monstera.jpg','Éviter le soleil direct.',39.90,4.8,20,now(),now(),1,1,5,true,0.0),
('Ficus Lyrata','Ficus lyrata','Plante d’intérieur très populaire.','Demande une lumière vive indirecte.','img/ficus.jpg','Attention au sur-arrosage.',49.90,4.6,12,now(),now(),2,2,1,true,0.0),
('Aloe Vera','Aloe vera','Succulente médicinale résistante.','Très facile à entretenir.','img/aloe.jpg','Arroser rarement.',12.90,4.9,30,now(),now(),3,3,2,true,0.0),
('Echeveria Elegans','Echeveria elegans','Petite succulente décorative.','Parfaite pour débutants.','img/echeveria.jpg','Soleil direct OK.',7.90,4.5,50,now(),now(),4,4,2,true,0.0),
('Orchidée Phalaenopsis','Phalaenopsis spp.','Orchidée élégante.','Lumière indirecte indispensable.','img/orchidee.jpg','Ne pas rempoter trop souvent.',24.90,4.2,18,now(),now(),5,5,3,true,0.0),
('Basilic','Ocimum basilicum','Aromatique incontournable.','Idéal pour la cuisine.','img/basilic.jpg','Pincer les têtes régulièrement.',5.90,4.7,40,now(),now(),6,6,4,true,0.0),
('Calamondin','Citrus mitis','Petit agrume décoratif.','Produit de petits fruits.','img/calamondin.jpg','Aime le soleil.',29.90,4.4,15,now(),now(),7,7,3,true,0.0),
('Schefflera','Schefflera arboricola','Plante robuste.','Supporte la mi-ombre.','img/schefflera.jpg','Arroser modérément.',22.90,4.1,25,now(),now(),8,8,1,true,0.0),
('Peperomia','Peperomia obtusifolia','Petite plante facile.','Peu exigeante.','img/peperomia.jpg','Éviter excès d’eau.',12.90,4.7,30,now(),now(),9,9,1,true,0.0),
('Palmier Areca','Dypsis lutescens','Palmier intérieur.','Purifie l’air.','img/areca.jpg','Aime l’humidité.',34.90,4.6,20,now(),now(),10,10,5,true,0.0),
('Philodendron Birkin','Philodendron birkin','Plante graphique.','Feuilles striées.','img/birkin.jpg','Éviter soleil direct.',28.90,4.8,22,now(),now(),11,11,5,true,0.0),
('Alocasia Polly','Alocasia amazonica','Plante tropicale.','Feuilles nervurées.','img/polly.jpg','Maintenir humidité élevée.',32.90,4.4,10,now(),now(),12,12,5,true,0.0),
('Kalanchoe','Kalanchoe blossfeldiana','Succulente fleurie.','Floraison durable.','img/kalanchoe.jpg','Arrosage modéré.',8.90,4.5,40,now(),now(),13,13,3,true,0.0),
('Cactus Peruvianus','Cereus peruvianus','Cactus haut.','Très résistant.','img/cactus.jpg','Peu d’eau.',15.90,4.6,25,now(),now(),14,14,2,true,0.0),
('Fougère de Boston','Nephrolepis exaltata','Fougère décorative.','Aime l’humidité.','img/fougere.jpg','Pulvériser souvent.',19.90,4.3,20,now(),now(),15,15,1,true,0.0),
('Alocasia Frydek','Alocasia micholitziana','Tropicale rare.','Feuilles veloutées.','img/frydek.jpg','Exigeante.',44.90,4.7,10,now(),now(),16,16,5,true,0.0),
('Epipremnum Aureum','Epipremnum aureum','Plante très facile.','Purifie l’air.','img/pothos.jpg','Idéal débutants.',14.90,4.9,35,now(),now(),17,17,1,true,0.0),
('Sansevieria','Sansevieria trifasciata','Plante increvable.','Supporte oubli arrosage.','img/sansevieria.jpg','Peu d’entretien.',19.90,4.7,30,now(),now(),18,18,2,true,0.0),
('Calathea Orbifolia','Calathea orbifolia','Feuilles larges décoratives.','Demande humidité.','img/calathea.jpg','Pas de soleil direct.',27.90,4.4,15,now(),now(),19,19,5,true,0.0),
('Sedum Morganianum','Sedum morganianum','Succulente tombante.','Très décorative.','img/sedum.jpg','Très peu d’eau.',11.90,4.8,50,now(),now(),20,20,2,true,0.0);


-- ============================
--    SIZE OPTIONS (4 types x produits)
-- ============================
insert into product_size_options (product_id, size_option) values
(1,'bouture'),(1,'petit'),(1,'moyen'),(1,'grand'),
(2,'petit'),(2,'moyen'),
(3,'petit'),(3,'moyen'),
(4,'petit'),
(5,'moyen'),(5,'grand'),
(6,'petit'),
(7,'moyen'),
(8,'petit'),
(9,'petit'),
(10,'moyen'),(10,'grand'),
(11,'moyen'),
(12,'moyen'),
(13,'petit'),
(14,'petit'),
(15,'moyen'),
(16,'moyen'),
(17,'petit'),
(18,'petit'),
(19,'moyen'),
(20,'petit');

-- ============================
--        CUSTOMERS (3)
-- ============================
insert into customer (email, first_name, last_name) values
('alice@example.com','Alice','Martin'),
('jean@example.com','Jean','Durand'),
('sophie@example.com','Sophie','Lambert');

-- ============================
--         ADDRESSES (3)
-- ============================
insert into address (city, country, street, zip_code, customer_id) values
('Paris','France','12 rue des Fleurs','75003',1),
('Lyon','France','8 avenue du Parc','69006',2),
('Marseille','France','45 boulevard Vert','13008',3);

-- ============================
--            CARTS (3)
-- ============================
insert into cart (customer_id) values
(1),(2),(3);

-- ============================
--       CART ITEMS
-- ============================
insert into cart_item (quantity, cart_id, product_id) values
(2,1,1),
(1,1,3),
(3,2,5),
(1,3,10);

-- ============================
--         FAVORITES
-- ============================
insert into favorite (customer_id, product_id) values
(1,1),
(1,5),
(2,3);

-- ============================
--           REVIEWS
-- ============================
insert into review (comment, rating, review_date, customer_id, product_id) values
('Magnifique plante, très bien emballée.',5,now(),1,1),
('Belle qualité, mais un peu fragile.',4,now(),3,5),
('Parfait pour débuter, ravie de mon aloe.',5,now(),2,3);

-- ============================
--          ORDERS (3)
-- ============================
insert into orders (order_date, billing_address_id, shipping_address_id, customer_id) values
(now(),1,1,1),
(now(),2,2,2),
(now(),3,3,3);

-- ============================
--        ORDER ITEMS
-- ============================
insert into order_item (quantity, order_id, product_id) values
(1,1,1),
(2,1,3),

(1,2,5),
(3,2,4),

(2,3,10);

-- ============================
--          PAYMENTS
-- ============================
insert into payment (amount, method, payment_date, order_id) values
(69.70,'carte',now(),1),
(48.60,'carte',now(),2),
(69.80,'paypal',now(),3);
